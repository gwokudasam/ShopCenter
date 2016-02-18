package com.exequiel.shopcenter.framework.data.local.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;

public class DBHelper extends OrmLiteSqliteOpenHelper {
	private final static String DATABASE_NAME = "asdb.sqlite";
	private final static String RUTA_ALTERNATIVA_DESARROLLADOR = "/mnt/sdcard/"
			+ DATABASE_NAME;
	private final static int DATABASE_VERSION = 1;

	private static DBHelper instance;

	public DBHelper(Context context) {
		super(context, RUTA_ALTERNATIVA_DESARROLLADOR, null, DATABASE_VERSION);
	}

	public static void iniciarBD(Context context) {
		getInstance(context).getWritableDatabase();
	}

	public static DBHelper getInstance(Context context) {
		if (instance == null) {
			instance = OpenHelperManager.getHelper(context, DBHelper.class);
		}
		return instance;
	}

	public static void closeInstance(Context context) {
		if (instance != null) {
			OpenHelperManager.releaseHelper();
			instance = null;
		}
	}

	@Override
	public void onCreate(SQLiteDatabase arg0, ConnectionSource arg1) {
		createTables();
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
			upgradeTables();
	}

	public void createTables(){

	}

	public void upgradeTables(){

	}
}
