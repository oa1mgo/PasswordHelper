package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DataInsertThread extends Thread {

	

	private DatabaseHelper databasehelper;
	private SQLiteDatabase mysqldatabase;
	private Context mContext;
	private String mname;
	private String maccount;
	private String mpassword;

	public DataInsertThread(Context context, String name, String account, String password) {
		mContext = context;
		mname = name;
		maccount = account;
		mpassword = password;
	}

	@Override
	public void run() {
		dbInsert();
	}

	

	private void dbInsert() {
		databasehelper = new DatabaseHelper(mContext);
		mysqldatabase = databasehelper.getWritableDatabase();
		ContentValues contentvalues = new ContentValues();
		contentvalues.put(DatabaseHelper.NAME, mname);
		contentvalues.put(DatabaseHelper.ACCOUNT, maccount);
		contentvalues.put(DatabaseHelper.PASSWORD, mpassword);
		mysqldatabase.insert(DatabaseHelper.TABLE_NAME, null, contentvalues);
	}

}
