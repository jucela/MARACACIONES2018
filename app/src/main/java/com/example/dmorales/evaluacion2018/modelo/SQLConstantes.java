package com.example.dmorales.evaluacion2018.modelo;

public class SQLConstantes {
    public static String DB_PATH = "/data/data/com.example.dmorales.evaluacion2018/databases/";
    //public static String DB_NAME = "mydatabase.sqlite";
    public static String DB_NAME = "marcoasistencia.sqlite";

    public static String tablanacional = "nacional";
    public static String tablausuariolocal = "usuario_local";
    public static String tablafecharegistro = "fecha_registro";
    public static String tablafecharegistrotemporal = "fecha_registro_temporal";
    public static String tablaregistroasistencia = "fecha_registroasistencia";

    //TABLAS NIVELES
    public static String tablaasistencia1 = "tablaasistencia1";
    public static String tablaasistencia2 = "tablaasistencia2";
    public static String tablaasistencia3 = "tablaasistencia3";
    public static String tablaasistencia41 = "tablaasistencia41";
    public static String tablaasistencia42 = "tablaasistencia42";
    public static String tablaasistencia43 = "tablaasistencia43";
    public static String tablaasistencia51 = "tablaasistencia51";
    public static String tablaasistencia52 = "tablaasistencia52";
    public static String tablaasistencia53 = "tablaasistencia53";
    public static String tablaasistencia54 = "tablaasistencia54";
    public static String tablaasistencia55 = "tablaasistencia55";
    public static String tablaasistencia6 = "tablaasistencia6";
    public static String tablaasistencia71 = "tablaasistencia71";
    public static String tablaasistencia72 = "tablaasistencia72";



    // TABLA SQLITE NACIONAL
    public static String nacional_id = "_id";
    public static String nacional_sede="sede";
    public static String nacional_nro_local="nro_local";
    public static String nacional_local_aplicacion = "local_aplicacion";
    public static String nacional_direccion_local = "direccion_local";
    public static String nacional_cod_sede_reg="cod_sede_reg";
    public static String nacional_cod_sede_prov="cod_sede_prov";
    public static String nacional_cod_sede_distrital="cod_sede_distrital";
    public static String nacional_sede_reg="sede_reg";
    public static String nacional_sede_prov="sede_prov";
    public static String nacional_sede_distrital="sede_distrital";
    public static String nacional_aula = "aula";
    public static String nacional_bungalow="bungalow";
    public static String nacional_apepat = "apepat";
    public static String nacional_numdoc = "numdoc";
    public static String nacional_tipo="tipo";
    public static String nacional_tipocargo="tipocargo";
    public static String nacional_cargo="cargo";
    public static String nacional_nivel="nivel";
    public static String nacional_responsable_bungalow="responsable_bungalow";

    //TABLA SQLITE USUARIO_LOCAL
    public static String usuario_local_id = "_id";
    public static String usuario_local_usuario = "usuario";
    public static String usuario_local_clave = "clave";
    public static String usuario_local_nro_local = "nro_local";
    public static String usuario_local_local_aplicacion = "local_aplicacion";
    public static String usuario_local_cod_sede_reg = "cod_sede_reg";
    public static String usuario_local_sede_region = "sede_region";
    public static String usuario_local_cod_nivel = "cod_nivel";
    public static String usuario_local_nom_nivel = "nom_nivel";
    public static String usuario_local_fase = "fase";
    public static String usuario_local_rol = "rol";

    //TABLA SQLITE FECHA_REGISTRO
    public static String fecha_de_registro_id = "_id";
    public static String fecha_de_registro_sede="sede";
    public static String fecha_de_registro_nro_local="nro_local";
    public static String fecha_de_registro_local_aplicacion = "local_aplicacion";
    public static String fecha_de_registro_direccion_local = "direccion_local";
    public static String fecha_de_registro_cod_sede_reg="cod_sede_reg";
    public static String fecha_de_registro_cod_sede_prov="cod_sede_prov";
    public static String fecha_de_registro_cod_sede_distrital="cod_sede_distrital";
    public static String fecha_de_registro_sede_reg="sede_reg";
    public static String fecha_de_registro_sede_prov="sede_prov";
    public static String fecha_de_registro_sede_distrital="sede_distrital";
    public static String fecha_de_registro_aula = "aula";
    public static String fecha_de_registro_bungalow="bungalow";
    public static String fecha_de_registro_apepat = "apepat";
    public static String fecha_de_registro_numdoc = "numdoc";
    public static String fecha_de_registro_tipo="tipo";
    public static String fecha_de_registro_tipocargo="tipocargo";
    public static String fecha_de_registro_cargo="cargo";
    public static String fecha_de_registro_nivel="nivel";
    public static String fecha_de_registro_responsable_bungalow="responsable_bungalow";
    public static String fecha_de_registro_estatus1 = "estatus1";
    public static String fecha_de_registro_dia1 = "dia1";
    public static String fecha_de_registro_mes1 = "mes1";
    public static String fecha_de_registro_anio1 = "anio1";
    public static String fecha_de_registro_hora1 = "hora1";
    public static String fecha_de_registro_minuto1 = "minuto1";
    public static String fecha_de_registro_estatus2 = "estatus2";
    public static String fecha_de_registro_dia2 = "dia2";
    public static String fecha_de_registro_mes2 = "mes2";
    public static String fecha_de_registro_anio2 = "anio2";
    public static String fecha_de_registro_hora2 = "hora2";
    public static String fecha_de_registro_minuto2 = "minuto2";
    public static String fecha_de_registro_subido1 = "subido1";
    public static String fecha_de_registro_subido2 = "subido2";

