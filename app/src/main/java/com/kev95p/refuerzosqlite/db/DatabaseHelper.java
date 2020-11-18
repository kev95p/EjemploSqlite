package com.kev95p.refuerzosqlite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kev95p.refuerzosqlite.db.models.Persona;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = DatabaseHelper.class.getSimpleName();
    public static DatabaseHelper instancia;
    private Context context;

    private DatabaseHelper(Context ctx) {
        super(ctx, DatabaseContract.DATABASE_NAME, null, DatabaseContract.DATABASE_VERSION);
        this.context = ctx;
    }

    public static DatabaseHelper getInstancia(Context context) {
        if (instancia == null) {
            instancia = new DatabaseHelper(context);
        }
        return instancia;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseContract.SQL_CREATE_PERSONA);
        db.execSQL(DatabaseContract.SQL_CREATE_MUNICIPIO);
        db.execSQL(DatabaseContract.SQL_CREATE_DEPARTAMENTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<Persona> getAllPersonas() {
        SQLiteDatabase db = getReadableDatabase();
        List<Persona> personas = new ArrayList<>();

        String query = "select * from persona";

        Cursor c = db.rawQuery(query, null);

        int id = c.getColumnIndex(DatabaseContract.PersonaTabla._ID);
        int nombre = c.getColumnIndex(DatabaseContract.PersonaTabla.COLUMN_NOMBRE);
        int apellido = c.getColumnIndex(DatabaseContract.PersonaTabla.COLUMN_APELLIDO);
        //int fechaNac = c.getColumnIndex(DatabaseContract.PersonaTabla.COLUMN_FECHA_NAC);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            Persona l = new Persona();
            l.setId(c.getInt(id));
            l.setNombre(c.getString(nombre));
            l.setApellido(c.getString(apellido));
            //Log.d("FECHA",c.getString(fechaNac));
            personas.add(l);
        }

        c.close();
        db.close();

        return personas;
    }

    public Persona getPersona(Integer idPersona){
        SQLiteDatabase db = getReadableDatabase();
        Persona p = new Persona();
        String query = "select * from persona where "+DatabaseContract.PersonaTabla._ID + " = ?" ;
        String[] args = {idPersona.toString()};

        Cursor c = db.rawQuery(query,args);

        int id = c.getColumnIndex(DatabaseContract.PersonaTabla._ID);
        int nombre = c.getColumnIndex(DatabaseContract.PersonaTabla.COLUMN_NOMBRE);
        int apellido = c.getColumnIndex(DatabaseContract.PersonaTabla.COLUMN_APELLIDO);

        if(c.moveToFirst()){
            p.setId(c.getInt(id));
            p.setNombre(c.getString(nombre));
            p.setApellido(c.getString(apellido));
        }
        c.close();
        db.close();

        return p;
    }

    public void insertPersona(Persona p) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.PersonaTabla._ID, p.getId());
        values.put(DatabaseContract.PersonaTabla.COLUMN_NOMBRE, p.getNombre());
        values.put(DatabaseContract.PersonaTabla.COLUMN_APELLIDO, p.getApellido());

        db.insert(DatabaseContract.PersonaTabla.TABLE_NAME, null, values);
    }

    public void updatePersona(Persona p){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.PersonaTabla.COLUMN_NOMBRE, p.getNombre());
        values.put(DatabaseContract.PersonaTabla.COLUMN_APELLIDO, p.getApellido());

        String where =  DatabaseContract.PersonaTabla._ID + " = ?";
        String[] args = {p.getId().toString()};

        db.update(DatabaseContract.PersonaTabla.TABLE_NAME,values,where,args);
    }

    public void deletePersona(Integer id){
        SQLiteDatabase db = getWritableDatabase();
        String where =  DatabaseContract.PersonaTabla._ID + " = ?";
        String[] args = {id.toString()};
        db.delete(DatabaseContract.PersonaTabla.TABLE_NAME,where,args);
    }


}
