package service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);

    }

    @AfterEach
    public void aferEach(){
        memberRepository.clearStore();

    }
    @Test
    void 회원가입() {
        //given 주어졌을때
        Member member = new Member();
        member.setName("hello");
        //when 이걸로 실행했을시
        Long saveId = memberService.join(member);
        //then 결과로 이게 나와야함
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    public void 중복_회원_예외(){
    //given
        Member  member1 = new Member();
        member1.setName("spring");

        Member  member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
     assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


        /*
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }

      */
            //then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}