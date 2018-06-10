package test.androidtestc.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import test.androidtestc.Factory.DatabaseFactory;
import test.androidtestc.Util.BancoUtil;

/**
 * Created by User on 05/06/2018.
 *
 * Classe para inserção, remoção, atualização e busca em Banco de Dados de um ProdutoNota.
 *
 */

public class ProdutoNotaDAO {
    private SQLiteDatabase db;
    private DatabaseFactory banco;

    public static final int PRODUTOS_TOTAL = 1;

    public ProdutoNotaDAO(Context context) {
        banco = new DatabaseFactory(context);
    }

    public long insereDado(ProdutoNota produtoNota) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(BancoUtil.PRODUTO_PRODUTO, produtoNota.getProduto());
        valores.put(BancoUtil.NUMERO_NOTA, produtoNota.getID_Nota());
        valores.put(BancoUtil.QUANTIDADE, produtoNota.getQuantidade()) ;
        valores.put(BancoUtil.VALOR, String.valueOf(produtoNota.getPreco())) ;


        resultado = db.insert(BancoUtil.TABELA_PRODUTO_NOTA, null, valores);
        db.close();

        return resultado;

    }

    public ProdutoNota carregaProdutoNotaPorID(int id){
        Cursor cursor;
        String[] campos = {BancoUtil.ID_PRODUTO_NOTA, BancoUtil.PRODUTO_PRODUTO,
                BancoUtil.NUMERO_NOTA, BancoUtil.QUANTIDADE,
                BancoUtil.VALOR};
        db = banco.getReadableDatabase();


        String where = BancoUtil.ID_PRODUTO_NOTA + " = " + id;

        cursor = db.query(BancoUtil.TABELA_PRODUTO_NOTA, campos, where, null, null, null, null, null);

        ProdutoNota produtoNota = new ProdutoNota();
        if (cursor != null) {
            cursor.moveToFirst();

            int ID = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_PRODUTO_NOTA));
            String nome = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.PRODUTO_PRODUTO));
            int nota = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.NUMERO_NOTA));
            long quantidade = cursor.getLong(cursor.getColumnIndexOrThrow(BancoUtil.QUANTIDADE));
            Double valor = cursor.getDouble(cursor.getColumnIndexOrThrow(BancoUtil.VALOR));

            produtoNota.setID(ID);
            produtoNota.setProduto(nome);
            produtoNota.setID_Nota(nota);
            produtoNota.setQuantidade(quantidade);
            produtoNota.setPreco(valor);

        }
        db.close();
        return produtoNota;
    }

    public Cursor carregaDados(long id_usuario) {
        Cursor cursor;
        String[] campos = {BancoUtil.ID_PRODUTO_NOTA, BancoUtil.PRODUTO_PRODUTO, BancoUtil.NUMERO_NOTA, BancoUtil.QUANTIDADE,
                BancoUtil.VALOR,BancoUtil.CLIENTE_USUARIO};
        db = banco.getReadableDatabase();

        String where =  BancoUtil.TABELA_CLIENTE+"."+BancoUtil.CLIENTE_USUARIO+" = " + BancoUtil.TABELA_NOTA+"."+BancoUtil.CLIENTE_NOTA +
                        " AND " + BancoUtil.TABELA_NOTA+"."+BancoUtil.ID_NOTA+" = " + BancoUtil.TABELA_PRODUTO_NOTA+"."+BancoUtil.NUMERO_NOTA +
                        " AND " + BancoUtil.TABELA_CLIENTE+"."+BancoUtil.CLIENTE_USUARIO + " = " + id_usuario;

        cursor = db.query((BancoUtil.TABELA_PRODUTO_NOTA+", "+ BancoUtil.TABELA_NOTA+","+BancoUtil.TABELA_CLIENTE), campos, where, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public List<ProdutoNota> carregaDadosLista(int TIPO_PRODUTO, long id_usuario) {
        Cursor cursor = null;
        if(TIPO_PRODUTO == PRODUTOS_TOTAL)
            cursor = carregaDados(id_usuario);

        List<ProdutoNota> produtoNotas = new ArrayList<>();

        try {
            if(cursor.getCount() > 0) {
                do {
                    ProdutoNota produtoNota = new ProdutoNota();

                    int ID = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_PRODUTO_NOTA));
                    String nome = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.PRODUTO_PRODUTO));
                    int nota = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.NUMERO_NOTA));
                    long quantidade = cursor.getLong(cursor.getColumnIndexOrThrow(BancoUtil.QUANTIDADE));
                    Double valor = cursor.getDouble(cursor.getColumnIndexOrThrow(BancoUtil.VALOR));

                    produtoNota.setID(ID);
                    produtoNota.setProduto(nome);
                    produtoNota.setID_Nota(nota);
                    produtoNota.setQuantidade(quantidade);
                    produtoNota.setPreco(valor);


                    produtoNotas.add(produtoNota);
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        return produtoNotas;
    }

    public void deletaRegistro(int id){
        String where = BancoUtil.ID_PRODUTO_NOTA + "=" + id;
        db = banco.getReadableDatabase();

        db.delete(BancoUtil.TABELA_PRODUTO_NOTA,where,null);
        db.close();
    }

    public boolean atualizaProdutoNota(ProdutoNota produtoNota){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = BancoUtil.ID_PRODUTO_NOTA + " = " + produtoNota.getID();

        valores = new ContentValues();
        valores.put(BancoUtil.PRODUTO_PRODUTO, produtoNota.getProduto());
        valores.put(BancoUtil.NUMERO_NOTA, produtoNota.getID_Nota());
        valores.put(BancoUtil.QUANTIDADE, produtoNota.getQuantidade());
        valores.put(BancoUtil.VALOR, produtoNota.getPreco());

        int resultado = db.update(BancoUtil.TABELA_PRODUTO_NOTA,valores,where,null);
        db.close();
        if(resultado > 0)
            return true;
        else
            return false;
    }
}