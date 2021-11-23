package xy.xiaoxing.go.core;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import xy.xiaoxing.go.MainActivity;

public class Login{
    public boolean LoginCheck(){
        MySQLiteHelper dbHelper = new MySQLiteHelper(MainActivity.Context, "database.db",null,1);
        return true;
    }
}
