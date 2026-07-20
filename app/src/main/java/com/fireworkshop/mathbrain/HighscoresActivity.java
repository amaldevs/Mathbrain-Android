package com.fireworkshop.mathbrain;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import android.os.Bundle;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;

public class HighscoresActivity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;
    private AdView adView;
        LinearLayout layout;
        String n1, n2, n3, n4, n5, n, s1, s2, s3,s4,s5, s;
        TextView first,firstscore,second,secondscore,third,thirdscore,fourth,fourthscore,fifth,fifthscore;
        HighScoreRepository repository;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_highscores);
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                displayInterstitial();
                finish();
            }
        });
        admob("ca-app-pub-3981454940982694/5356012364");
        repository = new HighScoreRepository(this);
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

                n1 = repository.getHighScoreName(1);
        s1 = repository.getHighScoreScore(1);
        n2 = repository.getHighScoreName(2);
        s2 = repository.getHighScoreScore(2);
        n3 = repository.getHighScoreName(3);
        s3 = repository.getHighScoreScore(3);
        n4 = repository.getHighScoreName(4);
        s4 = repository.getHighScoreScore(4);
        n5 = repository.getHighScoreName(5);
        s5 = repository.getHighScoreScore(5);
        s=repository.getPendingScore();
        if(!s.equals(""))
        {
                float sf=Float.valueOf(s);
            n=repository.getPreviousName();

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
            repository.setHighScoreName(1, n1);
            repository.setHighScoreName(2, n2);
            repository.setHighScoreName(3, n3);
            repository.setHighScoreName(4, n4);
            repository.setHighScoreName(5, n5);
            repository.setHighScoreScore(1, s1);
            repository.setHighScoreScore(2, s2);
            repository.setHighScoreScore(3, s3);
            repository.setHighScoreScore(4, s4);
            repository.setHighScoreScore(5, s5);
            repository.clearPendingScore();
        }
        first.setText(n1);
        firstscore.setText(s1);
        second.setText(n2);
        secondscore.setText(s2);
        third.setText(n3);
        thirdscore.setText(s3);
        fourth.setText(n4);
        fourthscore.setText(s4);
        fifth.setText(n5);
        fifthscore.setText(s5);

        loadInterstiatialAd("ca-app-pub-3981454940982694/5495613165");
    }

    private void loadInterstiatialAd(String adid) {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(
            this,
            adid,
            adRequest,
            new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(InterstitialAd interstitialAd) {
                    mInterstitialAd = interstitialAd;
                }

                @Override
                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    mInterstitialAd = null;
                }
            });
    }

    public void displayInterstitial() {
        if (mInterstitialAd != null) {
            mInterstitialAd.show(this);
            mInterstitialAd = null;
        }
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
                        return onSupportNavigateUp();
                }
                return super.onOptionsItemSelected(item);
        }

        @Override
        public boolean onSupportNavigateUp() {
                getOnBackPressedDispatcher().onBackPressed();
                return true;
        }

	public void play_click(View view)
	{
		Intent intent=new Intent(this, GameActivity.class);
		startActivity(intent);
		finish();
	}
}
