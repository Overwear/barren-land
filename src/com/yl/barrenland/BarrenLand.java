package com.yl.barrenland;

import java.util.ArrayList;
import java.util.List;

public class BarrenLand {
    final static int X_LIMIT = 400;
    final static int Y_LIMIT = 600;
    List<Integer[]> barrenLandCoordinates = new ArrayList<>();
    int[][] grid = new int[X_LIMIT][Y_LIMIT];

    // read input
    void parseInput(String in) {
        String[] subStrings = in.split(",");
        for (String s : subStrings) {
            s = s.replaceAll("\"", "");
            s = s.replaceAll("{", "");
            s = s.replaceAll("}", "");

            String[] coordinates = s.split(" ");
            int x1 = Integer.parseInt(coordinates[0]);
            int y1 = Integer.parseInt(coordinates[1]);
            int x2 = Integer.parseInt(coordinates[2]);
            int y2 = Integer.parseInt(coordinates[3]);

        }
    }

}
    // parse input string to int

    // store coordinates in a datastructure


    // fill in barren land function

    // create ds that stores disconnected areas

    // function that counts the disconnected areas

    // printing each area in sorted order

    //

    public static void main(String[] args) {

    }
}
