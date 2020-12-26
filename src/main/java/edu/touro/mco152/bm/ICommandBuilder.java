package edu.touro.mco152.bm;

import edu.touro.mco152.bm.persist.DiskRun;

/**
 * This is the Builder interface which every Command Builder will have to implement
 */
public interface ICommandBuilder {

    ICommandBuilder setUIBlueprint(UIBluePrint uiBluePrint);

    ICommandBuilder setNumOfMarks(int numOfMarks);

    ICommandBuilder setNumOfBlocks(int numOfBlocks);

    ICommandBuilder setBlockSizeKB(int blockSizeKB);

    ICommandBuilder setBlockSequence(DiskRun.BlockSequence blockSequence);

    BenchmarkController build();
}
