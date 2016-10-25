package com.safemybaby.vtree.safemybaby;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.eftimoff.androidplayer.Player;
import com.eftimoff.androidplayer.actions.property.PropertyAction;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SplashScreenActivity extends Activity {
    @BindView(R.id.imgLogoSplash) View imgLogo;
    @BindView(R.id.imgTextSplash) View imgText;
    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(SplashScreenActivity.this);
        animateSampleFour( imgLogo, imgText);
        //animateSampleFive(activityMainProfileName);
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    private void animateSampleFour(View imglogo, View imgtext) {
        final PropertyAction imglogo1 =  PropertyAction.newPropertyAction(imglogo).interpolator(new DecelerateInterpolator()).translationY(-200).duration(1000).alpha(0.4f).build();
        final PropertyAction imgtext1 =   PropertyAction.newPropertyAction(imgtext).duration(1500).alpha(0f).build();
        Player.init().
        animate(imglogo1).
                then().
                animate(imgtext1).
                play();
    }

}
