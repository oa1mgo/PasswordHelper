package logic;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

public class SharedPreferencesHelper {

	private static final String APP_NAME = "PasswordHelper";
	public static final String MAX_RETRY_TIME = "max_retry_time";
	public static final String FIRST_LOAD = "firstload";
	public static final String PASSWORD_HELPER_PREF = "PasswordHelperPref";

	public static void prefencesFirstReload(Context context) {
		SharedPreferences sp = context.getSharedPreferences(PASSWORD_HELPER_PREF, 0);
		SharedPreferences.Editor editor = sp.edit();
		editor.putBoolean(FIRST_LOAD, true);
		editor.putInt(MAX_RETRY_TIME, 100);
		editor.commit();
		Toast.makeText(context, "SP LOAD!", Toast.LENGTH_SHORT).show();
		Log.i(APP_NAME, "SP LOAD");
	}

	public int getMaxRetryTime(Context context) {
		SharedPreferences sp_getMaxRetryTime = context.getSharedPreferences(PASSWORD_HELPER_PREF, 0);
		Log.i(APP_NAME, "getMaxRetryTime");
		return sp_getMaxRetryTime.getInt(MAX_RETRY_TIME, 100);
	}
	
	public void setMaxRetryTime(Context context,int num) {
		SharedPreferences sp_setMaxRetryTime = context.getSharedPreferences(PASSWORD_HELPER_PREF, 0);
		SharedPreferences.Editor editor = sp_setMaxRetryTime.edit();
		editor.putInt(MAX_RETRY_TIME, num);
		editor.commit();
		Log.i(APP_NAME, "setMaxRetryTime:"+num);
	}

	public boolean getFirstLoad(Context context) {
		SharedPreferences sp_getFirstLoad = context.getSharedPreferences(PASSWORD_HELPER_PREF, 0);
		Log.i(APP_NAME, "getFirstLoad");
		return sp_getFirstLoad.getBoolean(FIRST_LOAD, false);
	}
	
	public void setFirstLoad(Context context,boolean flag) {
		SharedPreferences sp_setFirstLoad = context.getSharedPreferences(PASSWORD_HELPER_PREF, 0);
		SharedPreferences.Editor editor = sp_setFirstLoad.edit();
		editor.putBoolean(APP_NAME, flag);
		editor.commit();
		Log.i(APP_NAME, "setFirstLoad:"+flag);
	}
		
}
