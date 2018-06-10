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
 * Classe para inserção, remoção, atualização e busca em Banco de Dados de um Nota.
 *
 */

public class NotaDAO {
    private SQLiteDatabase db;
    private DatabaseFactory banco;

    public static final int NOTAS_TOTAL = 1;

    public NotaDAO(Context context) {
        banco = new DatabaseFactory(context);
    }

    public long insereDado(Nota nota) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(BancoUtil.TIPO_PAGAMENTO, nota.getTipo_Pagamento());
        valores.put(BancoUtil.CLIENTE_NOTA,nota.getIdCliente());

        resultado = db.insert(BancoUtil.TABELA_NOTA, null, valores);
        db.close();

        return resultado;

    }

    public Nota carregaNotaPorID(int id){
        Cursor cursor;
        String[] campos = {BancoUtil.ID_NOTA, BancoUtil.TIPO_PAGAMENTO};
        db = banco.getReadableDatabase();


        String where = BancoUtil.ID_NOTA + " = " + id;

        cursor = db.query(BancoUtil.TABELA_NOTA, campos, where, null, null, null, null, null);

        Nota nota = new Nota();
        if (cursor != null) {
            cursor.moveToFirst();

            int ID = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_NOTA));
            String pagamento = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.TIPO_PAGAMENTO));

            nota.setID(ID);
            nota.setTipo_Pagamento(pagamento);

        }
        db.close();
        return nota;
    }

    public Cursor carregaDados(long id_usuario) {
        Cursor cursor;
        String[] campos = {BancoUtil.ID_CLIENTE, BancoUtil.TIPO_PAGAMENTO};
        db = banco.getReadableDatabase();

        String where = BancoUtil.CLIENTE_NOTA + " = " + id_usuario;

        cursor = db.query(BancoUtil.TABELA_NOTA, campos, where, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public List<Nota> carregaDadosLista(int TIPO_PAGAMENTO, long id_usuario) {
        Cursor cursor = null;
        if(TIPO_PAGAMENTO == NOTAS_TOTAL)
            cursor = carregaDados(id_usuario);

        List<Nota> notas = new ArrayList<>();

        try {
            if(cursor.getCount() > 0) {
                do {
                    Nota nota = new Nota();
                    int ID = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_NOTA));
                    String pagamento = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.TIPO_PAGAMENTO));

                    nota.setID(ID);
                    nota.setTipo_Pagamento(pagamento);


                    notas.add(nota);
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        return notas;
    }

    public void deletaRegistro(int id){
        String where = BancoUtil.ID_NOTA + "=" + id;
        db = banco.getReadableDatabase();

        db.delete(BancoUtil.TABELA_NOTA,where,null);
        db.close();
    }

    public boolean atualizarNota(Nota nota){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = BancoUtil.ID_NOTA + " = " + nota.getID();

        valores = new ContentValues();
        valores.put(BancoUtil.TIPO_PAGAMENTO, nota.getTipo_Pagamento());


        int resultado = db.update(BancoUtil.TABELA_NOTA,valores,where,null);
        db.close();
        if(resultado > 0)
            return true;
        else
            return false;
    }
}