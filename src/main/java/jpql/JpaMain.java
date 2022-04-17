package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            member.setTeam(team);
            em.persist(member);

            Member member2 = new Member();
            member2.setAge(10);
            member2.setTeam(team);
            em.persist(member2);

            em.flush();
            em.clear();

//            List<Member> result = em.createQuery("select m from Member m inner join m.team t", Member.class)
//                    .getResultList();
            List<String> result = em.createQuery("SELECT coalesce(m.username, 'test') FROM Member m", String.class)
                    .getResultList();

            System.out.println("result.size() = " + result.size());
            for (String rs : result) {
                System.out.println("rs = " + rs);
            }

            tx.commit();
        } catch(Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }
}
