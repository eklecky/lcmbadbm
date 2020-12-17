package edu.touro.mco152.bm;

public interface ICommand {
    /**
     * Basic Command Interface which all Command Classes will have to inherit from.
     *
     * The method is called 'executeCommand' instead of simply 'execute' to help differentiate between
     * Swing's 'execute' method
     * @param uiBluePrint
     */

    void executeCommand(UIBluePrint uiBluePrint);

}
