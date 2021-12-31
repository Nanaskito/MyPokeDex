package br.com.unicuritiba.minhapokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Locale;

import br.com.unicuritiba.minhapokedex.adapter.PokeAdapter;
import br.com.unicuritiba.minhapokedex.model.Pokemon;
import br.com.unicuritiba.minhapokedex.network.PokeAPI;

public class PokemonDetailActivity extends AppCompatActivity {

    ImageView imageViewPokeDetail;
    TextView textviewpokemondetailnome;
    TextView pokemondetailtamanho;
    TextView pokemondetailpeso;
    TextView pokemonType;
    TextView pokemonAbility;
    TextView pokemonMove;
    TextView pokemonStats;
    TextView pokemonStats1;
    TextView pokemonStats2;
    TextView pokemonStats3;
    TextView pokemonStats4;
    TextView pokemonStats5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);
        Pokemon pokemon = (Pokemon) getIntent().getSerializableExtra("pokemon");
        imageViewPokeDetail = findViewById(R.id.image_pokemon);
        textviewpokemondetailnome = findViewById(R.id.name_pokemondetail);
        textviewpokemondetailnome.setText(pokemon.getName().toUpperCase(Locale.ROOT));
        Picasso.get()
                .load(pokemon.getImage())
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imageViewPokeDetail);

        new PokeAPI().getPokemonDetail(pokemon.getId(), new PokeAPI.PokeDetailListener() {
            @Override
            public void onPokemonMapperDetailFinish(Pokemon pokemon) {

                pokemondetailtamanho = findViewById(R.id.height_pokemon);
                pokemondetailtamanho.setText("Height: " + pokemon.getHeight());
                pokemondetailpeso = findViewById(R.id.weight_pokemon);
                pokemondetailpeso.setText("Weight: " + pokemon.getWeight());
                pokemonType = findViewById(R.id.type);
                pokemonAbility = findViewById(R.id.ability);
                pokemonMove = findViewById(R.id.move);
                pokemonStats = findViewById(R.id.stats);
                pokemonStats1 = findViewById(R.id.stats1);
                pokemonStats2 = findViewById(R.id.stats2);
                pokemonStats3 = findViewById(R.id.stats3);
                pokemonStats4 = findViewById(R.id.stats4);
                pokemonStats5 = findViewById(R.id.stats5);

                String aux = "";
                for (int type = 0; type < pokemon.getTypes().size(); type++) {
                    aux = aux + pokemon.getTypes().get(type) + "; ";
                }
                pokemonType.setText(aux.toUpperCase(Locale.ROOT));



                String auxability = "";
                for (int ability = 0; ability < pokemon.getAbilities().size(); ability++) {
                    auxability = auxability + pokemon.getAbilities().get(ability) + "; ";
                }
                pokemonAbility.setText(auxability.toUpperCase(Locale.ROOT));


                String auxmove = "";
                for (int move = 0; move < pokemon.getMoves().size(); move++) {
                    auxmove = auxmove + pokemon.getMoves().get(move) + ";\n";
                }
                pokemonMove.setText(auxmove.toUpperCase(Locale.ROOT));

                pokemonStats.setText("HP: " + pokemon.getStats().get(0).toUpperCase(Locale.ROOT));
                pokemonStats1.setText("ATTACK: " + pokemon.getStats().get(1).toUpperCase(Locale.ROOT));
                pokemonStats2.setText("DEFENSE: " + pokemon.getStats().get(2).toUpperCase(Locale.ROOT));
                pokemonStats3.setText("SPECIAL-ATTACK: " + pokemon.getStats().get(3).toUpperCase(Locale.ROOT));
                pokemonStats4.setText("SPECIAL-DEFENSE: " + pokemon.getStats().get(4).toUpperCase(Locale.ROOT));
                pokemonStats5.setText("SPEED: " + pokemon.getStats().get(5).toUpperCase(Locale.ROOT));
            }
        });
    }
}