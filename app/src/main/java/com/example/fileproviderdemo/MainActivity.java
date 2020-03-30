package com.example.fileproviderdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;

import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;
import static android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION;
import static androidx.core.content.FileProvider.getUriForFile;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Intent mRequestFileIntent;
    private ParcelFileDescriptor mInputPFD;
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = findViewById(R.id.imageView);

        mRequestFileIntent = getIntent();
        if (mRequestFileIntent != null) {
            Uri returnUri = mRequestFileIntent.getData();
            Log.i(TAG, "returnUri : " + returnUri);
            if (returnUri != null) {
                try {
                    mInputPFD = getContentResolver().openFileDescriptor(returnUri, "r");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Log.e(TAG, "File not found.");
                    return;
                }
                Log.i(TAG, "mInputPFD : " + mInputPFD);
                if (mInputPFD != null) {
                    Bitmap bitmap = BitmapFactory.decodeFileDescriptor(mInputPFD.getFileDescriptor());
                    Log.i(TAG, "bitmap : " + bitmap);
                    if (bitmap != null) {
                        mImageView.setImageBitmap(bitmap);
                    }
                }
            }
        }
    }
}
