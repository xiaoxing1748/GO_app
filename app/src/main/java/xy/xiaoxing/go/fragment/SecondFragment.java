package xy.xiaoxing.go.fragment;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xy.xiaoxing.go.adapter.CarAdapter;
import xy.xiaoxing.go.core.Car;
import xy.xiaoxing.go.R;
import xy.xiaoxing.go.core.MySQLiteHelper;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SecondFragment extends Fragment {


    private RecyclerView tvSecond;
    private ArrayList<Car> mlist;
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
        tvSecond = (RecyclerView) view.findViewById(R.id.tv_second);
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
            String id = cursor.getString(1);
            String name = cursor.getString(1);
            String brand = cursor.getString(1);
            String date = cursor.getString(1);
            String info = cursor.getString(1);
            String price = cursor.getString(1);
            Car car = new Car(id,name,brand,date,info,price);
            mlist.add(car);
        }
        cursor.close();
        db.close();
        adapter.notifyDataSetChanged();



    }
}