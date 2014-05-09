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
		
		LinearLayout layout = (LinearLayout) findViewById(R.id.wv_container);
		
		Cursor c = getWords();
		
		
		eu.psha.etymbrute.WordCompound wc;
		eu.psha.etymbrute.SenseCompound s_comp;
		while (c.moveToNext()) {
			
			//add WordCompound
			wc = new eu.psha.etymbrute.WordCompound(this);
			wc.setData(c.getString(1), c.getString(3), c.getString(5), c.getString(2));
			layout.addView(wc);
			
			//add Senses
			Cursor sc = getSenses(c.getString(1));
			while(sc.moveToNext()){
				s_comp = new eu.psha.etymbrute.SenseCompound(this);
				s_comp.setData(sc.getString(1), sc.getString(1), sc.getString(1));
				layout.addView(s_comp);
			}
			
		}
		

	
		c.close();
		
	}

	private Cursor getWords(){
		String word_id =  getIntent().getExtras().getString("word_id");
		Uri uri = Uri.withAppendedPath(WordProvider.WORD_URI, word_id);
		
		return getContentResolver().query(uri, null, null, new String[]{word_id}, null);
	}

	private Cursor getSenses(String word_id){
		Uri uri = Uri.withAppendedPath(WordProvider.SENSES_URI, word_id);
		
		return getContentResolver().query(uri, null, null, new String[]{word_id}, null);
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
