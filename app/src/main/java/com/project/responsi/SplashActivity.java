package com.project.responsi;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import org.json.JSONArray;
import org.json.JSONObject;

import static com.project.responsi.config.Config.BASE_URL;
import static com.project.responsi.config.Config.KASUS_COVID;
import static com.project.responsi.config.Config.RUJUKAN_COVID;

public class SplashActivity extends Activity {
    //This is the time it will take for the splash screen to be displayed
    private static int SPLASH_TIME_OUT = 20000;
    dbHelp helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        AndroidNetworking.initialize(SplashActivity.this);
        helper = new dbHelp(this);
        helper.deleteKC();
        helper.deleteRC();
        getDataKC();
        getDataRC();

        //This is where we change our app name font to blacklist font
        Typeface typeface = ResourcesCompat.getFont(this, R.font.blacklist);

        TextView appname= findViewById(R.id.appname);
        appname.setTypeface(typeface);

        // We use the Yoyo to make our app logo to bounce app and down.
        //There is a lot of Attension Techniques styles
        // example Flash, Pulse, RubberBand, Shake, Swing, Wobble, Bounce, Tada, StandUp, Wave.
        // Your can change the techniques to fit your liking.
        YoYo.with(Techniques.Bounce)
                .duration(7000)
                .playOn(findViewById(R.id.logo));

        //This is where we make our app name fade in as it moves up
        // There are other Fade Techniques too
        //example FadeIn, FadeInUp, FadeInDown, FadeInLeft, FadeInRight
        //FadeOut, FadeOutDown, FadeOutLeft, FadeOutRight, FadeOutUp
        YoYo.with(Techniques.FadeInUp)
                .duration(5000)
                .playOn(findViewById(R.id.appname));

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                startActivity(new Intent(com.project.responsi.SplashActivity.this,MainActivity.class));
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    public void getDataKC(){
        String url = KASUS_COVID;
        Log.e("cek url", url);
        AndroidNetworking.get(url)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            JSONObject jsondata = response.getJSONObject("data");
                            JSONArray jsonarray = jsondata.getJSONArray("content");
                            for (int a=0; a<jsonarray.length(); a++){
                                JSONObject c = jsonarray.getJSONObject(a);
                                ContentValues values = new ContentValues();
                                values.put("TANGGAL", c.getString("tanggal"));
                                values.put("SEMBUH", c.getString("confirmation_selesai"));
                                values.put("MENINGGAL", c.getString("confirmation_meninggal"));
                                values.put("KONFIRMASI", c.getString("confirmation_diisolasi"));

                                helper.insertKC(values);

                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        Log.e("FragmentPertama", "onError: "  + error.getErrorDetail() );
                    }
                });
    }

    public void getDataRC(){
        String url = RUJUKAN_COVID;
        Log.e("cek url", url);
        AndroidNetworking.get(url)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            JSONArray data = response.getJSONArray("data");
                            for (int a=0; a<data.length(); a++){
                                JSONObject c = data.getJSONObject(a);
                                ContentValues values = new ContentValues();
                                values.put("NAMA", c.getString("nama"));
                                values.put("ALAMAT", c.getString("alamat"));
                                values.put("LONGITUDE", c.getString("longitude"));
                                values.put("LATITUDE", c.getString("latitude"));

                                helper.insertRC(values);

                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        Log.e("FragmentSeason", "onError: "  + error.getErrorDetail() );
                    }
                });
    }
}