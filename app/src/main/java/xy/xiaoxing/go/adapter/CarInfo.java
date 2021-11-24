package xy.xiaoxing.go.adapter;


public class CarInfo {
    private String name;
    private int price;
    private boolean is_non_renewable;
    private String date;

    public CarInfo() {

    }

    public CarInfo(String name) {
        this.name = name;

    }

    public CarInfo(String name, int price) {
        this.name = name;
        this.price = price;

    }

    public CarInfo(String name, int price, boolean is_non_renewable) {
        this.name = name;
        this.price = price;
        this.is_non_renewable = is_non_renewable;
    }

    public CarInfo(String name, int price, boolean is_non_renewable, String date) {
        this.name = name;
        this.price = price;
        this.is_non_renewable = is_non_renewable;
        this.date = date;
    }

    public CarInfo(String name, int price, String date) {
        this.name = name;
        this.price = price;
        this.is_non_renewable = is_non_renewable;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isIs_non_renewable() {
        return is_non_renewable;
    }

    public void setIs_non_renewable(boolean is_non_renewable) {
        this.is_non_renewable = is_non_renewable;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
