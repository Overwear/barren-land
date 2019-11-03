package com.yl.main;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.List;

import static org.junit.Assert.*;

public class BarrenLandTest {

    private BarrenLand bl;
    ByteArrayOutputStream baos;
    PrintStream stdout = System.out;
    InputStream stdin = System.in;

    @Before
    public void beforeTest(){

        bl = new BarrenLand();
        baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
    }

    @After
    public void afterTest(){
        bl = null;
        System.out.flush();
        System.setOut(stdout);
        System.setIn(stdin);
    }

    @Test
    public void testSet1() throws IOException {
        String s = "{“0 292 399 307”}";
        System.setIn(new ByteArrayInputStream(s.getBytes()));
        bl.readInput();
        bl.parseInput();
        bl.fillBarrenLand();
        bl.countFertileLand();
        bl.printFertileLand();
        String output = baos.toString();
        assertEquals("116800 116800 ",output);
    }

    @Test
    public void testSet2() throws IOException {
        String s = "{“48 192 351 207”, “48 392 351 407”, “120 52 135 547”, “260 52 275 547”}";
        System.setIn(new ByteArrayInputStream(s.getBytes()));
        bl.readInput();
        bl.parseInput();
        bl.fillBarrenLand();
        bl.countFertileLand();
        bl.printFertileLand();
        String output = baos.toString();
        assertEquals("22816 192608 ", output);
    }

    @Test
    public void testInputInvalid() throws IOException {
        String s = "{“0 292 399 -1”}";
        System.setIn(new ByteArrayInputStream(s.getBytes()));
        try{
            bl.readInput();
        }
        catch(NullPointerException e){
            String output = baos.toString();
            assertEquals("Input not valid please give valid input!\n", output);
        }
    }

    @Test
    public void testInvalidCharacterInput() throws IOException {
        String s = "{“0 292 399 d”}";
        System.setIn(new ByteArrayInputStream(s.getBytes()));
        try{
            bl.readInput();
        }
        catch(NullPointerException e){
            String output = baos.toString();
            assertEquals("Input not valid please give valid input!\n", output);
        }
    }

    @Test
    public void testCheckBoundariesPass() {
        bl.checkBoundaries(0,292,399,307);
    }

    @Test
    public void testCheckBoundariesPass2() {
        bl.checkBoundaries(399,599,399,599);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckLowerBoundary_X1() {
        bl.checkBoundaries(-1,0,0,0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckUpperBoundary_X1() {
        bl.checkBoundaries(400,0,0,0);
    }
    @Test
    public void testCheckLowerBoundary_Y1() {
        bl.checkBoundaries(0,0,0,0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckUpperBoundary_Y1() {
        bl.checkBoundaries(0,600,0,0);
    }

    @Test
    public void testCheckIntParse() throws IOException {
        String s = "{“0 292 399 307”}";
        System.setIn(new ByteArrayInputStream(s.getBytes()));
        bl.readInput();
        bl.parseInput();
        List<int[]> c = bl.getBarrenLandCoordinates();
        int[] firstCoordinate = c.get(0);
        assertEquals(0, firstCoordinate[0]);
        assertEquals(292, firstCoordinate[1]);
        assertEquals(399, firstCoordinate[2]);
        assertEquals(307, firstCoordinate[3]);
    }

}