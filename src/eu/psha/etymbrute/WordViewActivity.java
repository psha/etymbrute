package eu.psha.etymbrute;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class WordViewActivity extends Activity {
	
	private static final String tag = "EtymBrute.WordViewActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_word_view);

		if (savedInstanceState == null) {
		}
		
		populateView();

	}




	private void populateView() {
		
		String word_id =  getIntent().getExtras().getString("word_id");
		Uri uri = Uri.withAppendedPath(WordProvider.CONTENT_URI, word_id);
		
		Cursor c = getContentResolver().query(uri, null, null, new String[]{word_id}, null);
		
		
		c.moveToFirst();
		eu.psha.etymbrute.WordCompound wc = new eu.psha.etymbrute.WordCompound(this);
		wc.setData(c.getString(1), c.getString(3), c.getString(5), c.getString(2));
		LinearLayout layout = (LinearLayout) findViewById(R.id.wv_container);
		layout.addView(wc );
	
		c.close();
		
	}

	private void addWordCompound(){
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.word_view, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
}