    //TABLA SQLITE N_REGISTRO
    public static String registroasistencia_id_max = "max(_id)";
    public static String registroasistencia_id = "_id";
    public static String registroasistencia_sede="sede";
    public static String registroasistencia_nro_local="nro_local";
    public static String registroasistencia_local_aplicacion = "local_aplicacion";
    public static String registroasistencia_direccion_local = "direccion_local";
    public static String registroasistencia_cod_sede_reg="cod_sede_reg";
    public static String registroasistencia_cod_sede_prov="cod_sede_prov";
    public static String registroasistencia_cod_sede_distrital="cod_sede_distrital";
    public static String registroasistencia_sede_reg="sede_reg";
    public static String registroasistencia_sede_prov="sede_prov";
    public static String registroasistencia_sede_distrital="sede_distrital";
    public static String registroasistencia_aula = "aula";
    public static String registroasistencia_bungalow="bungalow";
    public static String registroasistencia_apepat = "apepat";
    public static String registroasistencia_numdoc = "numdoc";
    public static String registroasistencia_tipo="tipo";
    public static String registroasistencia_tipocargo="tipocargo";
    public static String registroasistencia_cargo="cargo";
    public static String registroasistencia_nivel="nivel";
    public static String registroasistencia_responsable_bungalow="responsable_bungalow";
    public static String registroasistencia_estatus1 = "estatus1";
    public static String registroasistencia_dia1 = "dia1";
    public static String registroasistencia_mes1 = "mes1";
    public static String registroasistencia_anio1 = "anio1";
    public static String registroasistencia_hora1 = "hora1";
    public static String registroasistencia_minuto1 = "minuto1";
    public static String registroasistencia_estatus2 = "estatus2";
    public static String registroasistencia_dia2 = "dia2";
    public static String registroasistencia_mes2 = "mes2";
    public static String registroasistencia_anio2 = "anio2";
    public static String registroasistencia_hora2 = "hora2";
    public static String registroasistencia_minuto2 = "minuto2";
    public static String registroasistencia_subido1 = "subido1";
    public static String registroasistencia_subido2 = "subido2";



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
    public static String fecha_de_registro_estado3 = "estado3";
    public static String fecha_de_registro_estado4 = "estado4";
    public static String fecha_de_registro_subido3 = "subido3";
    public static String fecha_de_registro_subido4 = "subido4";



    public static final String SQL_CREATE_TABLA_FECHA_REGISTRO =
            "CREATE TABLE " + tablafecharegistro + "(" +
                    fecha_de_registro_id + " TEXT PRIMARY KEY," +
                    fecha_de_registro_sede + " TEXT," +
                    fecha_de_registro_nro_local + " INTEGER," +
                    fecha_de_registro_local_aplicacion + " TEXT," +
                    fecha_de_registro_direccion_local + " TEXT," +
                    fecha_de_registro_cod_sede_reg + " TEXT," +
                    fecha_de_registro_cod_sede_prov+ " TEXT," +
                    fecha_de_registro_cod_sede_distrital+ " TEXT," +
                    fecha_de_registro_sede_reg+ " TEXT," +
                    fecha_de_registro_sede_prov+ " TEXT," +
                    fecha_de_registro_sede_distrital+ " TEXT," +
                    fecha_de_registro_aula + " TEXT," +
                    fecha_de_registro_bungalow+ " TEXT," +
                    fecha_de_registro_apepat + " TEXT," +
                    fecha_de_registro_numdoc + " TEXT," +
                    fecha_de_registro_tipo+ " TEXT," +
                    fecha_de_registro_tipocargo+ " INTEGER," +
                    fecha_de_registro_cargo+ " TEXT," +
                    fecha_de_registro_nivel+ " INTEGER," +
                    fecha_de_registro_responsable_bungalow+ " INTEGER," +
                    fecha_de_registro_estatus1 + " INTEGER," +
                    fecha_de_registro_dia1 + " INTEGER," +
                    fecha_de_registro_mes1 + " INTEGER," +
                    fecha_de_registro_anio1 + " INTEGER," +
                    fecha_de_registro_hora1 + " INTEGER," +
                    fecha_de_registro_minuto1 + " INTEGER," +
                    fecha_de_registro_estatus2 + " INTEGER," +
                    fecha_de_registro_dia2 + " INTEGER," +
                    fecha_de_registro_mes2 + " INTEGER," +
                    fecha_de_registro_anio2 + " INTEGER," +
                    fecha_de_registro_hora2 + " INTEGER," +
                    fecha_de_registro_minuto2 + " INTEGER," +
                    fecha_de_registro_subido1 + " INTEGER," +
                    fecha_de_registro_subido2 + " INTEGER"+");"
            ;

