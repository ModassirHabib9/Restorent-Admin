package com.example.resturentadminside;

public class Model {
    public String project_url="https://resturentapp-d923c-default-rtdb.firebaseio.com";

    public  String resturent_name;
    public  String provide_meal;
    public  String provide_fastfood;
    public  String mobile_number;
    public  String data_insertion_type;
    public String Customer_mobile_number;
    //////////////////// jobs details variables ///////////////////////////////




    private static final Model ourInstance = new Model();

    public static Model getInstance() {

        return ourInstance;
    }

    Model() {
    }
}
