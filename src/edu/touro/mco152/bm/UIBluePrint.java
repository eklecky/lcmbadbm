package edu.touro.mco152.bm;


import java.beans.PropertyChangeListener;


public interface UIBluePrint {

    boolean cancelled();

    void provideProgress(int x);

    void newPublish(DiskMark diskMark);

    boolean setCancel(boolean bool);

    void newAddPropertyChangeListener(PropertyChangeListener listener);
}