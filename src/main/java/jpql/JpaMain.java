package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team team1 = new Team();
            team1.setName("TeamA");
            em.persist(team1);

            Team team2 = new Team();
            team2.setName("TeamB");
            em.persist(team2);

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            member.setTeam(team1);
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setAge(10);
            member2.setTeam(team1);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("member3");
            member3.setAge(10);
            member3.setTeam(team2);
            em.persist(member3);

            em.flush();
            em.clear();

//            List<Member> result = em.createQuery("select m from Member m inner join m.team t", Member.class)
//                    .getResultList();

//            String query = "select m from Member m join fetch m.team t";
//            String query = "select t from Team t join fetch t.members";
//            String query = "select t from Team t where t = :team";


//            Member result = em.createNamedQuery("findByName", Member.class)
//                    .setParameter("username", member.getUsername())
//                    .getSingleResult();
//
//            System.out.println("result.toString() = " + result.toString());


//            for (Team t : resultList) {
//                System.out.println("t = " + t.toString() + ", members.size = " + t.getMembers().size());
//            }

            for(int i = 0; i < 10; i++) {
                Member tmpMember = new Member();
                tmpMember.setUsername("tmp_member" + i+1);
                em.persist(tmpMember);
            }
//            em.persist(memList);

//            Member member4 = new Member();
//            member4.setUsername("member3");
//            member4.setAge(10);
//            member4.setTeam(team2);
//            em.persist(member4);

            em.flush();
            em.clear();

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
