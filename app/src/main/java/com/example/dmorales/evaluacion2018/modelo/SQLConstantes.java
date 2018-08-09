package com.example.dmorales.evaluacion2018.modelo;

public class SQLConstantes {
    public static String DB_PATH = "/data/data/com.example.dmorales.evaluacion2018/databases/";
    public static String DB_NAME = "mydatabase.sqlite";

    public static String tablanacional = "nacional";
    public static String tablausuariolocal = "usuario_local";
    public static String tablafecharegistro = "fecha_registro";
    public static String tablafecharegistrotemporal = "fecha_registro_temporal";


    public static String nacional_codigo = "codigo";
    public static String nacional_sede = "sede";
    public static String nacional_id_local = "id_local";
    public static String nacional_nom_local = "nom_local";
    public static String nacional_aula = "aula";
    public static String nacional_nombres = "nombres";
    public static String nacional_cargo = "cargo";

    public static String usuario_local_usuario = "usuario";
    public static String usuario_local_clave = "clave";
    public static String usuario_local_nombrelocal = "nombreLocal";
    public static String usuario_local_sede = "sede";

    public static String fecha_de_registro_id = "_id";
    public static String fecha_de_registro_codigo = "codigo";
    public static String fecha_de_registro_sede = "sede";
    public static String fecha_de_registro_id_local = "id_local";
    public static String fecha_de_registro_nom_local = "nom_local";
    public static String fecha_de_registro_aula = "aula";
    public static String fecha_de_registro_nombres = "nombres";
    public static String fecha_de_registro_dia1 = "dia1";
    public static String fecha_de_registro_mes1 = "mes1";
    public static String fecha_de_registro_anio1 = "anio1";
    public static String fecha_de_registro_hora1 = "hora1";
    public static String fecha_de_registro_minuto1 = "minuto1";
    public static String fecha_de_registro_dia2 = "dia2";
    public static String fecha_de_registro_mes2 = "mes2";
    public static String fecha_de_registro_anio2 = "anio2";
    public static String fecha_de_registro_hora2 = "hora2";
    public static String fecha_de_registro_minuto2 = "minuto2";
    public static String fecha_de_registro_dia3 = "dia3";
    public static String fecha_de_registro_mes3 = "mes3";
    public static String fecha_de_registro_anio3 = "anio3";
    public static String fecha_de_registro_hora3 = "hora3";
    public static String fecha_de_registro_minuto3 = "minuto3";
    public static String fecha_de_registro_dia4 = "dia4";
    public static String fecha_de_registro_mes4 = "mes4";
    public static String fecha_de_registro_anio4 = "anio4";
    public static String fecha_de_registro_hora4 = "hora4";
    public static String fecha_de_registro_minuto4 = "minuto4";
    public static String fecha_de_registro_estado1 = "estado1";
    public static String fecha_de_registro_estado2 = "estado2";
    public static String fecha_de_registro_estado3 = "estado3";
    public static String fecha_de_registro_estado4 = "estado4";
    public static String fecha_de_registro_subido1 = "subido1";
    public static String fecha_de_registro_subido2 = "subido2";
    public static String fecha_de_registro_subido3 = "subido3";
    public static String fecha_de_registro_subido4 = "subido4";



    public static final String SQL_CREATE_TABLA_FECHA_REGISTRO =
            "CREATE TABLE " + tablafecharegistro + "(" +
                    fecha_de_registro_id + " TEXT PRIMARY KEY," +
                    fecha_de_registro_codigo + " TEXT," +
                    fecha_de_registro_sede + " TEXT," +
                    fecha_de_registro_id_local + " TEXT," +
                    fecha_de_registro_nom_local + " TEXT," +
                    fecha_de_registro_aula + " TEXT," +
                    fecha_de_registro_nombres + " TEXT," +
                    fecha_de_registro_dia1 + " TEXT," +
                    fecha_de_registro_mes1 + " TEXT," +
                    fecha_de_registro_anio1 + " TEXT," +
                    fecha_de_registro_hora1 + " TEXT," +
                    fecha_de_registro_minuto1 + " TEXT," +
                    fecha_de_registro_dia2 + " TEXT," +
                    fecha_de_registro_mes2 + " TEXT," +
                    fecha_de_registro_anio2 + " TEXT," +
                    fecha_de_registro_hora2 + " TEXT," +
                    fecha_de_registro_minuto2 + " TEXT," +
                    fecha_de_registro_dia3 + " TEXT," +
                    fecha_de_registro_mes3 + " TEXT," +
                    fecha_de_registro_anio3 + " TEXT," +
                    fecha_de_registro_hora3 + " TEXT," +
                    fecha_de_registro_minuto3 + " TEXT," +
                    fecha_de_registro_dia4 + " TEXT," +
                    fecha_de_registro_mes4 + " TEXT," +
                    fecha_de_registro_anio4 + " TEXT," +
                    fecha_de_registro_hora4 + " TEXT," +
                    fecha_de_registro_minuto4 + " TEXT," +
                    fecha_de_registro_estado1 + " TEXT," +
                    fecha_de_registro_estado2 + " TEXT," +
                    fecha_de_registro_estado3 + " TEXT," +
                    fecha_de_registro_estado4 + " TEXT," +
                    fecha_de_registro_subido1 + " INTEGER," +
                    fecha_de_registro_subido2 + " INTEGER," +
                    fecha_de_registro_subido3 + " INTEGER," +
                    fecha_de_registro_subido4 + " INTEGER" +");"
            ;

