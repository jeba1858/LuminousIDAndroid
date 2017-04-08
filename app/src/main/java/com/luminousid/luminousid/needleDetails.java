package com.luminousid.luminousid;

import org.parceler.Parcel;

/**
 * Created by chase on 4/8/2017.
 * Allows data to be stored in the way needed for the Field Guide listviews.
 * Gets data for the deciduous section of Woody's.
 * Uses the Parcel library allow this class to be wrapped and passed on to a detail activity.
 */

@Parcel(Parcel.Serialization.BEAN)
public class needleDetails {
    private String species_name = null;
    private String common_name = null;
    private int plant_thumbnail = 0;
    private String family_name = null;
    private String growth_form = null;
    private String needle_apex = null;
    private String needle_arrangement = null;
    private String needle_per_fascile = null;
    private String leaf_type = null;
    private String cone = null;
    private String notes = null;
    private String photo_credit = null;
    private String plant_code = null;
    private String synonyms = null;

    public needleDetails(){

    }

    public needleDetails(String species_name, String common_name, int plant_thumbnail, String family_name,
                         String growth_form, String needle_apex, String needle_arrangement,
                         String needle_per_fascile, String leaf_type, String cone, String notes,
                         String photo_credit, String plant_code, String synonyms){

        this.species_name = species_name;
        this.common_name = common_name;
        this.plant_thumbnail = plant_thumbnail;
        this.family_name = family_name;
        this.growth_form = growth_form;
        this.needle_apex = needle_apex;
        this.needle_arrangement = needle_arrangement;
        this.needle_per_fascile = needle_per_fascile;
        this.leaf_type = leaf_type;
        this.cone = cone;
        this.notes = notes;
        this.photo_credit = photo_credit;
        this.plant_code = plant_code;
        this.synonyms = synonyms;
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

    public String getFamily_name() { return family_name; }

    public void setFamily_name(String family_name) { this.family_name = family_name; }

    public String getNeedle_apex() { return needle_apex; }

    public void setNeedle_apex(String needle_apex) { this.needle_apex = needle_apex; }

    public String getNeedle_arrangement() { return needle_arrangement; }

    public void setNeedle_arrangement(String needle_arrangement) { this.needle_arrangement = needle_arrangement; }

    public String getNeedle_per_fascile() { return needle_per_fascile; }

    public void setNeedle_per_fascile(String needle_per_fascile) { this.needle_per_fascile = needle_per_fascile; }

    public String getLeaf_type() { return leaf_type; }

    public void setLeaf_type(String leaf_type) { this.leaf_type = leaf_type; }

    public String getCone() { return cone; }

    public void setCone(String cone) { this.cone = cone; }

    public String getGrowth_form() { return growth_form; }

    public void setGrowth_form(String growth_form) { this.growth_form = growth_form; }

    public String getNotes() { return notes; }

    public void setNotes(String notes) { this.notes = notes; }

    public String getPhoto_credit() { return photo_credit; }

    public void setPhoto_credit(String photo_credit) { this.photo_credit = photo_credit; }

    public String getPlant_code() { return plant_code; }

    public void setPlant_code(String plant_code) { this.plant_code = plant_code; }

    public String getSynonyms() { return synonyms; }

    public void setSynonyms(String synonyms) { this.synonyms = synonyms; }

}