    public static final String SQL_CREATE_TABLA_FECHA_REGISTRO_TEMPORAL =
            "CREATE TABLE " + tablafecharegistrotemporal + "(" +
                    fecha_de_registro_id + " TEXT PRIMARY KEY," +
                    fecha_de_registro_sede + " TEXT," +
                    fecha_de_registro_nro_local + " INTEGER," +
                    fecha_de_registro_local_aplicacion + " TEXT," +
                    fecha_de_registro_direccion_local + " TEXT," +
                    fecha_de_registro_cod_sede_reg + " TEXT," +
                    fecha_de_registro_cod_sede_prov+ " TEXT," +
                    fecha_de_registro_cod_sede_distrital+ " TEXT," +
                    fecha_de_registro_sede_reg+ " TEXT," +
                    fecha_de_registro_sede_prov+ " TEXT," +
                    fecha_de_registro_sede_distrital+ " TEXT," +
                    fecha_de_registro_aula + " TEXT," +
                    fecha_de_registro_bungalow+ " TEXT," +
                    fecha_de_registro_apepat + " TEXT," +
                    fecha_de_registro_numdoc + " TEXT," +
                    fecha_de_registro_tipo+ " TEXT," +
                    fecha_de_registro_tipocargo+ " INTEGER," +
                    fecha_de_registro_cargo+ " TEXT," +
                    fecha_de_registro_nivel+ " INTEGER," +
                    fecha_de_registro_responsable_bungalow+ " INTEGER," +
                    fecha_de_registro_estatus1 + " INTEGER," +
                    fecha_de_registro_dia1 + " INTEGER," +
                    fecha_de_registro_mes1 + " INTEGER," +
                    fecha_de_registro_anio1 + " INTEGER," +
                    fecha_de_registro_hora1 + " INTEGER," +
                    fecha_de_registro_minuto1 + " INTEGER," +
                    fecha_de_registro_estatus2 + " INTEGER," +
                    fecha_de_registro_dia2 + " INTEGER," +
                    fecha_de_registro_mes2 + " INTEGER," +
                    fecha_de_registro_anio2 + " INTEGER," +
                    fecha_de_registro_hora2 + " INTEGER," +
                    fecha_de_registro_minuto2 + " INTEGER," +
                    fecha_de_registro_subido1 + " INTEGER," +
                    fecha_de_registro_subido2 + " INTEGER"+");"
            ;
 //CREACION DE  TABLAS DE ASISTENCIA
    public static final String SQL_CREATE_TABLA_ASISTENCIA1 =
            "CREATE TABLE " + tablaasistencia1 + "(" +
                    fecha_de_registro_id + " TEXT PRIMARY KEY," +
                    fecha_de_registro_sede + " TEXT," +
                    fecha_de_registro_nro_local + " INTEGER," +
                    fecha_de_registro_local_aplicacion + " TEXT," +
                    fecha_de_registro_direccion_local + " TEXT," +
                    fecha_de_registro_aula + " TEXT," +
                    fecha_de_registro_bungalow+ " TEXT," +
                    fecha_de_registro_apepat + " TEXT," +
                    fecha_de_registro_numdoc + " TEXT," +
                    fecha_de_registro_cargo+ " TEXT," +
                    fecha_de_registro_nivel+ " INTEGER," +
                    fecha_de_registro_responsable_bungalow+ " INTEGER," +
                    fecha_de_registro_estatus1 + " INTEGER," +
                    fecha_de_registro_dia1 + " INTEGER," +
                    fecha_de_registro_mes1 + " INTEGER," +
                    fecha_de_registro_anio1 + " INTEGER," +
                    fecha_de_registro_hora1 + " INTEGER," +
                    fecha_de_registro_minuto1 + " INTEGER," +
                    fecha_de_registro_estatus2 + " INTEGER," +
                    fecha_de_registro_dia2 + " INTEGER," +
                    fecha_de_registro_mes2 + " INTEGER," +
                    fecha_de_registro_anio2 + " INTEGER," +
                    fecha_de_registro_hora2 + " INTEGER," +
                    fecha_de_registro_minuto2 + " INTEGER," +
                    fecha_de_registro_subido1 + " INTEGER," +
                    fecha_de_registro_subido2 + " INTEGER"+");"
            ;
    public static final String SQL_CREATE_TABLA_ASISTENCIA2 =
            "CREATE TABLE " + tablaasistencia2 + "(" +
                    fecha_de_registro_id + " TEXT PRIMARY KEY," +
                    fecha_de_registro_sede + " TEXT," +
                    fecha_de_registro_nro_local + " INTEGER," +
                    fecha_de_registro_local_aplicacion + " TEXT," +
                    fecha_de_registro_direccion_local + " TEXT," +
                    fecha_de_registro_aula + " TEXT," +
                    fecha_de_registro_bungalow+ " TEXT," +
                    fecha_de_registro_apepat + " TEXT," +
                    fecha_de_registro_numdoc + " TEXT," +
                    fecha_de_registro_cargo+ " TEXT," +
                    fecha_de_registro_nivel+ " INTEGER," +
                    fecha_de_registro_responsable_bungalow+ " INTEGER," +
                    fecha_de_registro_estatus1 + " INTEGER," +
                    fecha_de_registro_dia1 + " INTEGER," +
                    fecha_de_registro_mes1 + " INTEGER," +
                    fecha_de_registro_anio1 + " INTEGER," +
                    fecha_de_registro_hora1 + " INTEGER," +
                    fecha_de_registro_minuto1 + " INTEGER," +
                    fecha_de_registro_estatus2 + " INTEGER," +
                    fecha_de_registro_dia2 + " INTEGER," +
                    fecha_de_registro_mes2 + " INTEGER," +
                    fecha_de_registro_anio2 + " INTEGER," +
                    fecha_de_registro_hora2 + " INTEGER," +
                    fecha_de_registro_minuto2 + " INTEGER," +
                    fecha_de_registro_subido1 + " INTEGER," +
                    fecha_de_registro_subido2 + " INTEGER"+");"
            ;
    public static final String SQL_CREATE_TABLA_ASISTENCIA3 =
            "CREATE TABLE " + tablaasistencia3 + "(" +
                    fecha_de_registro_id + " TEXT PRIMARY KEY," +
                    fecha_de_registro_sede + " TEXT," +
                    fecha_de_registro_nro_local + " INTEGER," +
                    fecha_de_registro_local_aplicacion + " TEXT," +
                    fecha_de_registro_direccion_local + " TEXT," +
                    fecha_de_registro_aula + " TEXT," +
                    fecha_de_registro_apepat + " TEXT," +
                    fecha_de_registro_numdoc + " TEXT," +
                    fecha_de_registro_cargo+ " TEXT," +
                    fecha_de_registro_nivel+ " INTEGER," +
                    fecha_de_registro_estatus1 + " INTEGER," +
                    fecha_de_registro_dia1 + " INTEGER," +
                    fecha_de_registro_mes1 + " INTEGER," +
                    fecha_de_registro_anio1 + " INTEGER," +
                    fecha_de_registro_hora1 + " INTEGER," +
                    fecha_de_registro_minuto1 + " INTEGER," +
                    fecha_de_registro_subido1 + " INTEGER"+");"
            ;


