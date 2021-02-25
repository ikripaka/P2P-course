package com.shpp.p2p.cs.ikripaka.assignment12;

import java.util.regex.Pattern;

/**
 * Contains constants that are used for finding silhouettes
 */
class Constants {
    // Determines picture format
    Pattern PICTURE_FORMAT = Pattern.compile("((assets\\/).+)|(.+)\\.(jpg|png)");

    // Uses for wasting garbage
    double ENTRY_THRESHOLD = 0.2;
}
