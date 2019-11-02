package com.yl.barrenland;

import java.util.*;

public class BarrenLand {
    final static int X_LIMIT = 400;
    final static int Y_LIMIT = 600;
    List<int[]> barrenLandCoordinates = new ArrayList<int[]>();
    Queue<int[]> queue = new LinkedList<>();
    Map<Integer, Integer> areaMap = new HashMap<>();
    int[][] grid;

    public BarrenLand() {
        // constructor
        grid = new int[X_LIMIT][Y_LIMIT];
        initGrid();
    }

    // read input
    void parseInput(String in) {
        String[] subStrings = in.split(",");
        for (String s : subStrings) {
            s = s.replaceAll("\"", "");
            s = s.replaceAll("\\{", "");
            s = s.replaceAll("\\}", "");
            s = s.replaceAll("^ ", "");
            s = s.replaceAll("“", "");
            s = s.replaceAll("”", "");

            String[] coordinates = s.split(" ");
            int x1 = Integer.parseInt(coordinates[0]);
            int y1 = Integer.parseInt(coordinates[1]);
            int x2 = Integer.parseInt(coordinates[2]);
            int y2 = Integer.parseInt(coordinates[3]);
            int[] rectangle = {x1, y1, x2, y2};
            barrenLandCoordinates.add(rectangle);
        }
    }

    void printCoordinates() {
        for (int[] i : barrenLandCoordinates) {
            System.out.println(Arrays.toString(i));
        }
    }

    // initialize grid to 0
    void initGrid() {
        for (int[] row : grid) {
            Arrays.fill(row, 0);
        }
    }

    // fill in barren land function
    void fillBarrenLand() {
        for (int[] coordinates : barrenLandCoordinates) {
            int x1 = coordinates[0];
            int y1 = coordinates[1];
            int x2 = coordinates[2];
            int y2 = coordinates[3];

            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    grid[i][j] = 1;     // 1 means barren land
                }
            }
        }
    }

    // function that counts the disconnected areas
    void countFertileLand() {
        int landNumber = 1;     // 2 represents the first fertile land
        int counter = 0;
        for (int i = 0; i < X_LIMIT; i++) {
            for (int j = 0; j < Y_LIMIT; j++) {
                if (grid[i][j] == 0) {
                    counter = 0;
                    queue.add(new int[]{i, j});
                    landNumber++;
                } else {
                    continue;
                }
                while (!queue.isEmpty()) {
                    int[] coordinate = queue.remove();
                    int x = coordinate[0];
                    int y = coordinate[1];

                    // Set the cell to a land area number if fertile
                    if (grid[x][y] == 0) {
                        grid[x][y] = landNumber;
                        counter++;

                        // Add the cell above
                        if (y < Y_LIMIT - 1) {
                            queue.add(new int[]{x, y + 1});
                        }
                        // Add the cell below
                        if (y > 0) {
                            queue.add(new int[]{x, y - 1});
                        }
                        // Add the cell to the right
                        if (x < X_LIMIT - 1) {
                            queue.add(new int[]{x + 1, y});
                        }
                        // Add the cell to the left
                        if (x > 0) {
                            queue.add(new int[]{x - 1, y});
                        }
                    }
                }
                areaMap.put(landNumber, counter);
            }
        }
    }

    // Print Sorted Fertile Land Area
    void printFertileLand() {
        List<Integer> values = new ArrayList<Integer>(areaMap.values());
        Collections.sort(values);
        for (int area : values) {
            System.out.print(area + " ");
        }
    }
}
