package org.techtown.AndroidStudioAigoyak;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static org.techtown.AndroidStudioAigoyak.AppConstants.DATABASE_NAME;

public class NoteDatabase {
    private static final String TAG = "NoteDatabase";
    private static NoteDatabase database;

    //table name
    public static String TABLE_NOTE ="NOTE";
    public static String TABLE_BOOKMARK = "BOOKMARK";
    public static String TABLE_USER = "USER";

    public static int DATABASE_VERSION = 1;

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private Context context;

    private NoteDatabase(Context context) {
        this.context = context;
    }
    public static NoteDatabase getInstance(Context context) {
        if (database == null) {
            database = new NoteDatabase(context);
        }

        return database;
    }
    public boolean open() {
        println("opening database [" + DATABASE_NAME + "].");
        dbHelper = new DatabaseHelper(context);//dbHelper
        db = dbHelper.getWritableDatabase();

        return true;
    }
    public void close() {
        db.close();
        database = null;
    }


    public Cursor rawQuery(String SQL) {
        println("\nexecuteQuery called.\n");

        Cursor c1 = null;
        try {
            c1 = db.rawQuery(SQL, null);
        } catch(Exception ex) {
            Log.e(TAG, "Exception in executeQuery", ex);
        }

        return c1;
    }


    public boolean execSQL(String SQL) {
        println("\nexecute called.\n");

        try {
            Log.d(TAG, "SQL : " + SQL);
            db.execSQL(SQL);
        } catch(Exception ex) {
            Log.e(TAG, "Exception in executeQuery", ex);
            return false;
        }

        return true;
    }
    private class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            println("creating database [" + DATABASE_NAME + "].");

            String DROP_SQL_NOTE = "drop table if exists " + TABLE_NOTE;
            String DROP_SQL_BOOKMARK =  "drop table if exists " + TABLE_BOOKMARK;
            String DROP_SQL_USER = "drop table if exists " + TABLE_USER;

            try {
                db.execSQL(DROP_SQL_NOTE);
                db.execSQL(DROP_SQL_BOOKMARK);
                db.execSQL(DROP_SQL_USER);
            } catch(Exception ex) {
                Log.e(TAG, "Exception in DROP_SQL", ex);
            }


            String CREATE_SQL_NOTE = "create table " + TABLE_NOTE + "("
                    + "  _id INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    + "  CODE TEXT DEFAULT '', "
                    + "  NAME TEXT DEFAULT '', "
                    + "  CORP TEXT DEFAULT '', "
                    + "  CLOCK TEXT DEFAULT '', "
                    + "  DATE INTEGER , "
                    + "  ALARM INTEGER , "
                    + "  DATE2 INTEGER);";
            String CREATE_SQL_BOOKMARK = "create table " + TABLE_BOOKMARK + "("
                    + " _id INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    + "  CODE TEXT DEFAULT '',"
                    + "  NAME TEXT DEFAULT '',"
                    + "  CORP TEXT DEFAULT '');";

            String CREATE_SQL_USER = "create table " + TABLE_USER + "("
                    + " _id INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    + "  FEATURE TEXT DEFAULT '');";

            try {
                db.execSQL(CREATE_SQL_NOTE);
                db.execSQL(CREATE_SQL_BOOKMARK);
                db.execSQL(CREATE_SQL_USER);

            } catch(Exception ex) {
                Log.e(TAG, "Exception in CREATE_SQL", ex);
            }


            String CREATE_INDEX_SQL_NOTE = "create index " + TABLE_NOTE + "_IDX ON " + TABLE_NOTE + "("
                    + "_id"
                    + ")";
            String CREATE_INDEX_SQL_BOOKMARK = "create index " + TABLE_BOOKMARK + "_IDX ON " + TABLE_BOOKMARK + "("
                    + "_id"
                    + ")";

            String CREATE_INDEX_SQL_USER = "create index " + TABLE_USER + "_IDX ON " + TABLE_USER + "("
                    + "_id"
                    + ")";

            try {
                db.execSQL(CREATE_INDEX_SQL_NOTE);
                db.execSQL(CREATE_INDEX_SQL_BOOKMARK);
                db.execSQL(CREATE_INDEX_SQL_USER);

            } catch(Exception ex) {
                Log.e(TAG, "Exception in CREATE_INDEX_SQL", ex);
            }
        }
        public void onOpen(SQLiteDatabase db) {
            println("opened database [" + DATABASE_NAME + "].");
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            println("Upgrading database from version " + oldVersion + " to " + newVersion + ".");
        }
    }

    private void println(String msg) {
        Log.d(TAG, msg);
    }
}
