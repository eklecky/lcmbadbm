package edu.touro.mco152.bm;


import java.beans.PropertyChangeListener;

/**
 * Define the contract of a generic Interface uses to start and montior a benchmark
 * Modeled after Swing Interface
 */


public interface UIBluePrint {

    boolean cancelled();

    void provideProgress(int x);

    void newPublish(DiskMark diskMark);

    boolean setCancel(boolean bool);

    void newAddPropertyChangeListener(PropertyChangeListener listener);

    void NowExecute();


}