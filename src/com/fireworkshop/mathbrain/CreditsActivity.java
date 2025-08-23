package com.fireworkshop.mathbrain;

import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class CreditsActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_credits);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.credits, menu);
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
                onBackPressed();
                return true;
        }

	public void rate_click(View view)
	{
		try{
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName()));
    	startActivity(browserIntent);
    	Toast.makeText(getApplicationContext(), "Thanks", Toast.LENGTH_SHORT).show();
		}
		catch(Exception e){}
	}
}
