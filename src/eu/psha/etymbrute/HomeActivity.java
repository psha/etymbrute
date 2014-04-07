package eu.psha.etymbrute;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.SearchView;

public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.options_menu, menu);
		getActionBar().setDisplayShowTitleEnabled(false);
		
		//Keep the search bar open (not minimised to a magnifying glass icon)
		SearchView sw = (SearchView) menu.findItem(R.id.menu_search).getActionView();
		sw.setIconifiedByDefault(false);
		
		return true;
	}

}
