package br.com.unicuritiba.minhapokedex.network;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import br.com.unicuritiba.minhapokedex.model.Pokemon;

public class PokeAPI {

    public void getPokemons(PokemonListener listener) {


        ConnectionAsyncTask connectionAsyncTask = new ConnectionAsyncTask(
                new ConnectionAsyncTask.ConnectionListener() {
                    @Override
                    public void onRequestResult(JSONObject object) {

                        ArrayList<Pokemon> pokemons = new ArrayList<>();

                        try {
                            JSONArray pokemonResults = object.getJSONArray("results");

                            for (int index = 0; index < pokemonResults.length(); index++) {

                                JSONObject objectPokemon = pokemonResults.getJSONObject(index);

                                int id = index + 1;
                                String name = objectPokemon.getString("name");
                                String url = objectPokemon.getString("url");
                                String idUrl = url.replace("https://pokeapi.co/api/v2/pokemon/", "").replace("/", "");
                                String image = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + id + ".png";

                                pokemons.add(new Pokemon(id, name, image));
                            }
                        } catch (Exception e) {

                        }

                        listener.onPokemonMapperFinish(pokemons);

                    }
                }
        );
        connectionAsyncTask.execute("https://pokeapi.co/api/v2/pokemon?limit=151", "GET");
    }

    public void getPokemonDetail(int id, PokeDetailListener pokeDetailListener) {


        ConnectionAsyncTask connectionAsyncTask = new ConnectionAsyncTask(new ConnectionAsyncTask.ConnectionListener() {
            @Override
            public void onRequestResult(JSONObject object) {
                Pokemon pokemon = new Pokemon();

                try {
                    String height = object.getString("height");
                    pokemon.setHeight(height);
                    String weight = object.getString("weight");
                    pokemon.setWeight(weight);

                    JSONArray pokemonType = object.getJSONArray("types");
                    ArrayList typeResult = new ArrayList();
                    for (int i = 0; i < pokemonType.length(); i++) {
                        JSONObject pokemontipo = pokemonType.getJSONObject(i);

                        JSONObject typename = pokemontipo.getJSONObject("type");
                        String nametipo = typename.getString("name");


                        typeResult.add(nametipo);

                    }
                    pokemon.setTypes(typeResult);
                    //---------------//
                    JSONArray pokemonAbility = object.getJSONArray("abilities");
                    ArrayList abilityResult = new ArrayList();
                    for (int a = 0; a < pokemonAbility.length(); a++) {
                        JSONObject pokeabi = pokemonAbility.getJSONObject(a);

                        JSONObject PokeAbility = pokeabi.getJSONObject("ability");
                        String nameAbility = PokeAbility.getString("name");
                        abilityResult.add(nameAbility);

                    }
                    pokemon.setAbilities(abilityResult);
                    //-------------------------//

                    JSONArray pokemonMoves = object.getJSONArray("moves");
                    ArrayList movesResult = new ArrayList();
                    for (int m = 0; m<pokemonMoves.length();m++){
                        JSONObject pokemove = pokemonMoves.getJSONObject(m);

                        JSONObject PokeMove = pokemove.getJSONObject("move");
                        String move = PokeMove.getString("name");
                        movesResult.add(move);
                    }
pokemon.setMoves(movesResult);
                    //-------------------------//
                    JSONArray pokeStats = object.getJSONArray("stats");
                    ArrayList statsResult = new ArrayList();

                    for (int s = 0; s<pokeStats.length();s++){
                        JSONObject pokestats = pokeStats.getJSONObject(s);

                        String PokemonStats = pokestats.getString("base_stat");
                        JSONObject Stats = pokestats.getJSONObject("stat");
                        String statsname = Stats.getString("name");

                        statsResult.add(PokemonStats);


                    }
                    pokemon.setStats(statsResult);

                } catch (Exception e) {

                }
                pokeDetailListener.onPokemonMapperDetailFinish(pokemon);

            }
        });
        connectionAsyncTask.execute("https://pokeapi.co/api/v2/pokemon/" + id + "/", "GET");
    }

    public interface PokemonListener {
        void onPokemonMapperFinish(ArrayList<Pokemon> pokemons);
    }

    public interface PokeDetailListener {
        void onPokemonMapperDetailFinish(Pokemon pokemon);
    }
}
