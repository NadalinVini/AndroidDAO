package test.androidtestc.Factory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import test.androidtestc.Util.BancoUtil;

/**
 * Created by Diego on 13/09/2017.
 */

public class DatabaseFactory extends SQLiteOpenHelper {

    public DatabaseFactory(Context context){
        super(context, BancoUtil.NOME_BANCO,null,BancoUtil.VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+ BancoUtil.TABELA_USUARIO+"("
                + BancoUtil.ID_USUARIO+ " integer primary key autoincrement,"
                + BancoUtil.LOGIN_USUARIO + " text,"
                + BancoUtil.SENHA_USUARIO + " text"
                +")";
        db.execSQL(sql);

        sql = "CREATE TABLE "+ BancoUtil.TABELA_CLIENTE+"("
                + BancoUtil.ID_CLIENTE+ " integer primary key autoincrement,"
                + BancoUtil.NOME_CLIENTE+ " text,"
                + BancoUtil.ESTADO_CLIENTE + " text,"
                + BancoUtil.CIDADE_CLIENTE + " text,"
                + BancoUtil.CEP_CLIENTE + " integer,"
                + BancoUtil.CPF_CLIENTE + " integer,"
                + BancoUtil.CLIENTE_USUARIO + " integer,"
                + " FOREIGN KEY (" + BancoUtil.CLIENTE_USUARIO + ") REFERENCES " + BancoUtil.TABELA_USUARIO + "(" + BancoUtil.ID_USUARIO + ")"
                +")";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BancoUtil.TABELA_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS " + BancoUtil.TABELA_CLIENTE);
        onCreate(db);
    }
}
