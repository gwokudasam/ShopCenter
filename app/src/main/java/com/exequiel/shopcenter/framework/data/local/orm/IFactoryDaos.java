package com.exequiel.shopcenter.framework.data.local.orm;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public interface IFactoryDaos {

	public <T, I> Dao<T, I> getDaoEspecifico(Class<T> claseDtoEspecifico,
                                             Class<I> claseId, Context context) throws SQLException;
}
