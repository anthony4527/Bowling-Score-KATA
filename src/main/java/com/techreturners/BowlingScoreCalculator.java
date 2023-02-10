package com.techreturners;

public class BowlingScoreCalculator {

    public int calculate (String pinRecord) {
        int[] scoreForFrame= {0,0,0,0,0,0,0,0,0,0};
        int resultScore = 0;
        String[] pinforThrow;

        // split the input string by space to get the pin for each throw
        // sum the pin for each throw and add to the scoreForFrame
        pinforThrow = pinRecord.split(" ");
        for (int i=0; i < pinforThrow.length ; i++ ){
            scoreForFrame[i] = Character.getNumericValue(pinforThrow[i].charAt(0)) + Character.getNumericValue(pinforThrow[i].charAt(1));
            resultScore += scoreForFrame[i];
        }

        return resultScore;
    }
}
