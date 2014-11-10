package com.fireworkshop.mathbrain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.content.Context;
import android.content.Intent;

public class ScoresActivity extends Activity {

	TextView time,wrong,total,tname,div,comment;
	EditText name;
	Button hs,play;
	Boolean savestatus;
	byte[] b;
	int c;
	String s;
	float tot;
	Random r;
	FileInputStream fis;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scores);
		Intent intent=getIntent();
		r=new Random();
		String t=intent.getStringExtra(GameActivity.TotalTime);
		String w=intent.getStringExtra(GameActivity.Wrong);
		time=(TextView)findViewById(R.id.scoreTime);
		wrong=(TextView)findViewById(R.id.scoreWrong);
		total=(TextView)findViewById(R.id.scoreTotal);
		tname=(TextView)findViewById(R.id.textViewName);
		name=(EditText)findViewById(R.id.scoreName);
		hs=(Button)findViewById(R.id.scoreSaveButton);
		play=(Button)findViewById(R.id.scorePlayAgain);
		div=(TextView)findViewById(R.id.textViewDiv);
		comment=(TextView)findViewById(R.id.scoreComment);
		time.setText(t);
		wrong.setText(w);
		tot=(int)(Float.valueOf(t)*100.0)+(int)(Integer.valueOf(w)*100.0);
		tot=(float) (tot/100.0);
		total.setText(String.valueOf(tot));
		name.setText("");
		name.setVisibility(View.GONE);
		tname.setVisibility(View.GONE);
		div.setVisibility(View.GONE);
		savestatus=false;
		hs.setText("Highscores");
		try {
			fis=openFileInput("prevname");
			b=new byte[10];
			if(fis.read(b)!=-1)
			{
				String tem=new String(b).trim();
				name.setText(tem);
			}
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
		String fifth="";
		try {
			fis=openFileInput("fifthscore");
			b=new byte[10];
			if(fis.read(b)!=-1)
			{
				fifth=new String(b);
			}
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
		int status=0;
		if(fifth=="" || tot < Float.valueOf(fifth))
		{
			c=rnd(0,5);
			 switch (c)
             {
                 case 0: s = "Well done!!!";
                 break;
                 case 1: s = "Way to go :)";
                 break;
                 case 2: s = "Nice job!!!";
                 break;
                 case 3: s = "Good to see you in the list";
                 break;
                 default:
                 s = "Keep it up!!!";
                 break;
             }
             status = 1;
             String first="";
             try {
     			fis=openFileInput("firstscore");
     			b=new byte[10];
     			if(fis.read(b)!=-1)
     			{
     				first=new String(b);
     			}
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
            if (first == "" || tot < Float.valueOf(first))
             {
                 s = "New Highscore";
                 status = 2;
             }
            name.setVisibility(View.VISIBLE);
     		tname.setVisibility(View.VISIBLE);
     		div.setVisibility(View.VISIBLE);
     		savestatus=true;
     		hs.setText("Save Score");
		}
		else
		{
			c = rnd(0, 4);
            switch (c)
            {
                case 0: s = "Good job, not your best :)";
                    break;
                case 1: s = "You can do better :)";
                    break;
                case 2: s = "Nice one, but not enough!";
                    break;
                default: s = "See you soon in the list";
                    break;
            }
		}
		if (tot < 61)
        {
            if (status > 0)
            {
                c = rnd(0, 5);
                switch (c)
                {
                    case 0: s = "Mr. Genius :)";
                        break;
                    case 1: s = "Master!!!";
                        break;
                    case 2: s = "You are genius";
                        break;
                    case 3: s = "Einstein reloaded :)";
                        break;
                    default:
                        s = "Mathemagician!";
                        break;
                }
            }
            else
            {
                s = "Talent!!!";
            }
        }
        else if (tot < 95)
        {
            if (status > 0)
            {
                c = rnd(0, 5);
                switch (c)
                {
                    case 0: s = "Wow!!! :)";
                        break;
                    case 1: s = "Skill!!!";
                        break;
                    case 2: s = "Mathbrain!!!";
                        break;
                    case 3: s = "Amazing!!!";
                        break;
                    default:
                        s = "Bravo!!!";
                        break;
                }
            }
            else
            {
                s = "Good job, but seen better!";
            }
        }
		comment.setText(s);
	}
	
	public void playClicked(View view)
	{
		Intent intent=new Intent(this, GameActivity.class);
		startActivity(intent);
		finish();
	}
	
	public void hsClicked(View view)
	{
		FileOutputStream fos=null;
		if(savestatus)
		{
			try {
				fos=openFileOutput("prevname", Context.MODE_PRIVATE);
				Editable editable=name.getText();
				String s=editable.toString().trim();
				fos.write(s.getBytes());
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
			try {
				fos=openFileOutput("newscore", Context.MODE_PRIVATE);
				fos.write(total.getText().toString().getBytes());
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
		else
		{
			try {
				fos=openFileOutput("newscore", Context.MODE_PRIVATE);
				fos.write("".getBytes());
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
		Intent intent=new Intent(this, HighscoresActivity.class);
		startActivity(intent);
		finish();
	}
	
	private int rnd(int min,int max)
	{
		
		return r.nextInt(max-min+1)+min;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.scores, menu);
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

}
