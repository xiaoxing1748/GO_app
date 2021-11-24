package xy.xiaoxing.go.fragment;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import xy.xiaoxing.go.adapter.CarAdapter;
import xy.xiaoxing.go.core.Car;
import xy.xiaoxing.go.R;
import xy.xiaoxing.go.core.MySQLiteHelper;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FirstFragment extends Fragment {


    private EditText editTextCarName;
    private Button button;
    private RecyclerView searchRv;
    private ArrayList<Car> mlist;
    private CarAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        editTextCarName = view.findViewById(R.id.editTextCarName);
        button = view.findViewById(R.id.buttonSearch);

        searchRv = view.findViewById(R.id.search_rv);
        mlist = new ArrayList<>();
        adapter = new CarAdapter(this.getContext(), mlist);
        searchRv.setAdapter(adapter);
        searchRv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("FirstFragment.initView", "onClick: 点击了搜索汽车");
                if (!TextUtils.isEmpty(editTextCarName.getText().toString().trim())) {
                    String keyword = editTextCarName.getText().toString();
                    initData(keyword);
                } else {
                    Toast.makeText(getContext(), "输入不能为空", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    private void initData(String keyword) {
        MySQLiteHelper mySQLiteHelper = new MySQLiteHelper(getContext());
        SQLiteDatabase db = mySQLiteHelper.getReadableDatabase();
        //模糊查询
        String query="select * from car where car_name like '%"+keyword+"%' or car_brand like '%"+keyword+"%' or car_info like '%"+keyword+"%';";
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
        Log.i("query",query);
        if (cursor != null) {
            //先清除mlist再添加
            mlist.clear();
            while(cursor.moveToNext()){
//                String id = cursor.getString(0);
                int id=cursor.getInt(0);
                String name = cursor.getString(1);
                String brand = cursor.getString(2);
                String date = cursor.getString(3);
                String info = cursor.getString(4);
                String price = cursor.getString(5);
                Car car = new Car(id,name,brand,date,info,price);
                mlist.add(car);
            }
            Toast.makeText(getContext(), "成功搜索到"+cursor.getCount()+"款车辆", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), "测试", Toast.LENGTH_LONG).show();

        }
        cursor.close();
        db.close();
        adapter.notifyDataSetChanged();


    }
}