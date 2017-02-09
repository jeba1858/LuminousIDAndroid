package com.luminousid.luminousid;

import android.provider.BaseColumns;
//import android.database.sqlite;

/**
 * Created by chase on 2/7/2017.
 */

public final class localFieldGuideContract {
    public static final int DATABASE_VERSION    = 1;
    public static final String AUTHORITY        = "com.luminousid.luminousid";
    public static final String DATABASE_NAME    = "localFieldGuide.db";
    public static final String TEXT_TYPE        = " TEXT";
    public static final String COMMA_SEP        = ",";

    // Create all the tables in DB
    public static final String[] SQL_CREATE_TABLE_ARRAY = {
            speciesName.CREATE_TABLE,
            familyName.CREATE_TABLE,
            FG_Photos.CREATE_TABLE,
            forbs.CREATE_TABLE,
            graminoids.CREATE_TABLE,
            poaceae.CREATE_TABLE,
            cyperceae.CREATE_TABLE,
            juncaceae.CREATE_TABLE,
            woody.CREATE_TABLE,
            needle.CREATE_TABLE,
            deciduous.CREATE_TABLE
    };

    // Resource IDs of all data to be loaded into DB
    public static final int[] RAW_IDS = {

    };

    // Prevent accidental instantiation
    private localFieldGuideContract() {}

    public static class speciesName implements BaseColumns {
        public static final String TABLE_NAME           = "speciesName";
        public static final String KEY_SPECIES_NAME     = "species_name";
        public static final String KEY_GROWTH_FORM      = "growth_form";

        public static final String CREATE_TABLE = "CREATE TABLE" + TABLE_NAME + " (" + _ID +
                " INTEGER PRIMARY KEY," +
                KEY_SPECIES_NAME + TEXT_TYPE + COMMA_SEP +
                KEY_GROWTH_FORM + TEXT_TYPE + " )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class familyName implements BaseColumns {
        public static final String TABLE_NAME           = "familyName";
        public static final String KEY_FAMILY_NAME      = "family_name";

        public static final String CREATE_TABLE = "CREATE TABLE" + TABLE_NAME + " (" + _ID +
                " INTEGER PRIMARY KEY," +
                KEY_FAMILY_NAME + TEXT_TYPE + " )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class FG_Photos implements BaseColumns {
        public static final String TABLE_NAME           = "FG_Photos";
        public static final String KEY_PLANT_CODE       = "plant_code";
        public static final String KEY_PLANT_PHOTO      = "plant_photo";

        public static final String CREATE_TABLE = "CREATE TABLE" + TABLE_NAME + " (" + _ID +
                " INTEGER PRIMARY KEY," +
                KEY_PLANT_CODE + TEXT_TYPE + COMMA_SEP +
                KEY_PLANT_PHOTO + TEXT_TYPE + " )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class forbs implements BaseColumns {
        public static final String TABLE_NAME               = "Forbs";
        public static final String KEY_SPECIES_NAME         = "species_name";
        public static final String KEY_GROWTH_FORM          = "growth_form";
        public static final String KEY_PLANT_CODE           = "plant_code";
        public static final String KEY_COMMON_NAME          = "common_name";
        public static final String KEY_FAMILY_NAME          = "family_name";
        public static final String KEY_SYNONYMS             = "synonyms";
        public static final String KEY_FLOWER_COLOR         = "flower_color";
        public static final String KEY_PETAL_NUMBER         = "petal_number";
        public static final String KEY_FLOWER_SHAPE         = "flower_shape";
        public static final String KEY_LEAF_ARRANGEMENT     = "leaf_arrangement";
        public static final String KEY_LEAF_SHAPE_FILTER    = "leaf_shape_filter";
        public static final String KEY_HABITAT              = "habitat";
        public static final String KEY_NOTES                = "notes";
        public static final String KEY_PHOTO_CREDIT         = "photo_credit";

