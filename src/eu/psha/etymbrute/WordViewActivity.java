package eu.psha.etymbrute;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class WordViewActivity extends Activity {
	
	private static final String tag = "EtymBrute.WordViewActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e("EtymBrute", "Im here");
		setContentView(R.layout.activity_word_view);

		if (savedInstanceState == null) {
		}
		
		String word_id =  getIntent().getExtras().getString("word_id");
		Uri uri = Uri.withAppendedPath(WordProvider.CONTENT_URI, word_id);
		
		Cursor c = getContentResolver().query(uri, null, null, new String[]{word_id}, null);
		
		if (c.moveToFirst()) {
			setTitle(c.getString(1));
			setEtym(c.getString(2));
			setPronunciation(c.getString(5));
		}
		else{
			Log.e("EtymBrute", "Tried to display Word, but got cursor was not returned correctly from WordProvider.");
		}
		c.close();
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
	
	//----------- methods for updating fields
	private void setTitle(String t){
		if(t != null ){
			TextView tv = (TextView) findViewById(R.id.wv_title);
			tv.setText(t);
		}
	}
	
	private void setPronunciation(String s){
		if(s != null ){
			TextView tv = (TextView) findViewById(R.id.wv_pro);
			tv.setText(s);
		}
	}
	
	private void setEtym(String s){
		if(s != null ){
			TextView tv = (TextView) findViewById(R.id.wv_etym);
			tv.setText(s);
		}
	}
}
