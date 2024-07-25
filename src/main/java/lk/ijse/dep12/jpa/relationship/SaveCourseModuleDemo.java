package lk.ijse.dep12.jpa.relationship;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lk.ijse.dep12.jpa.relationship.entity.Course;
import lk.ijse.dep12.jpa.relationship.entity.CourseModule;
import lk.ijse.dep12.jpa.relationship.entity.Module;
import lk.ijse.dep12.jpa.relationship.util.JpaUtil;

import java.util.List;

public class SaveCourseModuleDemo {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
             EntityManager em = emf.createEntityManager()) {

            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                Course dep = em.find(Course.class, "C001");
                Course cmjd = em.find(Course.class, "C002");
                Course gdse = em.find(Course.class, "C003");

                Module m001 = em.find(Module.class, "M001");
                Module m002 = em.find(Module.class, "M002");
                Module m003 = em.find(Module.class, "M003");
                Module m004 = em.find(Module.class, "M004");

                CourseModule cm1 = new CourseModule(dep, m001);
                CourseModule cm2 = new CourseModule(dep, m002);

                CourseModule cm3 = new CourseModule(cmjd, m001);
                CourseModule cm4 = new CourseModule(cmjd, m002);

                CourseModule cm5 = new CourseModule(gdse, m001);
                CourseModule cm6 = new CourseModule(gdse, m002);
                CourseModule cm7 = new CourseModule(gdse, m003);
                CourseModule cm8 = new CourseModule(gdse, m004);

                List.of(cm1, cm2, cm3, cm4, cm5, cm6, cm7, cm8).forEach(em::persist);
                tx.commit();
            } catch (Throwable t) {
                tx.rollback();
                t.printStackTrace();
            }
        }
    }
}
