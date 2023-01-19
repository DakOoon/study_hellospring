package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemoryMemberRepository memoryMemberRepository;
    MemberService memberService;

    @BeforeEach
    void beforeEach() {
        memoryMemberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryMemberRepository);
    }
    @AfterEach
    void afterEach() {
        memoryMemberRepository.clearStore();
    }
    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("m1");

        // when
        Long memberId = memberService.join(member);

        // then
        Member result = memberService.findOne(memberId).get();
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
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        // then
        assertThat(e.getMessage()).isEqualTo("이미 가입된 회원입니다.");
    }
}