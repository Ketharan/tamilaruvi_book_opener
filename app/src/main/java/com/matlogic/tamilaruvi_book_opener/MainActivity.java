package com.matlogic.tamilaruvi_book_opener;

import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

public class MainActivity extends AppCompatActivity {
    private Button scanButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        hideSystemUI();

        setContentView(R.layout.activity_main);

        scanButton = (Button) findViewById(R.id.button);

        scanButton.setOnTouchListener(fingerScan);
        loadThumpImage();
    }

    private void scanAcceptImage(){
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.ezgif);

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();


        SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.accept_view);
        draweeView.setImageURI(uri);
        draweeView.setController(controller);
    }

    private void loadScannerImage(){
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.security_bnr_fp);

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();


        SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.scanner_view);
        draweeView.setImageURI(uri);
        draweeView.setController(controller);
    }

    private void loadThumpImage(){
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.finger_print);

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();


        SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.my_image_view);
        draweeView.setImageURI(uri);
        draweeView.setController(controller);
    }

    private void hideSystemUI() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        ActionBar actionBar = getSupportActionBar();
        //actionBar.setTitle(s);
        actionBar.hide();



    }

    private View.OnTouchListener fingerScan = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {


            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    loadScannerImage();
                    Toast.makeText(getApplicationContext(),"Button down",Toast.LENGTH_LONG).show();
                    break;
//                case MotionEvent.ACTION_MOVE:
//                    Log.i("TAG", "moving: (" + x + ", " + y + ")");
//                    break;
                case MotionEvent.ACTION_UP:
                    scanAcceptImage();
                    break;
            }

            return true;
        }
    };

}
