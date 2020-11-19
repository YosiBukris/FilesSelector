Specification Heading
=====================
Created by Yossi Bukris on 16/11/2020

## Dependency
Add this to your module's `build.gradle` file (Note: version should match the jitpack badge above)
```
dependencies {
	implementation 'com.github.Amit7474:Dolly-SharedPreferences:1.0.0'
}

##FileSelector
to create file selector from your activity define fs in your attributs -
        *private FilesSelectorActivity fs;
then initial it:
        *this.fs = new FilesSelectorActivity(this);
        *this.fs.openFile(MimsType.type);

mimType is the type of the file you want to get - you can pass empty string to get all the options

you have to overrite the function "onActivityResult" on the activity you create the fileselector
with that function:
        *@Override
        *protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        *    this.fs.onActivityResult(requestCode, resultCode, data);
        *    super.onActivityResult(requestCode, resultCode, data);
        *}
fs is the fileSelector attribute

to load images to your app you should ask for WRITE_EXTERNAL_STORAGE permission, you can use this method:
    *private void requestPermission() {
    *    if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
    *        Toast.makeText(this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
    *    } else {
    *       ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    *    }
    *}
