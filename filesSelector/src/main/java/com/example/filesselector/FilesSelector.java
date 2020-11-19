package com.example.filesselector;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.List;

public class FilesSelector implements FileSelectorInterface {
    private final int CHOOSE_FILE_REQUESTCODE = 100;
    private static FilesSelector instance;
    private Activity myActivity;
    private ImageView imageView;
    private String myType;
    private String urlString;
    private String fileDescription;
    private String fileTitle;
    private String outPutString;
    private List list;
    private callBackFunction callBack;

    public FilesSelector(Activity mActivity) {
        this.setMyActivity(mActivity);

    }

    public static synchronized FilesSelector getInstance(Activity activity) {
        if (instance == null) {
            instance = new FilesSelector(activity); //getApplicationContext to avoid memory leaks
        }
        return instance;
    }

    public void setMyActivity(Activity myActivity) {
        this.myActivity = myActivity;
    }

    public void openFile(MimsType mimeType, @Nullable callBackFunction callBack) {
        this.myType = mimeType.getType();
        if (callBack != null)
            this.callBack = callBack;

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType(myType);
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        // special intent for Samsung file manager
        Intent sIntent = new Intent("com.sec.android.app.myfiles.PICK_DATA");
        // if you want any file type, you can skip next line
        sIntent.putExtra("CONTENT_TYPE", myType);
        sIntent.addCategory(Intent.CATEGORY_DEFAULT);

        Intent chooserIntent;
        if (this.myActivity.getPackageManager().resolveActivity(sIntent, 0) != null) {
            // it is device with Samsung file manager
            chooserIntent = Intent.createChooser(sIntent, "Open file");
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{intent});
        } else {
            chooserIntent = Intent.createChooser(intent, "Open file");
        }

        try {
            this.myActivity.startActivityForResult(chooserIntent, CHOOSE_FILE_REQUESTCODE);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this.myActivity.getApplicationContext(), "No suitable File Manager was found.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void openFileToImageView(MimsType mimeType, ImageView imageView) {
        this.imageView = imageView;
        this.openFile(mimeType, callBackFunction.addPictureToImageView);
    }

    @Override
    public void openFileToList(MimsType mimeType, List list) {
        this.list = list;
        this.openFile(mimeType, callBackFunction.addToList);
    }

    @Override
    public void openFileToUpload(MimsType mimeType, String urlString, String title, String description) {
        this.urlString = urlString;
        this.fileTitle = title;
        this.fileDescription = description;
        this.openFile(mimeType, callBackFunction.updalodFile);
    }

    @Override
    public void getFileUri(MimsType mimeType, String outPutString) {
        this.list = list;
        this.outPutString = outPutString;
        this.openFile(mimeType, callBackFunction.returnFile);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CHOOSE_FILE_REQUESTCODE) {
            Uri uriUploadPath = data.getData();
            if (this.callBack != null) {
                switch (this.callBack) {
                    case updalodFile:
                        this.uploadFile(uriUploadPath);
                        break;
                    case returnFile:
                            this.returnFile(uriUploadPath);
                        break;
                    case addToList:
                        this.addToList(uriUploadPath);
                        break;
                    case addPictureToImageView:
                        if (this.imageView != null) {
                            this.addPictureToImageView(uriUploadPath);
                        } else
                            Toast.makeText(this.myActivity.getApplicationContext(), "Use 'OpenWithImage'", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + this.callBack);
                }
            }
            return;
        }
    }

    private void addPictureToImageView(Uri file) {
        try {
            Bitmap myBitmap = MediaStore.Images.Media.getBitmap(this.myActivity.getContentResolver(), file);
            this.imageView.setImageBitmap(myBitmap);
        } catch (Exception e) {
            Log.d("EXCEPTION LOAD PIC", "addPictureToImageView: " + e.getMessage());
        }
    }

    private void returnFile(Uri filePath) {
        this.outPutString = filePath.getPath();
    }

    private void addToList(Uri file) {
        this.list.add(file);
        return;
    }

    private void uploadFile(Uri file) {
        FileInputStream fstrm = null;
        try {
            fstrm = new FileInputStream(file.getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // Set your server page url (and the file title/description)
        HttpFileUpload hfu = new HttpFileUpload(this.urlString, this.fileTitle, this.fileDescription);
        hfu.Send_Now(fstrm);
    }
}