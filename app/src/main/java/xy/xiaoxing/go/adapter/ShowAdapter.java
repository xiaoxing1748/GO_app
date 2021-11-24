package xy.xiaoxing.go.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import xy.xiaoxing.go.R;

import java.util.List;

public class ShowAdapter extends  RecyclerView.Adapter<ShowAdapter.ViewHolder> {
    private final Context context;
    private  final List<CarInfo> data;

    public ShowAdapter(Context context, List<CarInfo> data) {
        this.context = context;
        this.data = data;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.show_item_layout,
                parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CarInfo carInfo =data.get(position);
        holder.tv_name.setText("汽车品牌："+carInfo.getName());
        holder.tv_price.setText("售价： "+carInfo.getPrice());
        holder.tv_date.setText("上市日期： "+carInfo.getDate());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout layout;
        TextView tv_price;
        TextView tv_date;
        TextView tv_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout=itemView.findViewById(R.id.show_item);
            tv_price=itemView.findViewById(R.id.tv_price1);
            tv_date = itemView.findViewById(R.id.tv_date1);
            tv_name = itemView.findViewById(R.id.tv_name1);
        }
    }
}
