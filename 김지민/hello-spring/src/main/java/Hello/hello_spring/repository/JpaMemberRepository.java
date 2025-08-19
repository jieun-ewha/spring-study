package Hello.hello_spring.repository;

import Hello.hello_spring.domain.Member;

import jakarta.persistence.EntityManager;//javax ->jakarta
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    //생성자
    public JpaMemberRepository(EntityManager em){
        this.em = em;
    }
    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class,id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        //멤버 엔티티 자체를 select
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() { //객체를 대상으로 쿼리를 날린다???
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
