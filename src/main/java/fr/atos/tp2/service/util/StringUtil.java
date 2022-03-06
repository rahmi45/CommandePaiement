package fr.atos.tp2.service.util;

public class StringUtil {

    public static boolean isEmpty(String value){
        return (value==null || value.isEmpty());
    }

    public static boolean isNotEmpty(String value){
        return !isEmpty(value);
    }
}
