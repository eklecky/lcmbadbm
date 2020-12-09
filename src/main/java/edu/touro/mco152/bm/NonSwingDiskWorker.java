package edu.touro.mco152.bm;

import java.beans.PropertyChangeListener;

/**
 * This class provides a Non-Swing ability to DiskWorker
 * It is to be used mainly for JUnit tests
 */

public class NonSwingDiskWorker implements UIBluePrint{

    boolean isProgressComplete;

    public void provideProgress(int x) {
       if(x < 100 ){
            isProgressComplete = false;
        }
       else isProgressComplete = true;

    }

    public boolean cancelled() {
        return false;
    }


    public boolean setCancel(boolean bool) {
        return false;
    }

    public void newAddPropertyChangeListener(PropertyChangeListener listener) {
        System.out.println(listener);
    }

    public void NowExecute() {

    }

    public void newPublish(DiskMark diskMark) {
        System.out.println(diskMark);

    }
}
