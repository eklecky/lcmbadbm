package edu.touro.mco152.bm;

import edu.touro.mco152.bm.persist.DiskRun;
import edu.touro.mco152.bm.ui.Gui;

import javax.swing.*;
import java.util.ArrayList;

import static edu.touro.mco152.bm.App.*;

/**
 * This class has been modified to be Invoker of the benchmarking commands created.
 * The class's constructor takes ICommands
 *
 * Additionally, following the Observer Design Pattern, this class is now a 'Subject'/'Observable' which
 * when appropriate, 'notifies' its 'Observers' that something has changed.
 */

public class DiskWorker implements IObservable{


     UIBluePrint uiBluePrint;


    public  DiskWorker(UIBluePrint uiBluePrint){
        this.uiBluePrint = uiBluePrint;
    }

    public void callExecute(){
        uiBluePrint.NowExecute();
    }

    DiskRun run;

    /**
     * We 'got here' because:
     * a) End-user clicked 'Start' on the benchmark UI,
     * which triggered the start-benchmark event associated with the App::startBenchmark()
     * method.
     * b) startBenchmark() then instantiated a DiskWorker, and called
     * its (super class's) execute() method, causing Swing to eventually
     * call this doInBackground() method.
     */
    public  Boolean decoupledDoInBackground() throws Exception {


        System.out.println("*** starting new worker thread");
        msg("Running readTest " + readTest + "   writeTest " + writeTest);
        msg("num files: " + numOfMarks + ", num blks: " + numOfBlocks
                + ", blk size (kb): " + App.blockSizeKb + ", blockSequence: " + blockSequence);



        Gui.updateLegend();  // init chart legend info

        if (App.autoReset) {
            App.resetTestData();
            Gui.resetTestData();
        }


        BenchmarkInvoker benchmarkInvoker = new BenchmarkInvoker();

        //Create read and write commands to be passed into invoker below
        ICommand read = new DiskWorkerReadCommand(uiBluePrint, numOfMarks,
                numOfBlocks, blockSizeKb, blockSequence);
        ICommand write = new DiskWorkerWriteCommand(uiBluePrint, numOfMarks,
                numOfBlocks, blockSizeKb, blockSequence);


        /**
         * The GUI allows either a write, read, or both types of BMs to be started. They are done serially.
         */
        if (App.writeTest) {
            benchmarkInvoker.setCommand(write);
            benchmarkInvoker.run();

            notifyObservers();
        }

        /**
         * Most benchmarking systems will try to do some cleanup in between 2 benchmark operations to
         * make it more 'fair'. For example a networking benchmark might close and re-open sockets,
         * a memory benchmark might clear or invalidate the Op Systems TLB or other caches, etc.
         */

        // try renaming all files to clear cache
        if ( App.readTest && App.writeTest && !uiBluePrint.cancelled() ) {
            JOptionPane.showMessageDialog(Gui.mainFrame,
                    "For valid READ measurements please clear the disk cache by\n" +
                            "using the included RAMMap.exe or flushmem.exe utilities.\n" +
                            "Removable drives can be disconnected and reconnected.\n" +
                            "For system drives use the WRITE and READ operations \n" +
                            "independantly by doing a cold reboot after the WRITE",
                    "Clear Disk Cache Now", JOptionPane.PLAIN_MESSAGE);
        }

        // Same as above, just for Read operations instead of Writes.
        if (App.readTest) {
           benchmarkInvoker.setCommand(read);
            benchmarkInvoker.run();

            notifyObservers();
        }

        nextMarkNumber += numOfMarks;

        return true;
    }


    //initialize an arrayList of type IObserver to be used in the 'addObserver', 'removeObserver'
    // and 'notifyObservers' methods
     ArrayList<IObserver> observers = new ArrayList<IObserver>();

    @Override
    public void registerObserver(IObserver iObserver) {
        observers.add(iObserver);
    }

    @Override
    public void removeObserver(IObserver iObserver) {
        observers.remove(iObserver);
    }

    @Override
    public void notifyObservers() {
        for (IObserver io : observers)
        {
            io.update(run);
        }
    }
}
