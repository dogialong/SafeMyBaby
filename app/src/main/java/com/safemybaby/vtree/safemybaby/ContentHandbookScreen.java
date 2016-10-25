package com.safemybaby.vtree.safemybaby;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContentHandbookScreen extends Activity {
    @BindView(R.id.img_tile_handbook)
    ImageView imgTileHandbook;
    @BindView(R.id.txt_tile_cate)
    TextView tvTileCate;
    @BindView(R.id.txt_content_cate)
    TextView tvContentCate;
    Typeface type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_handbook);
        ButterKnife.bind(this);

        String img = (String) getIntent().getSerializableExtra("linkimg");
        String content = (String) getIntent().getSerializableExtra("content");
        String name = (String)getIntent().getSerializableExtra("name");
//        Toast.makeText(getApplicationContext(), img+"", Toast.LENGTH_SHORT).show();
        loadData(img,content,name);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
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
}
