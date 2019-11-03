package com.yl.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BarrenLand {
    final static int X_LIMIT = 400;
    final static int Y_LIMIT = 600;
    final int BARREN_LAND = 1;
    final int UNVISITED_LAND = 0;
    private List<int[]> barrenLandCoordinates = new ArrayList<int[]>();
    private Queue<int[]> queue = new LinkedList<>();
    private Map<Integer, Integer> areaMap = new HashMap<>();
    private int[][] grid;
    private String inputCoordinates;

    public BarrenLand() {
        grid = new int[X_LIMIT][Y_LIMIT];
        initGrid();
    }

    // read input
    public void parseInput() {
        String[] subStrings = inputCoordinates.split(",");
        for (String s : subStrings) {
            s = s.replaceAll("\"", "");     // remove "
            s = s.replaceAll("\\{", "");      // remove {
            s = s.replaceAll("\\}", "");      // remove {
            s = s.replaceAll("^ ", "");     // remove leading space
            s = s.replaceAll("“", "");      // remove open quote
            s = s.replaceAll("”", "");      // remove close quote

            String[] coordinates = s.split(" ");
            int x1 = Integer.parseInt(coordinates[0]);
            int y1 = Integer.parseInt(coordinates[1]);
            int x2 = Integer.parseInt(coordinates[2]);
            int y2 = Integer.parseInt(coordinates[3]);

            // check to make sure coordinates are within boundary
            try {
                checkBoundaries(x1, y1, x2, y2);
            } catch (IllegalArgumentException e) {
                System.out.println("Error. Invalid Coordinates!");
                System.exit(1);
            }

            // add valid rectangle to the list of barren land
            int[] rectangle = {x1, y1, x2, y2};
            barrenLandCoordinates.add(rectangle);
        }
    }

    public void checkBoundaries(int x1, int y1, int x2, int y2) {
        if (x1 < 0 || x1 > X_LIMIT - 1) {
            throw new IllegalArgumentException();
        }
        if (x2 < 0 || x2 > X_LIMIT - 1) {
            throw new IllegalArgumentException();
        }
        if (y2 < 0 || y2 > Y_LIMIT - 1) {
            throw new IllegalArgumentException();
        }
        if (y1 < 0 || y1 > Y_LIMIT - 1) {
            throw new IllegalArgumentException();
        }
    }

    public void readInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            inputCoordinates = br.readLine();
            if (inputCoordinates.matches("[0-9\",}{“” ]+")) {
                break;
            } else {
                System.out.println("Input not valid please give valid input!");
            }
        }
    }

    // initialize grid to 0
    private void initGrid() {
        for (int[] row : grid) {
            Arrays.fill(row, UNVISITED_LAND);
        }
    }

    // fill in barren land function
    public void fillBarrenLand() {
        for (int[] coordinates : barrenLandCoordinates) {
            int x1 = coordinates[0];
            int y1 = coordinates[1];
            int x2 = coordinates[2];
            int y2 = coordinates[3];

            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    grid[i][j] = BARREN_LAND;
                }
            }
        }
    }

    // function that counts the disconnected areas
    public void countFertileLand() {
        int landNumber = 1;     // 2 represents the first fertile land
        int counter;

        // BFS Algorithm to find disconnected fertile land
        for (int i = 0; i < X_LIMIT; i++) {
            for (int j = 0; j < Y_LIMIT; j++) {
                if (grid[i][j] == UNVISITED_LAND) {
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
                    if (grid[x][y] == UNVISITED_LAND) {
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
    public void printFertileLand() {
        List<Integer> values = new ArrayList<Integer>(areaMap.values());
        Collections.sort(values);
        for (int area : values) {
            System.out.print(area + " ");
        }
    }

    public List<int[]> getBarrenLandCoordinates() {
        return barrenLandCoordinates;
    }

    public Queue<int[]> getQueue() {
        return queue;
    }

    public Map<Integer, Integer> getAreaMap() {
        return areaMap;
    }

    public int[][] getGrid() {
        return grid;
    }

    public String getInputCoordinates() {
        return inputCoordinates;
    }
}
