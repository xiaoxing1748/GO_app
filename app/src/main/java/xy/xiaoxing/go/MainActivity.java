package xy.xiaoxing.go;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
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

import xy.xiaoxing.go.adapter.User;
import xy.xiaoxing.go.core.Login;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText userPhone;
    private EditText userPassword;
    private Button btnLogin;
    private Button btnRegister;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pref = getSharedPreferences("account_password", MODE_PRIVATE);
        initView();
        if(!CopyDatabase()){
            Log.i("初始化","初始化失败");
            Toast.makeText(this,"初始化失败，请尝试停止应用并重启",Toast.LENGTH_LONG).show();
        }
    }
    private void initView(){
        userPhone = (EditText) findViewById(R.id.user_phone);
        userPassword = (EditText) findViewById(R.id.user_password);
        btnRegister = (Button) findViewById(R.id.btn_register);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        userPhone.setText(pref.getString("user_phone",""));
        userPassword.setText(pref.getString("user_password",""));
    }
    //按钮判断
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_register:
                Log.i("btn_register", "转到注册页");
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            case R.id.btn_login:
                if(!(userPhone.getText().toString().length() !=11)){
                    Log.i("btn_login", "用户正在尝试登录");
                    if(true){

                        Log.i("btn_login", "用户登录成功");
                        startActivity(new Intent(this,ListActivity.class));
                    }
                }else{
                    Toast.makeText(this,"请正确输入手机号",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
    //复制数据库
    public boolean CopyDatabase(){
        // 检查数据库文件是否存在
        String DB_PATH="/data/data/"+getPackageName()+"/databases/";
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
                InputStream inputStream =getResources().openRawResource(R.raw.database);
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
                Log.i("CopyDatabase","成功复制数据库到"+DB_PATH);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else{
            Log.i("CopyDatabase", DB_PATH+"已有数据库");
            return true;
        }
        return false;
    }
    @Override
    protected void onRestart() {
        super.onRestart();

    }
}