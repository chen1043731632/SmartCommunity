package com.way.tabui.gokit;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.json.JSONException;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gizwits.gizwifisdk.api.GizWifiDevice;
import com.way.tabui.commonmodule.GosBaseActivity;

public class SmartAirConditionActivity extends GosBaseActivity {

	/** 开 */
	private Button bt_open;
	private int OPEN = 327432;// 04 FF 08

	/** 关 */
	private Button bt_close;
	private int CLOSE = 262152;// 04 00 08

	/** 制冷 */
	private Button bt_cool;
	private int COOL = 327944;// 05 01 08

	/** 抽湿 */
	private Button bt_dehumidifier;
	private int DEHUM = 328200;// 05 02 08
	
	/** 送风 */
	private Button bt_airsupply;
	private int AIRSUPPLY = 328456;// 05 03 08
	
	/** 制热 */
	private Button bt_heat;
	private int HEAT = 328712;// 05 04 08

	/** 减少温度 */
	private Button bt_sub;
	/** 增加温度 */
	private Button bt_add;
	/** 发送温度 */
	private Button bt_sendtem;
	private int sendtem = 393216; // 06 xx xx
	
	/** 发送遥控类型 */
	private Button bt_type;
	private int sendtype = 131072;// 02 xx xx
	
	/** 休眠 */
	private Button bt_hibernate;
	private int hib;

	/** 初始化自动 */
	private Button bt_initauto;
	private int INITAUTO = 11184648;//AA AA 08

	/** 初始化结束 */
	private Button bt_initover;
	private int INITOVER = 13421576;//CC CC 08

	/** 自动风速 */
	private Button bt_wsauto;
	private int WSAUTO = 458760;//07 00 08
	
	/** 风速1 */
	private Button bt_ws1;
	private int WS1 = 459016;  //07 01 08
	
	/** 风速2 */
	private Button bt_ws2;
	private int WS2 = 459272; //07 02 08
	
	/** 风速3 */
	private Button bt_ws3;
	private int WS3 = 459528; //07 03 08

	/** 自动风向 */
	private Button bt_wdauto;
	private int WDAUTO = 524296; //08 00 08

	/** 手动风向 */
	private Button bt_wdmanual;
	private int WDMANUAL = 524552; //08 01 08

	/** 温度编辑 */
	private EditText ed_tem;
	/** 遥控类型编辑 */
	private EditText ed_type;

	/** The GizWifiDevice device */
	private GizWifiDevice device;
	/** The device statu. */
	private HashMap<String, Object> deviceStatu;
	/** 空调命令 */
	private static final String KEY_Sendair = "Send_aircon";
	
