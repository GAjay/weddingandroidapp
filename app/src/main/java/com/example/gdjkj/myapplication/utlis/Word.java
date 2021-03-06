package com.example.gdjkj.myapplication.utlis;

/**
 * Created by gdjkj on 11/6/16.
 */

public class Word {

    /** String resource ID for the default translation of the word */
    private String functionName;

    /** String resource ID for the Miwok translation of the word */
    private String dateOfFunction;

    /** Audio resource ID for the word */
    private String placeOfFunction;

    /** Image resource ID for the word */
    private String locationImage;

    /** Constant value that represents no image was provided for this word */
    private static final int NO_IMAGE_PROVIDED = -1;

    /**
     * Create a new Word object.
     *
     * @param functionName is the string resource ID for the word in a language that the
     *                             user is already familiar with (such as English)
     * @param dateOfFunction is the string resource Id for the word in the Miwok language
     * @param placeOfFunction is the resource ID for the audio file associated with this word
     */
    public Word(String functionName, String dateOfFunction, String placeOfFunction , String locationImage) {

        this.functionName = functionName;
        this.dateOfFunction = dateOfFunction;
        this.placeOfFunction = placeOfFunction;
        this.locationImage = locationImage;
    }



    /**
     * Get the string resource ID for the default translation of the word.
     */
    public String getFunctionName() {
        return functionName;
    }

    /**
     * Get the string resource ID for the Miwok translation of the word.
     */
    public String getDateOfFunction() {
        return dateOfFunction;
    }

    /**
     * Return the image resource ID of the word.
     */
    public String getPlaceOfFunction() {
        return placeOfFunction;
    }


    /**
     * Return the audio resource ID of the word.
     */
    public String getLocationImage() {
        return locationImage;
    }
}
