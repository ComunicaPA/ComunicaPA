package org.source.comunicapa.datastore;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ComunicaPADatabaseHelper extends SQLiteOpenHelper {

	public ComunicaPADatabaseHelper(Context context) {
		super(context, DbConstants.DATABASE_NAME, null, DbConstants.DATABASE_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		StringBuilder query = new StringBuilder();
		// Table Email

		query.append(" CREATE TABLE ");
		query.append(DbConstants.EMAIL_TABLE).append("(");
		query.append(DbConstants.ID_COLUMN).append(" INTEGER PRIMARY KEY AUTOINCREMENT ,");
		query.append(DbConstants.DATE_COLUMN).append(" INTEGER ,");
		query.append(DbConstants.EMAIL_COLUMN).append(" TEXT ,");
		query.append(DbConstants.SUBJECT_COLUMN).append(" TEXT ,");
		query.append(DbConstants.BODY_COLUMN).append(" TEXT ,");
		query.append(DbConstants.ISSENT_COLUMN).append(" INTEGER ");
		query.append(" ) ");

		db.beginTransaction();
		try {
			System.out.println(query.toString());
			db.execSQL(query.toString());
			db.setTransactionSuccessful();
			System.out.println("Transaction OK- table created " + DbConstants.EMAIL_TABLE);
		} finally {
			db.endTransaction();
		}

		query = new StringBuilder();
		// Table Rubric

		query.append(" CREATE TABLE ");
		query.append(DbConstants.RUBRIC_TABLE).append("(");
		query.append(DbConstants.ID_COLUMN).append(" INTEGER PRIMARY KEY AUTOINCREMENT ,");
		query.append(DbConstants.LABEL_COLUMN).append(" TEXT ,");
		query.append(DbConstants.NAME_COLUMN).append(" TEXT ,");
		query.append(DbConstants.PHONE_COLUMN).append(" TEXT ,");
		query.append(DbConstants.EMAIL_COLUMN).append(" TEXT ");

		query.append(" ) ");

		db.beginTransaction();
		try {
			db.execSQL(query.toString());
			db.setTransactionSuccessful();
			System.out.println("Transaction OK- table created " + DbConstants.RUBRIC_TABLE);
		} finally {
			db.endTransaction();
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL("DROP TABLE IF EXISTS " + DbConstants.EMAIL_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + DbConstants.RUBRIC_TABLE);
		onCreate(db);

	}

}
