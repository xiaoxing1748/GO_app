package xy.xiaoxing.go;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import xy.xiaoxing.go.core.MySQLiteHelper;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText userPhone;
    private EditText userPassword;
    private Button btnLogin;
    private Button btnRegister;
    private String phone_string;
    private String password_string;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pref = getSharedPreferences("account_password", MODE_PRIVATE);
        initView();
        if (!CopyDatabase()) {
            Log.i("初始化", "初始化失败");
            Toast.makeText(this, "初始化失败，请尝试停止应用并重启", Toast.LENGTH_LONG).show();
        }
    }

    private void initView() {
        userPhone = findViewById(R.id.user_phone);
        userPassword = findViewById(R.id.user_password);
        btnRegister = findViewById(R.id.btn_register);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        userPhone.setText(pref.getString("user_phone", ""));
        userPassword.setText(pref.getString("user_password", ""));
        phone_string=userPhone.getText().toString();
        password_string=userPassword.getText().toString();
    }

    /**
     * 按钮判断
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                Log.i("btn_register", "转到注册页");
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.btn_login:
                if (userPhone.getText().toString().length() == 11) {
                    Log.i("btn_login", "用户正在尝试登录");
                    try {
                        if (LoginCheck(userPhone,userPassword)) {
                            Log.i("btn_login", "用户登录成功");
                            Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(this, ListActivity.class));
                        }else{
                            Log.i("loginTry","尝试登录失败");
                            Toast.makeText(this, "登录失败", Toast.LENGTH_LONG).show();
                        }
                    }catch (Exception e){
                        Log.i("loginTry","尝试登录失败");
                        Toast.makeText(this, "登录失败", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "请正确输入手机号", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
    /**
     * 登录验证
     */
    public boolean LoginCheck(EditText userPhone,EditText userPassword){
        MySQLiteHelper dbHelper = new MySQLiteHelper(MainActivity.this);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        String query="select * from user where user_phone= '"+userPhone.getText().toString()+"' and user_password= '"+userPassword.getText().toString()+"';";
        Log.i("loginTry", query);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() != 0) {
            return true;
        }
        return false;
    }

    /**
     * 复制数据库
     */
    public boolean CopyDatabase() {
        // 检查数据库文件是否存在
        String DB_PATH = "/data/data/" + getPackageName() + "/databases/";
        String DB_NAME = "database.db";
        if (!(new File(DB_PATH + DB_NAME)).exists()) {
            // 数据库文件不存在则检查databases目录是否存在
            File f = new File(DB_PATH);
            // databases目录不存在则新建该目录
            if (!f.exists()) {
                f.mkdir();
            }

            try {
                // 获取数据库作为输入流
                InputStream inputStream = getResources().openRawResource(R.raw.database);
                // 输出流
                OutputStream outputStream = new FileOutputStream(DB_PATH + DB_NAME);
                // 文件写入
                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
                // 关闭文件流
                outputStream.flush();
                outputStream.close();
                inputStream.close();
                Log.i("CopyDatabase", "成功复制数据库到" + DB_PATH);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            Log.i("CopyDatabase", DB_PATH + "已有数据库");
            return true;
        }
        return false;
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }
}