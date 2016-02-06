package com.exequiel.shopcenter.androidframework.exceptions;

import java.sql.SQLException;

public class RepositoryException extends SQLException {
	private static final long serialVersionUID = 1L;
	public static final String ERROR_ABRIENDO_CONEXION = "Error obtener el dao espec?fico";
	public static final String ERROR_DELETE = "Error al borrar el dto";
	public static final String ERROR_INSERT = "Error al insertar el dto";
	public static final String ERROR_UPDATE = "Error al actualizar el dto";
	public static final String ERROR_SELECT = "Error al seleccionar el dto";

	public RepositoryException() {
		super("Error al trabajar con la base de datos local");
	}

	public RepositoryException(String msj) {
		super(msj);
	}
}
