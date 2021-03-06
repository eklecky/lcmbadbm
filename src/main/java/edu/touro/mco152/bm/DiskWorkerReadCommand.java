package edu.touro.mco152.bm;

import edu.touro.mco152.bm.persist.DiskRun;

/**
 * This class is a Command class which executes the 'read' method in our 'receiver' BenchmarkController
 * with "particular set of run parameters" described in the assignment
 */

public class DiskWorkerReadCommand implements ICommand {

    BenchmarkController benchmarkController;


    public DiskWorkerReadCommand(UIBluePrint uiBluePrint, int numOfMarks, int numOfBlocks, int blockSizeKb,
                                  DiskRun.BlockSequence blockSequence) {
        benchmarkController = new BenchmarkController(uiBluePrint, numOfMarks, numOfBlocks, blockSizeKb, blockSequence);

    }


    @Override
    public void executeCommand(UIBluePrint uiBluePrint) {

        benchmarkController.read();
    }

}



