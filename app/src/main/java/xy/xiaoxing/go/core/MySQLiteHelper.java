package xy.xiaoxing.go.core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



import androidx.annotation.Nullable;

public class MySQLiteHelper extends SQLiteOpenHelper {

    static String dbname="database.db";
    static int dbVersion=1;
    public MySQLiteHelper(Context context) {
        super(context, dbname, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
