package edu.touro.mco152.bm;

/**
 * This class will act as the Invoker/Executor of the commands created.
 * DiskWorker will implement a new 'BenchmarkInvoker' in order to access the read and write commands.
 */
public class BenchmarkInvoker {
    private ICommand command;
    UIBluePrint uiBluePrint;

    public void setCommand(ICommand command){
        this.command = command;

    }

    public void run(){
        command.executeCommand(uiBluePrint);
    }

}
