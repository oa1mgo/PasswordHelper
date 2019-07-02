package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	public static final String DB_NAME = "hellomiao.db";

	public static final int DB_VERSION = 1;

	public static final String TABLE_NAME = "passwordhelper";

	public static final String NAME = "name";
	public static final String ACCOUNT = "account";
	public static final String PASSWORD = "password";

	// sqlite>
	// CREATE TABLE COMPANY(ID INT PRIMARY KEY NOT NULL,NAME TEXT NOT NULL,AGE
	// INT NOT NULL,ADDRESS CHAR(50),SALARY REAL);

	// private static final String CREATE_TABLE_SQL_TEST = "create table " +
	// TABLE_NAME + " (" + COLUMN1
	// + " varchar(40) not null," + COLUMN2 + " varchar(2));";

	// private static final String CREATE_TABLE_SQL_TEST_0329 = "create table " +
	// TABLE_NAME + " (" + CMD
	// + " int not null," + LEN + " int not null," + SER + " int not null," + RSSI +
	// " int not null," + MAC
	// + " int not null," + ACT + " int not null," + VOL + " int not null," + AX + "
	// int not null," + AY
	// + " int not null," + AZ + " int not null," + GX + " int not null," + GY + "
	// int not null," + GZ
	// + " int not null," + TEMP + " int not null," + SUMP + " int not null," + SUMN
	// + " int not null," + STARTTIME
	// + " int not null," + ENDTIME + " int nut null" + ");";
	//
	private static final String CREATE_TABLE_SQL = "create table " + TABLE_NAME + " (" + NAME + " text not null," + ACCOUNT + " text not null,"
			+ PASSWORD + " text not null" + ");";

	// private static final String CREATE_TABLE_SQL_TEST_0329 = "create table " +
	// TABLE_NAME + " (" + CMD
	// + " interger not null," + LEN + " interger not null" + ");";

	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_SQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int DB_VERSION, int newVersion) {
		// TODO Auto-generated method stub
	}

}
