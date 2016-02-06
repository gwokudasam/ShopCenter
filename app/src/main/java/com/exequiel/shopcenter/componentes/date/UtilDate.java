package com.exequiel.shopcenter.componentes.date;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by exequiel on 26/08/2015.
 */
public class UtilDate {

    /**
     * Obtiene un String con el formato "dd/MM/yyyy" desde un objeto del tipo
     * Date
     *
     * @param date
     *            El objeto Date con la fecha
     * @return El String con la fecha formateada
     */
    @SuppressLint("SimpleDateFormat")
    public static String fechaToString(Date date) {
        if (date != null) {
            SimpleDateFormat fechaHora = new SimpleDateFormat("dd/MM/yyyy");
            return fechaHora.format(date);
        }else
            return "";

    }

    /**
     * Obtiene un String con el formato "HH:mm" 0-23 Horas desde un objeto del
     * tipo Date
     *
     * @param date
     *            El objeto Date con la fecha
     * @return El String con la fecha formateada
     */
    @SuppressLint("SimpleDateFormat")
    public static String horaToString(Date date) {
        if (date != null)
        {
            SimpleDateFormat fechaHora = new SimpleDateFormat("HH:mm");
            return fechaHora.format(date);
        }else
            return "";
    }

    /**
     * Obtiene un String con el formato "dd/MM/yyyy HH:mm" 0-23 Horas desde un
     * objeto del tipo Date
     *
     * @param date
     *            El objeto Date con la fecha
     * @return El String con la fecha formateada
     */
    @SuppressLint("SimpleDateFormat")
    public static String fechaHoraToString(Date date) {
        if (date != null)
        {
            SimpleDateFormat fechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            return fechaHora.format(date);
        }else
            return "";
    }

    /**
     * Convierte una Cadena con el formato "dd/MM/yyyy" a un objeto del tipo
     * Date
     *
     * @param cadena
     *            La cadena que contiene la hora en el formato necesario
     * @return Un objeto Date que contiene la hora solicitada
     */
    @SuppressLint("SimpleDateFormat")
    public static Date fechaToDate(String cadena) {
        if (cadena == null || cadena.compareTo("")==0) {
            String a = new String("00/00/0000");
            cadena = a;
        }
        SimpleDateFormat formato;
        Date fecha = null;
        try {
            formato = new SimpleDateFormat("dd/MM/yyyy");
            fecha = formato.parse(cadena);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return fecha;
    }

    /**
     * Convierte una Cadena con el formato HH:mm desde 0-23 Horas y m minutos a
     * un objeto del tipo Date
     *
     * @param cadena
     *            La cadena que contiene la hora en el formato necesario
     * @return Un objeto Date que contiene la hora solicitada
     */
    @SuppressLint("SimpleDateFormat")
    public static Date horaToDate(String cadena) {
        if (cadena == null || cadena.compareTo("")==0) {
            String a = new String("00:00");
            cadena = a;
        }
        SimpleDateFormat formato;
        Date fecha = null;
        try {
            formato = new SimpleDateFormat("HH:mm");
            fecha = formato.parse(cadena);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return fecha;
    }

    /**
     * Convierte una Cadena con el formato "dd/MM/yyyy HH:mm" desde 0-23 Horas y
     * m minutos a un objeto del tipo Date
     *
     * @param cadena
     *            La cadena que contiene la hora en el formato necesario
     * @return Un objeto Date que contiene la hora solicitada
     */
    @SuppressLint("SimpleDateFormat")
    public static Date fechaHoraToDate(String cadena) {
        if (cadena == null || cadena.compareTo("")==0) {
            cadena = "00/00/00 00:00";
        }
        SimpleDateFormat formato;
        Date fecha = null;
        try {
            formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            fecha = formato.parse(cadena);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return fecha;
    }

    public static Date fechaHoraToDate(String cadena,String formato) {
        SimpleDateFormat dateFormat;
        Date fecha = null;
        try {
            dateFormat = new SimpleDateFormat(formato);
            fecha = dateFormat.parse(cadena);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return fecha;
    }



    /**
     * Luego agregar parametro segun corresponda
     * @return
     */
    public static Date newDate(){
        return new Date();
    }

    public static long calculateDifference(Date startDate, Date endDate, boolean inDays, boolean inHours, boolean inMinutes, boolean inSeconds) {

        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long out = 0;

        if(inDays) {
            long elapsedDays = different / daysInMilli;
            different = different % daysInMilli;
            out = elapsedDays;
        }

        if(inHours) {
            long elapsedHours = different / hoursInMilli;
            different = different % hoursInMilli;
            out = elapsedHours;
        }

        if(inMinutes) {
            long elapsedMinutes = different / minutesInMilli;
            different = different % minutesInMilli;
            out = elapsedMinutes;
        }

        if(inSeconds) {
            long elapsedSeconds = different / secondsInMilli;
            out = elapsedSeconds;
        }

        return out;
    }




    /**
     * Retorna un dia de la semana
     * @param d
     * @return
     */
    public static DIA_SEMANA getDayOfTheWeek(Date d){
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(d);
        int dia=cal.get(Calendar.DAY_OF_WEEK);
        switch (dia){
            case 1:
                return DIA_SEMANA.DOMINGO;
            case 2:
                return DIA_SEMANA.LUNES;
            case 3:
                return DIA_SEMANA.MARTES;
            case 4:
                return DIA_SEMANA.MIERCOLES;
            case 5:
                return DIA_SEMANA.JUEVES;
            case 6:
                return DIA_SEMANA.VIERNES;
            case 7:
                return DIA_SEMANA.DOMINGO;
        }
        return null;
    }


    public static MES getMesHolandes(Date d){
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(d);
        int mes=cal.get(Calendar.MONTH);
        switch (mes){
            case 0:
                return MES.ENERO;
            case 1:
                return MES.FEBRERO;
            case 2:
                return MES.MARZO;
            case 3:
                return MES.ABRIL;
            case 4:
                return MES.MAYO;
            case 5:
                return MES.JUNIO;
            case 6:
                return MES.JULIO;
            case 7:
                return MES.AGOSTO;
            case 8:
                return MES.SEPTIEMBRE;
            case 9:
                return MES.OCTUBRE;
            case 10:
                return MES.NOVIEMBRE;
            case 11:
                return MES.DICIEMBRE;
        }
        return null;
    }
}
