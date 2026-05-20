package interfaces;

import moves.Move;

import java.util.ArrayList;

public interface Battleable {

    void useMove(int index, Battleable target);
    void receiveDamage(int damage);
    void receiveSpecialDamage(int damage);
    void healFull();

    boolean isFainted();
    boolean isAlive();

    String getName();
    int getHp();
    int getSpeed();
    ArrayList<Move> getMoves();


    void showMoves();
    void showStatus();
    void showSummary();
}