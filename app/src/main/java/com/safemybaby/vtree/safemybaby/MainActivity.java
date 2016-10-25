package com.safemybaby.vtree.safemybaby;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {
    @BindView(R.id.btnParrent)
    Button btnParent;
    @BindView(R.id.btnKid) Button btnKid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
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
}
