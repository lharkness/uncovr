package com.leeharkness.cryptostuff;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BifidTest {

    private static final String simplePlainText = "thisisasimplemessage";
    private static final char PAD_CHAR = 'x';
    private static final String key = "thekey";
    private static final int period = 3;

    private Bifid noKeyNoPeriodTarget = new Bifid("", PAD_CHAR, -1);
    private Bifid keyNoPeriodTarget = new Bifid(key, PAD_CHAR, -1);
    private Bifid keyPeriodTarget = new Bifid(key, PAD_CHAR, period);

    @Test
    public void testThatNoKeyNoPeriodWorks() {
        String cipherText = noKeyNoPeriodTarget.encrypt(simplePlainText);

        assertThat(noKeyNoPeriodTarget.decrypt(cipherText), is(simplePlainText));
    }

    @Test
    public void testThatKeyNoPeriodWorks() {
        String cipherText = keyNoPeriodTarget.encrypt(simplePlainText);

        assertThat(keyNoPeriodTarget.decrypt(cipherText), is(simplePlainText));
    }

    @Test
    public void testThatKeyAndPeriodWorks() {
        String cipherText = keyPeriodTarget.encrypt(simplePlainText);

        assertThat(keyPeriodTarget.decrypt(cipherText), is(simplePlainText));
    }

    @Test
    public void testThatPadCharIsUsed() {

        String plainText = "thisisanothertestmessagetodecrypt";
        Bifid testApp = new Bifid("short", 'x', 5);
        String cipherText = testApp.encrypt(plainText);
        String decryptedText = testApp.decrypt(cipherText);

        assertThat(decryptedText, is(plainText + 'x'));

    }

}