        public static final String CREATE_TABLE = "CREATE TABLE" + TABLE_NAME + " (" + _ID +
                " INTEGER PRIMARY KEY," +
                KEY_SPECIES_NAME + TEXT_TYPE + COMMA_SEP +
                KEY_GROWTH_FORM + TEXT_TYPE + COMMA_SEP +
                KEY_PLANT_CODE + TEXT_TYPE + COMMA_SEP +
                KEY_COMMON_NAME + TEXT_TYPE + COMMA_SEP +
                KEY_FAMILY_NAME + TEXT_TYPE + COMMA_SEP +
                KEY_SYNONYMS + TEXT_TYPE + COMMA_SEP +
                KEY_FLOWER_COLOR + TEXT_TYPE + COMMA_SEP +
                KEY_PETAL_NUMBER + TEXT_TYPE + COMMA_SEP +
                KEY_FLOWER_SHAPE + TEXT_TYPE + COMMA_SEP +
                KEY_LEAF_ARRANGEMENT + TEXT_TYPE + COMMA_SEP +
                KEY_LEAF_SHAPE_FILTER + TEXT_TYPE + COMMA_SEP +
                KEY_HABITAT + TEXT_TYPE + COMMA_SEP +
                KEY_NOTES + TEXT_TYPE + COMMA_SEP +
                KEY_PHOTO_CREDIT + TEXT_TYPE + " )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class graminoids implements BaseColumns {
        public static final String TABLE_NAME               = "Graminoids";
        public static final String KEY_SPECIES_NAME         = "species_name";
        public static final String KEY_GROWTH_FORM          = "growth_form";
        public static final String KEY_FAMILY_NAME          = "family_name";

        public static final String CREATE_TABLE = "CREATE TABLE" + TABLE_NAME + " (" + _ID +
                " INTEGER PRIMARY KEY," +
                KEY_SPECIES_NAME + TEXT_TYPE + COMMA_SEP +
                KEY_GROWTH_FORM + TEXT_TYPE + COMMA_SEP +
                KEY_FAMILY_NAME + TEXT_TYPE + " )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class poaceae implements BaseColumns {
        public static final String TABLE_NAME               = "Poaceae";
        public static final String KEY_SPECIES_NAME         = "species_name";
        public static final String KEY_GROWTH_FORM          = "growth_form";
        public static final String KEY_PLANT_CODE           = "plant_code";
        public static final String KEY_COMMON_NAME          = "common_name";
        public static final String KEY_FAMILY_NAME          = "family_name";
        public static final String KEY_SYNONYMS             = "synonyms";
        public static final String KEY_STEM_CROSS_SECTION   = "stem_cross_section";
        public static final String KEY_LEAF_BLADE           = "leaf_blade";
        public static final String KEY_INFLORESCENCE        = "inflorescence";
        public static final String KEY_FLORETS_PER_SPIKELET = "florets_per_spikelet";
        public static final String KEY_AWNS                 = "awns";
        public static final String KEY_HABITAT              = "habitat";
        public static final String KEY_NOTES                = "notes";
        public static final String KEY_PHOTO_CREDIT         = "photo_credit";

        public static final String CREATE_TABLE = "CREATE TABLE" + TABLE_NAME + " (" + _ID +
                " INTEGER PRIMARY KEY," +
                KEY_SPECIES_NAME + TEXT_TYPE + COMMA_SEP +
                KEY_GROWTH_FORM + TEXT_TYPE + COMMA_SEP +
                KEY_PLANT_CODE + TEXT_TYPE + COMMA_SEP +
                KEY_COMMON_NAME + TEXT_TYPE + COMMA_SEP +
                KEY_FAMILY_NAME + TEXT_TYPE + COMMA_SEP +
                KEY_SYNONYMS + TEXT_TYPE + COMMA_SEP +
                KEY_STEM_CROSS_SECTION + TEXT_TYPE + COMMA_SEP +
                KEY_LEAF_BLADE + TEXT_TYPE + COMMA_SEP +
                KEY_INFLORESCENCE + TEXT_TYPE + COMMA_SEP +
                KEY_FLORETS_PER_SPIKELET + TEXT_TYPE + COMMA_SEP +
                KEY_AWNS + TEXT_TYPE + COMMA_SEP +
                KEY_HABITAT + TEXT_TYPE + COMMA_SEP +
                KEY_NOTES + TEXT_TYPE + COMMA_SEP +
                KEY_PHOTO_CREDIT + TEXT_TYPE + " )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class cyperceae implements BaseColumns {
        public static final String TABLE_NAME               = "Cyperceae";
        public static final String KEY_SPECIES_NAME         = "species_name";
        public static final String KEY_GROWTH_FORM          = "growth_form";
        public static final String KEY_PLANT_CODE           = "plant_code";
        public static final String KEY_COMMON_NAME          = "common_name";
        public static final String KEY_FAMILY_NAME          = "family_name";
        public static final String KEY_SYNONYMS             = "synonyms";
        public static final String KEY_STEM_CROSS_SECTION   = "stem_cross_section";
        public static final String KEY_LEAF_BLADE           = "petal_number";
        public static final String KEY_INFLORESCENCE        = "flower_shape";
        public static final String KEY_SPIKE_COLOR          = "leaf_arrangement";
        public static final String KEY_HABITAT              = "habitat";
        public static final String KEY_NOTES                = "notes";
        public static final String KEY_PHOTO_CREDIT         = "photo_credit";

