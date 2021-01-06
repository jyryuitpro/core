package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        Assertions.assertThat(order.getDicountPrice()).isEqualTo(1000);

    }

    // 필드 주입
    // 외부에서 변경이 불가능해서 테스트 하기 힘들다는 치명적인 단점이 있다.
    // DI 프레임워크가 없으면 아무것도 할 수 없다.
    // 사용하지 말자!
    // 애플리케이션의 실제 코드와 관계 없는 테스트 코드에서는 사용 가능
    // 스프링 설정을 목적으로 하는 @Configuration 같은 곳에서만 특별한 용도로 사용 가능
//    @Test
//    void fieldIngectionTest() {
//        OrderServiceImpl orderService = new OrderServiceImpl();
//        // java.lang.NullPointerException
//        orderService.createOrder(1L, "itemA", 10000);
//    }
}
