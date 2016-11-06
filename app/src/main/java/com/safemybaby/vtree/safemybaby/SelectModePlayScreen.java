package com.safemybaby.vtree.safemybaby;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SelectModePlayScreen extends AppCompatActivity {

    @BindView(R.id.btnFlashcard)
    Button btnFlashCard;
    @BindView(R.id.btnQuestion)
    Button btnQuestion;
    Unbinder unbinder;
    boolean doubleBackToExitPressedOnce = false;
    @OnClick(R.id.btnBackSelectModePlay)
    public void backToPreScreen(){
        Intent  i = new Intent(SelectModePlayScreen.this,MainActivity.class);
        startActivity(i);
        finish();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_mode_play);
        ButterKnife.bind(this);
        // Opacity button flashcard
        btnFlashCard.getBackground().setAlpha(255);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);

    }
    @OnClick(R.id.btnQuestion)
    public void startPlay(){
        Intent in = new Intent(SelectModePlayScreen.this,QuestionCateScreen.class);
        startActivity(in);
        finish();
    }
    @Override
    public void onResume(){
        super.onResume();
        this. overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
       // unbinder.unbind();
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
