package com.example.resturentadminside.Order_Related_Stuff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.resturentadminside.Conformation_Activity;
import com.example.resturentadminside.Model;
import com.example.resturentadminside.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class Order_List_Retreivew extends AppCompatActivity implements Order_List_Adapter.OnItemClickListener {
    private Order_List_Adapter mAdapter;

    public RecyclerView mRecyclerView;
    private FirebaseStorage mStorage;
    private DatabaseReference mDatabaseRef;
    private ValueEventListener mDBListener;
    private ProgressBar mProgressCircle;
    private List<Firebase_Order_Jobs> mUploads;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__list__retreivew);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProgressCircle = findViewById(R.id.progress_circle);
        mUploads = new ArrayList<>();
        mAdapter = new Order_List_Adapter(Order_List_Retreivew.this, mUploads);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(Order_List_Retreivew.this);
        mStorage = FirebaseStorage.getInstance();
        retreving_customer_order_details();
    }

    private void retreving_customer_order_details() {
        mDatabaseRef = FirebaseDatabase.getInstance(Model.getInstance().project_url).
                getReference("Resturent Orders").child(Model.getInstance().mobile_number);
        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUploads.clear();
                for (DataSnapshot child : dataSnapshot.getChildren())
                {
                    Firebase_Order_Jobs upload = child.getValue(Firebase_Order_Jobs.class);
                    upload.setKey(child.getKey());
                    mUploads.add(upload);
                }
                mAdapter.notifyDataSetChanged();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Order_List_Retreivew.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onItemClick(int position) {
    Model.getInstance().Customer_mobile_number = mUploads.get(position).getOrder_mobile_number();
//    Model.getInstance().Resturent_mobile_no = mUploads.get(position).getUser_mobile_number();
//    JobSeeker_Model.getInstance().jobseeker_experence = mUploads.get(position).getJob_seeker_experence();
//    JobSeeker_Model.getInstance().jobseeker_speciality = mUploads.get(position).getJob_seeker_speciality();
//    JobSeeker_Model.getInstance().jobseeker_certificate_status = mUploads.get(position).getJob_seeker_certification_status();
//    JobSeeker_Model.getInstance().jobseeker_image_url = mUploads.get(position).getJob_seeker_image_url();
//    JobSeeker_Model.getInstance().jobseeker_mobile_number = mUploads.get(position).getJob_seeker_mobile_no();
//

        Intent intent = new Intent(Order_List_Retreivew.this, Conformation_Activity.class);
        startActivity(intent);


    }

    @Override
    public void onWhatEverClick(int position) {

        Toast.makeText(this, "Whatever click at position: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(int position) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListener);
    }
}