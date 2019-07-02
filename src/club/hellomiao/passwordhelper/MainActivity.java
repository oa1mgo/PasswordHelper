package club.hellomiao.passwordhelper;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private Button psw_confirm;
	private EditText psw;


	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
			
		welcomeCheck();
		
		uIInit();
			
	}

	private void welcomeCheck() {
		SharedPreferences sp = getSharedPreferences("PasswordHelperPref", 0);
		boolean flag = sp.getBoolean("firstload", true);
		if(flag) {
			SharedPreferences.Editor editor = sp.edit();
			editor.putBoolean("firstload", false);
			editor.commit();
			Intent intent = new Intent(MainActivity.this,WelcomeActivity.class);
			startActivity(intent);
		}
		
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		psw_confirm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				checkpsw(psw.getText().toString());
			}
		});
	}

	private void checkpsw(String string) {
		SharedPreferences sp = getSharedPreferences("PasswordHelperPref", 0);
		if (string.equals(sp.getString("loginpsw", null))) {
//			setContentView(R.layout.activity_menu);
			Intent intent = new Intent(MainActivity.this,MenuActivity.class);
			startActivity(intent);
		} else {
			Toast.makeText(MainActivity.this, "√‹¬Î¥ÌŒÛ£°", Toast.LENGTH_SHORT).show();
		} 
	}

	private void uIInit() {
		// TODO Auto-generated method stub
		psw_confirm = (Button) findViewById(R.id.pew_confirm);
		psw = (EditText) findViewById(R.id.editText_psw);
	}

	
}
