package com.fireworkshop.mathbrain;

import java.util.Random;

import com.google.android.gms.ads.*;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.content.Intent;


public class GameActivity extends Activity {
	
	private AdView adView;
	LinearLayout layout;
	
	public static final String TotalTime = "com.fireworkshop.mathbrain.totaltime";
	public static final String Wrong = "com.fireworkshop.mathbrain.wrong";
	private Handler handler= new Handler();
	int i,finished,result;
	long ct;
	String total;
	Random r;
	TextView time,indicator,answer,question,qno,wrong;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
        admob("ca-app-pub-3981454940982694/2402545967");
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		r=new Random();
		time=(TextView) findViewById(R.id.time);
		indicator=(TextView) findViewById(R.id.indicator);
		answer=(TextView) findViewById(R.id.answer);
		question=(TextView) findViewById(R.id.question);
		qno=(TextView) findViewById(R.id.questionNo);
		wrong=(TextView) findViewById(R.id.wrong);
		start();
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
	  public void onDestroy() {
	    if (adView != null) {
	      adView.destroy();
	    }
	    super.onDestroy();
	  }
	
	
	private void start()
	{
		handler.removeCallbacks(runnable);
		i=1;
		time.setText("0");
		answer.setText("");
        question.setText("");
        qno.setText("0");
        wrong.setText("0");
        finished = 1;
        handler.postDelayed(runnable, 100);
	}
	
	public void refresh_click(View view)
	{
		start();
	}
	
	private void validate()
    {
        int v = Integer.parseInt(answer.getText().toString());
        if (v == result)
        {
            finished = 1;
            if (Integer.parseInt(qno.getText().toString())==30)
                stop();
        }
        else if (String.valueOf(v).length() >= String.valueOf(result).length())
        {
            if(i==6)
            	 wrong.setText(String.valueOf(Integer.parseInt(wrong.getText().toString()) + 1));
            answer.setText("");
        }
    }
	
	private void stop()
	{
		total=time.getText().toString();
		handler.removeCallbacks(runnable);
		Intent intent=new Intent(this, ScoresActivity.class);
		intent.putExtra(TotalTime, total);
		intent.putExtra(Wrong, wrong.getText().toString());
  		startActivity(intent);
  		finish();
	}

	private int rnd(int min,int max)
	{
		
		return r.nextInt(max-min+1)+min;
	}
	
	private int generateQuestion(int qn)
	{
		int op,first,second,result;
		op = rnd(1, 5);
		if(op==1) // addition
		{
		    if (qn <= 5)
		    {
		        first = rnd(0, 11);
		        second = rnd(0, 11);
		    }
		    else if (qn <= 10)
		    {
		        first = rnd(10, 21);
		        second = rnd(0, 11);
		    }
		    else if (qn <= 15)
		    {
		        first = rnd(25, 51);
		        second = rnd(0, 101);
		    }
		    else if (qn <= 20)
		    {
		        first = rnd(50, 151);
		        second = rnd(0, 101);
		    }
		    else if (qn <= 25)
		    {
		        first = rnd(150, 301);
		        second = rnd(0, 201);
		    }
		    else
		    {
		        first = rnd(300, 500);
		        second = rnd(0, 401);
		    }
		    result = first + second;
		    question.setText(String.valueOf(first) + " + " + String.valueOf(second));
		}
		else if (op == 2) // subtraction
		{
		    if (qn <= 10)
		    {
		        second = rnd(0, 10);
		        first = rnd(second, 11);

		    }
		    else if (qn <= 15)
		    {
		        second = rnd(10, 51);
		        first = rnd(second, 71);
		    }
		    else if (qn <= 20)
		    {
		        second = rnd(20, 51);
		        first = rnd(second, 151);
		    }
		    else if (qn <= 25)
		    {
		        second = rnd(100, 201);
		        first = rnd(second, 301);
		    }
		    else
		    {
		        second = rnd(0, 500);
		        first = rnd(((second>300)?second:300), 501);
		    }
		    result = first - second;
		    question.setText(String.valueOf(first) + " - " + String.valueOf(second));
		}
		else if (op == 3) // multiplication
		{
		    if (qn <= 10)
		    {
		        first = rnd(0, 6);
		        second = rnd(0, 6);

		    }
		    else if (qn <= 15)
		    {
		        first = rnd(10, 16);
		        second = rnd(0, 16);
		    }
		    else if (qn <= 20)
		    {
		        first = rnd(15, 21);
		        second = rnd(0, 16);
		    }
		    else if (qn <= 25)
		    {
		        first = rnd(20, 26);
		        second = rnd(0, 21);
		    }
		    else
		    {
		        first = rnd(20, 26);
		        second = rnd(15, 21);
		    }
		    result = first * second;
		    question.setText(String.valueOf(first) + " x " + String.valueOf(second));
		}
		else // division
		{
		    if (qn <= 10)
		    {
		        second = rnd(1, 6);
		        result = rnd(1, 6);

		    }
		    else if (qn <= 15)
		    {
		        second = rnd(1, 11);
		        result = rnd(5, 11);
		    }
		    else if (qn <= 20)
		    {
		        second = rnd(1, 11);
		        result = rnd(10, 16);
		    }
		    else if (qn <= 25)
		    {
		        second = rnd(10, 21);
		        result = rnd(10, 21);
		    }
		    else
		    {
		        second = rnd(15, 31);
		        result = rnd(15, 26);
		    }
		    first = result * second;
		    question.setText(String.valueOf(first) + " / " + String.valueOf(second));
		}
		answer.setText("");
		return result;
	}

