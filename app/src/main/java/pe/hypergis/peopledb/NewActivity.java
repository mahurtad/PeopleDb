package pe.hypergis.peopledb;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import pe.hypergis.peopledb.model.Person;
import pe.hypergis.peopledb.model.PersonDbHelper;
import pe.hypergis.peopledb.utils.CustomDialog;

public class NewActivity extends AppCompatActivity {
    private EditText txtNombre;
    private EditText txtDni;
    private EditText txtEdad;
    private EditText txtBio;
    private EditText txtImagenurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtDni = (EditText) findViewById(R.id.txtDni);
        txtEdad = (EditText) findViewById(R.id.txtEdad);
        txtBio = (EditText) findViewById(R.id.txtBio);
        txtImagenurl = (EditText) findViewById(R.id.txtImagenurl);

        Button btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GuardarDatos();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    public void GuardarDatos() {
        if(txtNombre.getText().toString().isEmpty()){
            CustomDialog.ShowCustomAlert("Ingrese el nombre!", NewActivity.this);
            txtNombre.requestFocus();
            return;
        }
        if(txtDni.getText().toString().isEmpty()){
            CustomDialog.ShowCustomAlert("Ingrese el DNI!", NewActivity.this);
            txtDni.requestFocus();
            return;
        }

        if(txtEdad.getText().toString().isEmpty()){
            CustomDialog.ShowCustomAlert("Ingrese la edad!", NewActivity.this);
            txtEdad.requestFocus();
            return;
        }

        if(txtBio.getText().toString().isEmpty()){
            CustomDialog.ShowCustomAlert("Ingrese alguna biografia!", NewActivity.this);
            txtBio.requestFocus();
            return;
        }

        if(txtImagenurl.getText().toString().isEmpty()){
            CustomDialog.ShowCustomAlert("Ingrese la URL de una imagen!", NewActivity.this);
            txtImagenurl.requestFocus();
            return;
        }

        //Confirmar accion
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Confirmar")
                .setMessage("Estas seguro?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Aqui las acciones
                        String nombre = txtNombre.getText().toString();
                        String dni = txtDni.getText().toString();
                        String edad = txtEdad.getText().toString();
                        String bio = txtBio.getText().toString();
                        String imagenurl = txtImagenurl.getText().toString();
                        Person item = new Person(imagenurl, nombre, dni, Integer.parseInt(edad), bio);

                        PersonDbHelper db = new PersonDbHelper(NewActivity.this);
                        db.savePerson(item);
                        CustomDialog.ShowCustomAlert("Registro creado satisfactoriamente!", NewActivity.this);

                        txtNombre.setText("");
                        txtDni.setText("");
                        txtEdad.setText("");
                        txtBio.setText("");
                        txtImagenurl.setText("");
                    }
                })
                .setNegativeButton("No", null)
                .show();

    }
}
