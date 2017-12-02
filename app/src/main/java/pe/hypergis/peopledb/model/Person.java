package pe.hypergis.peopledb.model;

import android.content.ContentValues;

import java.util.UUID;

/**
 * Created by Marlon on 3/11/2017.
 */

public class Person {
    private String nombre;
    private String dni;
    private int edad;
    private String bio;
    private String imagenurl;
    private String id;

    public Person(String imagenurl, String nombre, String dni, int edad, String bio) {
        this.nombre = nombre;
        this.dni = dni;
        this.edad = edad;
        this.bio = bio;
        this.imagenurl = imagenurl;
        this.id = UUID.randomUUID().toString();
    }

    public Person(String nombre, String dni, int edad, String bio, String imagenurl, String id) {
        this.nombre = nombre;
        this.dni = dni;
        this.edad = edad;
        this.bio = bio;
        this.imagenurl = imagenurl;
        this.id = id;
    }

    public String getImagenurl() {
        return imagenurl;
    }

    public void setImagenurl(String imagenurl) {
        this.imagenurl = imagenurl;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    //Metodo para insertar los datos en SQLite
    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        // Pares clave-valor
        values.put(PersonContract.PersonEntry.ID, this.getId());
        values.put(PersonContract.PersonEntry.NOMBRE, this.getNombre());
        values.put(PersonContract.PersonEntry.DNI, this.getDni());
        values.put(PersonContract.PersonEntry.EDAD, this.getEdad());
        values.put(PersonContract.PersonEntry.IMAGENURL, this.getImagenurl());
        values.put(PersonContract.PersonEntry.BIO, this.getBio());

        return values;
    }
}
