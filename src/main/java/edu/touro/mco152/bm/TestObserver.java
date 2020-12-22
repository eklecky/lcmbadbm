package edu.touro.mco152.bm;

/**
 * This class is an 'Observer' used purely for JUnit testing.
 */
public class TestObserver implements IObserver {

    DiskWorker diskWorker;
    public TestObserver(DiskWorker diskWorker){
        this.diskWorker = diskWorker;
        diskWorker.registerObserver(this);
    }


    boolean flag = false;
    @Override
    public void update() {
        flag = true;
    }
}
