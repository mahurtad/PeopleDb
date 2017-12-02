package pe.hypergis.peopledb;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import pe.hypergis.peopledb.model.PersonDbHelper;

public class BioActivity extends AppCompatActivity {
    TextView nombre;
    TextView dni;
    TextView bio;
    ImageView imagen;
    Button btnEliminar;
    String personId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nombre = (TextView) findViewById(R.id.nombre);
        dni = (TextView) findViewById(R.id.dni);
        bio = (TextView) findViewById(R.id.biografia);
        imagen = (ImageView) findViewById(R.id.imagen);

        nombre.setText(getIntent().getExtras().getString("curNombre"));
        dni.setText("DNI: " + getIntent().getExtras().getString("curDni"));
        bio.setText(getIntent().getExtras().getString("curBio"));
        personId = getIntent().getExtras().getString("personId");

        //imagen.setImageResource(getIntent().getExtras().getInt("curImagen"));
        Picasso.with(imagen.getContext())
                .load(getIntent().getExtras().getString("curImagen")).into(imagen);

        //Boton eliminar
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(v.getContext())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Confirmar")
                        .setMessage("Estas seguro?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                PersonDbHelper db = new PersonDbHelper(btnEliminar.getContext());
                                int delete = db.deletePerson(personId);
                                finish();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
    }

}
