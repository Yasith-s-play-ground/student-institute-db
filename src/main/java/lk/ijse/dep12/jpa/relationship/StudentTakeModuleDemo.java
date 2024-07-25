package lk.ijse.dep12.jpa.relationship;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lk.ijse.dep12.jpa.relationship.entity.*;
import lk.ijse.dep12.jpa.relationship.entity.Module;
import lk.ijse.dep12.jpa.relationship.util.JpaUtil;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class StudentTakeModuleDemo {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
             EntityManager em = emf.createEntityManager()) {

            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                Student nuwan = em.find(Student.class, "931021796V");
                Student supun = em.find(Student.class, "991021796V");

                Batch b001 = em.find(Batch.class, "B001");

                Module pf = em.find(Module.class, "M001");
                Module oop = em.find(Module.class, "M002");

                StudentBatchModule sbm1 = new StudentBatchModule(nuwan, b001, pf);
                StudentBatchModule sbm2 = new StudentBatchModule(nuwan, b001, oop);
                StudentBatchModule sbm3 = new StudentBatchModule(supun, b001, oop);
                StudentBatchModule sbm4 = new StudentBatchModule(supun, b001, pf);

                List.of(sbm1, sbm2, sbm3, sbm4).forEach(em::persist);

                tx.commit();
            } catch (Throwable t) {
                tx.rollback();
                t.printStackTrace();
            }
        }
    }
}
