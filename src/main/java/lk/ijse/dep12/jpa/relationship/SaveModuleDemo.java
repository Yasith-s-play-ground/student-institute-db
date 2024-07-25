package lk.ijse.dep12.jpa.relationship;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lk.ijse.dep12.jpa.relationship.entity.Course;
import lk.ijse.dep12.jpa.relationship.entity.Module;
import lk.ijse.dep12.jpa.relationship.util.JpaUtil;

import java.util.List;

public class SaveModuleDemo {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
             EntityManager em = emf.createEntityManager()) {

            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                Module m001 = new Module("M001", "Programming Fundamentals", 2, Module.Type.Mandatory);
                Module m002 = new Module("M002", "Object Oriented Programming", 3, Module.Type.Mandatory);
                Module m003 = new Module("M003", "Communication Skills", 1, Module.Type.Mandatory);
                Module m004 = new Module("M004", "Entrepreneurship Skills", 2, Module.Type.Optional);

                List.of(m001, m002, m003, m004).forEach(em::persist);
                tx.commit();
            } catch (Throwable t) {
                tx.rollback();
                t.printStackTrace();
            }
        }
    }
}
