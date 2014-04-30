package eu.psha.etymbrute;

import java.io.File;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.preference.PreferenceManager;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper{

	public DbHelper(Context context) {
		// Context c, String name, CursorFactory factory, int version
		super(context, "etymbrute.db", null, 2);
		
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
		String url = sharedPref.getString("db_name", context.getString(R.string.db_file_default));
		File f = new File(url);
		if(f.exists() && !f.isDirectory()) { 
			
		}
		else{
			Log.d("EtymBrute", "Database file does not exist.");
		}
	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		super.onOpen(db);
	}

	//Called if the database is accessed but not yet created.
	//This must not ever happen.
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.e("EtymBrute", "Attempt to use database. There is no database. This is not good.");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
}