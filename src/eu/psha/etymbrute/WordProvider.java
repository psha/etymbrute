package eu.psha.etymbrute;

import java.io.File;
import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.DatabaseUtils;
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
    	myUriMatcher.addURI("eu.psha.etymbrute.wordprovider", "search_suggest_query/*", 1);
    	myUriMatcher.addURI("com.example.app.provider", "open/#", 2);
    }
	private void setupDb() {

		if (db == null) {
			
			SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
			//String url = sharedPref.getString("db_name", getContext().getString(R.string.db_file_default));

			File private_app_mem = new File(getContext().getFilesDir().toString() + "/etym.db");
			File external_app_mem = new File(Environment.getExternalStorageDirectory().getPath().toString() + "/etym.db");
						
			if (private_app_mem.exists() && !private_app_mem.isDirectory()) {
				Log.d("EtymBrute", "Opening database in apps private memory on internal memory: " + private_app_mem.getAbsolutePath());
				db = SQLiteDatabase.openDatabase(private_app_mem.getAbsolutePath(), null, SQLiteDatabase.OPEN_READONLY);
			}
			else if (external_app_mem.exists() && !external_app_mem.isDirectory()){
				Log.d("EtymBrute", "Opening database in apps private memory on external memory (SD card): " + external_app_mem.getAbsolutePath());
				db = SQLiteDatabase.openDatabase(external_app_mem.getAbsolutePath(), null, SQLiteDatabase.OPEN_READONLY);
				
			}
			else{
				File external_crazy = new File("/storage/extSdCard/etymbrute/etym.db");
				Log.d("EtymBrute", "Database file does not exist in any sensible location. Using: " + external_crazy.getAbsolutePath());
				if (external_crazy.exists() && !external_crazy.isDirectory()){
					Log.d("EtymBrute", "Found db in: " + external_crazy.getAbsolutePath());
					db = SQLiteDatabase.openDatabase(external_crazy.getAbsolutePath(), null, SQLiteDatabase.OPEN_READONLY);
				}
				else
					Log.e("EtymBrute", "Found no db");
			}
			
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
		
		
        switch (myUriMatcher.match(uri))
        {
            case SUGGESTIONS:
                return find_suggestion(uri.getLastPathSegment());
            case WORD:
                return null; //TODO: Plug in word search
            default:
                return null;
        }

		
	}

	private Cursor find_suggestion(String q) {
		
		setupDb();
		if(q.equals("") || q.equals("search_suggest_query"))
			return null;
		
		//fix the query to work with sql wildcards _ %
		q = q.replaceAll("_", "}_");
		q = q.replaceAll("%", "}%");
		q = DatabaseUtils.sqlEscapeString(q + "%" );
		
		String query = "SELECT word AS " + SearchManager.SUGGEST_COLUMN_TEXT_1 + ", _id, _id AS " + SearchManager.SUGGEST_COLUMN_INTENT_DATA_ID + " FROM Words WHERE word LIKE " + q + " ESCAPE '}' LIMIT 20;";
		Cursor c = db.rawQuery(query,null);

		return c;
	}

	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return 0;
	}

}
