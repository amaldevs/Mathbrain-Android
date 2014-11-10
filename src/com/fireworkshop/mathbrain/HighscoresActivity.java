package com.fireworkshop.mathbrain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.google.android.gms.ads.*;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.content.Context;
import android.content.Intent;

public class HighscoresActivity extends Activity {

    private InterstitialAd interstitial;
    private AdView adView;
	LinearLayout layout;
	String n1, n2, n3, n4, n5, n, s1, s2, s3,s4,s5, s;
	TextView first,firstscore,second,secondscore,third,thirdscore,fourth,fourthscore,fifth,fifthscore;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_highscores);
        admob("ca-app-pub-3981454940982694/5356012364");
        first=(TextView)findViewById(R.id.first);
		firstscore=(TextView)findViewById(R.id.firstscore);
		second=(TextView)findViewById(R.id.second);
		secondscore=(TextView)findViewById(R.id.secondscore);
		third=(TextView)findViewById(R.id.third);
		thirdscore=(TextView)findViewById(R.id.thirdscore);
		fourth=(TextView)findViewById(R.id.fourth);
		fourthscore=(TextView)findViewById(R.id.fourthscore);
		fifth=(TextView)findViewById(R.id.fifth);
		fifthscore=(TextView)findViewById(R.id.fifthscore);
		
		n1 = rfile("first");
        s1 = rfile("firstscore");
        n2 = rfile("second");
        s2 = rfile("secondscore");
        n3 = rfile("third");
        s3 = rfile("thirdscore");
        n4 = rfile("fourth");
        s4 = rfile("fourthscore");
        n5 = rfile("fifth");
        s5 = rfile("fifthscore");
        s=rfile("newscore");
        if(s!="")
        {
        	float sf=Float.valueOf(s);
            n=rfile("prevname");

            if (s1 == "" || (sf < Float.valueOf(s1)))
            {
                s5 = s4;
                n5 = n4;
                s4 = s3;
                n4 = n3;
                s3 = s2;
                n3 = n2;
                s2 = s1;
                n2 = n1;
                s1 = s;
                n1 = n;
            }
            else if (s2 == "" || (sf < Float.valueOf(s2)))
            {
                s5 = s4;
                n5 = n4;
                s4 = s3;
                n4 = n3;
                s3 = s2;
                n3 = n2;
                s2 = s;
                n2 = n;
            }
            else if (s3 == "" || (sf < Float.valueOf(s3)))
            {
                s5 = s4;
                n5 = n4;
                s4 = s3;
                n4 = n3;
                s3 = s;
                n3 = n;
            }
            else if (s4 == "" || (sf < Float.valueOf(s4)))
            {
                s5 = s4;
                n5 = n4;
                s4 = s;
                n4 = n;
            }
            else if (s5 == "" || (sf < Float.valueOf(s5)))
            {
                s5 = s;
                n5 = n;
            }
            wfile("first", n1);
            wfile("second", n2);
            wfile("third", n3);
            wfile("fourth", n4);
            wfile("fifth", n5);
            wfile("firstscore", s1);
            wfile("secondscore", s2);
            wfile("thirdscore", s3);
            wfile("fourthscore", s4);
            wfile("fifthscore", s5);
            wfile("newscore", "");
        }
        first.setText(n1);
        firstscore.setText(rfile("firstscore"));
        second.setText(n2);
        secondscore.setText(rfile("secondscore"));
        third.setText(n3);
        thirdscore.setText(rfile("thirdscore"));
        fourth.setText(n4);
        fourthscore.setText(rfile("fourthscore"));
        fifth.setText(n5);
        fifthscore.setText(rfile("fifthscore"));

        loadInterstiatialAd("ca-app-pub-3981454940982694/5495613165");
    }

    private void loadInterstiatialAd(String adid) {
        interstitial = new InterstitialAd(this);
        interstitial.setAdUnitId(adid);

        AdRequest adRequest = new AdRequest.Builder().build();

        interstitial.loadAd(adRequest);
    }

    public void displayInterstitial() {
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }

    @Override
    public void onBackPressed() {
        displayInterstitial();
        super.onBackPressed();
    }


    private void admob(String adid) {
        adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(adid);
        layout = (LinearLayout) findViewById(R.id.adlayout);
        layout.addView(adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }


	String rfile(String filename)
    {
		String read;
        FileInputStream fis=null;
        byte[] b;
            try {
    			fis=openFileInput(filename);
    			b=new byte[15];
    			if(fis.read(b)!=-1)
    			{
    				read=new String(b);
    				read=read.trim();
    				return read;
    			}
    			else
    				return "";
    		} catch (FileNotFoundException e) {
    		} catch (IOException e) {
    		}
    		finally{
    			if(fis!=null)
    				try {
    					fis.close();
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    		}
        return "";
    }
    void wfile(String filename,String write)
    {
    	FileOutputStream fos=null;
        try {
			fos=openFileOutput(filename, Context.MODE_PRIVATE);
			fos.write(write.trim().getBytes());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(fos!=null)
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}        
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.highscores, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void play_click(View view)
	{
		Intent intent=new Intent(this, GameActivity.class);
		startActivity(intent);
		finish();
	}
}
