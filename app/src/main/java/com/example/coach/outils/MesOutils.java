package com.example.coach.outils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class MesOutils {

    /**
     * Conversion d'une date du format String vers le format Date avec un format reçu
     * @param uneDate au format String
     * @param expectedPattern
     * @return la date au format date
     */
    public static Date convertStringToDate(String uneDate, String expectedPattern){
        SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
        try {
            Date date = formatter.parse(uneDate);
                return date;
            }catch(ParseException e){
            e.printStackTrace();
        }
            return null;
        }

        public static Date convertStringToDate(String uneDate){
        return convertStringToDate(uneDate, "EEE MMM dd hh:mm:ss 'GMT+00:00' yyyy");
        }
    /**
     * Permet de convertir une Date en String
     * @param uneDate
     * @return String
     */
    public static String convertDateToString(Date uneDate){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return date.format(uneDate);
    }

    public static String format2Decimal(Float unevaleur){
        return String.format("%.01f",unevaleur);
    }
}
