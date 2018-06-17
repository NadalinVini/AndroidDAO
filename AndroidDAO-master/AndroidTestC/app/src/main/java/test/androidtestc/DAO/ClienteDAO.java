package test.androidtestc.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import test.androidtestc.Factory.DatabaseFactory;
import test.androidtestc.Util.BancoUtil;

/**
 * Created by User on 05/06/2018.
 *
 * Classe para inserção, remoção, atualização e busca em Banco de Dados de um Cliente.
 *
 */

public class ClienteDAO {
    private SQLiteDatabase db;
    private DatabaseFactory banco;
    public static final int CLIENTES_TOTAL = 1;

    public ClienteDAO(Context context) {
        banco = new DatabaseFactory(context);
    }

    public ClienteDAO() {

    }

    public long insereDado(Cliente cliente) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(BancoUtil.NOME_CLIENTE, cliente.getNome());
        valores.put(BancoUtil.ESTADO_CLIENTE, cliente.getEstado());
        valores.put(BancoUtil.CIDADE_CLIENTE, cliente.getCidade()) ;
        valores.put(BancoUtil.CEP_CLIENTE, cliente.getCEP()) ;
        valores.put(BancoUtil.CPF_CLIENTE, cliente.getCPF()) ;
        valores.put(BancoUtil.CLIENTE_USUARIO,cliente.getId_usuario());

        resultado = db.insert(BancoUtil.TABELA_CLIENTE, null, valores);
        db.close();

        return resultado;

    }

    public Cliente carregaClientePorID(int id){
        Cursor cursor;
        String[] campos = {BancoUtil.ID_CLIENTE, BancoUtil.NOME_CLIENTE,
                           BancoUtil.ESTADO_CLIENTE, BancoUtil.CIDADE_CLIENTE,
                           BancoUtil.CEP_CLIENTE, BancoUtil.CPF_CLIENTE};
        db = banco.getReadableDatabase();


        String where = BancoUtil.ID_CLIENTE + " = " + id;

        cursor = db.query(BancoUtil.TABELA_CLIENTE, campos, where, null, null, null, null, null);

        Cliente cliente = new Cliente();
        if (cursor != null) {
            cursor.moveToFirst();

            int ID = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_CLIENTE));
            String nome = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.NOME_CLIENTE));
            String estado = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.ESTADO_CLIENTE));
            String cidade = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.CIDADE_CLIENTE));
            long cep = cursor.getLong(cursor.getColumnIndexOrThrow(BancoUtil.CEP_CLIENTE));
            long cpf = cursor.getLong(cursor.getColumnIndexOrThrow(BancoUtil.CPF_CLIENTE));

            cliente.setID(ID);
            cliente.setNome(nome);
            cliente.setEstado(estado);
            cliente.setCidade(cidade);
            cliente.setCEP(cep);
            cliente.setCPF(cpf);

        }
        db.close();
        return cliente;
    }

    public Cursor carregaDados(long id_usuario) {
        Cursor cursor;
        String[] campos = {BancoUtil.ID_CLIENTE, BancoUtil.NOME_CLIENTE, BancoUtil.ESTADO_CLIENTE, BancoUtil.CIDADE_CLIENTE,
                BancoUtil.CEP_CLIENTE, BancoUtil.CPF_CLIENTE};
        db = banco.getReadableDatabase();

        String where = BancoUtil.CLIENTE_USUARIO + " = " + id_usuario;

        cursor = db.query(BancoUtil.TABELA_CLIENTE, campos, where, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public List<Cliente> carregaDadosLista(int TIPOS_CIDADES, long id_usuario) {
        Cursor cursor = null;
        if(TIPOS_CIDADES == CLIENTES_TOTAL)
            cursor = carregaDados(id_usuario);

        List<Cliente> clientes = new ArrayList<>();

        try {
            if(cursor.getCount() > 0) {
                do {
                    Cliente cliente = new Cliente();
                    int ID = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_CLIENTE));
                    String nome = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.NOME_CLIENTE));
                    String estado = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.ESTADO_CLIENTE));
                    String cidade = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.CIDADE_CLIENTE));
                    long cep = cursor.getLong(cursor.getColumnIndexOrThrow(BancoUtil.CEP_CLIENTE));
                    long cpf = cursor.getLong(cursor.getColumnIndexOrThrow(BancoUtil.CPF_CLIENTE));

                    cliente.setID(ID);
                    cliente.setNome(nome);
                    cliente.setEstado(estado);
                    cliente.setCidade(cidade);
                    cliente.setCEP(cep);
                    cliente.setCPF(cpf);

                    clientes.add(cliente);
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        return clientes;
    }

    public void deletaRegistro(int id){
        String where = BancoUtil.ID_CLIENTE + "=" + id;
        db = banco.getReadableDatabase();

        db.delete(BancoUtil.TABELA_CLIENTE,where,null);
        db.close();
    }

    public boolean atualizaCliente(Cliente cliente){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = BancoUtil.ID_CLIENTE + " = " + cliente.getID();

        valores = new ContentValues();
        valores.put(BancoUtil.NOME_CLIENTE, cliente.getNome());
        valores.put(BancoUtil.ESTADO_CLIENTE, cliente.getEstado());
        valores.put(BancoUtil.CIDADE_CLIENTE, cliente.getCidade());
        valores.put(BancoUtil.CEP_CLIENTE, cliente.getCEP());
        valores.put(BancoUtil.CPF_CLIENTE, cliente.getCPF());

        int resultado = db.update(BancoUtil.TABELA_CLIENTE,valores,where,null);
        db.close();
        if(resultado > 0)
            return true;
        else
            return false;
    }
}