package edu.touro.mco152.bm;

import edu.touro.mco152.bm.ui.Gui;


import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.util.List;

import static edu.touro.mco152.bm.App.dataDir;

public class DiskWorkerRunner extends SwingWorker<Boolean, DiskMark> implements UIBluePrint {


    DiskWorker dw = new DiskWorker(this);


    @Override
    public boolean cancelled() {
        return isCancelled();
    }


    @Override
    public void provideProgress(int x) {
        setProgress(x);
    }


    @Override
    public void newPublish(DiskMark diskMark) {
        publish(diskMark);
    }


    @Override
    public boolean setCancel(boolean bool){
        return cancel(bool);
    }


    @Override
    public void newAddPropertyChangeListener(PropertyChangeListener listener){
        addPropertyChangeListener(listener);
    }

    @Override
    public void NowExecute() {
        execute();
    }

    @Override
    public Boolean doInBackground() throws Exception {

        System.out.println("Starting benchmark!");
        return dw.decoupledDoInBackground();
    }

    @Override
    public void process(List<DiskMark> markList) {

        markList.stream().forEach((dm) -> {
            if (dm.type == DiskMark.MarkType.WRITE) {
                Gui.addWriteMark(dm);
            } else {
                Gui.addReadMark(dm);
            }
        });
    }

    @Override
    public void done() {

        if (App.autoRemoveData) {
            Util.deleteDirectory(dataDir);
        }
        App.state = App.State.IDLE_STATE;
        Gui.mainFrame.adjustSensitivity();
        System.err.println("Reached done()");
    }

}