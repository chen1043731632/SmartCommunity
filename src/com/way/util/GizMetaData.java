package com.way.util;

import android.provider.BaseColumns;

/**
 * 
 * @author Administrator
 *
 */
public final class GizMetaData {
	private GizMetaData(){}
	//GIZ表
	public static abstract class GizTable implements BaseColumns{
		public static final String TABLE_NAME ="giz";
		public static final String  GIZ_ID ="id";  //主键
		public static final String  GIZ_NAME ="name";//名字
		public static final String  GIZ_ADDRESS="address";//设备控制地址码
		public static final String  GIZ_BINDGIZ="bindgiz";//绑定到此Mac地址的板子
		public static final String  GIZ_USERID="userid";//用户ID，备用
		public static final String  GIZ_FLAG="flag";//留用
	}

	
	public static abstract class AlertTable implements BaseColumns{
		public static final String TABLE_NAME ="alert";
		public static final String ALERT_NAME="name";
		public static final String ALERT_TIME="time";
		public static final String GIZ_BINDGIZ="bindgiz";//绑定到此Mac地址的板子
		public static final String  GIZ_USERID="userid";//用户ID，备用
		public static final String  GIZ_FLAG="flag";//留用
		
	}
}
