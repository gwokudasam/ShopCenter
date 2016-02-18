package com.exequiel.shopcenter.framework.data.local.orm;

import android.content.Context;

import com.exequiel.shopcenter.framework.data.local.helper.DBHelper;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;


public class FactoryDaos implements IFactoryDaos {

	@Override
	public <T, I> Dao<T, I> getDaoEspecifico(Class<T> claseDtoEspecifico,
			Class<I> claseId, Context context) throws SQLException {
		Dao<T, I> dao = null;
		dao = DBHelper.getInstance(context).getDao(claseDtoEspecifico);
		return dao;
	}
}
