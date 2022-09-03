package com.example.resturentadminside;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.resturentadminside.Order_Related_Stuff.Order_List_Retreivew;
import com.example.resturentadminside.Product_Adition.item_addition_screen;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    CardView card_Meal, card_fast_food,card_orders,card_gst_text;
 DatabaseReference database_retreview_resturent_details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Initialization_widgets();
        
    }

    private void Initialization_widgets() {

        card_Meal =(CardView)findViewById(R.id.card_Meal);
        card_fast_food =(CardView)findViewById(R.id.card_fast_food);
        card_orders =(CardView)findViewById(R.id.card_orders);

        card_Meal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Model.getInstance().data_insertion_type ="Meal";
                Intent intent = new Intent(MainActivity.this, item_addition_screen.class);
                startActivity(intent);
            }
        });
        card_fast_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Model.getInstance().data_insertion_type ="fastfood";
                Intent intent = new Intent(MainActivity.this, item_addition_screen.class);
                startActivity(intent);
            }
        });
        card_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Order_List_Retreivew.class);
                startActivity(intent);
            }
        });
    }


}