package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.filesselector.FilesSelector;
import com.example.filesselector.MimsType;


public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 250;
    public FilesSelector fs;
    private ImageView myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.requestPermission();
        this.myImage = findViewById(R.id.imageView);
        this.fs = new FilesSelector(this);
//        this.fs.openFileToImageView(MimsType.imageType, this.myImage);
        this.fs.openFileToUpload(MimsType.anyType,"http://192.168.0.6:2000/api/files/","test","test");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.fs.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

}