    public static final String SQL_CREATE_TABLA_ASISTENCIA41 =
            "CREATE TABLE " + tablaasistencia41 + "(" +
                    fecha_de_registro_id + " TEXT PRIMARY KEY," +
                    fecha_de_registro_sede + " TEXT," +
                    fecha_de_registro_nro_local + " INTEGER," +
                    fecha_de_registro_local_aplicacion + " TEXT," +
                    fecha_de_registro_direccion_local + " TEXT," +
                    fecha_de_registro_aula + " TEXT," +
                    fecha_de_registro_apepat + " TEXT," +
                    fecha_de_registro_numdoc + " TEXT," +
                    fecha_de_registro_cargo+ " TEXT," +
                    fecha_de_registro_nivel+ " INTEGER," +
                    fecha_de_registro_estatus1 + " INTEGER," +
                    fecha_de_registro_dia1 + " INTEGER," +
                    fecha_de_registro_mes1 + " INTEGER," +
                    fecha_de_registro_anio1 + " INTEGER," +
                    fecha_de_registro_hora1 + " INTEGER," +
                    fecha_de_registro_minuto1 + " INTEGER," +
                    fecha_de_registro_estatus2 + " INTEGER," +
                    fecha_de_registro_dia2 + " INTEGER," +
                    fecha_de_registro_mes2 + " INTEGER," +
                    fecha_de_registro_anio2 + " INTEGER," +
                    fecha_de_registro_hora2 + " INTEGER," +
                    fecha_de_registro_minuto2 + " INTEGER," +
                    fecha_de_registro_subido1 + " INTEGER," +
                    fecha_de_registro_subido2 + " INTEGER"+");"
            ;
    public static final String SQL_CREATE_TABLA_ASISTENCIA42 =
            "CREATE TABLE " + tablaasistencia42 + "(" +
                    fecha_de_registro_id + " TEXT PRIMARY KEY," +
                    fecha_de_registro_sede + " TEXT," +
                    fecha_de_registro_nro_local + " INTEGER," +
                    fecha_de_registro_local_aplicacion + " TEXT," +
                    fecha_de_registro_direccion_local + " TEXT," +
                    fecha_de_registro_aula + " TEXT," +
                    fecha_de_registro_apepat + " TEXT," +
                    fecha_de_registro_numdoc + " TEXT," +
                    fecha_de_registro_cargo+ " TEXT," +
                    fecha_de_registro_nivel+ " INTEGER," +
                    fecha_de_registro_estatus1 + " INTEGER," +
                    fecha_de_registro_dia1 + " INTEGER," +
                    fecha_de_registro_mes1 + " INTEGER," +
                    fecha_de_registro_anio1 + " INTEGER," +
                    fecha_de_registro_hora1 + " INTEGER," +
                    fecha_de_registro_minuto1 + " INTEGER," +
                    fecha_de_registro_estatus2 + " INTEGER," +
                    fecha_de_registro_dia2 + " INTEGER," +
                    fecha_de_registro_mes2 + " INTEGER," +
                    fecha_de_registro_anio2 + " INTEGER," +
                    fecha_de_registro_hora2 + " INTEGER," +
                    fecha_de_registro_minuto2 + " INTEGER," +
                    fecha_de_registro_subido1 + " INTEGER," +
                    fecha_de_registro_subido2 + " INTEGER"+");"
            ;
    public static final String SQL_CREATE_TABLA_ASISTENCIA43 =
            "CREATE TABLE " + tablaasistencia43 + "(" +
                    fecha_de_registro_id + " TEXT PRIMARY KEY," +
                    fecha_de_registro_sede + " TEXT," +
                    fecha_de_registro_nro_local + " INTEGER," +
                    fecha_de_registro_local_aplicacion + " TEXT," +
                    fecha_de_registro_direccion_local + " TEXT," +
                    fecha_de_registro_aula + " TEXT," +
                    fecha_de_registro_apepat + " TEXT," +
                    fecha_de_registro_numdoc + " TEXT," +
                    fecha_de_registro_cargo+ " TEXT," +
                    fecha_de_registro_nivel+ " INTEGER," +
                    fecha_de_registro_estatus1 + " INTEGER," +
                    fecha_de_registro_dia1 + " INTEGER," +
                    fecha_de_registro_mes1 + " INTEGER," +
                    fecha_de_registro_anio1 + " INTEGER," +
                    fecha_de_registro_hora1 + " INTEGER," +
                    fecha_de_registro_minuto1 + " INTEGER," +
                    fecha_de_registro_estatus2 + " INTEGER," +
                    fecha_de_registro_dia2 + " INTEGER," +
                    fecha_de_registro_mes2 + " INTEGER," +
                    fecha_de_registro_anio2 + " INTEGER," +
                    fecha_de_registro_hora2 + " INTEGER," +
                    fecha_de_registro_minuto2 + " INTEGER," +
                    fecha_de_registro_subido1 + " INTEGER," +
                    fecha_de_registro_subido2 + " INTEGER"+");"
            ;
    public static final String SQL_CREATE_TABLA_ASISTENCIA51 =
            "CREATE TABLE " + tablaasistencia51 + "(" +
                    fecha_de_registro_id + " TEXT PRIMARY KEY," +
                    fecha_de_registro_sede + " TEXT," +
                    fecha_de_registro_nro_local + " INTEGER," +
                    fecha_de_registro_local_aplicacion + " TEXT," +
                    fecha_de_registro_direccion_local + " TEXT," +
                    fecha_de_registro_aula + " TEXT," +
                    fecha_de_registro_apepat + " TEXT," +
                    fecha_de_registro_numdoc + " TEXT," +
                    fecha_de_registro_cargo+ " TEXT," +
                    fecha_de_registro_nivel+ " INTEGER," +
                    fecha_de_registro_estatus1 + " INTEGER," +
                    fecha_de_registro_dia1 + " INTEGER," +
                    fecha_de_registro_mes1 + " INTEGER," +
                    fecha_de_registro_anio1 + " INTEGER," +
                    fecha_de_registro_hora1 + " INTEGER," +
                    fecha_de_registro_minuto1 + " INTEGER," +
                    fecha_de_registro_estatus2 + " INTEGER," +
                    fecha_de_registro_dia2 + " INTEGER," +
                    fecha_de_registro_mes2 + " INTEGER," +
                    fecha_de_registro_anio2 + " INTEGER," +
                    fecha_de_registro_hora2 + " INTEGER," +
                    fecha_de_registro_minuto2 + " INTEGER," +
                    fecha_de_registro_subido1 + " INTEGER," +
                    fecha_de_registro_subido2 + " INTEGER"+");"
            ;
    public static final String SQL_CREATE_TABLA_ASISTENCIA52 =
            "CREATE TABLE " + tablaasistencia52 + "(" +
                    fecha_de_registro_id + " TEXT PRIMARY KEY," +
                    fecha_de_registro_sede + " TEXT," +
                    fecha_de_registro_nro_local + " INTEGER," +
                    fecha_de_registro_local_aplicacion + " TEXT," +
                    fecha_de_registro_direccion_local + " TEXT," +
                    fecha_de_registro_aula + " TEXT," +
                    fecha_de_registro_apepat + " TEXT," +
                    fecha_de_registro_numdoc + " TEXT," +
                    fecha_de_registro_cargo+ " TEXT," +
                    fecha_de_registro_nivel+ " INTEGER," +
                    fecha_de_registro_estatus1 + " INTEGER," +
                    fecha_de_registro_dia1 + " INTEGER," +
                    fecha_de_registro_mes1 + " INTEGER," +
                    fecha_de_registro_anio1 + " INTEGER," +
                    fecha_de_registro_hora1 + " INTEGER," +
                    fecha_de_registro_minuto1 + " INTEGER," +
                    fecha_de_registro_estatus2 + " INTEGER," +
                    fecha_de_registro_dia2 + " INTEGER," +
                    fecha_de_registro_mes2 + " INTEGER," +
                    fecha_de_registro_anio2 + " INTEGER," +
                    fecha_de_registro_hora2 + " INTEGER," +
                    fecha_de_registro_minuto2 + " INTEGER," +
                    fecha_de_registro_subido1 + " INTEGER," +
                    fecha_de_registro_subido2 + " INTEGER"+");"
            ;
    public static final String SQL_CREATE_TABLA_ASISTENCIA53 =
            "CREATE TABLE " + tablaasistencia53 + "(" +
                    fecha_de_registro_id + " TEXT PRIMARY KEY," +
                    fecha_de_registro_sede + " TEXT," +
                    fecha_de_registro_nro_local + " INTEGER," +
                    fecha_de_registro_local_aplicacion + " TEXT," +
                    fecha_de_registro_direccion_local + " TEXT," +
                    fecha_de_registro_aula + " TEXT," +
                    fecha_de_registro_apepat + " TEXT," +
                    fecha_de_registro_numdoc + " TEXT," +
                    fecha_de_registro_cargo+ " TEXT," +
                    fecha_de_registro_nivel+ " INTEGER," +
                    fecha_de_registro_estatus1 + " INTEGER," +
                    fecha_de_registro_dia1 + " INTEGER," +
                    fecha_de_registro_mes1 + " INTEGER," +
                    fecha_de_registro_anio1 + " INTEGER," +
                    fecha_de_registro_hora1 + " INTEGER," +
                    fecha_de_registro_minuto1 + " INTEGER," +
                    fecha_de_registro_estatus2 + " INTEGER," +
                    fecha_de_registro_dia2 + " INTEGER," +
                    fecha_de_registro_mes2 + " INTEGER," +
                    fecha_de_registro_anio2 + " INTEGER," +
                    fecha_de_registro_hora2 + " INTEGER," +
                    fecha_de_registro_minuto2 + " INTEGER," +
                    fecha_de_registro_subido1 + " INTEGER," +
                    fecha_de_registro_subido2 + " INTEGER"+");"
            ;
    public static final String SQL_CREATE_TABLA_ASISTENCIA54 =
            "CREATE TABLE " + tablaasistencia54 + "(" +
                    fecha_de_registro_id + " TEXT PRIMARY KEY," +
                    fecha_de_registro_sede + " TEXT," +
                    fecha_de_registro_nro_local + " INTEGER," +
                    fecha_de_registro_local_aplicacion + " TEXT," +
                    fecha_de_registro_direccion_local + " TEXT," +
                    fecha_de_registro_aula + " TEXT," +
                    fecha_de_registro_apepat + " TEXT," +
                    fecha_de_registro_numdoc + " TEXT," +
                    fecha_de_registro_cargo+ " TEXT," +
                    fecha_de_registro_nivel+ " INTEGER," +
                    fecha_de_registro_estatus1 + " INTEGER," +
                    fecha_de_registro_dia1 + " INTEGER," +
                    fecha_de_registro_mes1 + " INTEGER," +
                    fecha_de_registro_anio1 + " INTEGER," +
                    fecha_de_registro_hora1 + " INTEGER," +
                    fecha_de_registro_minuto1 + " INTEGER," +
                    fecha_de_registro_estatus2 + " INTEGER," +
                    fecha_de_registro_dia2 + " INTEGER," +
                    fecha_de_registro_mes2 + " INTEGER," +
                    fecha_de_registro_anio2 + " INTEGER," +
                    fecha_de_registro_hora2 + " INTEGER," +
                    fecha_de_registro_minuto2 + " INTEGER," +
                    fecha_de_registro_subido1 + " INTEGER," +
                    fecha_de_registro_subido2 + " INTEGER"+");"
            ;
    public static final String SQL_CREATE_TABLA_ASISTENCIA55 =
            "CREATE TABLE " + tablaasistencia55 + "(" +
                    fecha_de_registro_id + " TEXT PRIMARY KEY," +
                    fecha_de_registro_sede + " TEXT," +
                    fecha_de_registro_nro_local + " INTEGER," +
                    fecha_de_registro_local_aplicacion + " TEXT," +
                    fecha_de_registro_direccion_local + " TEXT," +
                    fecha_de_registro_aula + " TEXT," +
                    fecha_de_registro_apepat + " TEXT," +
                    fecha_de_registro_numdoc + " TEXT," +
                    fecha_de_registro_cargo+ " TEXT," +
                    fecha_de_registro_nivel+ " INTEGER," +
                    fecha_de_registro_estatus1 + " INTEGER," +
                    fecha_de_registro_dia1 + " INTEGER," +
                    fecha_de_registro_mes1 + " INTEGER," +
                    fecha_de_registro_anio1 + " INTEGER," +
                    fecha_de_registro_hora1 + " INTEGER," +
                    fecha_de_registro_minuto1 + " INTEGER," +
                    fecha_de_registro_estatus2 + " INTEGER," +
                    fecha_de_registro_dia2 + " INTEGER," +
                    fecha_de_registro_mes2 + " INTEGER," +
                    fecha_de_registro_anio2 + " INTEGER," +
                    fecha_de_registro_hora2 + " INTEGER," +
                    fecha_de_registro_minuto2 + " INTEGER," +
                    fecha_de_registro_subido1 + " INTEGER," +
                    fecha_de_registro_subido2 + " INTEGER"+");"
            ;
    public static final String SQL_CREATE_TABLA_ASISTENCIA6 =
            "CREATE TABLE " + tablaasistencia6 + "(" +
                    fecha_de_registro_id + " TEXT PRIMARY KEY," +
                    fecha_de_registro_sede + " TEXT," +
                    fecha_de_registro_nro_local + " INTEGER," +
                    fecha_de_registro_local_aplicacion + " TEXT," +
                    fecha_de_registro_direccion_local + " TEXT," +
                    fecha_de_registro_aula + " TEXT," +
                    fecha_de_registro_apepat + " TEXT," +
                    fecha_de_registro_numdoc + " TEXT," +
                    fecha_de_registro_cargo+ " TEXT," +
                    fecha_de_registro_nivel+ " INTEGER," +
                    fecha_de_registro_estatus1 + " INTEGER," +
                    fecha_de_registro_dia1 + " INTEGER," +
                    fecha_de_registro_mes1 + " INTEGER," +
                    fecha_de_registro_anio1 + " INTEGER," +
                    fecha_de_registro_hora1 + " INTEGER," +
                    fecha_de_registro_minuto1 + " INTEGER," +
                    fecha_de_registro_subido1 + " INTEGER"+");"
            ;
    public static final String SQL_CREATE_TABLA_ASISTENCIA71 =
            "CREATE TABLE " + tablaasistencia71 + "(" +
                    fecha_de_registro_id + " TEXT PRIMARY KEY," +
                    fecha_de_registro_sede + " TEXT," +
                    fecha_de_registro_nro_local + " INTEGER," +
                    fecha_de_registro_local_aplicacion + " TEXT," +
                    fecha_de_registro_direccion_local + " TEXT," +
                    fecha_de_registro_aula + " TEXT," +
                    fecha_de_registro_apepat + " TEXT," +
                    fecha_de_registro_numdoc + " TEXT," +
                    fecha_de_registro_cargo+ " TEXT," +
                    fecha_de_registro_nivel+ " INTEGER," +
                    fecha_de_registro_estatus1 + " INTEGER," +
                    fecha_de_registro_dia1 + " INTEGER," +
                    fecha_de_registro_mes1 + " INTEGER," +
                    fecha_de_registro_anio1 + " INTEGER," +
                    fecha_de_registro_hora1 + " INTEGER," +
                    fecha_de_registro_minuto1 + " INTEGER," +
                    fecha_de_registro_estatus2 + " INTEGER," +
                    fecha_de_registro_dia2 + " INTEGER," +
                    fecha_de_registro_mes2 + " INTEGER," +
                    fecha_de_registro_anio2 + " INTEGER," +
                    fecha_de_registro_hora2 + " INTEGER," +
                    fecha_de_registro_minuto2 + " INTEGER," +
                    fecha_de_registro_subido1 + " INTEGER," +
                    fecha_de_registro_subido2 + " INTEGER"+");"
            ;
    public static final String SQL_CREATE_TABLA_ASISTENCIA72 =
            "CREATE TABLE " + tablaasistencia72 + "(" +
                    fecha_de_registro_id + " TEXT PRIMARY KEY," +
                    fecha_de_registro_sede + " TEXT," +
                    fecha_de_registro_nro_local + " INTEGER," +
                    fecha_de_registro_local_aplicacion + " TEXT," +
                    fecha_de_registro_direccion_local + " TEXT," +
                    fecha_de_registro_aula + " TEXT," +
                    fecha_de_registro_apepat + " TEXT," +
                    fecha_de_registro_numdoc + " TEXT," +
                    fecha_de_registro_cargo+ " TEXT," +
                    fecha_de_registro_nivel+ " INTEGER," +
                    fecha_de_registro_estatus1 + " INTEGER," +
                    fecha_de_registro_dia1 + " INTEGER," +
                    fecha_de_registro_mes1 + " INTEGER," +
                    fecha_de_registro_anio1 + " INTEGER," +
                    fecha_de_registro_hora1 + " INTEGER," +
                    fecha_de_registro_minuto1 + " INTEGER," +
                    fecha_de_registro_estatus2 + " INTEGER," +
                    fecha_de_registro_dia2 + " INTEGER," +
                    fecha_de_registro_mes2 + " INTEGER," +
                    fecha_de_registro_anio2 + " INTEGER," +
                    fecha_de_registro_hora2 + " INTEGER," +
                    fecha_de_registro_minuto2 + " INTEGER," +
                    fecha_de_registro_subido1 + " INTEGER," +
                    fecha_de_registro_subido2 + " INTEGER"+");"
            ;



