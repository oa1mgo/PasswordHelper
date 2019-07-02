package club.hellomiao.passwordhelper;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import database.DatabaseHelper;
import logic.DESUtil;

public class QueryActivity extends Activity {
	
	private static final String SPASSWORD = "密码：\n";
	private static final String SACCOUNT = "账户名称：\n";
	private static final String SNAME = "应用名称：\n";
	private static final int LOGININFO = 1;
	private static final int QUERYFAIL = 2;
	
	Bundle b = new Bundle();

	Context mcontext;
	
	private Cursor cursor;
	private DatabaseHelper databasehelper;
	private SQLiteDatabase mysqldatabase;

	public String mname;
	public String maccount;
	public String mpassword;
	
	public String[] colums = {"name","account","password"};
	
	private EditText editText_query_account;
	private Button button_query_query;
	private TextView textView_query_name;
	private TextView textView_query_account;
	private TextView textView_query_password;

	private RadioButton radio_query_account;

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_query);
		uIInit();
	}

	
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		editText_query_account.addTextChangedListener(new TextWatcher() {	
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}	
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {	
			}
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				textView_query_name.setText(SNAME);
				textView_query_account.setText(SACCOUNT);
				textView_query_password.setText(SPASSWORD);
				mpassword = "";
				if(radio_query_account.isChecked()) {
					mname = "";			
				} else {
					maccount = "";				
				}
			}
		});
		
		
		button_query_query.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				maccount = editText_query_account.getText().toString().trim();
				if(!editText_query_account.getText().toString().equals("")) {
					
					//queryThread
					new Thread(new Runnable() {
						@Override
						public void run() {
							// TODO:  Query Option
							databasehelper = new DatabaseHelper(mcontext);
							mysqldatabase = databasehelper.getWritableDatabase();

							String[] mparms = {""};
							mparms[0] = maccount;
	
							if(radio_query_account.isChecked()) {
								cursor = mysqldatabase.query(DatabaseHelper.TABLE_NAME, colums, "account=?", mparms, null, null, null);				
							} else {
								cursor = mysqldatabase.query(DatabaseHelper.TABLE_NAME, colums, "name=?", mparms, null, null, null);				
							}
//							Cursor cursor = mysqldatabase.rawQuery("select * from "+ DatabaseHelper.TABLE_NAME , {""});
							
							int count = cursor.getCount();
							
							if (count > 0) {
								if (cursor.moveToFirst()) {
									for (int i = 0; i < count; i++) {
										cursor.move(i);
										mname = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.NAME));
										maccount = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.ACCOUNT));
										mpassword = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.PASSWORD));
									}
								}
								cursor.close();

								byte[] bkeydata = {1,2,3,4,5,6,7,8};		
								byte[] tmp = DESUtil.parseHexStr2Byte(mpassword);
								tmp = DESUtil.desDecrypt(tmp, bkeydata);
								mpassword = new String(tmp);
								
								Message m = Message.obtain();
								m.what = LOGININFO;
								b.putString("Name", mname);
								b.putString("Account", maccount);
								b.putString("Password", mpassword);
								m.setData(b);
								mhandler.sendMessage(m);   
								
							} else {
								Message m = Message.obtain();
								m.what = QUERYFAIL;
								b.putString("Name", "");
								b.putString("Account", "");
								b.putString("Password", "");
								m.setData(b);
								mhandler.sendMessage(m);   
							}
							
						}
					}).start();
					
				}
			}
		});
	}


	Handler mhandler=new Handler(){
        public void handleMessage(Message msg) {
            super.handleMessage(msg);        
            Bundle newdata = msg.getData();
			String name = newdata.getString("Name");
			String account = newdata.getString("Account");
			String password  = newdata.getString("Password");
            textView_query_name.setText(SNAME + name);
            textView_query_account.setText(SACCOUNT + account);
            textView_query_password.setText(SPASSWORD + password);
            switch (msg.what) {
			case LOGININFO:	
				Toast.makeText(mcontext, "查询成功", Toast.LENGTH_SHORT).show();
				break;
			case QUERYFAIL:
				Toast.makeText(mcontext, "查询失败！", Toast.LENGTH_SHORT).show();
				break;
			}
		}
    };

	private void uIInit() {
		mcontext = QueryActivity.this;
		editText_query_account = (EditText) findViewById(R.id.editText_query_account);
		button_query_query = (Button) findViewById(R.id.button_query_query);
		textView_query_name = (TextView) findViewById(R.id.textView_query_name);
		textView_query_account = (TextView) findViewById(R.id.textView_query_account);
		textView_query_password = (TextView) findViewById(R.id.textView_query_password);

		radio_query_account = (RadioButton) findViewById(R.id.radio_query_account);

		
		textView_query_name.setText(SNAME);
		textView_query_account.setText(SACCOUNT);
		textView_query_password.setText(SPASSWORD);
	}
	
}
