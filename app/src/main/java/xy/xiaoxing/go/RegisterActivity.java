package xy.xiaoxing.go;

import androidx.appcompat.app.AppCompatActivity;

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

import xy.xiaoxing.go.core.MySQLiteHelper;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText userPhone;
    private EditText userPassword;
    private EditText userPasswordConfirm;
    private Button btnRegister;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        pref = getSharedPreferences("account_password", MODE_PRIVATE);
        initView();
    }

    private void initView() {
        userPhone = findViewById(R.id.user_phone);
        userPassword = findViewById(R.id.user_password);
        userPasswordConfirm = findViewById(R.id.user_password_confirm);
        btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        SharedPreferences.Editor editor = getSharedPreferences("account_password", MODE_PRIVATE).edit();

        switch (v.getId()) {
            case R.id.btn_register:
                if (userPhone.getText().toString().length() != 11) {
                    Log.i("注册页", "手机号未正确输入");
                    Toast.makeText(this, "请正确输入手机号", Toast.LENGTH_LONG).show();
                } else {
                    if (userPassword.getText().toString().equals(userPasswordConfirm.getText().toString())) {
                        if (Register(userPhone, userPassword)) {
                            editor.putString("user_phone", userPhone.getText().toString());
                            editor.putString("user_password", userPassword.getText().toString());
                            editor.putBoolean("isRegister", true);
                            editor.apply();
                            Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
                            Log.i("注册页", "跳转到登陆页");
                            startActivity(new Intent(this, MainActivity.class));
                        } else {
                            Log.i("注册页", "注册失败");
                            Toast.makeText(this, "注册失败", Toast.LENGTH_LONG).show();

                        }
                    }else{
                        Log.i("注册页", "重复密码不一致");
                        Toast.makeText(this, "重复密码不一致", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    public boolean Register(EditText userPhone, EditText userPassword) {
        MySQLiteHelper dbHelper = new MySQLiteHelper(RegisterActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "insert into user(user_phone,user_password) values('" + userPhone.getText().toString() + "','" + userPassword.getText().toString() + "');";
        Log.i("RegisterTry", sql);
        try {
            db.execSQL(sql);
            return true;
        } catch (Exception e) {
            Log.i("RegisterTry", "插入失败");
        }
        return false;
    }
}