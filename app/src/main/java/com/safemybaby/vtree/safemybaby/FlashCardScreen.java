package com.safemybaby.vtree.safemybaby;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by NamLong on 9/22/2016.
 */

public class FlashCardScreen extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_screen);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
