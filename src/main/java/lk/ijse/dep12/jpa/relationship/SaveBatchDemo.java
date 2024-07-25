package lk.ijse.dep12.jpa.relationship;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lk.ijse.dep12.jpa.relationship.entity.Batch;
import lk.ijse.dep12.jpa.relationship.entity.Course;
import lk.ijse.dep12.jpa.relationship.util.JpaUtil;

import java.math.BigDecimal;
import java.util.List;

public class SaveBatchDemo {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
             EntityManager em = emf.createEntityManager()) {

            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                Course dep = em.find(Course.class, "C001");
                Course cmjd = em.find(Course.class, "C002");
                Course gdse = em.find(Course.class, "C003");

                Batch dep12 = new Batch("B001", dep, "6 Months", new BigDecimal("200000"));
                Batch cmjd150 = new Batch("CMJD0150", cmjd, "12 Months", new BigDecimal("80000"));
                Batch gdse80 = new Batch("GDSE0080", gdse, "2 Years", new BigDecimal("400000"));
                Batch gdse81 = new Batch("GDSE0081", gdse, "2 Years", new BigDecimal("400000"));

                List.of(dep12, cmjd150, gdse80, gdse81).forEach(em::persist);
                tx.commit();
            } catch (Throwable t) {
                tx.rollback();
                t.printStackTrace();
            }
        }
    }
}
