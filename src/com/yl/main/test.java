package com.yl.main;

import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {
        BarrenLand bl = new BarrenLand();
        bl.readInput();
        bl.parseInput();
        bl.fillBarrenLand();
        bl.countFertileLand();
        bl.printFertileLand();
    }
}
