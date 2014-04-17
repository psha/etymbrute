package eu.psha.etymbrute;

import android.os.Bundle;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

public class HomeActivity extends Activity{


	String tag = "EtymBrute";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
	    Intent intent = getIntent();
	    if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
	      String query = intent.getStringExtra(SearchManager.QUERY);
	      Log.d(tag, "onCreate() caugth search");
	      doSearch(query);
	    }

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.options_menu, menu);
		getActionBar().setDisplayShowTitleEnabled(false);

		
		
		///SEARCH BAR
		// Keep the search bar open (not minimised to a magnifying glass icon)
		SearchView sw = (SearchView) menu.findItem(R.id.menu_search)
				.getActionView();
		sw.setIconifiedByDefault(false);

		// TODO: move icon closer to app icon, does this really work, feels
		// hacky
		sw.setPadding(-16, 0, 0, 0);

		  // Get the SearchView and set the searchable configuration
	    SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
	    SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
	    // Assumes current activity is the searchable activity
	    searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_settings:
			openSettings();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
    protected void onNewIntent(Intent intent) {
        // Because this activity has set launchMode="singleTop", the system calls this method
        // to deliver the intent if this activity is currently the foreground activity when
        // invoked again (when the user executes a search from this activity, we don't create
        // a new instance of this activity, so the system delivers the search intent here)
    	//
    	// The emulator has a bug, this is called twice every time. Should work on actual devices.
	    if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
		      String query = intent.getStringExtra(SearchManager.QUERY);
		      Log.d(tag, "onNewIntent() caugth search");
		      doSearch(query);
		    }
    }
    
    public void doSearch(String query){
	      Log.d(tag, "Handle query: " + query);
    }
    
    // Open the settings dialog
    private void openSettings(){
    	Log.d(tag, "Opening Settings dialog.");
        //settings menu
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new EtymbruteSettingsFragment())
                .addToBackStack(getResources().getString(R.string.action_settings) )
                .commit();
    }
}
