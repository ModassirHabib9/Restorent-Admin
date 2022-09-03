package com.example.resturentadminside.SignInAnd_SignUp_Releated_Classes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.resturentadminside.MainActivity;
import com.example.resturentadminside.Model;
import com.example.resturentadminside.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Login_Activity extends AppCompatActivity {
Button btn_login;
EditText edit_user_number,edit_user_password;
TextView  txt_sign_up;
DatabaseReference database_retreview_resturent_details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        initalization_widget();

    }

    private void initalization_widget() {
        edit_user_number = (EditText)findViewById(R.id.edit_user_number) ;
        edit_user_password = (EditText)findViewById(R.id.edit_user_password) ;

        btn_login = (Button)findViewById(R.id.btn_login);
        txt_sign_up = (TextView) findViewById(R.id.txt_sign_up);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Model.getInstance().mobile_number= edit_user_number.getText().toString();
                retreviewing_resturent_profile();

            }
        });
        txt_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Login_Activity.this, Registration_Screen.class);
                startActivity(intent);
            }
        });
    }

    public void retreviewing_resturent_profile(){
             String password = edit_user_password.getText().toString();
        try {
            database_retreview_resturent_details= FirebaseDatabase.getInstance(Model.getInstance().project_url)
                    .getReference("Resturent Registration").child(edit_user_number.getText().toString());
            database_retreview_resturent_details.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    HashMap<String, Object> data = new HashMap<>();
                       for (DataSnapshot child :dataSnapshot.getChildren()) {
                           data.put(child.getKey(), child.getValue());


                           Log.e("HELO",""+data);

                           try {
                               // Toast.makeText(Retreving_Expense_Records.this,data.toString(),Toast.LENGTH_LONG).show();

                               //    fruits.add(data.get("shirt_half_back").toString());
                               //  txt_buyer_name.setText(data.get("buyer_name").toString());

                               Model.getInstance().resturent_name = data.get("resturent_name").toString();
                               Model.getInstance().mobile_number = data.get("user_mobile_number").toString();
                               Model.getInstance().provide_meal = data.get("provide_meal").toString();
                               Model.getInstance().provide_fastfood = data.get("provide_fasfood").toString();
                               Log.e("url", data.get("user_password").toString());
                               if (data.get("user_password").toString() .equals(password)) {
                                   Intent intent = new Intent(Login_Activity.this, MainActivity.class);
                                   startActivity(intent);
                               }else {
                                   Toast.makeText(Login_Activity.this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                               }


                           }
//
                           catch (Exception ae) {
                               ae.printStackTrace();
                           }


                       }



                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }catch (Exception ae){
            ae.printStackTrace();

        }

    }
}