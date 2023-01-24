package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    // DIP 원칙 위반 , 클라이언트가 추상클래스에만 의존해야 하는데 추상클래스뿐만 아니라 구현체에도 동시에 의존하고 있다.
    // OCP 원칙 위반 , 구현체를 바꿔줘야 할 때 클라이언트 (OrderServiceImpl) 의 코드를 바꿔줘야만 하는 문제점
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // 추상클래스(인터페이스)에만 의존하게 설계하면?? -> 구현체가 존재하지 않으므로 코드 실행 불가능 -> NPE(null pointer exception)
//    private DiscountPolicy discountPolicy;

    // 따라서 추상클래스 의존 및 생성자를 만들어두고 구현체를 외부에서 생성하는 방법으로 의존성 주입!!!!

    private final MemberRepository memberRepository;

    // 테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }

    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy){
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
