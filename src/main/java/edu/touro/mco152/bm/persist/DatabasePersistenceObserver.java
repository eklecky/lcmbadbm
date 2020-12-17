package edu.touro.mco152.bm.persist;

import edu.touro.mco152.bm.App;
import edu.touro.mco152.bm.DiskWorker;
import edu.touro.mco152.bm.IObserver;

import javax.persistence.EntityManager;

/**
 * Reflecting the Observer Design Pattern, this class is an 'Observer' to DiskWorker and it gets 'notified'
 * when DiskWorker deems it appropriate.
 */

public class DatabasePersistenceObserver implements IObserver {

    DiskRun runWrite = new DiskRun(DiskRun.IOMode.WRITE, App.blockSequence);
    DiskRun runRead = new DiskRun(DiskRun.IOMode.READ, App.blockSequence);

    DiskWorker diskWorker;

    public DatabasePersistenceObserver(DiskWorker diskWorker){
        this.diskWorker = diskWorker;
        this.diskWorker.registerObserver(this);
    }

    @Override
    public void update() {

        /**
         * Persist info about the Write BM Run (e.g. into Derby Database) and add it to a GUI panel.
         *
         * This block of code was take out of 'BenchmarkController' and placed here in order to use the Observer Design
         * Pattern to do database persistence.
         */

        if (App.writeTest) {
            EntityManager em = EM.getEntityManager();
            em.getTransaction().begin();
            em.persist(runWrite);
            em.getTransaction().commit();
        }
        if (App.readTest) {
            EntityManager em = EM.getEntityManager();
            em.getTransaction().begin();
            em.persist(runRead);
            em.getTransaction().commit();
        }
    }
}
