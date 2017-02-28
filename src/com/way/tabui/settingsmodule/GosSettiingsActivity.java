package com.way.tabui.settingsmodule;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.json.JSONException;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.gizwits.gizwifisdk.api.GizWifiDevice;
import com.gizwits.gizwifisdk.api.GizWifiSDK;
import com.gizwits.gizwifisdk.enumration.GizPushType;
import com.way.tabui.commonmodule.GosBaseActivity;
import com.way.tabui.commonmodule.GosConstant;
import com.way.tabui.gokit.R;
import com.way.tabui.pushmodule.GosPushManager;

public class GosSettiingsActivity extends GosBaseActivity implements
		OnClickListener {

	/** The ll About */
	private LinearLayout llAbout, llexit,llsetbund,llSetLed;

	private Switch sw_red;
	/** led红灯开关 0=关 1=开. */
	private static final String KEY_RED_SWITCH = "LED_OnOff";
	
	/** The Intent */
	Intent intent;

	/** The ActionBar */
	ActionBar actionBar;
	
	public GizWifiDevice device=null;
	private HashMap<String, Object> deviceStatu;
	//private String title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gos_settings);
		initDevice();
		// 设置ActionBar
		setActionBar(true, true, R.string.site);
		initView();
		initEvent();
		if (device==null) {
			llSetLed.setVisibility(View.GONE);
		}else{
			llSetLed.setVisibility(View.VISIBLE);
		}
	}

   public void initDevice() {
		Intent intent = getIntent();
		device = (GizWifiDevice) intent.getParcelableExtra("GizWifiDevice");
		deviceStatu = new HashMap<String, Object>();
	}
	
	private void initView() {
		llSetLed= (LinearLayout) findViewById(R.id.llSetLed);
		llAbout = (LinearLayout) findViewById(R.id.llAbout);
		llexit = (LinearLayout) findViewById(R.id.llexit);
		llsetbund= (LinearLayout) findViewById(R.id.llsetbund);
		sw_red=(Switch) findViewById(R.id.sw_red);
	}

	// GosPushManager gosPushManager;
	private void initEvent() {
		llAbout.setOnClickListener(this);
		llexit.setOnClickListener(this);
		llsetbund.setOnClickListener(this);
		sw_red.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					sendJson(KEY_RED_SWITCH, sw_red.isChecked());
					if(sw_red.isChecked()){
						Toast.makeText(getApplicationContext(), "请看网关测试灯是否亮起",Toast.LENGTH_SHORT ).show();
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.llAbout:
			intent = new Intent(GosSettiingsActivity.this,
					GosAboutActivity.class);
			intent.putExtra("title", "关于");
			startActivity(intent);
			break;
		case R.id.llsetbund:
			intent = new Intent(GosSettiingsActivity.this,
					SetBundMesActivity.class);
			startActivity(intent);
			break;
			
		case R.id.llexit:
			Intent eintent = new Intent();
			eintent.setAction("com.way.util.exit_app");
			sendBroadcast(eintent);
			break;

		default:
			break;
		}

	}

	private void sendJson(String key, Object value) throws JSONException {
		ConcurrentHashMap<String, Object> hashMap = new ConcurrentHashMap<String, Object>();
		hashMap.put(key, value);
		device.write(hashMap, 0);
		Log.v("==",""+hashMap.get(key));
	//	Log.i("Apptest", hashMap.toString());
	}
	
	/**
	 * 设置ActionBar（工具方法*开发用*）
	 * 
	 * @param HBE
	 * @param DSHE
	 * @param Title
	 */
	public void setActionBar(Boolean HBE, Boolean DSHE, int Title) {

		actionBar = getActionBar();// 初始化ActionBar
		actionBar.setHomeButtonEnabled(HBE);
		actionBar.setIcon(R.drawable.back_bt);
		actionBar.setTitle(Title);
		actionBar.setDisplayShowHomeEnabled(DSHE);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			this.finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
