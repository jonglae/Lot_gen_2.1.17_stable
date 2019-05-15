package gotopark.buster.lottogen.externaldb;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

import gotopark.buster.lottogen.R;

public class ExternalDBActivity extends Activity {
	private static final String TAG = "ExtDB";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		TextView contentLog = (TextView) findViewById(R.id.content_log);

		// Create the database
		DataBaseHelper myDbHelper = new DataBaseHelper(
				this.getApplicationContext());
		myDbHelper = new DataBaseHelper(this);

		try {
			myDbHelper.createDataBase();
			contentLog.append("Database Created\n");
		} catch (IOException ioe) {
			throw new Error("Unable to create database");
		}

		// Open the database
		try {

			myDbHelper.openDataBase();
			contentLog.append("Database Opened\n");
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		// Get the readable version
		SQLiteDatabase db = myDbHelper.getReadableDatabase();
		contentLog.append("Get the readable database\n");

		// Make a select
		Cursor cur = db.rawQuery(
				"SELECT * FROM import_db ORDER BY Dvide ASC;", null);

		cur.moveToPosition(0);
		Log.v(TAG, "Nb Col:" + cur.getColumnCount());
		Log.v(TAG, "Nb Records:" + cur.getCount());
		cur.close();
		contentLog.append("Select:\t" + cur.getColumnCount() + " cols, "
				+ cur.getCount() + " rows\n");

		// Make an insert
		ContentValues values = new ContentValues();
		values.put("name", "Serval");
		values.put("surname", "Cat");
		long servalCatID = db.insert("import_db", null, values);
		Log.v(TAG, "Serval Cat Inserted @: " + servalCatID);
		contentLog.append("Insert @ \t" + servalCatID + "\n");

		// Check insert
		cur = db.rawQuery(
				"SELECT * FROM import_db ORDER BY Dvide ASC;", null);

		cur.moveToPosition(0);
		Log.v(TAG, "Nb Col:" + cur.getColumnCount());
		Log.v(TAG, "Nb Records:" + cur.getCount());
		cur.close();
		contentLog.append("Select:\t" + cur.getColumnCount() + " cols, "
				+ cur.getCount() + " rows\n");

		// dumb
		cur = db.rawQuery(
				"SELECT * FROM import_db ORDER BY Dvide ASC;",
				null);
		contentLog.append("\nDUMP\n");
		int i = 0;
		cur.moveToFirst();
		while (!cur.isAfterLast()) {
			contentLog.append("(" + i++ + ")\t\t" + cur.getString(0) + "\t"
					+ cur.getString(1) + "\t"
					+ cur.getString(2) + "\t"
					+ cur.getString(3) + "\t"
//					+ cur.getString(4) + "\t"
//					+ cur.getString(5) + "\t"
//					+ cur.getString(6) + "\t"




			);
			cur.moveToNext();
		}

		cur.moveToPosition(0);

		// Close
		myDbHelper.close();
		contentLog.append("Database closed.");

		// YEAH
	}
}