	private Runnable runnable=new Runnable(){
		@Override
		public void run()
		{
			
			if (i == 6)
            {
				long ctemp=System.currentTimeMillis();
				float t;
				t=(float) ((int)((ctemp-ct)/10.0)/100.0);
				time.setText(String.valueOf(t));
                if (finished == 1)
                {
                    finished = 0;
                    qno.setText(String.valueOf(Integer.parseInt(qno.getText().toString()) + 1));
                    result=generateQuestion(Integer.parseInt(qno.getText().toString()));

                } 
                handler.postDelayed(this, 80);
            }
            else if(i<4)
            {
                indicator.setText(String.valueOf(4-i));
                ++i;
                handler.postDelayed(this, 650);
            }
            else if (i < 5)
            {
            	indicator.setText("START");
                ++i;
                handler.postDelayed(this, 1000);
            }
            else if(i<6)
            {
            	indicator.setText("");
                ++i;
                ct=System.currentTimeMillis();
                handler.postDelayed(this, 0);
            }
		}
	};
	
	
	public void _1_click(View view)
	{
	    String var = answer.getText().toString();
	    if (var == "")
	        var = "0";
	    answer.setText(String.valueOf(Integer.parseInt(var)*10 + 1));
	    validate();
	}

	public void _2_click(View view)
	{
	    String var = answer.getText().toString();
	    if (var == "")
	        var = "0";
	    answer.setText(String.valueOf(Integer.parseInt(var)*10 + 2));
	    validate();
	}

	public void _3_click(View view)
	{
	    String var = answer.getText().toString();
	    if (var == "")
	        var = "0";
	    answer.setText(String.valueOf(Integer.parseInt(var)*10 + 3));
	    validate();
	}

	public void _4_click(View view)
	{
	    String var = answer.getText().toString();
	    if (var == "")
	        var = "0";
	    answer.setText(String.valueOf(Integer.parseInt(var)*10 + 4));
	    validate();
	}

	public void _5_click(View view)
	{
	    String var = answer.getText().toString();
	    if (var == "")
	        var = "0";
	    answer.setText(String.valueOf(Integer.parseInt(var)*10 + 5));
	    validate();
	}

	public void _6_click(View view)
	{
	    String var = answer.getText().toString();
	    if (var == "")
	        var = "0";
	    answer.setText(String.valueOf(Integer.parseInt(var)*10 + 6));
	    validate();
	}

	public void _7_click(View view)
	{
	    String var = answer.getText().toString();
	    if (var == "")
	        var = "0";
	    answer.setText(String.valueOf(Integer.parseInt(var)*10 + 7));
	    validate();
	}

	public void _8_click(View view)
	{
	    String var = answer.getText().toString();
	    if (var == "")
	        var = "0";
	    answer.setText(String.valueOf(Integer.parseInt(var)*10 + 8));
	    validate();
	}

	public void _9_click(View view)
	{
	    String var = answer.getText().toString();
	    if (var == "")
	        var = "0";
	    answer.setText(String.valueOf(Integer.parseInt(var)*10 + 9));
	    validate();
	}

	public void C_click(View view)
	{
	    answer.setText("");
	}

	public void _0_click(View view)
	{
	    String var = answer.getText().toString();
	    if (var == "")
	        var = "0";
	    answer.setText(String.valueOf(Integer.parseInt(var)*10));
	    validate();
	}

	public void b_click(View view)
	{
	    String s = answer.getText().toString();
	    if (s.length() > 0)
	    {
	        answer.setText(String.valueOf(Integer.parseInt(s)/10));
	    }
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
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

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		start();
	}
}
