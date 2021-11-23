package xy.xiaoxing.go.adapter;

public class CarBean {
    public final static String CAR_ID = "car_id";//car_id
    public final static String CAR_NAME = "car_name";//名称
    public final static String CAR_INFO = "car_info";//详情
    public final static String CAR_DATE = "car_date";//上市日期
    public final static String CAR_PRICE = "car_price";//价格

    private int car_id;
    private String car_name;
    private String car_info;
    private String car_date;
    private int car_price;

    public CarBean(int car_id, String car_name, String car_info, String car_date, String car_brand, int car_price) {
        this.car_id = car_id;
        this.car_name = car_name;
        this.car_info = car_info;
        this.car_date = car_date;
        this.car_price = car_price;
    }

    public int getId() {
        return car_id;
    }

    public void setId(int car_id) {
        this.car_id = car_id;
    }

    public String getCar_name() {
        return car_name;
    }

    public void setCar_name(String car_name) {
        this.car_name = car_name;
    }

    public String getCar_info() {
        return car_info;
    }

    public void setCar_info(String car_info) {
        this.car_info = car_info;
    }

    public String getCar_date() {
        return car_date;
    }

    public void setCar_date(String car_date) {
        this.car_date = car_date;
    }

    public int getCar_price() {
        return car_price;
    }

    public void setCar_price(int car_price) {
        this.car_price = car_price;
    }
}
