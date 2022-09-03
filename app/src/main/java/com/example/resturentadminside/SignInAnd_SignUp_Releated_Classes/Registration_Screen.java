package com.example.resturentadminside.SignInAnd_SignUp_Releated_Classes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.resturentadminside.Model;
import com.example.resturentadminside.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class Registration_Screen extends AppCompatActivity {
EditText edit_resturent_name,edit_location,edit_mobile_number,edit_password;
CheckBox chk_meal,chk_fast_food;


    DatabaseReference databaseReference;
    /////////////////////// new work ///////////////////////
    private static final int PICK_IMAGE_REQUEST = 1;
    private Button mButtonChooseImage;
    private Button mButtonUpload,btn_submit;
    private TextView mTextViewShowUploads;
    private EditText mEditTextFileName;
    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private Uri mImageUri;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private DatabaseReference mDatabaseMeal;
    private DatabaseReference mDatabaseFastFood;
    private StorageTask mUploadTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration__screen);
        intalization_widget();

        mButtonChooseImage = findViewById(R.id.button_choose_image);
        mImageView = findViewById(R.id.image_view);
        mProgressBar = findViewById(R.id.progress_bar);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mDatabaseRef = FirebaseDatabase.getInstance(Model.getInstance().project_url).getReference("Resturent Registration");
        mDatabaseMeal = FirebaseDatabase.getInstance(Model.getInstance().project_url).getReference("Resturent Registration Meal");
        mDatabaseFastFood = FirebaseDatabase.getInstance(Model.getInstance().project_url).getReference("Resturent Registration Fastfood");


        mButtonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }


        });
    }
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();
            Picasso.with(this).load(mImageUri).into(mImageView);
        }
    }
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    private void uploadFile() {
        String provide_meal,provide_fast_food;
        if (chk_fast_food.isChecked()){
            provide_fast_food ="yes provide fastfood";
        }else{
            provide_fast_food = "Not provide fastfood";
        }
        if (chk_meal.isChecked()){
            provide_meal ="Yes we provide meal";
        } else {
            provide_meal = "No we not provide meal";
        }
        if (mImageUri != null) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));
            mUploadTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(0);
                                }
                            }, 500);
                            Toast.makeText(Registration_Screen.this, "Upload successful", Toast.LENGTH_LONG).show();

                            Upload upload = new Upload(
                                    taskSnapshot.getDownloadUrl().toString(),
                                    edit_resturent_name.getText().toString(),
                                    edit_location.getText().toString(),
                                    "zone",
                                    edit_mobile_number.getText().toString(),
                                   edit_password.getText().toString(),
                                    provide_meal,
                                    provide_fast_food
                            );

                            mDatabaseRef.child(edit_mobile_number.getText().toString()).setValue(upload);
                            if (chk_fast_food.isChecked()){
                                mDatabaseFastFood.child(edit_mobile_number.getText().toString()).setValue(upload);
                            } if (chk_meal.isChecked()){
                                mDatabaseMeal.child(edit_mobile_number.getText().toString()).setValue(upload);
                            }
                            Intent intent = new Intent(Registration_Screen.this,Login_Activity.class);
                            startActivity(intent);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Registration_Screen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mProgressBar.setProgress((int) progress);
                        }
                    });
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }
    public void intalization_widget() {
        edit_resturent_name =(EditText)findViewById(R.id.edit_resturent_name);
        edit_location =(EditText)findViewById(R.id.edit_location);
        edit_mobile_number =(EditText)findViewById(R.id.edit_mobile_number);
        edit_password =(EditText)findViewById(R.id.edit_password);
        chk_meal =(CheckBox) findViewById(R.id.chk_meal);
        chk_fast_food =(CheckBox)findViewById(R.id.chk_fast_food);

        btn_submit=(Button)findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(Registration_Screen.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                } else {

                    uploadFile();
                    // worker_data_insertion();
                }

            }
        });
    }
}