package com.leeharkness.cryptostuff;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the Bifid Cipher: http://practicalcryptography.com/ciphers/bifid-cipher/
 * Usage: Bifid <test|encrypt|decrypt> [text] [key] [period]
 */
public class Bifid {

    // The size of the Polybius square used
    // TODO: make this configurable?
    @SuppressWarnings("FieldCanBeLocal")
    private final int SQUARE_SIZE = 5;

    private PolybiusSquare square;

    private int period;
    private char padChar;

    /**
     * Initialization ctor
     *
     * @param key     The key to use
     * @param padChar the pad character to use for odd-length plaintext
     * @param period  the period to use for fractionating
     */
    Bifid(String key, char padChar, int period) {
        square = new PolybiusSquare(key, SQUARE_SIZE);
        this.padChar = padChar;
        this.period = period;
    }

    /**
     * Encrypts the input
     *
     * @param input the text to encrypt
     * @return the encrypted text
     */
    String encrypt(String input) {

        String plainText = input;

        // Add the pad character to odd-length plaintext
        if (input.length() % 2 != 0) {
            plainText += padChar;
        }

        List<PolybiusSquare.RowColumnPair> rowColumnPairs = new ArrayList<>();
        List<PolybiusSquare.RowColumnPair> encipheredRowColumnPairs = new ArrayList<>();
        List<Character> cipherTextList = new ArrayList<>();

        // List of lists to support periods (groups)
        List<List<Integer>> rows = new ArrayList<>();
        List<List<Integer>> cols = new ArrayList<>();

        // Get the row/column pairs for each letter in the plaintext - removing everything except letters
        // TODO: perhaps leave un-encipherable characters in the ciphertext?
        for (byte b : plainText.replaceAll("[^a-zA-Z]", "").toLowerCase().getBytes()) {
            rowColumnPairs.add(square.getPairFor((char) b));
        }

        int periodToUse = period;
        // If we don't have a period then use the whole string
        if (period == -1) {
            periodToUse = rowColumnPairs.size();
        }

        int curIndex = 0;

        // Get all the row numbers, group them by period
        while (curIndex < rowColumnPairs.size()) {
            List<Integer> curGroup = new ArrayList<>();
            for (int i = 0; i < periodToUse; i++) {
                if (curIndex < rowColumnPairs.size()) {
                    curGroup.add(rowColumnPairs.get(curIndex).row);
                    curIndex++;
                }
            }
            rows.add(curGroup);
        }

        // Get all column numbers, group them by period
        curIndex = 0;
        while (curIndex < rowColumnPairs.size()) {
            List<Integer> curGroup = new ArrayList<>();
            for (int i = 0; i < periodToUse; i++) {
                if (curIndex < rowColumnPairs.size()) {
                    curGroup.add(rowColumnPairs.get(curIndex).col);
                    curIndex++;
                }
            }
            cols.add(curGroup);
        }

        List<Integer> encipheredNumbers = new ArrayList<>();

        // Add all row and column numbers into one list, alternating row and column number
        for (int i = 0; i < rows.size(); i++) {
            encipheredNumbers.addAll(rows.get(i));
            encipheredNumbers.addAll(cols.get(i));
        }

        // Create RowColumn pairs from our enciphered numbers
        // (not strictly required as we have a getCharAt(row, col), but I thought it was more readable
        for (int i = 0; i < encipheredNumbers.size() - 1; i += 2) {
            encipheredRowColumnPairs.add(new PolybiusSquare.RowColumnPair(encipheredNumbers.get(i),
                    encipheredNumbers.get(i + 1)));
        }

        // Create our ciphertext
        for (PolybiusSquare.RowColumnPair rowColumnPair : encipheredRowColumnPairs) {
            cipherTextList.add(square.getCharAt(rowColumnPair));
        }

        StringBuilder stringBuilder = new StringBuilder();
        cipherTextList.forEach(stringBuilder::append);
        return stringBuilder.toString();
    }

