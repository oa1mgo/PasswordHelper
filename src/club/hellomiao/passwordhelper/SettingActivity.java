package club.hellomiao.passwordhelper;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Switch;

public class SettingActivity extends Activity {
	
	private Switch switch_reset;
	private EditText edittext;
	private Button button;
	private boolean firstload = false;
	private int max_wrong_time;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		uIInit();
	}
	
	
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				max_wrong_time = Integer.parseInt(edittext.getText().toString());
				SharedPreferences sp = getSharedPreferences("PasswordHelperPref", 0);
				SharedPreferences.Editor editor = sp.edit();
				editor.putBoolean("firstload", firstload);
				editor.putInt("maxwrongtime", max_wrong_time);
				editor.commit();
				Intent intent = new Intent(SettingActivity.this,MainActivity.class);
				startActivity(intent);
			}
		});
		
		switch_reset.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked) {
					firstload = true; 
				} else {
					firstload = false;
				}
			}
		});
	}



	private void uIInit() {
		// TODO Auto-generated method stub
		switch_reset = (Switch) findViewById(R.id.switch_setting_reset);
		edittext = (EditText) findViewById(R.id.editText_setting_maxwrongtime);
		button = (Button) findViewById(R.id.button_settin_confirm);
	}
}
