package com.safemybaby.vtree.safemybaby;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.safemybaby.vtree.safemybaby.app.AppController;
import com.safemybaby.vtree.safemybaby.model.QuestionPlay;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import utils.Contants;

public class QuestionScreen extends Activity {
    private String TAG = "QuestionScreen";
    List<QuestionPlay.DataBean> listQuestionPlay ;
    List<QuestionPlay.DataBean> listQuestionPlay2;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    int i = 0;
    int diem = 0;
    Typeface type;
    @BindView(R.id.imgQuestion)
    ImageView imgQuestion;
    @BindView(R.id.txtTileQuestion)
    TextView tvTitleQuestion;
    Unbinder unbinder;
    boolean doubleBackToExitPressedOnce = false;
    @OnClick(R.id.btnBackQuestionScreen)
    public void backToPreScreen(){
        Intent i = new Intent(QuestionScreen.this,QuestionCateScreen.class);
        startActivity(i);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_screen);
        ButterKnife.bind(this);
        listQuestionPlay = new ArrayList<>();
        listQuestionPlay2 = new ArrayList<>()  ;
        //getAllQues(Contants.URL_QUESTIONPLAY);
        btn5_run();
        type = Typeface.createFromAsset(getAssets(),"fonts/textfont.ttf");
        tvTitleQuestion.setTypeface(type);
        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }
    @Override
    public void onResume(){
        super.onResume();
        this. overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }

    /**
     * load data
     */
    public List<QuestionPlay.DataBean> getAllQues(String url) {
        JsonObjectRequest req = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        //get data
                        JSONObject obj = response;
                        JSONArray array = null;
                        try {
                            array = obj.getJSONArray("data");
                            for(int i = 0 ; i < array.length() ; i++){
                                listQuestionPlay.add(new QuestionPlay.DataBean(array.getJSONObject(i).getString("result"),
                                        array.getJSONObject(i).getString("link_image"),array.getJSONObject(i).getString("link_explain")));
//
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req,
                tag_json_obj);
        // Cancelling request
        AppController.getInstance().getRequestQueue().cancelAll(tag_json_arry);
        Log.d(TAG, "getAllCate: " + listQuestionPlay.size());
        return listQuestionPlay;
    }
    private String _getCurrentTimestamp() {
        return new SimpleDateFormat("k:m:s:S a").format(new Date());
    }
    private void showDialog(){
        // custom dialog
        final Dialog dialog = new Dialog(QuestionScreen.this);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.customdialog);


        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);

        dialog.getWindow().setBackgroundDrawableResource(R.color.colorYellow);
        ImageView image = (ImageView)dialog.findViewById(R.id.imgViewDialog);
       // dialog.getWindow().setBackgroundDrawableResource(R.color.colorYellow);
          Picasso.with(getApplicationContext()).load(listQuestionPlay.get(i).getLink_explain())
                .error(R.mipmap.ic_launcher)
                .into(image);

        dialog.findViewById(R.id.positive_button).setOnClickListener(new OnClickListener() {

            @Override

            public void onClick(View v) {

                dialog.dismiss();
                i++;
                Picasso.with(getApplicationContext()).load(listQuestionPlay.get(i).getLink_image())
                        .error(R.mipmap.ic_launcher)
                        .into(imgQuestion);
            }

        });

        // Close

        dialog.findViewById(R.id.close_button).setOnClickListener(new OnClickListener() {

            @Override

            public void onClick(View v) {

                dialog.dismiss();
                if(i < listQuestionPlay.size()-1){
                    i++;
                    Picasso.with(getApplicationContext()).load(listQuestionPlay.get(i).getLink_image())
                            .error(R.mipmap.ic_launcher)
                            .into(imgQuestion);
                }  else {
                    showDialog2();
                }

            }

        });
        dialog.show();
    }
    private void showDialog2(){
        // custom dialog
        final Dialog dialog = new Dialog(QuestionScreen.this);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogend);
        TextView tv = (TextView)dialog.findViewById(R.id.txtEnd);
        tv.setText("Chuc mung ban da tra loi xong. Ban duoc " + diem +" diem.");
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);

        dialog.getWindow().setBackgroundDrawableResource(R.color.colorYellow);

        dialog.findViewById(R.id.positive_button_end).setOnClickListener(new OnClickListener() {

            @Override

            public void onClick(View v) {

            }

        });

        // Close

        dialog.findViewById(R.id.close_button).setOnClickListener(new OnClickListener() {

            @Override

            public void onClick(View v) {

                dialog.dismiss();

            }

        });
        dialog.show();
    }
    @OnClick(R.id.btnNo1)
    public void btn1_RunSingleTaskAfter2s() {
        String result = listQuestionPlay.get(i).getResult();
        showDialog();
        if(result.equals("1")){
           diem++;
        }


    }
    @OnClick(R.id.btnYes1)
    public void btnYesClick(){
        String result = listQuestionPlay.get(i).getResult();
//        if(result.equals("0") && i < listQuestionPlay.size()-1){
//            i++;
//                Picasso.with(getApplicationContext()).load(listQuestionPlay.get(i).getLink_image())
//                        .error(R.mipmap.ic_launcher)
//                        .into(imgQuestion);
//        }
        if(result.equals("0")){
            diem++;
        }
        showDialog();
    }
    public void btn5_run(){
        Observable.just(getAllQues(Contants.URL_QUESTIONPLAY))
                .doOnNext(new Action1<List<QuestionPlay.DataBean>>() {
                    @Override
                    public void call(List<QuestionPlay.DataBean> dataBeen) {
                    }
                })
                .delay(1,TimeUnit.SECONDS)
                .doOnNext(new Action1<List<QuestionPlay.DataBean>>() {
                    @Override
                    public void call(List<QuestionPlay.DataBean> dataBeen) {
                        _log2(" after ");
                    }
                })
                .subscribe(new Subscriber<List<QuestionPlay.DataBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(List<QuestionPlay.DataBean> dataBeen) {
                    }
                }) ;
    }
    private void _log(final String logMsg) {
        // You can only do below stuff on main thread.
        new Handler(getMainLooper()).post(new Runnable() {

            @Override
            public void run() {
                getAllQues(Contants.URL_QUESTIONPLAY);
            }
        });
    }

    private void _log2(final String a) {
        // You can only do below stuff on main thread.
        new Handler(getMainLooper()).post(new Runnable() {
            @Override
            public void run() {

                    Picasso.with(getApplicationContext()).load(listQuestionPlay.get(i).getLink_image())
                            .error(R.mipmap.ic_launcher)
                            .into(imgQuestion);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
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
