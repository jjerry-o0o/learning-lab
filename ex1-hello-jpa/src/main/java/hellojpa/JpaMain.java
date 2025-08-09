package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 트랜잭션 단위마다 EntityManager 생성
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            // insert
            /* 비영속 상태 */
//            Member member = new Member();
//            member.setId(3L);
//            member.setName("Jel");

            /* 영속 상태 - EntityManager 라는 영속성 컨텍스트 안에서 member 객체가 관리됨*/
//            em.persist(member);

            // select
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.name = " + findMember.getName());

            // delete
//            Member findMember = em.find(Member.class, 3L);
//            em.remove(findMember);

            // update - persist() 안해도 객체 상태를 알아서 체크후 변경되면 update 쿼리 날림
//            Member findMember = em.find(Member.class, 2L);
//            findMember.setName("Jell");

            // 직접 쿼리 작성 - 우리가 아는 쿼리 문법과 달리 객체를 대상으로 작성해야함
//            List<Member> findMembers = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(0)
//                    .setMaxResults(1)
//                    .getResultList();
//            for (Member member : findMembers) {
//                System.out.println("member = " + member.getName());
//            }

            // 1차 캐시에서 조회
            // - persist() 가 아닌 commit() 할때 쿼리 날림.
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("Hello Jerry");
//
//            System.out.println("=== before ===");
//            em.persist(member);
//            System.out.println("=== after ===");
//
//            Member findJerry = em.find(Member.class, 101L);
//            System.out.println("Where is Jerry ? " + findJerry.getId());

            // 영속성
//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B");
//
//            em.persist(member1);
//            em.persist(member2);
//
//            System.out.println("======================");

//            Member member = em.find(Member.class, 150L);
//            member.setName("ZZZ");

            // flush()
//            Member member = new Member(200L, "flushMem");
//            em.persist(member);
//
//            System.out.println("=== start flush ===");
//            em.flush();
//            System.out.println("=== end flush ===");

            // 영속성 관리에서 제외시키기
//            Member member = em.find(Member.class, 150L);
//            member.setName("AAA");
//
//            em.detach(member);

            // 영속성 관리 clear - 1차 캐시에 데이터가 없어서 쿼리 2번 실행 됨.
//            Member member1 = em.find(Member.class, 150L);
//            em.clear();
//            Member member2 = em.find(Member.class, 150L);



            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
