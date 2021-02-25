package com.shpp.p2p.cs.ikripaka.assignment12;

import java.util.regex.Pattern;

/**
 * Contains constants that are used for finding silhouettes
 */
public class Constants {
    /* Determines picture format*/
    Pattern PICTURE_FORMAT = Pattern.compile(".+\\.(jpg|png)");

    /* Uses to identify where is font or silhouette*/
    double HALF_OF_THE_MAX_COLOUR_VALUE = 255 / 2.0;

    /* Uses to waste garbage*/
    double ENTRY_THRESHOLD = 0.37;
}
