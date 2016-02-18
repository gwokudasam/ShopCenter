package com.exequiel.shopcenter.framework.data.local.repository;


import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.exequiel.shopcenter.framework.data.entity.BaseDto;
import com.exequiel.shopcenter.framework.data.local.ContentPersonalizado;
import com.exequiel.shopcenter.framework.data.local.helper.DBHelper;
import com.exequiel.shopcenter.framework.data.local.orm.FactoryDaos;
import com.exequiel.shopcenter.framework.exceptions.RepositoryException;
import com.exequiel.shopcenter.framework.ui.loaders.RepositoryLoader;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {


	private Class<? extends BaseDto> classDto;
	private Class<?> classId;
	private Context context;
	private Dao daoEspecifico;

	public Repository(Context context, Class<? extends BaseDto> classDto,
			Class<?> classId) throws RepositoryException {
		this.classDto = classDto;
		this.classId = classId;
		this.context = context;
		daoEspecifico = abrirConexion();
	}

	@SuppressWarnings("rawtypes")
	private Dao abrirConexion() throws RepositoryException {
		FactoryDaos factoryDaos = new FactoryDaos();
		Dao daoEspecifico;
		try {
			daoEspecifico = factoryDaos.getDaoEspecifico(classDto, classId,
					context);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RepositoryException(
					RepositoryException.ERROR_ABRIENDO_CONEXION);
		}
		return daoEspecifico;
	}

	public static void cerrarConexion(Context context) {
		DBHelper.closeInstance(context);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public long deleteAll() throws RepositoryException {
		DeleteBuilder deleteBuilder = daoEspecifico.deleteBuilder();
		long resp = 0;
		try {
			resp = daoEspecifico.delete(deleteBuilder.prepare());
            lanzarBroadcast();
		} catch (SQLException e) {
			e.printStackTrace();
			new RepositoryException(RepositoryException.ERROR_DELETE);
		}
		return resp;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public long deleteByIdLocal(Long idLocal) throws RepositoryException {
		long resp = 0;
		DeleteBuilder deleteBuilder = daoEspecifico.deleteBuilder();
		try {
			deleteBuilder.setWhere(deleteBuilder.where().eq(BaseDto._ID,
					idLocal));
			resp = daoEspecifico.delete(deleteBuilder.prepare());
            lanzarBroadcast();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RepositoryException(RepositoryException.ERROR_DELETE);
		}
		return resp;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public long deleteByIdWeb(Long idWeb) throws RepositoryException {
		DeleteBuilder deleteBuilder = daoEspecifico.deleteBuilder();
		long resp = 0;
		try {
			deleteBuilder.setWhere(deleteBuilder.where().eq(
					BaseDto.getNombreCampoIdWeb(), idWeb));
			resp = daoEspecifico.delete(deleteBuilder.prepare());
            lanzarBroadcast();
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new RepositoryException(RepositoryException.ERROR_DELETE);
		}
		return resp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<? extends BaseDto> getAll() throws RepositoryException {
		List<? extends BaseDto> lista = new ArrayList<BaseDto>();
		try {
			lista = daoEspecifico.queryForAll();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RepositoryException(RepositoryException.ERROR_SELECT);
		}
		return lista;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object getByIdLocal(Long idLocal) throws RepositoryException {
		QueryBuilder queryBuilder = daoEspecifico.queryBuilder();
		Object dto = null;
		try {
			queryBuilder
					.setWhere(queryBuilder.where().eq(BaseDto._ID, idLocal));
			dto = daoEspecifico.queryForId(idLocal);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RepositoryException(RepositoryException.ERROR_SELECT);
		}
		return dto;
	}

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public List<? extends BaseDto> getNoSincronizado() throws RepositoryException {
        QueryBuilder queryBuilder = daoEspecifico.queryBuilder();
        List<? extends BaseDto> dtos = null;
        try {
            queryBuilder
                    .setWhere(queryBuilder.where().eq(BaseDto.getNombreCampoSincronizado(), false));
            dtos = daoEspecifico.query(queryBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RepositoryException(RepositoryException.ERROR_SELECT);
        }
        return dtos;
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object getByIdWeb(Long idWeb) throws RepositoryException {
		QueryBuilder queryBuilder = daoEspecifico.queryBuilder();
		Object dto = null;
		try {
			queryBuilder.setWhere(queryBuilder.where().eq(
					BaseDto.getNombreCampoIdWeb(), idWeb));
			dto = daoEspecifico.query(queryBuilder.prepare());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RepositoryException(RepositoryException.ERROR_SELECT);
		}
		return dto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public long insert(BaseDto dto) throws RepositoryException {
		long re = 0;
		try {
			re = daoEspecifico.create(dto);
            lanzarBroadcast();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RepositoryException(RepositoryException.ERROR_INSERT);
		}
		return re;
	}

	@Override
	public void sincronize(List<BaseDto> dtos) throws RepositoryException {
		deleteAll();
		insertAll(dtos);
	}

	@SuppressWarnings("unchecked")
	@Override
	public long updateByIdLocal(BaseDto dto) throws RepositoryException {
		long resp = 0;
		try {
			resp = daoEspecifico.update(dto);
            lanzarBroadcast();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RepositoryException(RepositoryException.ERROR_UPDATE);
		}
		return resp;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public long updateByIdWeb(BaseDto dto) throws RepositoryException {
		long resp = 0;
		try {
			UpdateBuilder updateBuilder = daoEspecifico.updateBuilder();
			updateBuilder.setWhere(updateBuilder.where().eq("IdWeb",
					String.valueOf(dto.getIdWeb())));
            ContentPersonalizado content = dto.getValuesPair();
            for (int i = 0; i<content.size();i++){
                ContentPersonalizado.ValuePair value = content.get(i);
                updateBuilder.updateColumnValue(value.getNombColum(),
                        value.getData());
            }
			resp = daoEspecifico.update(updateBuilder.prepare());
            lanzarBroadcast();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RepositoryException(RepositoryException.ERROR_UPDATE);
		}
		return resp;
	}

	//TODO verificar que se pueda hacer con el parametro <?
	@SuppressWarnings("unchecked")
	@Override
	public long insertAll(List<BaseDto> dtos) throws RepositoryException {
		long resp = 0;
		try {
			for (BaseDto elem : dtos) {
				daoEspecifico.create(elem);
				resp++;
			}
            lanzarBroadcast();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RepositoryException(RepositoryException.ERROR_INSERT);
		}
		return resp;
	}

    private void lanzarBroadcast(){
        Intent i = new Intent();
        i.setAction(RepositoryLoader.ACTION_BROADCAST_REPOSITORY_UPDATE);
        i.putExtra(RepositoryLoader.EXTRA_HASH_CODE_CLASS_NAME_OBSERVER, classDto.toString().hashCode());
        LocalBroadcastManager.getInstance(context).sendBroadcast(i);
    }
}
