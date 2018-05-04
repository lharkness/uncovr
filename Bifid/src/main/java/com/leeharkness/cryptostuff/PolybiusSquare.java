package com.leeharkness.cryptostuff;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class PolybiusSquare {

    private int squareSize;
    char[][] polybiusSquare;

    String alphabet = "abcdefghiklmnopqrstuvwxyz";

    public PolybiusSquare(String key, int squareSize) {
        this.squareSize = squareSize;
        polybiusSquare = new char[squareSize][squareSize];
        // I suspect there's a better way to do this...
        Set<Character> keyChars = new LinkedHashSet<>();
        for (byte b : key.getBytes()) {
            keyChars.add((char)b);
        }

        Set<Character> alphaChars = new LinkedHashSet<>();
        for (byte b : alphabet.getBytes()) {
            alphaChars.add((char)b);
        }

        Iterator<Character> keyIter = keyChars.iterator();
        Iterator<Character> alphaIter = alphaChars.iterator();

        int i = 0;
        int j = 0;

        while (i < 5) {
            if (keyIter.hasNext()) {
                polybiusSquare[i][j] = keyIter.next();
                j++;
                if (j >= squareSize) {
                    j = 0;
                    i++;
                }
            }
            else {
                char alphaChar = alphaIter.next();
                if (!keyChars.contains(alphaChar)) {
                    polybiusSquare[i][j] = alphaChar;
                    j++;
                    if (j >= squareSize) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    public char[][] getPolybiusSquare() {
        char[][] defensiveCopy = new char[squareSize][squareSize];
        for (int i = 0; i < squareSize; i++) {
            for (int j = 0; i < squareSize; j++) {
                defensiveCopy[i][j] = polybiusSquare[i][j];
            }
        }

        return defensiveCopy;
    }

    public RowColumnPair getPairFor(char c) {
        RowColumnPair pair = new RowColumnPair();
        for (int i = 0; i < squareSize; i++) {
            for (int j = 0; j < squareSize; j++) {
                if (polybiusSquare[i][j] == c) {
                    pair.row = i;
                    pair.col = j;
                    return pair;
                }
            }
        }

        return pair;
    }

    public char getCharAt(int row, int col) {
        return polybiusSquare[row][col];
    }

    public char getCharAt(RowColumnPair rowColumnPair) {
        return getCharAt(rowColumnPair.row, rowColumnPair.col);
    }


    static class RowColumnPair {
        int row = -1;
        int col = -1;

        public RowColumnPair() {}

        public RowColumnPair(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "row: " + row + " col: " + col;
        }
    }
}
