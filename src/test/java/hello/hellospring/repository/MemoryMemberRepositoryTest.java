package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("m1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("m2");
        repository.save(member2);

        Member result = repository.findByName("m2").get();
        assertThat(member2).isEqualTo(result);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("m1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("m2");
        repository.save(member2);

        List<Member> results = repository.findAll();
        assertThat(results.size()).isEqualTo(2);
    }
}
