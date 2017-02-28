package com.way.adapter;

import java.util.ArrayList;

import com.way.util.Alertinfo;
import com.way.util.GizMetaData;
import com.way.util.Gizinfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAdapter {

	private DatebaseHelper dbHelper;
    
	public DatabaseAdapter(Context context){
		dbHelper= new DatebaseHelper(context);
	}
	public void add(Gizinfo gizinfo){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		if(gizinfo.getId()!=0){
			values.put(GizMetaData.GizTable.GIZ_ID,  gizinfo.getId());
		}
		
		values.put(GizMetaData.GizTable.GIZ_NAME,  gizinfo.getName());
		values.put(GizMetaData.GizTable.GIZ_ADDRESS, gizinfo.getAddress());
		values.put(GizMetaData.GizTable.GIZ_BINDGIZ, gizinfo.getBindgiz());
		values.put(GizMetaData.GizTable.GIZ_USERID, gizinfo.getUserid());
		values.put(GizMetaData.GizTable.GIZ_FLAG, gizinfo.getFlag());
		
		//参数说明(表名,可以为空的列名，ContentValues)
		db.insert(GizMetaData.GizTable.TABLE_NAME, null, values);
		
		db.close();
	}
	
	public void addalert(Alertinfo alertinfo){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		if(alertinfo.getId()!=0){
			values.put(GizMetaData.AlertTable._ID,  alertinfo.getId());
		}
		
		values.put(GizMetaData.AlertTable.ALERT_NAME,  alertinfo.getName());
		values.put(GizMetaData.AlertTable.ALERT_TIME, alertinfo.getTime());
		values.put(GizMetaData.AlertTable.GIZ_BINDGIZ, alertinfo.getBindgiz());
		values.put(GizMetaData.AlertTable.GIZ_USERID, alertinfo.getUserid());
		values.put(GizMetaData.AlertTable.GIZ_FLAG, alertinfo.getFlag());
		
		//参数说明(表名,可以为空的列名，ContentValues)
		db.insert(GizMetaData.AlertTable.TABLE_NAME, null, values);
		db.close();
	}
	
	public void delete(int id){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		String whereClause =GizMetaData.GizTable.GIZ_ID+"=?";
		String[] whereArgs = {String.valueOf(id)};
		//参数说明(表名,条件，条件的值)
		db.delete(GizMetaData.GizTable.TABLE_NAME, whereClause, whereArgs);
		db.close();
	}
	public void deletealert(int id){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		String whereClause =GizMetaData.AlertTable._ID+"=?";
		String[] whereArgs = {String.valueOf(id)};
		//参数说明(表名,条件，条件的值)
		db.delete(GizMetaData.AlertTable.TABLE_NAME, whereClause, whereArgs);
		db.close();
	}
	
	public void update(Gizinfo gizinfo){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(GizMetaData.GizTable.GIZ_NAME, gizinfo.getName());
		values.put(GizMetaData.GizTable.GIZ_ADDRESS, gizinfo.getAddress());
		values.put(GizMetaData.GizTable.GIZ_BINDGIZ, gizinfo.getBindgiz());
		values.put(GizMetaData.GizTable.GIZ_FLAG, gizinfo.getFlag());
		values.put(GizMetaData.GizTable.GIZ_USERID, gizinfo.getUserid());
		String whereClause =GizMetaData.GizTable.GIZ_ID+"=?";
		String[] whereArgs = {String.valueOf(gizinfo.getId())};
		//参数说明(表名,ContentValues,条件，条件的值)
		db.update(GizMetaData.GizTable.TABLE_NAME, values, whereClause, whereArgs);
	}
	
	public void updatealert(Alertinfo alertinfo){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(GizMetaData.AlertTable.ALERT_NAME,  alertinfo.getName());
		values.put(GizMetaData.AlertTable.ALERT_NAME, alertinfo.getTime());
		values.put(GizMetaData.AlertTable.GIZ_BINDGIZ, alertinfo.getBindgiz());
		values.put(GizMetaData.AlertTable.GIZ_USERID, alertinfo.getUserid());
		values.put(GizMetaData.AlertTable.GIZ_FLAG, alertinfo.getFlag());
		String whereClause =GizMetaData.GizTable.GIZ_ID+"=?";
		String[] whereArgs = {String.valueOf(alertinfo.getId())};
		//参数说明(表名,ContentValues,条件，条件的值)
		db.update(GizMetaData.AlertTable.TABLE_NAME, values, whereClause, whereArgs);
	}
	
	
	public Gizinfo findById(int id){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		String[] columns={GizMetaData.GizTable.GIZ_ID,GizMetaData.GizTable.GIZ_NAME,GizMetaData.GizTable.GIZ_ADDRESS,GizMetaData.GizTable.GIZ_BINDGIZ,GizMetaData.GizTable.GIZ_USERID,GizMetaData.GizTable.GIZ_FLAG};
		String whereClause =GizMetaData.GizTable.GIZ_ID+"=?";
		String[] whereArgs = {String.valueOf(id)};
		//参数说明(是否去除重复记录,表明,要查询的列，查询条件，查询条件的值，分组条件，分组条件的值，排序，排序条件)
		Cursor c = db.query(true, GizMetaData.GizTable.TABLE_NAME, columns,whereClause, whereArgs, null, null, null, null);
		Gizinfo gizinfo =null;
	   while(c.moveToNext()){
		   gizinfo=new Gizinfo();
		   gizinfo.setId(c.getInt(c.getColumnIndexOrThrow(GizMetaData.GizTable.GIZ_ID)));
		   gizinfo.setName(c.getString(c.getColumnIndexOrThrow(GizMetaData.GizTable.GIZ_NAME)));
		   gizinfo.setAddress(c.getString(c.getColumnIndexOrThrow(GizMetaData.GizTable.GIZ_ADDRESS)));
		   gizinfo.setBindgiz(c.getString(c.getColumnIndexOrThrow(GizMetaData.GizTable.GIZ_BINDGIZ)));
		   gizinfo.setUserid(c.getString(c.getColumnIndexOrThrow(GizMetaData.GizTable.GIZ_USERID)));
		   gizinfo.setFlag(c.getInt(c.getColumnIndexOrThrow(GizMetaData.GizTable.GIZ_FLAG)));
	   }
	   c.close();
	   db.close();
		return gizinfo;
	}
	

	public ArrayList<Gizinfo> findbybindgiz(String bindgiz){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		String whereClause =GizMetaData.GizTable.GIZ_BINDGIZ+"=?";
		String[] whereArgs = {bindgiz};
		String[] columns={GizMetaData.GizTable.GIZ_ID,GizMetaData.GizTable.GIZ_NAME,GizMetaData.GizTable.GIZ_ADDRESS,GizMetaData.GizTable.GIZ_BINDGIZ,GizMetaData.GizTable.GIZ_USERID,GizMetaData.GizTable.GIZ_FLAG};
		//参数说明(是否去除重复记录,表明,要查询的列，查询条件，查询条件的值，分组条件，分组条件的值，排序，排序条件)
		Cursor c = db.query(true, GizMetaData.GizTable.TABLE_NAME, columns, whereClause, whereArgs, null, null, null, null);
		ArrayList<Gizinfo> gizs = new ArrayList<Gizinfo>(); 
		Gizinfo gizinfo =null;
	   while(c.moveToNext()){
		   gizinfo=new Gizinfo();
		   gizinfo.setId(c.getInt(c.getColumnIndexOrThrow(GizMetaData.GizTable.GIZ_ID)));
		   gizinfo.setName(c.getString(c.getColumnIndexOrThrow(GizMetaData.GizTable.GIZ_NAME)));
		   gizinfo.setAddress(c.getString(c.getColumnIndexOrThrow(GizMetaData.GizTable.GIZ_ADDRESS)));
		   gizinfo.setBindgiz(c.getString(c.getColumnIndexOrThrow(GizMetaData.GizTable.GIZ_BINDGIZ)));
		   gizinfo.setUserid(c.getString(c.getColumnIndexOrThrow(GizMetaData.GizTable.GIZ_USERID)));
		   gizinfo.setFlag(c.getInt(c.getColumnIndexOrThrow(GizMetaData.GizTable.GIZ_FLAG)));
		   gizs.add(gizinfo);
	   }
	   c.close();
	   db.close();
	   return gizs;
		
	}
	
	public ArrayList<Alertinfo> findbybindgizalert(String bindgiz){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		String whereClause =GizMetaData.AlertTable.GIZ_BINDGIZ+"=?";
		String[] whereArgs = {bindgiz};
		String[] columns={GizMetaData.AlertTable._ID,GizMetaData.AlertTable.ALERT_NAME,GizMetaData.AlertTable.ALERT_TIME,GizMetaData.AlertTable.GIZ_BINDGIZ,GizMetaData.AlertTable.GIZ_USERID,GizMetaData.AlertTable.GIZ_FLAG};
		//参数说明(是否去除重复记录,表明,要查询的列，查询条件，查询条件的值，分组条件，分组条件的值，排序，排序条件)GizMetaData.AlertTable._ID+" DESC"
		Cursor c = db.query(true, GizMetaData.AlertTable.TABLE_NAME, columns, whereClause, whereArgs, null, null,GizMetaData.AlertTable._ID+" DESC", null);
		ArrayList<Alertinfo> alerts = new ArrayList<Alertinfo>(); 
		Alertinfo alertinfo =null;
	   while(c.moveToNext()){
		   alertinfo=new Alertinfo();
		   alertinfo.setId(c.getInt(c.getColumnIndexOrThrow(GizMetaData.AlertTable._ID)));
		   alertinfo.setName(c.getString(c.getColumnIndexOrThrow(GizMetaData.AlertTable.ALERT_NAME)));
		   alertinfo.setTime(c.getString(c.getColumnIndexOrThrow(GizMetaData.AlertTable.ALERT_TIME)));
		   alertinfo.setBindgiz(c.getString(c.getColumnIndexOrThrow(GizMetaData.AlertTable.GIZ_BINDGIZ)));
		   alertinfo.setUserid(c.getString(c.getColumnIndexOrThrow(GizMetaData.AlertTable.GIZ_USERID)));
		   alertinfo.setFlag(c.getInt(c.getColumnIndexOrThrow(GizMetaData.AlertTable.GIZ_FLAG)));
		   alerts.add(alertinfo);
	   }
	   c.close();
	   db.close();
	   return alerts;
		
	}
	
	public Alertinfo findbybindgizname(String bindgiz,String name){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		String whereClause =GizMetaData.AlertTable.GIZ_BINDGIZ+"=? and "+GizMetaData.AlertTable.ALERT_NAME+"=?";
		String[] whereArgs = {bindgiz,name};
		String[] columns={GizMetaData.AlertTable._ID,GizMetaData.AlertTable.ALERT_NAME,GizMetaData.AlertTable.ALERT_TIME,GizMetaData.AlertTable.GIZ_BINDGIZ,GizMetaData.AlertTable.GIZ_USERID,GizMetaData.AlertTable.GIZ_FLAG};
		//参数说明(是否去除重复记录,表明,要查询的列，查询条件，查询条件的值，分组条件，分组条件的值，排序，排序条件)GizMetaData.AlertTable._ID+" DESC"
		Cursor c = db.query(true, GizMetaData.AlertTable.TABLE_NAME, columns, whereClause, whereArgs, null, null,GizMetaData.AlertTable._ID+" DESC", null);
		Alertinfo alertinfo =null;
		if(c.moveToFirst()){
		   alertinfo=new Alertinfo();
		   alertinfo.setId(c.getInt(c.getColumnIndexOrThrow(GizMetaData.AlertTable._ID)));
		   alertinfo.setName(c.getString(c.getColumnIndexOrThrow(GizMetaData.AlertTable.ALERT_NAME)));
		   alertinfo.setTime(c.getString(c.getColumnIndexOrThrow(GizMetaData.AlertTable.ALERT_TIME)));
		   alertinfo.setBindgiz(c.getString(c.getColumnIndexOrThrow(GizMetaData.AlertTable.GIZ_BINDGIZ)));
		   alertinfo.setUserid(c.getString(c.getColumnIndexOrThrow(GizMetaData.AlertTable.GIZ_USERID)));
		   alertinfo.setFlag(c.getInt(c.getColumnIndexOrThrow(GizMetaData.AlertTable.GIZ_FLAG)));
		   c.close();
		   db.close();
		   return alertinfo;
		   }else{
			   c.close();
			   db.close();
			   return null;
		   }


	  
		
	}
	
	public ArrayList<Gizinfo> findAll(){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		String[] columns={GizMetaData.GizTable.GIZ_ID,GizMetaData.GizTable.GIZ_NAME,GizMetaData.GizTable.GIZ_ADDRESS,GizMetaData.GizTable.GIZ_BINDGIZ,GizMetaData.GizTable.GIZ_USERID,GizMetaData.GizTable.GIZ_FLAG};
		//参数说明(是否去除重复记录,表明,要查询的列，查询条件，查询条件的值，分组条件，分组条件的值，排序，排序条件)
		Cursor c = db.query(true, GizMetaData.GizTable.TABLE_NAME, columns, null, null, null, null, null, null);
		ArrayList<Gizinfo> gizs = new ArrayList<Gizinfo>(); 
		Gizinfo gizinfo =null;
	   while(c.moveToNext()){
		   gizinfo=new Gizinfo();
		   gizinfo.setId(c.getInt(c.getColumnIndexOrThrow(GizMetaData.GizTable.GIZ_ID)));
		   gizinfo.setName(c.getString(c.getColumnIndexOrThrow(GizMetaData.GizTable.GIZ_NAME)));
		   gizinfo.setAddress(c.getString(c.getColumnIndexOrThrow(GizMetaData.GizTable.GIZ_ADDRESS)));
		   gizinfo.setBindgiz(c.getString(c.getColumnIndexOrThrow(GizMetaData.GizTable.GIZ_BINDGIZ)));
		   gizinfo.setUserid(c.getString(c.getColumnIndexOrThrow(GizMetaData.GizTable.GIZ_USERID)));
		   gizinfo.setFlag(c.getInt(c.getColumnIndexOrThrow(GizMetaData.GizTable.GIZ_FLAG)));
		   gizs.add(gizinfo);
	   }
	   c.close();
	   db.close();
	   return gizs;
		
	}
	
	
}
