package com.project.responsi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class dbHelp extends SQLiteOpenHelper {
    String mQuery, mQuery2;
    SQLiteDatabase mDB;
    Context mContext;

    public static final String TABLE_KC        = "tb_kc";
    public final String _id                    = "_id";
    public final String TANGGAL                = "TANGGAL";
    public final String SEMBUH                 = "SEMBUH";
    public final String MENINGGAL              = "MENINGGAL";
    public final String KONFIRMASI             = "KONFIRMASI";

    public static final String TABLE_RC        = "tb_rc";
    public final String _id2                   = "_id";
    public final String NAMA                   = "NAMA";
    public final String ALAMAT                 = "ALAMAT";
    public final String LONGITUDE              = "LONGITUDE";
    public final String LATITUDE               = "LATITUDE";

    public dbHelp(Context context){
        super(context,"db_data",null,5);
        mContext = context;
        mDB = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        mQuery = "CREATE TABLE " + TABLE_KC + " (" +
                _id                                + " INTEGER PRIMARY KEY," +
                TANGGAL                            + " TEXT, " +
                SEMBUH                             + " TEXT, " +
                MENINGGAL                          + " TEXT, " +
                KONFIRMASI                         + " TEXT  " +
                ")";
        db.execSQL(mQuery);

        mQuery2 = "CREATE TABLE " + TABLE_RC + " (" +
                _id2                               + " INTEGER PRIMARY KEY," +
                NAMA                               + " TEXT," +
                ALAMAT                             + " TEXT, " +
                LONGITUDE                          + " TEXT, " +
                LATITUDE                           + " TEXT  " +
                ")";
        db.execSQL(mQuery2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("NEW",""+newVersion);
        Log.d("OLD",""+oldVersion);
        if(newVersion>oldVersion){
            db.execSQL("DROP TABLE " + TABLE_KC);
            db.execSQL("DROP TABLE " + TABLE_RC);
            onCreate(db);
        }
    }

    public void deleteKC() {
        SQLiteDatabase db = this.getWritableDatabase();
        mQuery = "DELETE FROM tb_kc";
        db.execSQL(mQuery);
    }

    public void insertKC(ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("insert", "" + values.toString());
        db.insert("tb_kc", null, values);
    }

    public Cursor selectKC() {
        SQLiteDatabase db = this.getWritableDatabase();
        mQuery = "SELECT * FROM tb_kc ORDER BY _id DESC";
        Cursor c = db.rawQuery(mQuery, null);
        return c;
    }

    public void deleteRC() {
        SQLiteDatabase db = this.getWritableDatabase();
        mQuery = "DELETE FROM tb_rc";
        db.execSQL(mQuery);
    }

    public void insertRC(ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("insert", "" + values.toString());
        db.insert("tb_rc", null, values);
    }

    public Cursor selectRC() {
        SQLiteDatabase db = this.getWritableDatabase();
        mQuery = "SELECT * FROM tb_rc";
        Cursor c = db.rawQuery(mQuery, null);
        return c;
    }

    public Cursor selectLocRC() {
        SQLiteDatabase db = this.getWritableDatabase();
        mQuery = "SELECT LONGITUDE, LATITUDE FROM tb_rc ";
        Cursor c = db.rawQuery(mQuery, null);
        return c;
    }
}
