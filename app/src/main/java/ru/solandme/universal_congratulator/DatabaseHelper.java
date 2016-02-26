package ru.solandme.universal_congratulator;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.sql.SQLException;

public class DatabaseHelper extends SQLiteAssetHelper {

    private static String DB_PATH = "";
    private static String DB_NAME = "Holidays.db";
    private static final int SCHEMA = 4; // версия базы данных
    static final String TABLE = "congratulate";

    public static final String ID = "_id";
    public static final String TEXT = "text";
    public static final String HOLIDAY = "holiday";
    public static final String SEX = "sex";

    public SQLiteDatabase database;


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, SCHEMA);
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