package me.imli.shareanima;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity {
	
	private ImageFragment mImageFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mImageFragment = (ImageFragment) getSupportFragmentManager().findFragmentById(R.id.main_image_fragment);
		final ImageView imageView = (ImageView) findViewById(R.id.image);
		
		imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showImageFragment(imageView, true);
			}
		});
	}

	
	
	
	public void showImageFragment(ImageView smallImageView , boolean show){
		if(show){
			getSupportFragmentManager().beginTransaction().show(mImageFragment).commit();
			mImageFragment.startScaleAnimation(smallImageView);
		}else{
			getSupportFragmentManager().beginTransaction().hide(mImageFragment).commit();
		}
		
	}
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
