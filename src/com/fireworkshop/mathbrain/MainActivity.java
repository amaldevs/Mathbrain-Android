package com.fireworkshop.mathbrain;

import com.google.android.gms.ads.*;

import com.fireworkshop.mathbrain.HighScoreRepository;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

	private AdView adView;
	LinearLayout layout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HighScoreRepository repository = new HighScoreRepository(this);
        repository.ensureDefaults();

        admob("ca-app-pub-3981454940982694/6972346366");
    }

    private void admob(String adid) {
        adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(adid);
        layout = (LinearLayout)findViewById(R.id.adlayout);
		layout.addView(adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
	}

    @Override
    public void onDestroy() {
	    if (adView != null) {
	      adView.destroy();
	    }
	    super.onDestroy();
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
