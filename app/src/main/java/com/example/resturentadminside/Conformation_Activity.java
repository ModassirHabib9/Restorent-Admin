package com.example.resturentadminside;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Conformation_Activity extends AppCompatActivity {
    EditText edit_expected_time;
    Button btn_reject,btn_accept;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conformation_);
        btn_reject =(Button)findViewById(R.id.btn_reject) ;
        btn_accept =(Button)findViewById(R.id.btn_accept) ;
        edit_expected_time = (EditText) findViewById(R.id.edit_expected_time);
        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                method_Conform_order();
                Intent intent  = new Intent(Conformation_Activity.this,MainActivity.class);
                startActivity(intent);
                Conformation_Activity.this.finish();
            }
        });
        btn_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Method_Reject_Order();
                Intent intent  = new Intent(Conformation_Activity.this,MainActivity.class);
                startActivity(intent);
                Conformation_Activity.this.finish();
            }
        });
    }
    private void Method_Reject_Order() {

        String sms = "Sorry Your order has Rejected";


        try{
            SmsManager smsManager= SmsManager.getDefault();
            smsManager.sendTextMessage(Model.getInstance().Customer_mobile_number,null,sms,null,null);
            Toast.makeText(Conformation_Activity.this,"please wait",Toast.LENGTH_SHORT).show();
        }catch (Exception ae){
            Toast.makeText(Conformation_Activity.this,"Faild",Toast.LENGTH_SHORT).show();
        }
    }

    private void method_Conform_order() {

        String sms = "Congragulation\n"+
                "your order has accepted\n"+
                "Order Expected Time:  "+ edit_expected_time.getText().toString() +"\n";
        try{
            SmsManager smsManager= SmsManager.getDefault();
            smsManager.sendTextMessage(Model.getInstance().Customer_mobile_number,null,sms,null,null);
            Toast.makeText(Conformation_Activity.this,"please wait",Toast.LENGTH_SHORT).show();

        }catch (Exception ae){
            Toast.makeText(Conformation_Activity.this,"Faild",Toast.LENGTH_SHORT).show();
        }
    }
}