package com.leeharkness.cryptostuff;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PolybiusSquareTest {

    private final String alphabet = "abcdefghiklmnopqrstuvwxyz";
    private final int SQUARE_SIZE = 5;

    @Test
    public void testThatSquareIsPopulatedInSimpleCase() {
        PolybiusSquare testSquare = new PolybiusSquare("", SQUARE_SIZE);
        char[][] square = testSquare.getPolybiusSquare();

        int i = 0;
        int j = 0;

        for (char c : alphabet.toCharArray()) {
            assertThat(square[i][j], is(c));
            j++;

            if (j > SQUARE_SIZE - 1) {
                i++;
                j = 0;
            }
        }
    }

    @Test
    public void testThatSquareIsPopulatedInSimpleKeyCase() {
        String key = "oliver";
        PolybiusSquare testSquare = new PolybiusSquare(key, SQUARE_SIZE);

        Set<Character> keyChars = key.chars().mapToObj(c -> (char)c).collect(Collectors.toSet());
        Set<Character> alphaChars = alphabet.chars().mapToObj(c -> (char)c).collect(Collectors.toSet());

        List<Character> squareChars = new ArrayList<>(keyChars);
        alphaChars.stream()
                .filter(c -> !keyChars.contains(c))
                .forEach(squareChars::add);

        char[][] square = testSquare.getPolybiusSquare();

        int i = 0;
        int j = 0;

        for (char c : squareChars) {

            assertThat(square[i][j], is(c));
            j++;

            if (j > SQUARE_SIZE - 1) {
                i++;
                j = 0;
            }
        }
    }

    @Test
    public void testThatGetCharAtWorks() {
        PolybiusSquare testSquare = new PolybiusSquare("", SQUARE_SIZE);

        int i = 0;
        int j = 0;

        for (char c : alphabet.toCharArray()) {
            assertThat(testSquare.getCharAt(i, j), is(c));
            j++;

            if (j > SQUARE_SIZE - 1) {
                i++;
                j = 0;
            }
        }
    }

    @Test
    public void testThatGetCharAtPairWorks() {
        PolybiusSquare testSquare = new PolybiusSquare("", SQUARE_SIZE);

        int i = 0;
        int j = 0;
        int curCharPos = 0;

        while (i < SQUARE_SIZE - 1) {
            PolybiusSquare.RowColumnPair curPair = new PolybiusSquare.RowColumnPair(i, j);
            assertEquals(testSquare.getCharAt(curPair), alphabet.charAt(curCharPos));
            curCharPos++;
            j++;
            if (j > SQUARE_SIZE - 1) {
                i++;
                j = 0;
            }
        }
    }

    @Test
    public void testThatGetPairForWorks() {
        PolybiusSquare testSquare = new PolybiusSquare("", SQUARE_SIZE);

        int i = 0;
        int j = 0;
        int curCharPos = 0;

        while (i < SQUARE_SIZE - 1) {
            PolybiusSquare.RowColumnPair expectedPair = new PolybiusSquare.RowColumnPair(i, j);
            assertThat(testSquare.getPairFor(alphabet.charAt(curCharPos)), is(expectedPair));
            curCharPos++;
            j++;
            if (j > SQUARE_SIZE - 1) {
                i++;
                j = 0;
            }
        }
    }

    @Test
    public void testThatGetPairForReturnsEmptyPairForCharNotItSquare() {
        PolybiusSquare testSquare = new PolybiusSquare("", SQUARE_SIZE);

        assertThat(testSquare.getPairFor('1'), is(new PolybiusSquare.RowColumnPair()));
    }

}