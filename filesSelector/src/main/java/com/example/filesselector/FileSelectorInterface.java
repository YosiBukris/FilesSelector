package com.example.filesselector;

import android.app.Activity;
import android.content.Intent;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import java.util.List;

public interface FileSelectorInterface {
    enum callBackFunction {updalodFile,returnFile, addToList,addPictureToImageView};

    public void setMyActivity(Activity myActivity);

    public void openFileToImageView(MimsType mimeType,ImageView imageView);

    public void openFileToUpload(MimsType mimeType, String urlString, String title, String description);

    public void openFileToList(MimsType mimeType, List list);

    public void getFileUri(MimsType mimeType,String outPutString);

    public void onActivityResult(int requestCode, int resultCode, Intent data);
}
