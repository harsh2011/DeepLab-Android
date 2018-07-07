package com.harsh.deeplabandroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private int PICK_IMAGE_REQUEST = 1;

    private ImageView imageView;
    private Button btn_select;

    private Mat imga;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        btn_select = (Button) findViewById(R.id.btn_select);



        if(OpenCVLoader.initDebug()){
            System.out.println("Loaded !!!!");
        }
        else{
            System.out.println("not loaded :(");
        }

        imga =  new Mat();
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageSelection();


            }
        });

    }



    public void ImageSelection(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));
                Bitmap bmp32 = bitmap.copy(Bitmap.Config.ARGB_8888, true);

                Utils.bitmapToMat(bmp32, imga);

                System.out.println(imga);

                for(int i =0;i<imga.height();i++){
                    for(int j=0; j<imga.width();j++){
                       double value[]  = imga.get(i,j);
                        System.out.println(value[0]+" ");

                    }
                    System.out.println("\n");

                }
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
