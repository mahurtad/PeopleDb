package pe.hypergis.peopledb.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import pe.hypergis.peopledb.R;

/**
 * Created by Marlon on 21/11/2017.
 */

public class PersonDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Customers.db";
    private List<Person> items = new ArrayList();

    public PersonDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Comandos SQL
        db.execSQL("CREATE TABLE " + PersonContract.PersonEntry.TABLE_NAME + " ("
                + PersonContract.PersonEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + PersonContract.PersonEntry.ID + " TEXT NOT NULL UNIQUE,"
                + PersonContract.PersonEntry.NOMBRE + " TEXT NOT NULL,"
                + PersonContract.PersonEntry.DNI + " TEXT NOT NULL,"
                + PersonContract.PersonEntry.EDAD + " INTEGER NOT NULL,"
                + PersonContract.PersonEntry.IMAGENURL + " TEXT NOT NULL,"
                + PersonContract.PersonEntry.BIO + " TEXT)");

        doLoadInitialData(db);
    }

    public void doLoadInitialData(SQLiteDatabase db){
        items.add(new Person("http://imageshack.com/a/img923/231/czLaY4.png", "Manuel Hurtado ", "40733912", 35, "MBA Ingeniero de Sistemas, con experiencia en gestión de proyectos"));
        items.add(new Person("http://imageshack.com/a/img922/8641/GkMF99.png", "Samuel Casana", "20181123", 38, "Arquitecto con experiencia en edificaciones industriales y rascacielos"));
        items.add(new Person("http://imageshack.com/a/img923/5478/FzcD0x.png", "Saul Pujaico", "10891211", 42, "Diseñador gráfico con experiencia en aplicaciones nativas para móviles"));
        items.add(new Person("http://imageshack.com/a/img922/526/G4ZQYI.png", "Marco Silverio", "45671232", 28, "Escritor con mas de 15 obras literarias de motivación personal"));


        for(int i=0; i<items.size(); i++) {
            long insert =  db.insert(PersonContract.PersonEntry.TABLE_NAME,
                    null, items.get(i).toContentValues());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Comandos SQL
        db.execSQL("DROP TABLE IF EXISTS " + PersonContract.PersonEntry.TABLE_NAME);
        onCreate(db);
    }

    public List<Person> getAllPersons(){
        List<Person> list = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(
                PersonContract.PersonEntry.TABLE_NAME,  // Nombre de la tabla
                null,  // Lista de Columnas a consultar
                null,  // Columnas para la cláusula WHERE
                null,  // Valores a comparar con las columnas del WHERE
                null,  // Agrupar con GROUP BY
                null,  // Condición HAVING para GROUP BY
                PersonContract.PersonEntry.NOMBRE  // Cláusula ORDER BY
        );
        while(c.moveToNext()){
            String nombre = c.getString(c.getColumnIndex(PersonContract.PersonEntry.NOMBRE));
            String dni = c.getString(c.getColumnIndex(PersonContract.PersonEntry.DNI));
            int edad = Integer.parseInt(c.getString(c.getColumnIndex(PersonContract.PersonEntry.EDAD)));
            String bio = c.getString(c.getColumnIndex(PersonContract.PersonEntry.BIO));
            String imagenurl = c.getString(c.getColumnIndex(PersonContract.PersonEntry.IMAGENURL));
            String id = c.getString(c.getColumnIndex(PersonContract.PersonEntry.ID));

            Person item = new Person(nombre, dni, edad, bio, imagenurl, id);
            list.add(item);
        }
        return list;
    }

    public long savePerson(Person person) {
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(PersonContract.PersonEntry.TABLE_NAME,
                null, person.toContentValues());
    }

    public int deletePerson(String personId) {
        return getWritableDatabase().delete(
                PersonContract.PersonEntry.TABLE_NAME,
                PersonContract.PersonEntry.ID + "=?",
                new String[]{personId});
    }
}