    public static final String WHERE_CLAUSE_CLAVE = "clave=?";
    public static final String WHERE_CLAUSE_CODIGO = "numdoc=?";
    public static final String WHERE_CLAUSE_SEDE = "sede_region=?";
    public static final String WHERE_CLAUSE_COD_LOCAL = "nro_local=?";
    public static final String WHERE_CLAUSE_SUBIDO1 = "subido1=? and estatus1=?";
    public static final String WHERE_CLAUSE_SUBIDO2 = "subido2=? and estatus2=?";
    public static final String WHERE_CLAUSE_SUBIDO3 = "subido3=?";
    public static final String WHERE_CLAUSE_SUBIDO4 = "subido4=?";
    public static final String WHERE_CLAUSE_SEDESUBIDO1 = "nro_local=?  and subido1=?";
    public static final String WHERE_CLAUSE_AULA = "aula=?";

    public static final String WHERE_CLAUSE_DIA = "estatus1=? and estatus2=?";


    public static final String[] COLUMNAS_NACIONAL = {
            nacional_id ,
            nacional_sede ,
            nacional_nro_local,
            nacional_local_aplicacion ,
            nacional_direccion_local ,
            nacional_cod_sede_reg ,
            nacional_cod_sede_prov,
            nacional_cod_sede_distrital,
            nacional_sede_reg,
            nacional_sede_prov,
            nacional_sede_distrital,
            nacional_aula ,
            nacional_bungalow,
            nacional_apepat ,
            nacional_numdoc ,
            nacional_tipo,
            nacional_tipocargo,
            nacional_cargo,
            nacional_nivel,
            nacional_responsable_bungalow
    };

