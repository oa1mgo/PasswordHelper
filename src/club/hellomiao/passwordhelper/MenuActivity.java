package club.hellomiao.passwordhelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends Activity {
	
	private Button menuQuery;
	private Button menuManage;
	private Button setting;
	private Context mcontext = MenuActivity.this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		UIInit();
		
		//Query PSW
		menuQuery.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				Toast.makeText(MenuActivity.this, "Query", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(MenuActivity.this,QueryActivity.class);
				startActivity(intent);
			}
		});
		
		//Manage PSW
		menuManage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(MenuActivity.this, "Manage", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(MenuActivity.this,AddActivity.class);
				startActivity(intent);
			}
		});
		
		//settings
		setting.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				Toast.makeText(MenuActivity.this, "Setting", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(MenuActivity.this,SettingActivity.class);
				startActivity(intent);
			}
		});
		
	}

	private void UIInit() {
		menuQuery = (Button) findViewById(R.id.button_query);
		menuManage = (Button) findViewById(R.id.button_manage);
		setting = (Button) findViewById(R.id.button_setting);
	}

}
