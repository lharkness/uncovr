package com.leeharkness.cryptostuff;

import java.util.*;
import java.util.stream.Collectors;

public class PolybiusSquare {

    private int squareSize;
    char[][] polybiusSquare;

    String alphabet = "abcdefghiklmnopqrstuvwxyz";

    public PolybiusSquare(String key, int squareSize) {
        this.squareSize = squareSize;
        polybiusSquare = new char[squareSize][squareSize];

        Set<Character> keyChars = key.chars().mapToObj(c -> (char)c).collect(Collectors.toSet());
        Set<Character> alphaChars = alphabet.chars().mapToObj(c -> (char)c).collect(Collectors.toSet());

        Iterator<Character> keyIter = keyChars.iterator();
        Iterator<Character> alphaIter = alphaChars.iterator();

        int i = 0;
        int j = 0;

        while (i < squareSize) {
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
            for (int j = 0; j < squareSize; j++) {
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof RowColumnPair)) return false;
            RowColumnPair that = (RowColumnPair) o;
            return row == that.row &&
                    col == that.col;
        }

        @Override
        public int hashCode() {

            return Objects.hash(row, col);
        }
    }
}