    public static final String[] COLUMNAS_USUARIO_LOCAL = {
            usuario_local_id,
            usuario_local_usuario,
            usuario_local_clave,
            usuario_local_nro_local,
            usuario_local_local_aplicacion,
            usuario_local_cod_sede_reg,
            usuario_local_sede_region,
            usuario_local_cod_nivel,
            usuario_local_nom_nivel,
            usuario_local_fase,
            usuario_local_rol

    };

    public static final String[] COLUMNAS_FECHA_REGISTRO = {
            fecha_de_registro_id ,
            fecha_de_registro_sede ,
            fecha_de_registro_nro_local ,
            fecha_de_registro_local_aplicacion ,
            fecha_de_registro_direccion_local ,
            fecha_de_registro_cod_sede_reg ,
            fecha_de_registro_cod_sede_prov,
            fecha_de_registro_cod_sede_distrital,
            fecha_de_registro_sede_reg,
            fecha_de_registro_sede_prov,
            fecha_de_registro_sede_distrital,
            fecha_de_registro_aula ,
            fecha_de_registro_bungalow,
            fecha_de_registro_apepat ,
            fecha_de_registro_numdoc ,
            fecha_de_registro_tipo,
            fecha_de_registro_tipocargo,
            fecha_de_registro_cargo,
            fecha_de_registro_nivel,
            fecha_de_registro_responsable_bungalow,
            fecha_de_registro_estatus1,
            fecha_de_registro_dia1 ,
            fecha_de_registro_mes1 ,
            fecha_de_registro_anio1 ,
            fecha_de_registro_hora1 ,
            fecha_de_registro_minuto1 ,
            fecha_de_registro_estatus2 ,
            fecha_de_registro_dia2 ,
            fecha_de_registro_mes2 ,
            fecha_de_registro_anio2 ,
            fecha_de_registro_hora2 ,
            fecha_de_registro_minuto2 ,
            fecha_de_registro_subido1 ,
            fecha_de_registro_subido2
    };

