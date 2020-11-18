package com.kev95p.refuerzosqlite.db;

import android.provider.BaseColumns;

public final class DatabaseContract {

    public static final String DATABASE_NAME = "rutastransporte.db";
    public static final int DATABASE_VERSION = 1;

    public static final String SQL_CREATE_PERSONA =
            "CREATE TABLE IF NOT EXISTS " + PersonaTabla.TABLE_NAME + "(" +
                    PersonaTabla._ID + " INTEGER PRIMARY KEY," +
                    PersonaTabla.COLUMN_NOMBRE + " TEXT," +
                    PersonaTabla.COLUMN_APELLIDO + " TEXT)";
                    //PersonaTabla.COLUMN_MUNICIPIO + " INTEGER )";
                    //PersonaTabla.COLUMN_FECHA_NAC + " DATE )";

    public static final String SQL_CREATE_DEPARTAMENTOS =
            "CREATE TABLE IF NOT EXISTS " + DepartamentoTabla.TABLE_NAME + "(" +
                    DepartamentoTabla._ID + " INTEGER PRIMARY KEY," +
                    DepartamentoTabla.COLUMN_IDDEPARTAMENTO + " INTEGER," +
                    DepartamentoTabla.COLUMN_DEPARTAMENTO + " TEXT )";

    public static final String SQL_CREATE_MUNICIPIO =
            "CREATE TABLE IF NOT EXISTS " + MunicipioTabla.TABLE_NAME + "(" +
                    MunicipioTabla._ID + " INTEGER PRIMARY KEY," +
                    MunicipioTabla.COLUMN_IDMUNICIPIO + " INTEGER," +
                    MunicipioTabla.COLUMN_MUNICIPIO + " TEXT," +
                    MunicipioTabla.COLUMN_IDDEPARTAMENTO + " INTEGER ) ";


    public static class PersonaTabla implements BaseColumns {
        public static final String TABLE_NAME = "persona";
        public static final String COLUMN_NOMBRE = "nombre";
        public static final String COLUMN_APELLIDO = "apellido";
        // public static final String COLUMN_FECHA_NAC = "fecha_nacimiento";
        // public static final String COLUMN_MUNICIPIO = "municipio_id";
    }

    public static class MunicipioTabla implements BaseColumns {
        public static final String TABLE_NAME = "municipio";
        public static final String COLUMN_IDMUNICIPIO = "idmunicipio";
        public static final String COLUMN_MUNICIPIO = "municipio";
        public static final String COLUMN_IDDEPARTAMENTO = "iddepartamento";
    }

    public static class DepartamentoTabla implements BaseColumns {
        public static final String TABLE_NAME = "departamento";
        public static final String COLUMN_IDDEPARTAMENTO = "iddepartamento";
        public static final String COLUMN_DEPARTAMENTO = "departamento";
    }

}
