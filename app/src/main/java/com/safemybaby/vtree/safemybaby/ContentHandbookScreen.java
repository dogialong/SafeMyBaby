package com.safemybaby.vtree.safemybaby;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ContentHandbookScreen extends Activity {
    @BindView(R.id.img_tile_handbook)
    ImageView imgTileHandbook;
    @BindView(R.id.txt_tile_cate)
    TextView tvTileCate;
    @BindView(R.id.txt_content_cate)
    TextView tvContentCate;
    Typeface type;
    Unbinder unbinder;
    static ContentHandbookScreen contentHandbookScreen;
    boolean doubleBackToExitPressedOnce = false;
    @OnClick(R.id.btnBackCOntentHandbook)
    public void backToPreScreen(){
        Intent  i = new Intent(ContentHandbookScreen.this,DetailHandbookScreen.class);
         startActivity(i);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_handbook);
        ButterKnife.bind(this);

        String img = (String) getIntent().getSerializableExtra("linkimg");
        String content = (String) getIntent().getSerializableExtra("content");
        String name = (String)getIntent().getSerializableExtra("name");
        loadData(img,content,name);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }
    @Override
    public void onResume(){
        super.onResume();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }
    private void loadData(String url,String text,String name){
        type = Typeface.createFromAsset(getAssets(),"fonts/textfont.ttf");
        Picasso.with(this).load(url).resize(720,560)
                .error(R.mipmap.logo)
                .into(imgTileHandbook);
        tvContentCate.setText(text);
        tvTileCate.setText(name);

        tvContentCate.setTypeface(type);
        tvTileCate.setTypeface(type);
    }

    public static ContentHandbookScreen getInstance(){
        return   contentHandbookScreen;
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
    @Override
    protected void onDestroy() {
        super.onDestroy();
       // unbinder.unbind();
    }
}
