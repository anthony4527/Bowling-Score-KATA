package com.techreturners;


public class BowlingScoreCalculator {

    private final int fullScoreforThrow = 10;
    private final int MaxFrame = 10;
    private final char Spare = '/';
    private final char Missed = '-';
    private final char Strike = 'x';

    public int calculate(String pinRecord) {
        int[] scoreForFrame = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int resultScore = 0;
        String[] pinsforFrame;
        int tempScore = 0;   //temp value before adjustment
        char isNormalFrame = 'Y';

        // split the input string by space to get the pin for each throw
        // sum the pin for each throw and add to the scoreForFrame
        pinsforFrame = pinRecord.split(" ");

        //if less than 10 records OR more than 12 records, return error
        if ((pinsforFrame.length < MaxFrame) || (pinsforFrame.length > MaxFrame +2 )){
            System.out.println("The pins records input are not correct - missing or too many");
            return -1;
        }

        //Start calculation by read each record
        for (int i = 0; i < pinsforFrame.length; i++) {
            tempScore = 0;
            // if pin record is 'x', score is 10;
            // if pin record has a '-', ignore it and only use the numeric
            // if pin record has a '/', score is 10
            for (int j=0; j< pinsforFrame[i].length(); j++ ){
                switch (pinsforFrame[i].charAt(j)){
                    case Missed:
                        break;
                    case Strike:
                        // check if this pinRecord has two data, it is error and should stop
                        if (pinsforFrame[i].length() > 1){
                            System.out.println("The pins records input are not correct - missing or too many");
                            return -1;
                        } else {
                            tempScore = fullScoreforThrow;
                        }
                        break;
                    case Spare:
                        tempScore = fullScoreforThrow;
                        break;
                    default:
                        //check if it is not alphanumeric, return error
                        if (Character.isDigit(pinsforFrame[i].charAt(j)) ) {
                            tempScore +=Character.getNumericValue(pinsforFrame[i].charAt(j));
                        } else {
                            System.out.println("The pins records input are not correct - missing or too many");
                            return -1;
                        }

                }
            }
            //check if tempScore > 10, there is error input
            if (tempScore> fullScoreforThrow  ) {
                System.out.println("The pins records input are not correct - missing or too many");
                return -1;
            }

            //check if this is more than 10 frames, the score should be added to the last frame
            //otherwise put tempScore to current frame score
            //handle different cases for 1st to 10th frame, add additional records for 11th or 12th throw
            if (i< MaxFrame){
                isNormalFrame = 'Y';
            }else{
                isNormalFrame = 'N';
            }
            switch( isNormalFrame) {
                case ('Y'):
                    //cases for 1st to 10 frames
                    //put the tempScore value to current frame score
                    scoreForFrame[i] += tempScore;
                    //if current record is a strike or spare, add to previous frame(s)
                    if ((i > 0) && (pinsforFrame[i - 1].equals(Character.toString(Strike)))) {
                        scoreForFrame[i - 1] += tempScore;
                    } else {
                        // check if previous frame is a spare and then add bonus
                        if ((i>0) && (pinsforFrame[i-1].charAt(1) == Spare)){
                            if (pinsforFrame[i].length() ==1){
                                scoreForFrame[i-1] += tempScore;
                            } else {
                                scoreForFrame[i-1] += Character.getNumericValue(pinsforFrame[i].charAt(0)) ;
                            }
                        }
                    }
                    //if current throw is 2nd roll after strike, add the score the previous strike frame
                    if ((i>1) && (pinsforFrame[i-2].equals(Character.toString(Strike))) && (pinsforFrame[i].length() ==1)) {
                        scoreForFrame[i - 2] += tempScore;
                    }
                    break;

                case ('N'):
                    // cases of throws after 10th frames
                    // add the score for additional throws to the 10th frame
                    scoreForFrame[MaxFrame-1] += tempScore;
                    // if the 9th frame is also a strike and this is the 11th throw, add the first roll to the 9th frame
                    if ((pinsforFrame[MaxFrame-2].equals(Character.toString(Strike))) && (i== MaxFrame)) {
                        if (pinsforFrame[i].length() == 1) {
                            scoreForFrame[MaxFrame-2] += tempScore;
                        } else {
                            scoreForFrame[MaxFrame-2] += Character.getNumericValue(pinsforFrame[i].charAt(0));
                        }
                    }
                    break;
            }
        } // end for all pinRecord

        //add all frames adjusted scores and return
        for (int i = 0; i < scoreForFrame.length; i++) {
            resultScore += scoreForFrame[i];
        }
        return resultScore;
    }

}

