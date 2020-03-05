package com.example.b.utilidades;

public class Utilidades {
    /**Constantes campos tabla**/

    public static String TABLA_COSAS="objetoCosa";
    public static String CAMPO_ID="id";
    public static String CAMPO_COSA="cosa";
    public static String CAMPO_CANTIDAD="cantidad";

public static final String CREAR_TABLA_COSAS= "CREATE TABLE "+TABLA_COSAS
        +" ("+CAMPO_ID+" INTEGER, "
        +CAMPO_COSA+" TEXT, "
        +CAMPO_CANTIDAD+" TEXT)";


}
