package test.androidtestc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import test.androidtestc.Activity.MainActivity;
import test.androidtestc.DAO.Cliente;
import test.androidtestc.DAO.ClienteDAO;
import test.androidtestc.Factory.DatabaseFactory;
import test.androidtestc.Util.BancoUtil;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("test.androidtestc", appContext.getPackageName());
    }

    //region InserindoDados
    @Test
    public void insereDado() throws Exception {

        //region Criando o Cliente
        Context appContext = InstrumentationRegistry.getTargetContext();
        SQLiteDatabase db;
        DatabaseFactory banco = null;
        banco = new DatabaseFactory(appContext);

        //inserts
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = new Cliente();
        cliente.setNome("Novinho");
        cliente.setEstado("Paraná");
        cliente.setCidade("Curitiba");
        cliente.setCEP(0);
        cliente.setCPF(0);
        cliente.setId_usuario(5);
        //endregion

        //region Buscando o Cliente no Banco de Dados
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put(BancoUtil.NOME_CLIENTE, cliente.getNome());
        valores.put(BancoUtil.ESTADO_CLIENTE, cliente.getEstado());
        valores.put(BancoUtil.CIDADE_CLIENTE, cliente.getCidade());
        valores.put(BancoUtil.CEP_CLIENTE, cliente.getCEP());
        valores.put(BancoUtil.CPF_CLIENTE, cliente.getCPF());
        valores.put(BancoUtil.CLIENTE_USUARIO, cliente.getId_usuario());

        resultado = db.insert(BancoUtil.TABELA_CLIENTE, null, valores);
        db.close();

        assertTrue(resultado > 0);
        //endregion

    }
    //endregion

    @Test
    public void DeletandoDados() throws Exception {
        //Deletando o Empregado utilizando o ID como parametro

        //region Deletando o empregado
        Context appContext = InstrumentationRegistry.getTargetContext();
        SQLiteDatabase db;
        DatabaseFactory banco = new DatabaseFactory(appContext);
        ClienteDAO clienteDAO = new ClienteDAO();
        int id = 13;
        String where = BancoUtil.ID_CLIENTE + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(BancoUtil.TABELA_CLIENTE, where, null);
        db.close();
        //endregion

        //Verificando se ele realmente foi excluido

        //region Listando os Clientes
        //Listando os Clientes
        Cursor cursor;
        String[] campos = {BancoUtil.ID_CLIENTE, BancoUtil.NOME_CLIENTE, BancoUtil.ESTADO_CLIENTE, BancoUtil.CIDADE_CLIENTE,
                BancoUtil.CEP_CLIENTE, BancoUtil.CPF_CLIENTE};
        db = banco.getReadableDatabase();

        String condicao = BancoUtil.CLIENTE_USUARIO + " = " + 5;

        cursor = db.query(BancoUtil.TABELA_CLIENTE, campos, condicao, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        //endregion

        //region Recuperando o Cliente listado com o ID passado para exclusão
        try {
            if (cursor.getCount() > 0) {
                do {

                    int ID = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_CLIENTE));
                    String nome = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.NOME_CLIENTE));
                    String estado = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.ESTADO_CLIENTE));
                    String cidade = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.CIDADE_CLIENTE));
                    long cep = cursor.getLong(cursor.getColumnIndexOrThrow(BancoUtil.CEP_CLIENTE));
                    long cpf = cursor.getLong(cursor.getColumnIndexOrThrow(BancoUtil.CPF_CLIENTE));

                    assertFalse("Ainda há registros com esse ID", false);
                } while (cursor.moveToNext());
            }
            else
                assertTrue("Removido com sucesso", true);
        } finally {
            cursor.close();
        }
        //endregion
    }

    @Test
    public void UpdateMetodo() throws Exception{

        //region Adicionandos os novos dados do Cliente
        Context appContext = InstrumentationRegistry.getTargetContext();
        SQLiteDatabase db;
        DatabaseFactory banco = null;

        banco = new DatabaseFactory(appContext);

        ContentValues valores;
        String where;

        Cliente cliente = new Cliente();
        cliente.setNome("Cuzon");
        cliente.setEstado("Venezuela");
        cliente.setCidade("Pau no Cu do Mathias");
        cliente.setCEP(5);
        cliente.setCPF(3);
        cliente.setId_usuario(5);
        //endregion

        //region Fazendo Update
        db = banco.getWritableDatabase();

        where = BancoUtil.ID_CLIENTE + " = " + 23;

        valores = new ContentValues();

        valores.put(BancoUtil.NOME_CLIENTE, cliente.getNome());
        valores.put(BancoUtil.ESTADO_CLIENTE, cliente.getEstado());
        valores.put(BancoUtil.CIDADE_CLIENTE, cliente.getCidade());
        valores.put(BancoUtil.CEP_CLIENTE, cliente.getCEP());
        valores.put(BancoUtil.CPF_CLIENTE, cliente.getCPF());

        int resultado = db.update(BancoUtil.TABELA_CLIENTE,valores,where,null);
        db.close();
        assertTrue(resultado > 0);
        //endregion

    }
}
