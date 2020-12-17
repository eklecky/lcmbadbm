package edu.touro.mco152.bm;

/**
 * This Interface is the Subject/Observable interface that concrete Subject/Observable will implement.
 * It is titled 'Observable' instead of 'Subject' because I find that 'Subject' is confusing to think about.
 */
public interface IObservable {
    void registerObserver(IObserver iObserver);

    //This method is not necessary for the HW assignment but it good Observer Design practice in general
    void removeObserver(IObserver iObserver);

    void notifyObservers();
}