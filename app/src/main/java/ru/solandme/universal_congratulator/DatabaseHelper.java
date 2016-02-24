package ru.solandme.universal_congratulator;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.sql.SQLException;

public class DatabaseHelper extends SQLiteAssetHelper {

    private static String DB_PATH = "";
    private static String DB_NAME = "Holidays.db";
    private static final int SCHEMA = 1; // версия базы данных
    static final String TABLE = "congratulate";

    public static final String ID = "_id";
    public static final String TEXT = "text";
    public static final String HOLIDAY = "holiday";
    public static final String SEX = "sex";

    public SQLiteDatabase database;
    private Context myContext;


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, SCHEMA);
        this.myContext=context;
        DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
    }

       public void open() throws SQLException {
        String path = DB_PATH + DB_NAME;
        database = SQLiteDatabase.openDatabase(path, null,
                SQLiteDatabase.OPEN_READWRITE);

    }

    @Override
    public synchronized void close() {
        if (database != null) {
            database.close();
        }
        super.close();
    }

}