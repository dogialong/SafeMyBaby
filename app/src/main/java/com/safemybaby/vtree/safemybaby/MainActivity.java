package com.safemybaby.vtree.safemybaby;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends Activity {
    @BindView(R.id.btnParrent)
    Button btnParent;
    @BindView(R.id.btnKid) Button btnKid;
    static MainActivity mainActivity;
    Unbinder unbinder;
    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this. overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);

    }
    @Override
    public void onResume(){
        super.onResume();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        System.gc();
    }

    @OnClick(R.id.btnParrent)
    public void goToHanbookCate(View v) {
        Intent in = new Intent(MainActivity.this,HandbookCateScreen.class);
        startActivity(in);
        finish();
    }
    @OnClick(R.id.btnKid)
    public void goToSelectModePlay(View v) {
        Intent in = new Intent(MainActivity.this,SelectModePlayScreen.class);
        startActivity(in);
        finish();
    }

    public static MainActivity getInstance(){
        return   mainActivity;
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
