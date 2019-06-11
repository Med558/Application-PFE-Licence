package com.theangel.ericsson.ericsson.activities;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.CircleProgress;
import com.theangel.ericsson.R;

public class SplashActivity extends AppCompatActivity {

    final Context context = this;
    private CircleProgress circleProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        circleProgress = (CircleProgress)findViewById(R.id.circle_progress);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#660099"));
        }

        if (isNetworkAvailable(getApplicationContext())) {
            PropertyValuesHolder donutAlphaProperty = PropertyValuesHolder.ofFloat("alpha", 0f, 1f);
            PropertyValuesHolder donutProgressProperty = PropertyValuesHolder.ofInt("progress", 0, 100);
            ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(circleProgress, donutAlphaProperty, donutProgressProperty);
            animator.setDuration(3000);
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.start();
            Thread timerThread = new Thread(){
                public void run(){
                    try{
                        sleep(3000);

                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }finally{
                        Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }
                }
            };
            timerThread.start();
        }else {
            // your code.
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context);

            // set title
            alertDialogBuilder.setTitle(R.string.connexion_internet);

            // set dialog message
            alertDialogBuilder

                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, close
                            // current activity
                            SplashActivity.this.finish();
                        }
                    });
//                    .setNegativeButton(R.string.non, new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            // if this button is clicked, just close
//                            // the dialog box and do nothing
//                            dialog.cancel();
//
//                        }
//                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
        }

    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