    public static final String[] COLUMNAS_FECHA_REGISTRO_TEMPORAL = {
            fecha_de_registro_id ,
            fecha_de_registro_sede ,
            fecha_de_registro_nro_local ,
            fecha_de_registro_local_aplicacion ,
            fecha_de_registro_direccion_local ,
            fecha_de_registro_cod_sede_reg ,
            fecha_de_registro_cod_sede_prov,
            fecha_de_registro_cod_sede_distrital,
            fecha_de_registro_sede_reg,
            fecha_de_registro_sede_prov,
            fecha_de_registro_sede_distrital,
            fecha_de_registro_aula ,
            fecha_de_registro_bungalow,
            fecha_de_registro_apepat ,
            fecha_de_registro_numdoc ,
            fecha_de_registro_tipo,
            fecha_de_registro_tipocargo,
            fecha_de_registro_cargo,
            fecha_de_registro_nivel,
            fecha_de_registro_responsable_bungalow,
            fecha_de_registro_estatus1,
            fecha_de_registro_dia1 ,
            fecha_de_registro_mes1 ,
            fecha_de_registro_anio1 ,
            fecha_de_registro_hora1 ,
            fecha_de_registro_minuto1 ,
            fecha_de_registro_estatus2 ,
            fecha_de_registro_dia2 ,
            fecha_de_registro_mes2 ,
            fecha_de_registro_anio2 ,
            fecha_de_registro_hora2 ,
            fecha_de_registro_minuto2 ,
            fecha_de_registro_subido1 ,
            fecha_de_registro_subido2
    };

