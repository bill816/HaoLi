package com.haoli.activity;

import java.util.List;

import org.apache.http.cookie.Cookie;

import com.haoli.R;
import com.haoli.net.GetHaoLiData;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class LoginActivity extends Activity {
	
	private EditText userName, password;
	private CheckBox rem_pw, auto_login;
	private Button btn_login;
	private ImageButton btnQuit;
    private String userNameValue,passwordValue;
	private SharedPreferences sp;
	private ProgressDialog progressDialog;
	public static PersistentCookieStore myCookieStore;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//去除标题 
//		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		myCookieStore = new PersistentCookieStore(this);
		//获得实例对象  
		sp = this.getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);
		userName = (EditText) findViewById(R.id.et_zh);
		password = (EditText) findViewById(R.id.et_mima);
        rem_pw = (CheckBox) findViewById(R.id.cb_mima);
		auto_login = (CheckBox) findViewById(R.id.cb_auto);
        btn_login = (Button) findViewById(R.id.btn_login);
        btnQuit = (ImageButton)findViewById(R.id.img_btn);
		
        
        //判断记住密码多选框的状态״̬
      if(sp.getBoolean("ISCHECK", false))
        {
    	//设置默认是记录密码状态 
          rem_pw.setChecked(true);
       	  userName.setText(sp.getString("USER_NAME", ""));
       	  password.setText(sp.getString("PASSWORD", ""));
       	//判断自动登陆多选框状态״̬
       	  if(sp.getBoolean("AUTO_ISCHECK", false))
       	  {
       		  //设置默认是自动登录状态״̬
       		  auto_login.setChecked(true);
       		  GetHaoLiData.login(sp.getString("USER_NAME", ""), sp.getString("PASSWORD", ""), resphandler,myCookieStore);
       	  }
        }
		
      // 登录监听事件  现在默认为用户名为：liu 密码：123
		btn_login.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				userNameValue = userName.getText().toString();
			    passwordValue = password.getText().toString();
			    GetHaoLiData.login(userNameValue, passwordValue, resphandler,myCookieStore);
		        progressDialog = new ProgressDialog(LoginActivity.this);
			    progressDialog.setTitle("登陆");
			    progressDialog.setMessage("登陆中...");
			    progressDialog.show();
				
			}
		});

		//监听记住密码多选框按钮事件  
		rem_pw.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
				if (rem_pw.isChecked()) {
					sp.edit().putBoolean("ISCHECK", true).commit();
					
				}else {
					sp.edit().putBoolean("ISCHECK", false).commit();
					
				}

			}
		});
		
		//监听自动登录多选框事件 
		auto_login.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
				if (auto_login.isChecked()) {
					sp.edit().putBoolean("AUTO_ISCHECK", true).commit();

				} else {
					sp.edit().putBoolean("AUTO_ISCHECK", false).commit();
				}
			}
		});
		
		btnQuit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
	
	//登陆http返回
	AsyncHttpResponseHandler resphandler = new AsyncHttpResponseHandler(){
		
		@Override
        public void onSuccess(String response) {
			 //跳转界面 
			if((response.contains("欢迎阁下的光临") && response.contains("豪俪资本")&&response.length()>100)
					|| response.equals("<script type='text/javascript'>location.href='/home.asp';</script>")){
//			if(true){
				Intent intent = new Intent(LoginActivity.this, HaoLiMainActivity.class);
				LoginActivity.this.startActivity(intent);
				//登录成功和记住密码框为选中状态才保存用户信息  
				if(rem_pw.isChecked())
				{
					//记住用户名、密码、  
				  Editor editor = sp.edit();
				  editor.putString("USER_NAME", userNameValue);
				  editor.putString("PASSWORD",passwordValue);
				  editor.commit();
				}
				finish();
			}else{
				progressDialog.cancel();
				Toast.makeText(LoginActivity.this,"用户名或密码错误，请重新登录", Toast.LENGTH_LONG).show();
			}
            System.out.println(response);
        }
		@Override
        public void onFailure(Throwable e, String response) {
			progressDialog.cancel();
			Toast.makeText(LoginActivity.this,"用户名或密码错误，请重新登录", Toast.LENGTH_LONG).show();
            System.out.println(response);
        }
		
		@Override
        public void onFinish() {
			// Completed the request (either success or failure)
//			progressDialog.cancel();
//			Toast.makeText(LoginActivity.this,"用户名或密码错误，请重新登录", Toast.LENGTH_LONG).show();
        }
	};
}