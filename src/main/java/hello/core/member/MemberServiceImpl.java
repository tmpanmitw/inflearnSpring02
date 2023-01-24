package hello.core.member;

public class MemberServiceImpl implements MemberService{
    //final 은 반드시 초기화를 해줘야 한다.

    // 1. 추상클래스 의존과 생성자를 통해 구현체를 바로 생성하는 방법
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 2. 추상클래스 의존 및 생성자를 만들어두고 구현체를 외부에서 생성하는 방법
    private final MemberRepository memberRepository;

    // 테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }

    public MemberServiceImpl(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