        public static final String CREATE_TABLE = "CREATE TABLE" + TABLE_NAME + " (" + _ID +
                " INTEGER PRIMARY KEY," +
                KEY_SPECIES_NAME + TEXT_TYPE + COMMA_SEP +
                KEY_GROWTH_FORM + TEXT_TYPE + COMMA_SEP +
                KEY_PLANT_CODE + TEXT_TYPE + COMMA_SEP +
                KEY_COMMON_NAME + TEXT_TYPE + COMMA_SEP +
                KEY_FAMILY_NAME + TEXT_TYPE + COMMA_SEP +
                KEY_SYNONYMS + TEXT_TYPE + COMMA_SEP +
                KEY_STEM_CROSS_SECTION + TEXT_TYPE + COMMA_SEP +
                KEY_LEAF_BLADE + TEXT_TYPE + COMMA_SEP +
                KEY_INFLORESCENCE + TEXT_TYPE + COMMA_SEP +
                KEY_SPIKE_COLOR + TEXT_TYPE + COMMA_SEP +
                KEY_HABITAT + TEXT_TYPE + COMMA_SEP +
                KEY_NOTES + TEXT_TYPE + COMMA_SEP +
                KEY_PHOTO_CREDIT + TEXT_TYPE + " )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class juncaceae implements BaseColumns {
        public static final String TABLE_NAME               = "Juncaceae";
        public static final String KEY_SPECIES_NAME         = "species_name";
        public static final String KEY_GROWTH_FORM          = "growth_form";
        public static final String KEY_PLANT_CODE           = "plant_code";
        public static final String KEY_COMMON_NAME          = "common_name";
        public static final String KEY_FAMILY_NAME          = "family_name";
        public static final String KEY_SYNONYMS             = "synonyms";
        public static final String KEY_STEM_CROSS_SECTION   = "stem_cross_section";
        public static final String KEY_LEAF_BLADE           = "leaf_blade";
        public static final String KEY_HABITAT              = "habitat";
        public static final String KEY_NOTES                = "notes";
        public static final String KEY_PHOTO_CREDIT         = "photo_credit";

        public static final String CREATE_TABLE = "CREATE TABLE" + TABLE_NAME + " (" + _ID +
                " INTEGER PRIMARY KEY," +
                KEY_SPECIES_NAME + TEXT_TYPE + COMMA_SEP +
                KEY_GROWTH_FORM + TEXT_TYPE + COMMA_SEP +
                KEY_PLANT_CODE + TEXT_TYPE + COMMA_SEP +
                KEY_COMMON_NAME + TEXT_TYPE + COMMA_SEP +
                KEY_FAMILY_NAME + TEXT_TYPE + COMMA_SEP +
                KEY_SYNONYMS + TEXT_TYPE + COMMA_SEP +
                KEY_STEM_CROSS_SECTION + TEXT_TYPE + COMMA_SEP +
                KEY_LEAF_BLADE + TEXT_TYPE + COMMA_SEP +
                KEY_HABITAT + TEXT_TYPE + COMMA_SEP +
                KEY_NOTES + TEXT_TYPE + COMMA_SEP +
                KEY_PHOTO_CREDIT + TEXT_TYPE + " )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class woody implements BaseColumns {
        public static final String TABLE_NAME               = "Woody";
        public static final String KEY_SPECIES_NAME         = "species_name";
        public static final String KEY_GROWTH_FORM          = "growth_form";
        public static final String KEY_LEAF_TYPE            = "leaf_type";

