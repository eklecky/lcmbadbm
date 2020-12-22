package edu.touro.mco152.bm;

/**
 * This interface is the 'Observer' interface that all concrete observers (for example Gui.java) will implement.
 *
 * It has a single method called update which an 'Observable' method will call
 */
public  interface IObserver {

    void update();
}