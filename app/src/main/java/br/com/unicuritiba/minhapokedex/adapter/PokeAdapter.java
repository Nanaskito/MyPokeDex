package br.com.unicuritiba.minhapokedex.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

import br.com.unicuritiba.minhapokedex.R;
import br.com.unicuritiba.minhapokedex.model.Pokemon;

public class PokeAdapter extends RecyclerView.Adapter<PokeAdapter.PokeViewHolder> {

    private ArrayList<Pokemon> pokemons;
    private PokeClickListener listener;

    public PokeAdapter(ArrayList<Pokemon> pokemons,
                       PokeClickListener listener){
        this.pokemons = pokemons;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PokeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View layout = layoutInflater.inflate(R.layout.view_item_pokemon,parent,false);
        return new PokeViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull PokeViewHolder holder, int position) {

        Pokemon pokemon = pokemons.get(position);

        TextView textViewPokemon = holder.itemView.findViewById(R.id.text_view_pokemon_name);
        ImageView imageViewPokemon = holder.itemView.findViewById(R.id.image_view_pokemon);

        textViewPokemon.setText(pokemon.getName().toUpperCase(Locale.ROOT));

        Picasso.get()
                .load(pokemon.getImage())
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imageViewPokemon);
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public class PokeViewHolder extends RecyclerView.ViewHolder{
        public PokeViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Pokemon pokemon = pokemons.get(position);
                    listener.onPokemonClick(pokemon);
                }
            });
        }
    }

    public interface PokeClickListener{
        void onPokemonClick(Pokemon pokemon);
    }
}
