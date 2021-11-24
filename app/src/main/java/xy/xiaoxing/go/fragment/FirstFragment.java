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

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import xy.xiaoxing.go.adapter.CarInfo;
import xy.xiaoxing.go.adapter.ShowAdapter;
import xy.xiaoxing.go.R;
import xy.xiaoxing.go.core.MySQLiteHelper;

import java.util.ArrayList;

public class FirstFragment extends Fragment  {


    private EditText editTextTextPersonName;
    private Button button;
    private RecyclerView searchRv;
    private ArrayList<CarInfo> mlist;
    private ShowAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        editTextTextPersonName = view.findViewById(R.id.editTextCarName);
        button = view.findViewById(R.id.buttonSearch);

        searchRv = view.findViewById(R.id.search_rv);
        mlist = new ArrayList<>();
        adapter = new ShowAdapter(this.getContext(), mlist);
        searchRv.setAdapter(adapter);
        searchRv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("搜索页", "onClick: 搜索");
                if (!TextUtils.isEmpty(editTextTextPersonName.getText().toString().trim())) {
                    String car_name = editTextTextPersonName.getText().toString();
                    initData(car_name);
                } else {
                    Toast.makeText(getContext(), "输入不能为空", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    private void initData(String car_name){
        MySQLiteHelper mySQLiteHelper = new MySQLiteHelper(getContext());
        SQLiteDatabase db =mySQLiteHelper.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor =
                db.rawQuery("select * from car",null);

       if(cursor !=null){
           String name = "BMW";
           int price = 6880000;
           String date = "2011/05";
           CarInfo carInfo = new CarInfo(name,price,date);
           mlist.add(carInfo);
       }else {
           Toast.makeText(getContext(), "qqqqqqqq", Toast.LENGTH_LONG).show();

       }

        Log.i("TAG", "initData:  11111111111111   "+mlist.size());
        cursor.close();
        db.close();
        adapter.notifyDataSetChanged();



    }
}