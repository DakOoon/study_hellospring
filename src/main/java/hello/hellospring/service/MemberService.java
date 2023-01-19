package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     *
     * @param member 가입할 회원정보
     * @return 가입한 회원아이디
     */
    public Long join(Member member) {
        validateMemberDuplication(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateMemberDuplication(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 가입된 회원입니다.");
                });
    }

    /**
     * 전체회원조회
     * @return 회원목록
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원조회
     * @param memberId 조회할 회원아이디
     * @return 조회한 회원정보
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
