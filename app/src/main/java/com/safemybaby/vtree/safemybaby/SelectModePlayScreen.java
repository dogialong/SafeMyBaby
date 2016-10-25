package com.safemybaby.vtree.safemybaby;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectModePlayScreen extends AppCompatActivity {

    @BindView(R.id.btnFlashcard)
    Button btnFlashCard;
    @BindView(R.id.btnQuestion)
    Button btnQuestion;

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
    }
}
