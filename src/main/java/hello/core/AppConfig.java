package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // 리팩토링 전
//    public MemberService memberService(){
//        return new MemberServiceImpl(new MemoryMemberRepository());
//    }
//
//    public OrderService orderService(){
//        return new OrderServiceImpl(
//                new MemoryMemberRepository(),
//                new FixDiscountPolicy()
//        );
//    }

    // 리팩토링 후 + 스프링으로 전환하기
    @Bean
    public MemberService memberService(){
        // 1번
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService(){
        // 1번
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy()
        );
    }

    @Bean
    public MemberRepository memberRepository(){
        // 2번? 3번?
        System.out.println("call AppConfig.memberRepository");
        return  new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy(){
//        return  new FixDiscountPolicy();
        return  new RateDiscountPolicy();
    }
}
