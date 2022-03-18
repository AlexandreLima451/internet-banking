package br.com.rest.internetbanking.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Converter {

    public static Date stringToDate(String dataTexto){
        Date data = new Date();

        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(dataTexto);
        }catch ( ParseException ex) {
            ex.printStackTrace();
        }
        return data;
    }
}
