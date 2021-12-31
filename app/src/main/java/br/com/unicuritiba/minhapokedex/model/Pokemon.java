package br.com.unicuritiba.minhapokedex.model;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Pokemon implements Serializable {

    private int id;
    private String name;
    private String image;
    private String height;
    private String weight;
    private ArrayList<String> types;
    private ArrayList<String> moves;
    private ArrayList<String> stats;
    private  ArrayList<String> abilities;

    public Pokemon(int id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public Pokemon() {

    }



    public ArrayList<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(ArrayList<String> abilities) {
        this.abilities = abilities;
    }

    public void setStats(ArrayList<String> stats) {
        this.stats = stats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList types) {
        this.types = types;
    }

    public ArrayList<String> getStats() {
        return stats;
    }

    public ArrayList<String> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<String> moves) {
        this.moves = moves;
    }
}
