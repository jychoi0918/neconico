package com.neconico.neconico.service.email.certgenerator;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class GenerateCertCharacter {
    private final char[] characterTable = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };

    private int numberLength;

    public void setNumberLength(int length) {
        numberLength = length;
    }

    public String executeGenerate() {
        Random random = new Random();
        int tableLength = characterTable.length;
        StringBuffer buffer = new StringBuffer();

        for(int i=0; i<numberLength; i++) {
            buffer.append(characterTable[random.nextInt(tableLength)]);
        }

        return buffer.toString();
    }
}
