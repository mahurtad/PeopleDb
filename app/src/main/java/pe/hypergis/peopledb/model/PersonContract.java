package pe.hypergis.peopledb.model;

import android.provider.BaseColumns;

/**
 * Created by Marlon on 21/11/2017.
 */

public class PersonContract {
    public static abstract class PersonEntry implements BaseColumns {
        public static final String TABLE_NAME ="Person";

        public static final String ID = "id";
        public static final String NOMBRE = "nombre";
        public static final String DNI = "dni";
        public static final String EDAD = "edad";
        public static final String BIO = "bio";
        public static final String IMAGENURL = "imagenurl";
    }

    //Incluir aqui otras tablas
}
