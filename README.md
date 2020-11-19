### FilesSelector
![GitHub](https://img.shields.io/github/license/YosiBukris/FilesSelector)
[![](https://jitpack.io/v/YosiBukris/FilesSelector.svg)](https://jitpack.io/#YosiBukris/FilesSelector)
[![API](https://img.shields.io/badge/API-18%2B-green.svg?style=flat)]()
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Files%20Selector%20-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/7577)
![GitHub repo size](https://img.shields.io/github/repo-size/YosiBukris/FilesSelector)
```
Files selector libary
Use this libary to use files from you device storage
```
## Setup

Step 1. Add it in your root build.gradle at the end of repositories:
```
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

Step 2. Add the dependency:
```
dependencies {
	implementation 'com.github.Amit7474:Dolly-SharedPreferences:1.0.0'
}
```

## Usage
###### StepProgress Constructor:
```java

// To create file selector from your activity define fs in your attributs -
        private FilesSelectorActivity fs;
	
// Then initial it:
        this.fs = new FilesSelectorActivity(this);
        this.fs.openFile(MimsType.type);

// mimType is the type of the file you want to get

// you have to overrite the function "onActivityResult" on the activity you create the fileselector with that function:
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            this.fs.onActivityResult(requestCode, resultCode, data);
            super.onActivityResult(requestCode, resultCode, data);
        }

// to load images to your app you should ask for WRITE_EXTERNAL_STORAGE permission, you can use this method:
    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
           ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }
    
// to upload file you have to send an actual working "post" method url of your server
 ```
    
## API
There is 4 functions you can use:
 1. openFileToImageView(MimsType mimeType, ImageView imageView)
 
 2. openFileToList(MimsType mimeType, List list)
 
 3. openFileToUpload(MimsType mimeType, String urlString, String title, String description)
 
 4. getFileUri(MimsType mimeType, String outPutString)
 
## License
```
Copyright (C) 2020, Yossi Bukris

