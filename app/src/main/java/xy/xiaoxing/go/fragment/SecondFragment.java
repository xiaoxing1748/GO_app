package xy.xiaoxing.go.fragment;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import xy.xiaoxing.go.adapter.CarAdapter;
import xy.xiaoxing.go.adapter.CarInfo;
import xy.xiaoxing.go.R;
import xy.xiaoxing.go.core.MySQLiteHelper;

import java.util.ArrayList;

public class SecondFragment extends Fragment {


    private RecyclerView tvSecond;
    private ArrayList<CarInfo> mlist;
    private CarAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        tvSecond = (RecyclerView) view.findViewById(R.id.fragment_second);
        mlist = new ArrayList<>();
        adapter=new CarAdapter(this.getContext(),mlist);
        tvSecond.setAdapter(adapter);
        tvSecond.setLayoutManager(new LinearLayoutManager(this.getContext()));

    }
    private void initData(){
        MySQLiteHelper mySQLiteHelper = new MySQLiteHelper(getContext());
        SQLiteDatabase db =mySQLiteHelper.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("select * from car",null);
        while(cursor.moveToNext()){
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("cat_type"));
            @SuppressLint("Range") int price = cursor.getInt(cursor.getColumnIndex("car_price"));
            @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex("date"));
            CarInfo carInfo = new CarInfo(name,price,date);
            mlist.add(carInfo);
        }
        cursor.close();
        db.close();
        adapter.notifyDataSetChanged();



    }
}