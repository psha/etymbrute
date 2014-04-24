package eu.psha.etymbrute;

import java.io.File;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;

public class WordProvider extends ContentProvider {
	// private MySqliteOpenHelper msoh;
	private SQLiteDatabase db = null;

	private static final int SUGGESTIONS = 1;
    private static final int WORD = 2;

    private static final UriMatcher myUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static{
    	myUriMatcher.addURI("eu.psha.etymbrute.wordprovider", "search_suggest_query", 1);
    	myUriMatcher.addURI("com.example.app.provider", "table3/#", 2);
    }
	private void setupDb() {

		if (db == null) {
			SharedPreferences sharedPref = PreferenceManager
					.getDefaultSharedPreferences(getContext());
			String url = sharedPref.getString("db_name", getContext()
					.getString(R.string.db_file_default));
			File f = new File(url);
			if (f.exists() && !f.isDirectory()) {
				String s = Environment.getExternalStorageDirectory().getPath().toString();
				Log.d("EtymBrute", "url is " + url + ", trying this instead: " + s +"/testingThis.db" );
				db = SQLiteDatabase.openDatabase(s+"/testingThis.db", null, SQLiteDatabase.OPEN_READONLY);
				//db = SQLiteDatabase.openDatabase(url, null, SQLiteDatabase.OPEN_READONLY);
			} else
				Log.d("EtymBrute", "Database file does not exist.");
		}

	}

	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri arg0, ContentValues arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onCreate() {
		// TODO: check that db is configured and exists.
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		setupDb();
		Log.d("EtymBrute", ".query() Uri= " + uri.toString() + " lastseg:" + uri.getLastPathSegment());
		return null;
	}

	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return 0;
	}

}
