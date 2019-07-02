package club.hellomiao.passwordhelper;

import java.io.UnsupportedEncodingException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import database.DataInsertThread;
import logic.DESUtil;

public class AddActivity extends Activity {
	
	private EditText editText_add_name;
	private EditText editText_add_account;
	private EditText editText_add_password;
	private Button button_add_confirm;
	
	private String mname;
	private String maccount;
	private String mpassword;
	
	private Context mcontext = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		uIInit();
	}
	
	
	
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		button_add_confirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(getData()) {
					new DataInsertThread(mcontext, mname, maccount, mpassword).start();
					Toast.makeText(AddActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
				}else Toast.makeText(AddActivity.this, "添加失败！请填写完整的信息", Toast.LENGTH_SHORT).show();
			}
		});
		
	}


	private void uIInit() {
		// TODO Auto-generated method stub
		editText_add_name = (EditText) findViewById(R.id.editText_add_name);
		editText_add_account = (EditText) findViewById(R.id.editText_add_account);
		editText_add_password = (EditText) findViewById(R.id.editText_add_password);
		button_add_confirm = (Button) findViewById(R.id.button_add_confirm);
	}


	private boolean getData() {
		mname = editText_add_name.getText().toString().trim();
		maccount = editText_add_account.getText().toString().trim();
		mpassword = editText_add_password.getText().toString().trim();
		try {
			byte[] bpsw  =mpassword.getBytes("UTF-8");
			byte[] bkeydata = {1,2,3,4,5,6,7,8};
			byte[] tmp = DESUtil.desEncrypt(bpsw, bkeydata);
			mpassword = DESUtil.parseByte2HexStr(tmp);			
//			tmp = DESUtil.parseHexStr2Byte(mpassword);
//			tmp = DESUtil.desDecrypt(tmp, bkeydata);
//			mpassword = new String(tmp);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (mname.equals("") || maccount.equals("") || mpassword.equals("")) {
			return false;
		} else return true;
	}
	
	
}
