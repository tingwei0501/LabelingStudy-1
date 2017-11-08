package LabelingStudy.nctu.minuku_2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jimmy on 2017/10/31.
 */

public class MyDBHelper extends SQLiteOpenHelper {
    final private static int _DB_VERSION = 1;
    final private static String _DB_DATABASE_NAME = "MyDatabases.db";

    public MyDBHelper(Context context) {
        super(context,_DB_DATABASE_NAME,null,_DB_VERSION);
    }

    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Minuku " +
                "(_id INTEGER PRIMARY KEY  NOT NULL , " +
                "_Data VARCHAR(50) " + ")"
                 );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS MyTable");
        onCreate(db);
    }
}
