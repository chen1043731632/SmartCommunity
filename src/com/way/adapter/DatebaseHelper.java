package com.way.adapter;

import java.util.ArrayList;

import com.larksmart7618.sdk.communication.tools.time.GetAndSetTime;
import com.way.tabui.gokit.R;
import com.way.util.Gizinfo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * 数据库助手类
 * @author Administrator
 *
 */

public class DatebaseHelper extends SQLiteOpenHelper {

	public static final String DB_NAME ="gizdb.db";
	public static final int VERSION = 2;
	private static final String CREATE_TABLE_GIZ ="create table giz(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,address TEXT,bindgiz TEXT,userid TEXT,flag INTEGER)";
	private static final String CREATE_TABLE_ALERT ="create table alert(_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,time TEXT,bindgiz TEXT,userid TEXT,flag INTEGER)";
	private static final String DROP_TABLE_GIZ="DROP TABLE IF EXISTS giz";
	private static final String DROP_TABLE_ALERT="DROP TABLE IF EXISTS alert";

	public DatebaseHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
		// TODO Auto-generated constructor stub
	}

	//如果数据库不存在，那么会执行该方法
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_TABLE_GIZ);
		db.execSQL(CREATE_TABLE_ALERT);
		
	}

	//数据库版本更新后会执行该方法
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL(DROP_TABLE_GIZ);
		db.execSQL(DROP_TABLE_ALERT);
		db.execSQL(CREATE_TABLE_GIZ);
		db.execSQL(CREATE_TABLE_ALERT);
	
		
		
	}

}
