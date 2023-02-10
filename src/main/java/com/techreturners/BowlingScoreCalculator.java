package com.techreturners;

public class BowlingScoreCalculator {

    private final int fullScoreforThrow = 10;
    private final int MaxFrame = 10;

    public int calculate(String pinRecord) {
        int[] scoreForFrame = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int resultScore = 0;
        String[] pinsforFrame;
        boolean currentStrike = false;
        boolean previousStrike = false;
        int tempScore = 0;     //temp value before adjustment

        // split the input string by space to get the pin for each throw
        // sum the pin for each throw and add to the scoreForFrame
        pinsforFrame = pinRecord.split(" ");
        //System.out.println(pinsforFrame.length);

        for (int i = 0; i < pinsforFrame.length; i++) {
            tempScore = 0;
            // if pinsforFrmae is 'x', score is 10; make a note for the next two throws for add
            if (pinsforFrame[i].equals("x")) {
                tempScore += fullScoreforThrow;
                currentStrike = true;

            } else {
                tempScore = Character.getNumericValue(pinsforFrame[i].charAt(0)) + Character.getNumericValue(pinsforFrame[i].charAt(1));
            }
            //check if this is more than 10 frames, the score should be added to the last frame
            //otherwise put tempScore to current frame
            if (i< MaxFrame){
                scoreForFrame[i] += tempScore;
                if ((i > 0) && (pinsforFrame[i - 1].equals("x"))) {
                    scoreForFrame[i - 1] += tempScore;
                }
                if ((i>1) && (pinsforFrame[i-2].equals("x")) && (pinsforFrame[i].length() ==1)) {
                    scoreForFrame[i - 2] += tempScore;
                }
            } else {
                // if this frame has one roll, add to 10th and 9th if each is a strike
                if (pinsforFrame[i].length() == 1) {
                    scoreForFrame[MaxFrame-1] += tempScore;
                    if ((pinsforFrame[MaxFrame-2].equals("x")) && (i == MaxFrame)) {
                        scoreForFrame[MaxFrame-2] += tempScore;
                    }
                }
                else { //add two rolls to the last Frame
                    scoreForFrame[MaxFrame-1] += tempScore;
                    // if 9th frame is also a strike, add first roll to 9th frame too
                    scoreForFrame[MaxFrame-2] += Character.getNumericValue(pinsforFrame[i].charAt(0));
                }



            }


        } // end for all pinRecord

        for (int i = 0; i < scoreForFrame.length; i++) {
            resultScore += scoreForFrame[i];
            System.out.println("final score for "+i+" is "+ scoreForFrame[i]);
        }
        return resultScore;
    }
}
