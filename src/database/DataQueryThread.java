package database;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import logic.UserDataModel;

public class DataQueryThread extends Thread {


	private final Context mContext;
	private DatabaseHelper databasehelper;
	private SQLiteDatabase mysqldatabase;

	public String mname;
	public String maccount;
	public String mpassword;
	
	public String[] colums = {"name","account","password"};

	
	public DataQueryThread(Context context,String account) {
		mContext = context;
		maccount = account;
	}

	@Override
	public void run() {
		super.run();
		query(maccount);
	}

	private void query() {
		databasehelper = new DatabaseHelper(mContext);
		mysqldatabase = databasehelper.getWritableDatabase();
		Cursor cursor = mysqldatabase.query(DatabaseHelper.TABLE_NAME, null, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			for (int i = 0; i < cursor.getCount(); i++) {
//				qcmd = (byte) cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.CMD));
				UserDataModel udm = new UserDataModel();
				mname = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.NAME));
				maccount = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.ACCOUNT));
				mpassword = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.PASSWORD));				
				udm.setMname(mname);
				udm.setMaccount(maccount);
				udm.setMpassword(mpassword);
				cursor.moveToNext();
				
			}
		}
	}
	
	
	//TODO:查询的对象如何返回cursor
	@SuppressWarnings({ "null" })
	private ArrayList<String> query(String account) {
		ArrayList<String> al = null;
		databasehelper = new DatabaseHelper(mContext);
		mysqldatabase = databasehelper.getWritableDatabase();

		String[] mparms = {""};
		mparms[0] = account;
		
		
		Cursor cursor = mysqldatabase.query(DatabaseHelper.TABLE_NAME, colums, "account=?", mparms, null, null, null);
//		Cursor cursor = mysqldatabase.rawQuery("select * from "+ DatabaseHelper.TABLE_NAME , {""});
		
		if (cursor.moveToFirst()) {
			int count = cursor.getCount();
			for (int i = 0; i < count; i++) {
				cursor.move(i);
				maccount = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.ACCOUNT));
				mname = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.NAME));
				mpassword = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.PASSWORD));
			}
		}

		cursor.close();
		
		
		if(maccount.equals("") || mname.equals("") || mpassword.equals("")) {
			Toast.makeText(mContext, "查询失败！", Toast.LENGTH_SHORT).show();
			return al;
		}else {
			al.add(maccount);
			al.add(mname);
			al.add(mpassword);
			return al;
		}
		
	}

}