    private TextView tx_state,tx_tem,tx_sw,tx_wd,tx_mod1,tx_mod2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_smart_air_condition);
		setActionBar(true, true, R.string.title_activity_smart_air_condition);
		initDevice();
		initView();
		initData();
		initEvent();
	}

	private void initView() {
		bt_open = (Button) findViewById(R.id.bt_open);
		bt_close = (Button) findViewById(R.id.bt_close);
		bt_cool = (Button) findViewById(R.id.bt_cool);
		bt_dehumidifier = (Button) findViewById(R.id.bt_dehumidifier);
		bt_airsupply = (Button) findViewById(R.id.bt_airsupply);
		bt_heat = (Button) findViewById(R.id.bt_heat);

		bt_sub = (Button) findViewById(R.id.bt_sub);
		bt_add = (Button) findViewById(R.id.bt_add);
		bt_sendtem = (Button) findViewById(R.id.bt_sendtem);
		bt_type = (Button) findViewById(R.id.bt_type);
		bt_hibernate = (Button) findViewById(R.id.bt_hibernate);
		bt_initauto = (Button) findViewById(R.id.bt_initauto);
		bt_initover = (Button) findViewById(R.id.bt_initover);
		bt_initover.setEnabled(false);

		bt_wsauto = (Button) findViewById(R.id.bt_wsauto);
		bt_ws1 = (Button) findViewById(R.id.bt_ws1);
		bt_ws2 = (Button) findViewById(R.id.bt_ws2);
		bt_ws3 = (Button) findViewById(R.id.bt_ws3);
		bt_wdauto = (Button) findViewById(R.id.bt_wdauto);
		bt_wdmanual = (Button) findViewById(R.id.bt_wdmanual);
		

		ed_tem = (EditText) findViewById(R.id.ed_tem);
		ed_type = (EditText) findViewById(R.id.ed_type);
		
		tx_state=  (TextView) findViewById(R.id.tx_state);
		tx_tem=  (TextView) findViewById(R.id.tx_tem);
		tx_sw=  (TextView) findViewById(R.id.tx_sw);
		tx_wd=  (TextView) findViewById(R.id.tx_wd);
		tx_mod1=  (TextView) findViewById(R.id.tx_mod1);
        tx_mod2=  (TextView) findViewById(R.id.tx_mod2);
	}

	private void initDevice() {
		Intent intent = getIntent();
		device = (GizWifiDevice) intent.getParcelableExtra("GizWifiDevice");
		deviceStatu = new HashMap<String, Object>();
	}

	private void initData() {
		
		ed_tem.setText("16");
		ed_type.setText("000");
		tx_mod2.setText("");

	}

	private void initEvent() {
		bt_open.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					sendJson(KEY_Sendair, OPEN);
					tx_state.setText("开");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		bt_close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					sendJson(KEY_Sendair, CLOSE);
					tx_state.setText("关");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		bt_cool.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					sendJson(KEY_Sendair, COOL);
					tx_mod1.setText("制冷");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		bt_dehumidifier.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					sendJson(KEY_Sendair, DEHUM);
					if(!(tx_mod2.getText().toString().equals("抽湿"))){
						tx_mod2.setText("抽湿");
					}else{
						tx_mod2.setText("");
					}
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		bt_airsupply.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					sendJson(KEY_Sendair, AIRSUPPLY);
					if(!(tx_mod2.getText().toString().equals("送风"))){
						tx_mod2.setText("送风");
					}else{
						tx_mod2.setText("");
					}
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		bt_heat.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				try {
					sendJson(KEY_Sendair, HEAT);
					tx_mod1.setText("制热");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		bt_sub.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int tem = Integer.parseInt(ed_tem.getText().toString());
				if (tem > 16) {
					ed_tem.setText(""+(tem-1));
				} else {
					Toast.makeText(getApplicationContext(), "请在16~30℃中选择温度",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
		bt_add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int tem = Integer.parseInt(ed_tem.getText().toString());
				if (tem <30) {
					ed_tem.setText(""+(tem+1));
				} else {
					Toast.makeText(getApplicationContext(), "请在16~30℃中选择温度",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		bt_sendtem.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int tem = Integer.parseInt(ed_tem.getText().toString());
				String temx = Integer.toHexString(tem) + "08";
                if (tem>=16&&tem<=30) {
					try {
						sendtem = sendtem + Integer.valueOf(temx,16);
						sendJson(KEY_Sendair, sendtem);
						tx_tem.setText(ed_tem.getText().toString());
						sendtem = 393216;
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					Toast.makeText(getApplicationContext(), "请在16~30℃中选择温度",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		bt_type.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int type = Integer.parseInt(ed_type.getText().toString());
				try {
					sendtype = sendtype + type;
					sendJson(KEY_Sendair, sendtype);
					sendtype = 131072;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		bt_hibernate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Toast.makeText(getApplicationContext(), "暂无此功能，敬请期待",
						Toast.LENGTH_SHORT).show();
			}
		});

		bt_initauto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				try {
					sendJson(KEY_Sendair, INITAUTO);
					bt_initover.setEnabled(true);
					Toast.makeText(getApplicationContext(), "初始化后请按‘初始化结束’按钮",
							Toast.LENGTH_SHORT).show();
					tx_mod1.setText("制热");
					tx_sw.setText("自动");
					tx_tem.setText("28");
					tx_state.setText("开");
					tx_wd.setText("自动");
					tx_mod2.setText("");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		bt_initover.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				try {
					sendJson(KEY_Sendair, INITOVER);
					Toast.makeText(getApplicationContext(), "初始化结束",
							Toast.LENGTH_SHORT).show();
					bt_initover.setEnabled(false);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		bt_wsauto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				try {
					sendJson(KEY_Sendair, WSAUTO);
					tx_sw.setText("自动");
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		bt_ws1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				try {
					sendJson(KEY_Sendair, WS1);
					tx_sw.setText("1档");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		bt_ws2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				try {
					sendJson(KEY_Sendair, WS2);
					tx_sw.setText("2档");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		bt_ws3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				try {
					sendJson(KEY_Sendair,WS3);
					tx_sw.setText("3档");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		bt_wdauto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				try {
					sendJson(KEY_Sendair,WDAUTO);
					tx_wd.setText("自动");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		bt_wdmanual.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				try {
					sendJson(KEY_Sendair,WDMANUAL);
					tx_wd.setText("手动");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	private void sendJson(String key, Object value) throws JSONException {
		ConcurrentHashMap<String, Object> hashMap = new ConcurrentHashMap<String, Object>();
		hashMap.put(key, value);
		device.write(hashMap, 0);
		Log.i("==", hashMap.toString());
		// Log.i("Apptest", hashMap.toString());
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
