package pe.hypergis.peopledb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import pe.hypergis.peopledb.model.Person;

/**
 * Created by Marlon on 3/11/2017.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {
    private List<Person> items;

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public CardView personCardView;
        public ImageView imagen;
        public TextView nombre;
        public TextView dni;
        public TextView edad;

        public PersonViewHolder(View v) {
            super(v);
            personCardView = (CardView) v.findViewById(R.id.person_card);
            imagen = (ImageView) v.findViewById(R.id.imagen);
            nombre = (TextView) v.findViewById(R.id.nombre);
            dni = (TextView) v.findViewById(R.id.dni);
            edad = (TextView) v.findViewById(R.id.edad);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public PersonAdapter(List<Person> items) {
        this.items = items;
    }

    public List<Person> getItems(){
        return this.items;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.person_card, viewGroup, false);
        return new PersonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder viewHolder, final int i) {
        //viewHolder.imagen.setImageResource(items.get(i).getImagen());
        Picasso.with(viewHolder.imagen.getContext())
                .load(items.get(i).getImagenurl()).into(viewHolder.imagen);

        viewHolder.nombre.setText(items.get(i).getNombre());
        viewHolder.dni.setText("DNI: " + items.get(i).getDni());
        viewHolder.edad.setText("Edad: " + String.valueOf(items.get(i).getEdad()));

        viewHolder.personCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("curImagen", items.get(i).getImagenurl());
                bundle.putString("curNombre", items.get(i).getNombre());
                bundle.putString("curDni", items.get(i).getDni());
                bundle.putString("curBio", items.get(i).getBio());
                bundle.putString("personId", items.get(i).getId());
                Intent iconIntent = new Intent(view.getContext(), BioActivity.class);
                iconIntent.putExtras(bundle);
                view.getContext().startActivity(iconIntent);
            }
        });
    }
}
