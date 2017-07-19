package com.octadev.demo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.octadev.downloadunzip.DowloadUnzip;

public class MainActivity extends AppCompatActivity implements DowloadUnzip.DownloadListener{


    ProgressBar progressBar;
    DowloadUnzip dowloadUnzip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.READ_EXTERNAL_STORAGE},100);

        }
        progressBar=(ProgressBar)findViewById(R.id.progress);
        dowloadUnzip=new DowloadUnzip(Environment.getExternalStorageDirectory() + "/temp");
        dowloadUnzip.setDownloadListener(this);
    }


    public void download(View v){
       dowloadUnzip.dowload("https://firebasestorage.googleapis.com/v0/b/salek-af0b0.appspot.com/o/sons%2Fsons.zip?alt=media&token=7d7d12d7-24d0-475d-b63c-4b313f24edc3");
    }

    @Override
    public void DownloadProgress(int event, int position) {
        if(event==3){

           //end of task
            Toast.makeText(this,"download and unzip finished",Toast.LENGTH_LONG).show();

        }else{
            progressBar.setProgress(position);
        }
    }
}
