package com.fireworkshop.mathbrain;

import java.io.File;
import com.google.ads.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	private AdView adView;
	LinearLayout layout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if(file("fifth", "Job"))
            if(file("fifthscore", "197.27"))
                if(file("fourth", "Vikie"))
                    if(file("fourthscore", "105.50"))
                        if(file("third", "Luis"))
                            if(file("thirdscore", "88.73"))
                                if(file("second", "Li"))
                                    if(file("secondscore", "74.41"))
                                        if(file("first", "Kidu"))
                                            if (file("firstscore", "60.15"))
                                            { }
		admob("a1525b78d6caaf4");
	}
	private void admob(String adid)
	{
		adView = new AdView(this, AdSize.BANNER, adid);
		layout = (LinearLayout)findViewById(R.id.adlayout);
		layout.addView(adView);
		AdRequest adRequest = new AdRequest();
		adView.loadAd(adRequest);
	}
	
	@Override
	  public void onDestroy() {
	    if (adView != null) {
	      adView.destroy();
	    }
	    super.onDestroy();
	  }
	
	boolean file(String filename,String w)
    {
        boolean status;
        String write=w;
        File file = getBaseContext().getFileStreamPath(filename);
        if(!file.exists()){
            status= true;
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
        else{
            status = false;
        }   
        return status;
    }
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void playClicked(View view)
	{
		Intent intent=new Intent(this, GameActivity.class);
		startActivity(intent);
	}
	
	public void hsClicked(View view)
	{
		Intent intent=new Intent(this, HighscoresActivity.class);
		startActivity(intent);
	}
	
	public void helpClicked(View view)
	{
		Intent intent=new Intent(this, HelpActivity.class);
		startActivity(intent);
	}
	
	public void creditsClicked(View view)
	{
		Intent intent=new Intent(this, CreditsActivity.class);
		startActivity(intent);
	}
	
}
