package xy.xiaoxing.go;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import xy.xiaoxing.go.core.MySQLiteHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class ListActivity extends AppCompatActivity {
    private BottomNavigationView nv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MySQLiteHelper helper =new MySQLiteHelper(this);
        helper.getWritableDatabase();
        initView();
    }
    private void initView() {
        nv = (BottomNavigationView) findViewById(R.id.nv);
        NavController navController = Navigation.findNavController(this,R.id.fragment);
        AppBarConfiguration configuration=new AppBarConfiguration.Builder(nv.getMenu()).build();
        NavigationUI.setupActionBarWithNavController(this,navController,configuration);
        NavigationUI.setupWithNavController(nv,navController);
    }
}