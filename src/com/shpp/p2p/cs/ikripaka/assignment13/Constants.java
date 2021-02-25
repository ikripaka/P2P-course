package com.shpp.p2p.cs.ikripaka.assignment13;

import java.util.regex.Pattern;

/**
 * Contains constants that are used for finding silhouettes
 */
class Constants {
    /* Determines picture format*/
    final Pattern PICTURE_FORMAT = Pattern.compile("((assets\\/).+)|(.+)\\.(jpg|png)");

    /* Uses for wasting garbage*/
    final double ENTRY_THRESHOLD = 0.2;

    /* Tolerance of the color component*/
    final int PERMISSIBLE_VALIDATION = 50;

    final String DEFAULT_PATH_TO_THE_FILE = "assets/image1.jpg";
}
