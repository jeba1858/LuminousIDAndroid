package com.luminousid.luminousid;

/**
 * Created by chase on 3/18/2017.
 * A class to help with the Firebase UI library to populate a listview.
 * Allows data to be stored in the way needed for the Field Guide listviews.
 */

public class speciesName {

    private String species_name = null;
    private String common_name = null;

    public speciesName(){

    }

    public speciesName(String species_name, String common_name){
        this.species_name = species_name;
        this.common_name = common_name;
    }

    public String getSpecies_name(){
        return species_name;
    }

    public void setSpecies_name(String newspecies_name){ this.species_name = newspecies_name; }

    public String getCommon_name(){
        return common_name;
    }

    public void setCommon_name(String newCommon_name){ this.common_name = newCommon_name; }

}
