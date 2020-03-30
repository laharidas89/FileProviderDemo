package com.example.fileproviderdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;

import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;
import static android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION;
import static androidx.core.content.FileProvider.getUriForFile;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String AUTHORITY = "com.example.fileproviderdemo.provider";
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext= getApplicationContext();
    }

    public void onButtonClicked(View view){
        Log.i("Info","imageClicked");
        File imagePath = new File("/data/data/com.example.mysampleapplication/app_imageDir/myTestImage.jpg");
        //File newFile = new File(imagePath, "myTestImage.jpg");
        Uri contentUri = getUriForFile(mContext, AUTHORITY, imagePath);
        Log.i(TAG, "contentUri: " + contentUri);
        /*Intent myIntent = new Intent(Intent.ACTION_VIEW);
        myIntent.setDataAndType(contentUri, mimeType);
        myIntent.setFlags(FLAG_GRANT_READ_URI_PERMISSION | FLAG_GRANT_WRITE_URI_PERMISSION);
        mContext.startActivity(myIntent);*/
    }
}
