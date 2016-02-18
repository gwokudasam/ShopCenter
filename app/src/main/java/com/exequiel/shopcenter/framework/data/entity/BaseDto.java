package com.exequiel.shopcenter.framework.data.entity;

import android.provider.BaseColumns;

import com.exequiel.shopcenter.framework.data.local.ContentPersonalizado;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Esta clase define la estructura basica de los objetos Dtos y se supone que
 * todos tienen una unica clave primaria, que es entera y autonumerica, esta
 * clase sirve para poder definir la interfaz en base a Dto's y lograr el
 * polimorfismo.
 * 
 * @author exequiel
 * 
 */
public abstract class BaseDto implements Serializable, BaseColumns {

	private static final long serialVersionUID = 1L;
	// Nombres de columnas
	private static final String NOMBRE_CAMPO_ID_MOVIL = _ID;
	private static final String NOMBRE_CAMPO_ID_WEB = "IdWeb";
    private static final String NOMBRE_CAMPO_SINCRONIZADO="SINCRONIZADO";

	// Nombres de campos para serializacion
	private static final String NOMBRE_CAMPO_SERIALIZADO_ID_LOCAL = "IdMovil";
	private static final String NOMBRE_CAMPO_SERIALIZADO_ID_WEB = "IdWeb";
	private static final String NOMBRE_CAMPO_SERIALIZADO_SINC = "Sinc";


	public static String getNombreCampoIdWeb() {
		return NOMBRE_CAMPO_ID_WEB;
	}
    public static String getNombreCampoSincronizado() {
        return NOMBRE_CAMPO_SINCRONIZADO;
    }

    @DatabaseField(columnName = NOMBRE_CAMPO_ID_MOVIL, generatedId = true)
	@SerializedName(NOMBRE_CAMPO_SERIALIZADO_ID_LOCAL)
	private long idLocal;

	@DatabaseField(columnName = NOMBRE_CAMPO_ID_WEB)
	@SerializedName(NOMBRE_CAMPO_SERIALIZADO_ID_WEB)
	private long idWeb;

    @DatabaseField(columnName = NOMBRE_CAMPO_SINCRONIZADO, dataType = DataType.BOOLEAN)
    @SerializedName(NOMBRE_CAMPO_SERIALIZADO_SINC)
    private boolean sinc;


	public BaseDto() {
		this.idLocal = -1;
	}

	public BaseDto(long idWeb) {
		super();
		this.idLocal = -1;
		this.idWeb = idWeb;
	}

    protected BaseDto(boolean sinc, long idLocal, long idWeb) {
        this.sinc = sinc;
        this.idLocal = idLocal;
        this.idWeb = idWeb;
    }

    public boolean isSinc() {
        return sinc;
    }

    public void setSinc(boolean sinc) {
        this.sinc = sinc;
    }

    public long getIdWeb() {
		return idWeb;
	}

	public void setIdWeb(long idWeb) {
		this.idWeb = idWeb;
	}

	public long getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(long idLocal) {
		this.idLocal = idLocal;
	}

	@Override
	public String toString() {
		return "DTO - Nro : " + idLocal;
	}

	public String getNombCampoIdMovil() {
		return NOMBRE_CAMPO_ID_MOVIL;
	}

	public String getNombCampoIdWeb() {
		return NOMBRE_CAMPO_ID_WEB;
	}

    public abstract ContentPersonalizado getValuesPair();

}
