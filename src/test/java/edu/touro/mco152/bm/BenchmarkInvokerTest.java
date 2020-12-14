package edu.touro.mco152.bm;

import edu.touro.mco152.bm.persist.DiskRun;
import edu.touro.mco152.bm.ui.Gui;
import edu.touro.mco152.bm.ui.MainFrame;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class will test the Invoker and in turn the Read and Write commands using the Non-Swing compliant UI
 */

class BenchmarkInvokerTest {
    /**
     * Bruteforce setup of static classes/fields to allow DiskWorker to run.
     *
     * @author lcmcohen
     */
    private void setupDefaultAsPerProperties()
    {
        /// Do the minimum of what  App.init() would do to allow to run.
        Gui.mainFrame = new MainFrame();
        App.p = new Properties();
        App.loadConfig();
        System.out.println(App.getConfigString());
        Gui.progressBar = Gui.mainFrame.getProgressBar(); //must be set or get Nullptr

        // configure the embedded DB in .jDiskMark
        System.setProperty("derby.system.home", App.APP_CACHE_DIR);

        // code from startBenchmark
        //4. create data dir reference
        App.dataDir = new File(App.locationDir.getAbsolutePath()+ File.separator+App.DATADIRNAME);

        //5. remove existing test data if exist
        if (App.dataDir.exists()) {
            if (App.dataDir.delete()) {
                App.msg("removed existing data dir");
            } else {
                App.msg("unable to remove existing data dir");
            }
        }
        else
        {
            App.dataDir.mkdirs(); // create data dir if not already present
        }
    }



    @Test
    void readCommandRun() {
        setupDefaultAsPerProperties();

        NonSwingDiskWorker nonSwingDiskWorker = new NonSwingDiskWorker();
        BenchmarkInvoker benchmarkInvoker = new BenchmarkInvoker();

         ICommand read = new DiskWorkerReadCommand(nonSwingDiskWorker, 25,
                128, 64, DiskRun.BlockSequence.SEQUENTIAL);
        benchmarkInvoker.setCommand(read);
        benchmarkInvoker.run();
        
        assertTrue(nonSwingDiskWorker.isProgressComplete);
    }


    @Test
    void writeCommandRun() {
        setupDefaultAsPerProperties();

        NonSwingDiskWorker nonSwingDiskWorker = new NonSwingDiskWorker();
        BenchmarkInvoker benchmarkInvoker = new BenchmarkInvoker();

        ICommand write = new DiskWorkerWriteCommand(nonSwingDiskWorker, 25,
                128, 64, DiskRun.BlockSequence.SEQUENTIAL);
        benchmarkInvoker.setCommand(write);
        benchmarkInvoker.run();

        assertTrue(nonSwingDiskWorker.isProgressComplete);
    }
}
