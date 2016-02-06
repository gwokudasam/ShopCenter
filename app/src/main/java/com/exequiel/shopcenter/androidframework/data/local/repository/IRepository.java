package com.exequiel.shopcenter.androidframework.data.local.repository;

import com.exequiel.shopcenter.androidframework.data.local.BaseDto;
import com.exequiel.shopcenter.androidframework.exceptions.RepositoryException;

import java.util.List;

public interface IRepository {
	public abstract long deleteAll() throws RepositoryException;

	public abstract long deleteByIdLocal(Long idLocal)
			throws RepositoryException;

	public abstract long deleteByIdWeb(Long idWeb) throws RepositoryException;

	public abstract List<? extends BaseDto> getAll() throws RepositoryException;

    public List<? extends BaseDto> getNoSincronizado() throws RepositoryException;

	public abstract <T> T getByIdLocal(Long idLocal) throws RepositoryException;

	public abstract <T> T getByIdWeb(Long idWeb) throws RepositoryException;

	public abstract long insert(BaseDto dto) throws RepositoryException;

	public abstract long insertAll(List<BaseDto> dtos)
			throws RepositoryException;

	public abstract void sincronize(List<BaseDto> dtos)
			throws RepositoryException;

	public abstract long updateByIdLocal(BaseDto dto)
			throws RepositoryException;

	public abstract long updateByIdWeb(BaseDto dto) throws RepositoryException;
}
