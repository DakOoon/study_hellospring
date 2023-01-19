package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class MemberServiceIntegratedTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("m1");

        // when
        Long memberId = memberService.join(member);

        // then
        Member result = memberRepository.findById(memberId).get();
        assertThat(member.getName()).isEqualTo(result.getName());
    }

    @Test
    void joinWhenDup() {
        // given
        Member member1 = new Member();
        member1.setName("m1");

        Member member2 = new Member();
        member2.setName("m1");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class
                , () -> memberService.join(member2));

        // then
        assertThat(e.getMessage()).isEqualTo("이미 가입된 회원입니다.");
    }
}
