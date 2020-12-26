package edu.touro.mco152.bm;

import edu.touro.mco152.bm.persist.DiskRun;

/**
 * This class is a Command class which executes the 'write' method in our 'receiver' BenchmarkController.
 *
 * This class now uses the Builder Design Pattern to build a BenchmarkController
 */

public class DiskWorkerWriteCommand implements ICommand {

    BenchmarkController benchmarkController;


    public DiskWorkerWriteCommand(UIBluePrint uiBluePrint, int numOfMarks, int numOfBlocks, int blockSizeKb,
                                  DiskRun.BlockSequence blockSequence) {
        //benchmarkController = new BenchmarkController(uiBluePrint, numOfMarks, numOfBlocks, blockSizeKb, blockSequence);

        //Build a 'benchmark request'
        benchmarkController = BenchmarkController.builder()
                .setUIBlueprint(uiBluePrint)
                .setNumOfMarks(numOfMarks)
                .setNumOfBlocks(numOfBlocks)
                .setBlockSizeKB(blockSizeKb)
                .setBlockSequence(blockSequence)
                .build();

    }

    @Override
    public void executeCommand(UIBluePrint uiBluePrint) {

        benchmarkController.write();
    }
}
