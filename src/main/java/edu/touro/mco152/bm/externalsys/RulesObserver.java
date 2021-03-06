package edu.touro.mco152.bm.externalsys;

import edu.touro.mco152.bm.App;
import edu.touro.mco152.bm.DiskWorker;
import edu.touro.mco152.bm.IObserver;
import edu.touro.mco152.bm.persist.DiskRun;
import edu.touro.mco152.bm.ui.Gui;

/**
 * This is a rules 'Observer' who knows about all post-benchmark-execution rules (only 1 so far, Slack)
 * and checks benchmark results against these rules.
 */
public class RulesObserver implements IObserver {

    DiskWorker diskWorker;
    public RulesObserver(DiskWorker diskWorker){
        this.diskWorker = diskWorker;
        diskWorker.registerObserver(this);
    }

    DiskRun runRead = new DiskRun(DiskRun.IOMode.READ, App.blockSequence);

    @Override
    public void update(DiskRun diskRun) {

        // Read benchmark result has an iteration 'max time' that exceeds
        // 3 per cent of the benchmarks's average time as per assignment
        if ((App.readTest) && (diskRun.getRunMax() > (diskRun.getRunAvg() * 0.03)))
        {
                SlackManager slackmgr = new SlackManager("BadBM");
                // Boolean worked = slackmgr.postMsg2OurChannel(":cry: Benchmark failed");
                Boolean worked = slackmgr.postMsg2OurChannel(":grimacing: The Benchmark's max time has exceeded" +
                        " 3 percent of its average time.");
                System.out.println("Retcode from test sending msg is " + worked);
        }
    }
}