    public static final String SQL_CREATE_TABLA_FECHA_REGISTRO_TEMPORAL =
            "CREATE TABLE " + tablafecharegistrotemporal + "(" +
                    fecha_de_registro_id + " TEXT PRIMARY KEY," +
                    fecha_de_registro_codigo + " TEXT," +
                    fecha_de_registro_sede + " TEXT," +
                    fecha_de_registro_id_local + " TEXT," +
                    fecha_de_registro_nom_local + " TEXT," +
                    fecha_de_registro_aula + " TEXT," +
                    fecha_de_registro_nombres + " TEXT," +
                    fecha_de_registro_dia1 + " TEXT," +
                    fecha_de_registro_mes1 + " TEXT," +
                    fecha_de_registro_anio1 + " TEXT," +
                    fecha_de_registro_hora1 + " TEXT," +
                    fecha_de_registro_minuto1 + " TEXT," +
                    fecha_de_registro_dia2 + " TEXT," +
                    fecha_de_registro_mes2 + " TEXT," +
                    fecha_de_registro_anio2 + " TEXT," +
                    fecha_de_registro_hora2 + " TEXT," +
                    fecha_de_registro_minuto2 + " TEXT," +
                    fecha_de_registro_dia3 + " TEXT," +
                    fecha_de_registro_mes3 + " TEXT," +
                    fecha_de_registro_anio3 + " TEXT," +
                    fecha_de_registro_hora3 + " TEXT," +
                    fecha_de_registro_minuto3 + " TEXT," +
                    fecha_de_registro_dia4 + " TEXT," +
                    fecha_de_registro_mes4 + " TEXT," +
                    fecha_de_registro_anio4 + " TEXT," +
                    fecha_de_registro_hora4 + " TEXT," +
                    fecha_de_registro_minuto4 + " TEXT," +
                    fecha_de_registro_estado1 + " TEXT," +
                    fecha_de_registro_estado2 + " TEXT," +
                    fecha_de_registro_estado3 + " TEXT," +
                    fecha_de_registro_estado4 + " TEXT," +
                    fecha_de_registro_subido1 + " INTEGER," +
                    fecha_de_registro_subido2 + " INTEGER," +
                    fecha_de_registro_subido3 + " INTEGER," +
                    fecha_de_registro_subido4 + " INTEGER" +");"
            ;


    public static final String WHERE_CLAUSE_CLAVE = "clave=?";
    public static final String WHERE_CLAUSE_CODIGO = "codigo=?";
    public static final String WHERE_CLAUSE_SEDE = "sede=?";
    public static final String WHERE_CLAUSE_SUBIDO1 = "subido1=?";
    public static final String WHERE_CLAUSE_SUBIDO2 = "subido2=?";
    public static final String WHERE_CLAUSE_SUBIDO3 = "subido3=?";
    public static final String WHERE_CLAUSE_SUBIDO4 = "subido4=?";
    public static final String WHERE_CLAUSE_SEDESUBIDO1 = "sede=?  and subido1=?";


    public static final String[] COLUMNAS_NACIONAL = {
            nacional_codigo,nacional_sede,nacional_id_local,nacional_nom_local,
            nacional_aula,nacional_nombres,nacional_cargo
    };

    public static final String[] COLUMNAS_USUARIO_LOCAL = {
            usuario_local_clave, usuario_local_nombrelocal,
            usuario_local_sede, usuario_local_usuario
    };

    public static final String[] COLUMNAS_FECHA_REGISTRO = {
            fecha_de_registro_id, fecha_de_registro_codigo,fecha_de_registro_sede,
            fecha_de_registro_id_local,fecha_de_registro_nom_local,fecha_de_registro_aula,fecha_de_registro_nombres,
            fecha_de_registro_dia1,fecha_de_registro_mes1,fecha_de_registro_anio1,fecha_de_registro_minuto1,fecha_de_registro_hora1,
            fecha_de_registro_dia2,fecha_de_registro_mes2,fecha_de_registro_anio2,fecha_de_registro_minuto2,fecha_de_registro_hora2,
            fecha_de_registro_dia3,fecha_de_registro_mes3,fecha_de_registro_anio3,fecha_de_registro_minuto3,fecha_de_registro_hora3,
            fecha_de_registro_dia4,fecha_de_registro_mes4,fecha_de_registro_anio4,fecha_de_registro_minuto4,fecha_de_registro_hora4,
            fecha_de_registro_estado1,fecha_de_registro_estado2,fecha_de_registro_estado3,fecha_de_registro_estado4,
            fecha_de_registro_subido1,fecha_de_registro_subido2,fecha_de_registro_subido3,fecha_de_registro_subido4
    };

    public static final String[] COLUMNAS_FECHA_REGISTRO_TEMPORAL = {
            fecha_de_registro_id, fecha_de_registro_codigo,fecha_de_registro_sede,
            fecha_de_registro_id_local,fecha_de_registro_nom_local,fecha_de_registro_aula,fecha_de_registro_nombres,
            fecha_de_registro_dia1,fecha_de_registro_mes1,fecha_de_registro_anio1,fecha_de_registro_minuto1,fecha_de_registro_hora1,
            fecha_de_registro_dia2,fecha_de_registro_mes2,fecha_de_registro_anio2,fecha_de_registro_minuto2,fecha_de_registro_hora2,
            fecha_de_registro_dia3,fecha_de_registro_mes3,fecha_de_registro_anio3,fecha_de_registro_minuto3,fecha_de_registro_hora3,
            fecha_de_registro_dia4,fecha_de_registro_mes4,fecha_de_registro_anio4,fecha_de_registro_minuto4,fecha_de_registro_hora4,
            fecha_de_registro_estado1,fecha_de_registro_estado2,fecha_de_registro_estado3,fecha_de_registro_estado4,
            fecha_de_registro_subido1,fecha_de_registro_subido2,fecha_de_registro_subido3,fecha_de_registro_subido4
    };

}
