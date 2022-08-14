package com.springex.jpa.util.random;

import java.util.Random;

import lombok.Data;

@Data
public class RandomGenEngKeyword {
    
    private String keyword;

    public String randomGenKeyword(int keywordSize) {
        String result = "";
        Random random = new Random();
        for(int i = 0; i < keywordSize; i++) {
            result += (char)('A' + random.nextInt(26));
        }
        return result;
    }

}