    public static final String[] COLUMNAS_ASISTENCIATIPO1 = {
            fecha_de_registro_id ,
            fecha_de_registro_sede ,
            fecha_de_registro_nro_local ,
            fecha_de_registro_local_aplicacion ,
            fecha_de_registro_direccion_local ,
            fecha_de_registro_aula ,
            fecha_de_registro_bungalow,
            fecha_de_registro_apepat ,
            fecha_de_registro_numdoc ,
            fecha_de_registro_cargo,
            fecha_de_registro_nivel,
            fecha_de_registro_responsable_bungalow,
            fecha_de_registro_estatus1,
            fecha_de_registro_dia1 ,
            fecha_de_registro_mes1 ,
            fecha_de_registro_anio1 ,
            fecha_de_registro_hora1 ,
            fecha_de_registro_minuto1 ,
            fecha_de_registro_estatus2 ,
            fecha_de_registro_dia2 ,
            fecha_de_registro_mes2 ,
            fecha_de_registro_anio2 ,
            fecha_de_registro_hora2 ,
            fecha_de_registro_minuto2 ,
            fecha_de_registro_subido1 ,
            fecha_de_registro_subido2
    };

    public static final String[] COLUMNAS_ASISTENCIATIPO2 = {
            fecha_de_registro_id ,
            fecha_de_registro_sede ,
            fecha_de_registro_nro_local ,
            fecha_de_registro_local_aplicacion ,
            fecha_de_registro_direccion_local ,
            fecha_de_registro_aula ,
            fecha_de_registro_apepat ,
            fecha_de_registro_numdoc ,
            fecha_de_registro_cargo,
            fecha_de_registro_nivel,
            fecha_de_registro_estatus1,
            fecha_de_registro_dia1 ,
            fecha_de_registro_mes1 ,
            fecha_de_registro_anio1 ,
            fecha_de_registro_hora1 ,
            fecha_de_registro_minuto1,
            fecha_de_registro_subido1
    };

    public static final String[] COLUMNAS_ASISTENCIATIPO3 = {
            fecha_de_registro_id ,
            fecha_de_registro_sede ,
            fecha_de_registro_nro_local ,
            fecha_de_registro_local_aplicacion ,
            fecha_de_registro_direccion_local ,
            fecha_de_registro_aula ,
            fecha_de_registro_apepat ,
            fecha_de_registro_numdoc ,
            fecha_de_registro_cargo,
            fecha_de_registro_nivel,
            fecha_de_registro_estatus1,
            fecha_de_registro_dia1 ,
            fecha_de_registro_mes1 ,
            fecha_de_registro_anio1 ,
            fecha_de_registro_hora1 ,
            fecha_de_registro_minuto1 ,
            fecha_de_registro_estatus2 ,
            fecha_de_registro_dia2 ,
            fecha_de_registro_mes2 ,
            fecha_de_registro_anio2 ,
            fecha_de_registro_hora2 ,
            fecha_de_registro_minuto2 ,
            fecha_de_registro_subido1 ,
            fecha_de_registro_subido2
    };



}
