package com.luminousid.luminousid;

/**
 * Created by chase on 3/18/2017.
 * A class to help with the Firebase UI library to populate a listview.
 * Allows data to be stored in the way needed for the Field Guide listviews.
 */

public class speciesName {

    private String species_name = null;
    private String common_name = null;
    private int plant_thumbnail = 0;

    public speciesName(){

    }

    public speciesName(String species_name, String common_name, int plant_thumbnail){
        this.species_name = species_name;
        this.common_name = common_name;
        this.plant_thumbnail = plant_thumbnail;
    }

    public String getSpecies_name(){
        return species_name;
    }

    public void setSpecies_name(String newspecies_name) { this.species_name = newspecies_name; }

    public String getCommon_name(){
        return common_name;
    }

    public void setCommon_name(String newCommon_name) { this.common_name = newCommon_name; }

    public int getPlant_thumbnail() { return plant_thumbnail; }

    public void setPlant_thumbnail(int newPlant_thumbnail) { this.plant_thumbnail = newPlant_thumbnail; }

}
