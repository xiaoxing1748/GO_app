package xy.xiaoxing.go.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import xy.xiaoxing.go.R;

public class ThirdFragment extends Fragment {


    private RatingBar rcRate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        initView(view);
        return view;
    }


    private void initView(View view) {
        rcRate = (RatingBar) view.findViewById(R.id.rc_rate);
        rcRate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(getContext(),"打分成功",Toast.LENGTH_LONG).show();

            }
        });
    }
}