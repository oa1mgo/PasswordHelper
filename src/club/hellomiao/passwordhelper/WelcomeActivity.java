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

public class WelcomeActivity extends Activity {
	EditText editText_welcome_psw;
	EditText editText_welcome_psw2;
	Button button_welcome_confirm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		uIInit();
	}
	
	

	@Override
	protected void onStart() {
		super.onStart();
		button_welcome_confirm.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				if(editText_welcome_psw.getText().toString().equals(editText_welcome_psw2.getText().toString())) {
					SharedPreferences sp = getSharedPreferences("PasswordHelperPref", 0);
					SharedPreferences.Editor editor = sp.edit();
					editor.putString("loginpsw", editText_welcome_psw.getText().toString());
					editor.commit();
					Intent intent = new Intent(WelcomeActivity.this,MenuActivity.class);
					startActivity(intent);
				} else {
					Toast.makeText(WelcomeActivity.this, "密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
					editText_welcome_psw.setText("");
					editText_welcome_psw2.setText("");
				}
			}
		});
	}



	private void uIInit() {
		editText_welcome_psw = (EditText) findViewById(R.id.editText_welcome_psw);
		editText_welcome_psw2 = (EditText) findViewById(R.id.editText_welcome_psw2);
		button_welcome_confirm = (Button) findViewById(R.id.button_welcome_confirm);
	}
}
