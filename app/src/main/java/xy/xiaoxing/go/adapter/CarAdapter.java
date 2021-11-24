package xy.xiaoxing.go.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import xy.xiaoxing.go.R;
import xy.xiaoxing.go.core.Car;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {
    private final Context context;
    private final List<Car> data;

    public CarAdapter(Context context, List<Car> data) {
        this.context = context;
        this.data = data;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.car_item_layout,
                parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Car car = data.get(position);

        holder.car_img.setImageResource(R.drawable.icon);
        holder.car_name.setText(car.getCar_name());
        holder.car_brand.setText("品牌：" + car.getCar_brand());
        holder.car_price.setText("售价：" + car.getCar_price());
        holder.car_date.setText("上市日期： " + car.getCar_date());
        holder.car_info.setText("车辆介绍： " + car.getCar_info());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        ImageView car_img;
        TextView car_name;
        TextView car_brand;
        TextView car_price;
        TextView car_date;
        TextView car_info;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.car_item);
            car_img = itemView.findViewById(R.id.car_img);
            car_name = itemView.findViewById(R.id.car_name);
            car_brand = itemView.findViewById(R.id.car_brand);
            car_price = itemView.findViewById(R.id.car_price);
            car_date = itemView.findViewById(R.id.car_date);
            car_info = itemView.findViewById(R.id.car_info);
        }
    }
}