        public static final String CREATE_TABLE = "CREATE TABLE" + TABLE_NAME + " (" + _ID +
                " INTEGER PRIMARY KEY," +
                KEY_SPECIES_NAME + TEXT_TYPE + COMMA_SEP +
                KEY_GROWTH_FORM + TEXT_TYPE + COMMA_SEP +
                KEY_LEAF_TYPE + TEXT_TYPE + " )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class needle implements BaseColumns {
        public static final String TABLE_NAME               = "Needle";
        public static final String KEY_SPECIES_NAME         = "species_name";
        public static final String KEY_GROWTH_FORM          = "growth_form";
        public static final String KEY_PLANT_CODE           = "plant_code";
        public static final String KEY_COMMON_NAME          = "common_name";
        public static final String KEY_FAMILY_NAME          = "family_name";
        public static final String KEY_SYNONYMS             = "synonyms";
        public static final String KEY_FLOWER_COLOR         = "flower_color";
        public static final String KEY_PETAL_NUMBER         = "petal_number";
        public static final String KEY_FLOWER_SHAPE         = "flower_shape";
        public static final String KEY_LEAF_ARRANGEMENT     = "leaf_arrangement";
        public static final String KEY_LEAF_SHAPE_FILTER    = "leaf_shape_filter";
        public static final String KEY_HABITAT              = "habitat";
        public static final String KEY_NOTES                = "notes";
        public static final String KEY_PHOTO_CREDIT         = "photo_credit";

        public static final String CREATE_TABLE = "CREATE TABLE" + TABLE_NAME + " (" + _ID +
                " INTEGER PRIMARY KEY," +
                KEY_SPECIES_NAME + TEXT_TYPE + COMMA_SEP +
                KEY_GROWTH_FORM + TEXT_TYPE + COMMA_SEP +
                KEY_PLANT_CODE + TEXT_TYPE + COMMA_SEP +
                KEY_COMMON_NAME + TEXT_TYPE + COMMA_SEP +
                KEY_FAMILY_NAME + TEXT_TYPE + COMMA_SEP +
                KEY_SYNONYMS + TEXT_TYPE + COMMA_SEP +
                KEY_FLOWER_COLOR + TEXT_TYPE + COMMA_SEP +
                KEY_PETAL_NUMBER + TEXT_TYPE + COMMA_SEP +
                KEY_FLOWER_SHAPE + TEXT_TYPE + COMMA_SEP +
                KEY_LEAF_ARRANGEMENT + TEXT_TYPE + COMMA_SEP +
                KEY_LEAF_SHAPE_FILTER + TEXT_TYPE + COMMA_SEP +
                KEY_HABITAT + TEXT_TYPE + COMMA_SEP +
                KEY_NOTES + TEXT_TYPE + COMMA_SEP +
                KEY_PHOTO_CREDIT + TEXT_TYPE + " )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class deciduous implements BaseColumns {
        public static final String TABLE_NAME               = "Deciduous";
        public static final String KEY_SPECIES_NAME         = "species_name";
        public static final String KEY_GROWTH_FORM          = "growth_form";
        public static final String KEY_PLANT_CODE           = "plant_code";
        public static final String KEY_COMMON_NAME          = "common_name";
        public static final String KEY_FAMILY_NAME          = "family_name";
        public static final String KEY_SYNONYMS             = "synonyms";
        public static final String KEY_LEAF_TYPE            = "leaf_type";
        public static final String KEY_CONE                 = "cone";
        public static final String KEY_LEAF_MARGIN          = "leaf_margin";
        public static final String KEY_LEAF_SHAPE           = "leaf_shape";
        public static final String KEY_LEAF_ARRANGEMENT     = "leaf_arrangement";
        public static final String KEY_NOTES                = "notes";
        public static final String KEY_PHOTO_CREDIT         = "photo_credit";

        public static final String CREATE_TABLE = "CREATE TABLE" + TABLE_NAME + " (" + _ID +
                " INTEGER PRIMARY KEY," +
                KEY_SPECIES_NAME + TEXT_TYPE + COMMA_SEP +
                KEY_GROWTH_FORM + TEXT_TYPE + COMMA_SEP +
                KEY_PLANT_CODE + TEXT_TYPE + COMMA_SEP +
                KEY_COMMON_NAME + TEXT_TYPE + COMMA_SEP +
                KEY_FAMILY_NAME + TEXT_TYPE + COMMA_SEP +
                KEY_SYNONYMS + TEXT_TYPE + COMMA_SEP +
                KEY_LEAF_TYPE + TEXT_TYPE + COMMA_SEP +
                KEY_CONE + TEXT_TYPE + COMMA_SEP +
                KEY_LEAF_MARGIN + TEXT_TYPE + COMMA_SEP +
                KEY_LEAF_ARRANGEMENT + TEXT_TYPE + COMMA_SEP +
                KEY_LEAF_SHAPE + TEXT_TYPE + COMMA_SEP +
                KEY_NOTES + TEXT_TYPE + COMMA_SEP +
                KEY_PHOTO_CREDIT + TEXT_TYPE + " )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

}
