package lk.ijse.dep12.jpa.relationship;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lk.ijse.dep12.jpa.relationship.entity.*;
import lk.ijse.dep12.jpa.relationship.util.JpaUtil;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class RegisterStudentDemo {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
             EntityManager em = emf.createEntityManager()) {

            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                Student nuwan = em.find(Student.class, "931021796V");
                Student supun = em.find(Student.class, "991021796V");

                User yasithperera = em.find(User.class, "yasithperera");
                Batch b001 = em.find(Batch.class, "B001");

                StudentRegistration studentRegistration1 = new StudentRegistration(nuwan, b001, yasithperera, Date.valueOf(LocalDate.now()));
                StudentRegistration studentRegistration2 = new StudentRegistration(supun, b001, yasithperera, Date.valueOf(LocalDate.now()));

                em.persist(studentRegistration1);
                em.persist(studentRegistration2);

                tx.commit();
            } catch (Throwable t) {
                tx.rollback();
                t.printStackTrace();
            }
        }
    }
}
