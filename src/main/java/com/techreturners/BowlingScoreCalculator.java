package com.techreturners;

import javax.swing.*;

public class BowlingScoreCalculator {

    private final int fullScoreforThrow = 10;
    private final int MaxFrame = 10;

    public int calculate(String pinRecord) {
        int[] scoreForFrame = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int resultScore = 0;
        String[] pinsforFrame;
        int tempScore = 0;     //temp value before adjustment
        boolean spareBonus = false;

        // split the input string by space to get the pin for each throw
        // sum the pin for each throw and add to the scoreForFrame
        pinsforFrame = pinRecord.split(" ");

        for (int i = 0; i < pinsforFrame.length; i++) {
            tempScore = 0;
            // if pinsforFrmae is 'x', score is 10; make a note for the next two throws for add
            if (pinsforFrame[i].equals("x")) {
                tempScore += fullScoreforThrow;

            } else  {
                // if pin record has a '-', ignore it and only use the numeric
                // if pin record has a '/', score is 10 and set a flag for next throw to add
                for (int j=0; j< pinsforFrame[i].length(); j++ ){
                    switch (pinsforFrame[i].charAt(j)){
                        case '-':
                            break;
                        case '/':   // this will always come as the 2nd value; if the 1st roll knocks all pin, then it will have been a strike
                            tempScore = 10;
                            spareBonus = true;
                            break;
                        default:
                            tempScore +=Character.getNumericValue(pinsforFrame[i].charAt(j));
                    }
                }

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
                // add bonus to previous frame if a spare bonus has been on
                if ((i>0) && (spareBonus == true)){
                    if (pinsforFrame[i].length() ==1){
                        scoreForFrame[i-1] += tempScore;
                    } else {
                        scoreForFrame[i-1] += Character.getNumericValue(pinsforFrame[i].charAt(0)) ;
                    }

                }
            } else {
                // if this frame has one roll, add to 10th for strike or spare
                // if 9th is also a strike, add to 10th frame too
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
