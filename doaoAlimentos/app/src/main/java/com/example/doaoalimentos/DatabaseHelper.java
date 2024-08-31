package com.example.doaoalimentos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "doacoes.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_DOACOES = "doacoes";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_CATEGORIA = "categoria";
    private static final String COLUMN_NOME_ITEM = "nome_item";
    private static final String COLUMN_QUANTIDADE = "quantidade";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_DOACOES + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CATEGORIA + " TEXT, " +
                COLUMN_NOME_ITEM + " TEXT, " +
                COLUMN_QUANTIDADE + " INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOACOES);
        onCreate(db);
    }

    public void adicionarItem(ItemDoacao item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CATEGORIA, item.getCategoria());
        values.put(COLUMN_NOME_ITEM, item.getNomeItem());
        values.put(COLUMN_QUANTIDADE, item.getQuantidade());

        db.insert(TABLE_DOACOES, null, values);
        db.close();
    }

    public Cursor getAllItens() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_DOACOES, null, null, null, null, null, null);
    }
}
