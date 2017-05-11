package com.way.tabui.gokit;

import com.way.tabui.commonmodule.GosBaseActivity;
import com.way.tabui.settingsmodule.SetBundMesActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SmartCurtainActivity extends GosBaseActivity {

	private Button btn_open, btn_colse;
	private TextView tv_mes;
	private LinearLayout li_main_curtain;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_smart_curtain);
		initView();
		initEvent();
		
	}

	private void initView() {
		btn_open = (Button) findViewById(R.id.btn_open);
		btn_colse = (Button) findViewById(R.id.btn_colse);
		tv_mes=(TextView) findViewById(R.id.tv_mes);
		li_main_curtain=(LinearLayout) findViewById(R.id.li_main_curtain);
	}

	private void initEvent(){
		btn_open.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
			//	li_main_curtain.setBackground(getResources().getDrawable(R.drawable.bg_curtain_shape1));
				tv_mes.setText("状态：开");
				tv_mes.setTextColor(getResources().getColor(R.color.golden));
			}
		});
		
		btn_colse.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			//	li_main_curtain.setBackground(getResources().getDrawable(R.drawable.bg_curtain_shape2));
				tv_mes.setText("状态：关");
				tv_mes.setTextColor(getResources().getColor(R.color.color_blue));
			}
		});
	}
	

}
