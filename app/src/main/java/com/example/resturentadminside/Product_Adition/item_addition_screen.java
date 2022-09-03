package com.example.resturentadminside.Product_Adition;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.resturentadminside.MainActivity;
import com.example.resturentadminside.Model;
import com.example.resturentadminside.R;
import com.example.resturentadminside.SignInAnd_SignUp_Releated_Classes.Login_Activity;
import com.example.resturentadminside.SignInAnd_SignUp_Releated_Classes.Registration_Screen;
import com.example.resturentadminside.SignInAnd_SignUp_Releated_Classes.Upload;
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

public class item_addition_screen extends AppCompatActivity {
    DatabaseReference databaseReference;
    /////////////////////// new work ///////////////////////
    private static final int PICK_IMAGE_REQUEST = 1;
    private CardView mButtonChooseImage;
    private Button mButtonUpload,btn_submit;
    private TextView mTextViewShowUploads;
    private EditText mEditTextFileName;
    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private Uri mImageUri;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private StorageTask mUploadTask;

    EditText edt_item_name,edt_item_price,edt_item_description,edt_item_quantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_addition_screen);
       mButtonChooseImage =(CardView) findViewById(R.id.mButtonChooseImage);
        mImageView = findViewById(R.id.image_view);
        mProgressBar = findViewById(R.id.progress_bar);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mDatabaseRef = FirebaseDatabase.getInstance(Model.getInstance().project_url).getReference("Resturent List");
        intalization_widget();
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
                            Toast.makeText(item_addition_screen.this, "Upload successful", Toast.LENGTH_LONG).show();
                            firebase_item_addition upload = new firebase_item_addition(
                                    taskSnapshot.getDownloadUrl().toString(),
                                   edt_item_name.getText().toString(),
                                    edt_item_price.getText().toString(),
                                    edt_item_description.getText().toString(),
                                    edt_item_quantity.getText().toString()
                            );
                            String uploadId = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(Model.getInstance().data_insertion_type).child(Model.getInstance().resturent_name).
                                    push().setValue(upload);
                            Intent intent = new Intent(item_addition_screen.this, MainActivity.class);
                            startActivity(intent);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(item_addition_screen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
        edt_item_name =(EditText)findViewById(R.id.edt_item_name);
        edt_item_price =(EditText)findViewById(R.id.edt_item_price) ;
        edt_item_description =(EditText)findViewById(R.id.edt_item_description) ;
        edt_item_quantity =(EditText)findViewById(R.id.edt_item_quantity) ;
        btn_submit=(Button)findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(item_addition_screen.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                } else {
                    uploadFile();
                    // worker_data_insertion();
                }

            }
        });
    }
}