    /**
     * Decrypts ciphertext
     *
     * @param input the ciphertext
     * @return the plaintext
     */
    String decrypt(String input) {

        List<PolybiusSquare.RowColumnPair> rowColumnPairs = new ArrayList<>();
        List<Character> plainTextList = new ArrayList<>();

        List<List<Integer>> rows = new ArrayList<>();
        List<List<Integer>> cols = new ArrayList<>();

        // Go get our ciphertext numbers
        for (byte b : input.getBytes()) {
            rowColumnPairs.add(square.getPairFor((char) b));
        }

        int periodToUse = period;

        // if we don't have a period use the whole thing
        if (period == -1) {
            periodToUse = rowColumnPairs.size();
        }

        int curIndex = 0;

        // Go get our rows, grouped into period-sized chunks
        while (curIndex < rowColumnPairs.size()) {
            List<Integer> curGroup = new ArrayList<>();
            for (int i = 0; i < periodToUse; i++) {
                if (curIndex < rowColumnPairs.size()) {
                    curGroup.add(rowColumnPairs.get(curIndex).row);
                    curIndex++;
                }
            }
            rows.add(curGroup);
        }

        // Go get our columns, grouped into period-sized chunks
        curIndex = 0;
        while (curIndex < rowColumnPairs.size()) {
            List<Integer> curGroup = new ArrayList<>();
            for (int i = 0; i < periodToUse; i++) {
                if (curIndex < rowColumnPairs.size()) {
                    curGroup.add(rowColumnPairs.get(curIndex).col);
                    curIndex++;
                }
            }
            cols.add(curGroup);
        }

        // Do this for each group
        for (int group = 0; group < rows.size(); group++) {

            List<Integer> currentDecipheredNumbers = new ArrayList<>();
            List<PolybiusSquare.RowColumnPair> decipheredRowColumnPairs = new ArrayList<>();

            // add row followed by column to our deciphered number list
            for (int j = 0; j < rows.get(group).size(); j++) {
                currentDecipheredNumbers.add(rows.get(group).get(j));
                currentDecipheredNumbers.add(cols.get(group).get(j));
            }

            // Bust the number list in half, then collect the numbers from each half next to each other
            // as: 1234 -> 1324
            List<Integer> rearrangedNumbers = new ArrayList<>();

            int halfwayPoint = currentDecipheredNumbers.size() / 2;

            for (int i = 0; i < halfwayPoint; i++) {
                rearrangedNumbers.add(currentDecipheredNumbers.get(i));
                rearrangedNumbers.add(currentDecipheredNumbers.get(i + halfwayPoint));
            }

            // Create RowCol pairs (not strictly required, but I think it reads better)
            for (int i = 0; i < rearrangedNumbers.size() - 1; i += 2) {
                decipheredRowColumnPairs.add(new PolybiusSquare.RowColumnPair(rearrangedNumbers.get(i),
                        rearrangedNumbers.get(i + 1)));
            }

            for (PolybiusSquare.RowColumnPair rowColumnPair : decipheredRowColumnPairs) {
                plainTextList.add(square.getCharAt(rowColumnPair));
            }
        }

        // Convert our decrypted characters to a string and return that
        StringBuilder stringBuilder = new StringBuilder();
        plainTextList.forEach(stringBuilder::append);
        return stringBuilder.toString();
    }

    public static void main(String[] args) {

        // Usage Bifid <command> [text] [key] [period]

        String command = args[0];

        if (command.equalsIgnoreCase("test")) {
            Bifid testApp = new Bifid("", 'x', -1);
            String plainText = "thisisatestmessage";
            System.out.println("Encrypting using no key and no period");
            System.out.println(String.format("Encrypting: %s", plainText));
            String cipherText = testApp.encrypt(plainText);
            System.out.println(String.format("Ciphertext: %s", cipherText));
            System.out.println("Decrypting");
            String decryptedText = testApp.decrypt(cipherText);
            System.out.println(String.format("Test passed: %b", decryptedText.equals(plainText)));

            plainText = "thisisanothertestmessage";
            testApp = new Bifid("short", 'x', -1);
            System.out.println("Encrypting using short key and no period");
            System.out.println(String.format("Encrypting: %s", plainText));
            cipherText = testApp.encrypt(plainText);
            System.out.println(String.format("Ciphertext: %s", cipherText));
            System.out.println("Decrypting");
            decryptedText = testApp.decrypt(cipherText);
            System.out.println(String.format("Test passed: %b", decryptedText.equals(plainText)));

            plainText = "thisisanothertestmessagetodecrypt";
            testApp = new Bifid("short", 'x', 5);
            System.out.println("Encrypting using short key and a period of 5");
            System.out.println(String.format("Encrypting: %s", plainText));
            cipherText = testApp.encrypt(plainText);
            System.out.println(String.format("Ciphertext: %s", cipherText));
            System.out.println("Decrypting");
            decryptedText = testApp.decrypt(cipherText);
            System.out.println(String.format("Test passed: %b", decryptedText.equals(plainText + 'x')));
        } else {
            // command input text key
            String inputText = args[1];
            int period = -1;
            String key = "";
            if (args.length > 2) {
                key = args[2];
            }
            if (args.length > 3) {
                period = Integer.parseInt(args[3]);
            }

            Bifid app = new Bifid(key, 'x', period);

            if (command.equalsIgnoreCase("encrypt")) {
                System.out.println(app.encrypt(inputText));
                System.exit(0);
            }

            if (command.equalsIgnoreCase("decrypt")) {
                System.out.println(app.decrypt(inputText));
                System.exit(0);
            }

        }
    }
}