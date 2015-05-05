package com.example.macbook.surveyapp_i1;


//Testing comit.


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHandler {

    public static final String KEY_ROWID = "rowid";
	public static final String KEY_NAME = "Name";
    public static final String KEY_LAST = "Last";
    public static final String KEY_AGE = "Age";
    public static final String KEY_PHONE = "Phone";
    public static final String KEY_TOKEN = "Token";
	public static final String TABLE_NAME = "myTable";
	public static final String DATABASE_NAME = "myDatabase";
	public static final int DATABASE_VERSION = 1;
	// public static final String CREATE_TABLE =
	// "create table myTable(Year text not null,Mileage text not null);";

	DatabaseHelper dbhelper;
	Context ctx;
	SQLiteDatabase db;

	public DataHandler(Context ctx) {
		this.ctx = ctx;
		dbhelper = new DatabaseHelper(ctx);
	}


	private static class DatabaseHelper extends SQLiteOpenHelper {

		public DatabaseHelper(Context ctx) {
			super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub

			db.execSQL("CREATE TABLE " + TABLE_NAME + "("
			+ KEY_ROWID + " integer primary key autoincrement not null, "

			+ KEY_NAME + " text not null, "
                    + KEY_LAST + " text not null, "
                    + KEY_AGE + " integer not NULL, "
			+ KEY_PHONE + " text not null, "
                    + KEY_TOKEN + " integer not null);");
		}


		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS myTable");
			onCreate(db);
		}
	}

	public DataHandler open() {
		db = dbhelper.getWritableDatabase();
		return this;
	}

	public void close() {
		dbhelper.close();
	}

	public long createEntry(String Name, String Last, String Age, String Phone, String Token) {
		ContentValues content = new ContentValues();
		content.put(KEY_NAME, Name);
        content.put(KEY_LAST, Last);
        content.put(KEY_AGE, Age);
        content.put(KEY_PHONE, Phone);
        content.put(KEY_PHONE, Token);
		return db.insert(TABLE_NAME, null, content);
	}


    public Cursor getRow(long rowId) {
        String where = KEY_ROWID + "=" + rowId;
        String[] columns = new String[] {KEY_ROWID,KEY_NAME, KEY_LAST, KEY_AGE, KEY_PHONE};
        Cursor c= db.query(TABLE_NAME, columns, where, null, null, null, null, null);

        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }


    public Cursor getAllRows() {
        String where = null;
        String[] columns = new String[] {KEY_ROWID,KEY_NAME, KEY_LAST, KEY_AGE, KEY_PHONE};
        Cursor c= db.query(TABLE_NAME, columns, where, null, null, null, null, null);

        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }


	public String getData() {
		// TODO Auto-generated method stub

        String[] columns = new String[] { KEY_ROWID, KEY_NAME,
				KEY_LAST, KEY_AGE,KEY_PHONE };

		Cursor c = db.query(TABLE_NAME, columns, null, null, null, null, null);
		// Cursor c= db.query(TABLE_NAME, columns, null, null, null, null, null,
		// null);

		String result = "";
		//int iRow = c.getColumnIndex(KEY_ROWID);
		int iName = c.getColumnIndex(KEY_NAME);
        int iLast = c.getColumnIndex(KEY_LAST);
        int iAge = c.getColumnIndex(KEY_AGE);
        int iPhone = c.getColumnIndex(KEY_PHONE);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result = result 
					//+ c.getString(iRow)
					+ " " + c.getString(iName)
					+ "     " + c.getString(iLast)
                    + "     " + c.getString(iAge)
                    + "     " + c.getString(iPhone)+ "\n";
		}
		return result;
	}


    public String getFirstLast() {
        // TODO Auto-generated method stub

        String[] columns = new String[] {KEY_NAME, KEY_LAST};
        Cursor c = db.query(TABLE_NAME, columns, null, null, null, null, null);
        String result = "";
        int iName = c.getColumnIndex(KEY_NAME);
        int iLast = c.getColumnIndex(KEY_LAST);

        //for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
        c.moveToFirst();
            result = result
                     + c.getString(iName)
                     + " " + c.getString(iLast);
       // }
        return result;
    }


    public String getName() {
        // TODO Auto-generated method stub
        String[] columns = new String[] {KEY_NAME};
        Cursor c = db.query(TABLE_NAME, columns, null, null, null, null, null);
        String result = "";
        int iName = c.getColumnIndex(KEY_NAME);
      //  for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
        c.moveToFirst();
            result = result
                    + " " + c.getString(iName);
            //  }
        return result;
    }


    public String getLast() {
        // TODO Auto-generated method stub
        String[] columns = new String[] {KEY_LAST};
        Cursor c = db.query(TABLE_NAME, columns, null, null, null, null, null);
        String result = "";
        int iName = c.getColumnIndex(KEY_LAST);
      //  for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
        c.moveToFirst();
            result = result
                    + c.getString(iName);
      //  }
        return result;
    }


    public String getAge() {
        // TODO Auto-generated method stub
        String[] columns = new String[] {KEY_AGE};
        Cursor c = db.query(TABLE_NAME, columns, null, null, null, null, null);
        String result = "";
        int iName = c.getColumnIndex(KEY_AGE);
       // for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            c.moveToFirst();
            result = result
                     + c.getString(iName);
      //  }
        return result;
    }


    public String getPhone() {
        // TODO Auto-generated method stub
        String[] columns = new String[] {KEY_PHONE};
        Cursor c = db.query(TABLE_NAME, columns, null, null, null, null, null);
        String result = "";
        int iName = c.getColumnIndex(KEY_PHONE);
      //  for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            c.moveToFirst();
            result = result
                     + c.getString(iName);
     //   }
        return result;
    }


    public String getId() {
        // TODO Auto-generated method stub
        String[] columns = new String[] {KEY_ROWID};
        Cursor c = db.query(TABLE_NAME, columns, null, null, null, null, null);
        String result = "";
        int iName = c.getColumnIndex(KEY_ROWID);
        //  for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
        c.moveToFirst();
        result = result
                + c.getString(iName);
        //   }
        return result;
    }


    //Delete a row from the database, by rowId (primary key)
    public boolean deleteRow(long rowId) {
        String where = KEY_ROWID + "=" + rowId;
        return db.delete(TABLE_NAME, where, null) != 0;
    }

}
