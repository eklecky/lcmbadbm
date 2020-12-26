package edu.touro.mco152.bm;

import edu.touro.mco152.bm.persist.DiskRun;

/**
 * This class Builds "benchmark requests" (using the Builder Design Pattern) which will be passed into anyone
 * trying to create a Command
 *
 * All its methods aside from 'build()' return a BenchmarkRequestBuilder in order to be able to call many
 * BenchmarkRequestBuilder methods in one statement resulting in Syntactic Sugar
 */
public class BenchmarkRequestBuilder implements ICommandBuilder {
    private UIBluePrint uiBluePrint;
    private int numOfMarks;
    private int numOfBlocks;
    private int blockSizeKB;
    private DiskRun.BlockSequence blockSequence;

    @Override
    public BenchmarkRequestBuilder setUIBlueprint(UIBluePrint uiBlueprint) {
        this.uiBluePrint = uiBlueprint;
        return this;
    }

    @Override
    public BenchmarkRequestBuilder setNumOfMarks(int numOfMarks) {
        this.numOfMarks = numOfMarks;
        return this;
    }

    @Override
    public BenchmarkRequestBuilder setNumOfBlocks(int numOfBlocks) {
        this.numOfBlocks = numOfBlocks;
        return this;
    }

    @Override
    public BenchmarkRequestBuilder setBlockSizeKB(int blockSizeKB) {
        this.blockSizeKB = blockSizeKB;
        return this;
    }

    @Override
    public BenchmarkRequestBuilder setBlockSequence(DiskRun.BlockSequence blockSequence) {
        this.blockSequence = blockSequence;
        return this;
    }

    /**
     * This method will return the 'Benchmark Request' Object needed by anyone wanting to create a Command
     *
     * @return
     */
    @Override
    public BenchmarkController build() {
        return new BenchmarkController(this.uiBluePrint, this.numOfBlocks, this.numOfMarks,
                this.blockSizeKB, this.blockSequence);
    }
}


