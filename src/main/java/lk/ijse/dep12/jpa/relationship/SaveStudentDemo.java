package lk.ijse.dep12.jpa.relationship;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lk.ijse.dep12.jpa.relationship.entity.Contact;
import lk.ijse.dep12.jpa.relationship.entity.Student;
import lk.ijse.dep12.jpa.relationship.entity.User;
import lk.ijse.dep12.jpa.relationship.util.JpaUtil;

import java.util.List;

public class SaveStudentDemo {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
             EntityManager em = emf.createEntityManager()) {

            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                Student nuwan = new Student("931021796V", "Nuwan Ramindu", "Panadura");
                Student supun = new Student("991021796V", "Supun Perera", "Matara");

                Contact cn1 = new Contact(nuwan, "071-1234567");
                Contact cn2 = new Contact(nuwan, "038-1234567");
                Contact cs1 = new Contact(supun, "077-1234567");

                nuwan.getContactList().add(cn1);
                nuwan.getContactList().add(cn2);

                supun.getContactList().add(cs1);

                List.of(nuwan, supun).forEach(em::persist);
                tx.commit();
            } catch (Throwable t) {
                tx.rollback();
                t.printStackTrace();
            }
        }
    }
}
