package com.abhilekh.uaassignment;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class FirstActivity extends Activity {

    private static final String TAG = "FirstActivity";


    private ServiceWorker serviceWorker1 = new ServiceWorker();
    private ServiceWorker serviceWorker2 = new ServiceWorker();
    private Button button1, button2;
    private ImageView imageView1, imageView2;
    public static final String IMAGE_1 = "https://teja8.kuikr.com/images/Badges-Assets/others/ic_email_verified.png";
    public static final String IMAGE_2 = "https://teja8.kuikr.com/images/Badges-Assets/Cars/ic_cars_gold.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchImage1AndSet();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchImage2AndSet();
            }
        });


    }

    private void fetchImage1AndSet() {
        serviceWorker1.addTask(new Task<Bitmap>() {
            @Override
            public Bitmap onExecuteTask() {
                //Fetching image1 through okhttp
                Log.v(TAG, "onExecuteTask: running in thread: "+Thread.currentThread()+" Task name:"+this.getTaskName());
                try {
                    Request request = new Request.Builder().url(IMAGE_1).build();
                    Response response = new OkHttpClient().newCall(request).execute();
                    final Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                    return bitmap;
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            public void onTaskComplete(Bitmap result) {
                Log.v(TAG, "onTaskComplete: running in thread: "+Thread.currentThread()+" Task name:"+this.getTaskName());
                if (result != null) {
                    imageView1.setImageBitmap(result);
                }
            }
        });
    }

    private void fetchImage2AndSet() {
        serviceWorker2.addTask(new Task<Bitmap>() {
            @Override
            public  Bitmap onExecuteTask() {
                Log.v(TAG, "onExecuteTask: running in thread: "+Thread.currentThread()+" Task name:"+this.getTaskName());
                try {
                    //Fetching image1 through okhttp
                    Request request = new Request.Builder().url(IMAGE_2).build();
                    Response response = new OkHttpClient().newCall(request).execute();
                    final Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                    return bitmap;
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            public void onTaskComplete(Bitmap result) {
                Log.v(TAG, "onTaskComplete: running in thread: "+Thread.currentThread().getName()+" Task name:"+this.getTaskName());
                if (result != null) {
                    imageView2.setImageBitmap(result);
                }
            }
        });
    }

}

