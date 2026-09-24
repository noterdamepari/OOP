package ru.nsu;


/**
 * Player's hand state which contain sum of points and amount of aces.
 */
public class HandState {
    int sum = 0;
    int aceCnt = 0;

    /**
     * Class constructor.
     */
    public HandState(int sum, int aceCnt) {
        this.sum = sum;
        this.aceCnt = aceCnt;
    }
}
