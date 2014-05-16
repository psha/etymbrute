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
	
		final int _id=0, word=1, etymology=2, partOfSpeech=3, wordForms=4, pronunciation=5, entry_id=6, senseIndex=7, gloss=8, examples=9;
		while (c.moveToNext()) {
			
			if (c.getString(senseIndex).equals("1")){ //new entry
				eu.psha.etymbrute.WordCompound wc = new eu.psha.etymbrute.WordCompound(this);
				wc.setData(c.getString(word), c.getString(partOfSpeech), c.getString(pronunciation), c.getString(etymology));
				layout.addView(wc);
			}
			
			//add Senses
			
			eu.psha.etymbrute.SenseCompound s_comp = new eu.psha.etymbrute.SenseCompound(this);
			s_comp.setData(c.getString(senseIndex), c.getString(gloss), c.getString(examples));
			layout.addView(s_comp);
		
		}
		c.close();
		
	}

	private Cursor getWords(){
		String word_id =  getIntent().getExtras().getString("word_id");
		Uri uri = Uri.withAppendedPath(WordProvider.WORD_URI, word_id);
		Log.d("EtymBrute", uri.toString());
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
