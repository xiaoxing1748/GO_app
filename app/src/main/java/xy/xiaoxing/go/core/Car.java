package xy.xiaoxing.go.core;

import android.util.Log;

public class Car {
    private String car_id;
    private String car_name;
    private String car_brand;
    private String car_date;
    private String car_info;
    private String car_price;

    public Car(String car_id, String car_name, String car_brand, String car_date, String car_info, String car_price) {
        this.car_id = car_id;
        this.car_name = car_name;
        this.car_brand = car_brand;
        this.car_date = car_date;
        this.car_info = car_info;
        this.car_price = car_price;
        Log.i("Car类",car_id+car_name+car_brand+car_date+car_info+car_price);
    }

    public String getCar_id() {
        return car_id;
    }

    public void setCar_id(String car_id) {
        this.car_id = car_id;
    }

    public String getCar_name() {
        return car_name;
    }

    public void setCar_name(String car_name) {
        this.car_name = car_name;
    }

    public String getCar_brand() {
        return car_brand;
    }

    public void setCar_brand(String car_brand) {
        this.car_brand = car_brand;
    }

    public String getCar_date() {
        return car_date;
    }

    public void setCar_date(String car_date) {
        this.car_date = car_date;
    }

    public String getCar_info() {
        return car_info;
    }

    public void setCar_info(String car_info) {
        this.car_info = car_info;
    }

    public String getCar_price() {
        return car_price;
    }

    public void setCar_price(String car_price) {
        this.car_price = car_price;
    }


}
