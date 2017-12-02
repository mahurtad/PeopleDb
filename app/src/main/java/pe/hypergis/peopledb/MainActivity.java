package pe.hypergis.peopledb;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import pe.hypergis.peopledb.model.Person;
import pe.hypergis.peopledb.model.PersonDbHelper;

public class MainActivity extends AppCompatActivity {
    private List<Person> items = new ArrayList();
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FillPersons();

        // Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        adapter = new PersonAdapter(items);
        recycler.setAdapter(adapter);

        FloatingActionButton btnAgregar = (FloatingActionButton) findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iconIntent = new Intent(view.getContext(), NewActivity.class);
                view.getContext().startActivity(iconIntent);
            }
        });
    }

    private void FillPersons(){
        PersonDbHelper db = new PersonDbHelper(MainActivity.this);
        items = db.getAllPersons();
    }

    @Override
    public void onRestart()
    {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}
