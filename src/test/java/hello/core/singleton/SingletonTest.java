package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.MemberService;

public class SingletonTest {
	@Test
	@DisplayName("스프링 없는 순수한 DI 컨테이너")
	void pureContainer() {
		AppConfig appConfig = new AppConfig();
		
		// 조회: 호출할 때 마다 객체 생성
		MemberService memberService1 = appConfig.memberService();
		
		// 조회: 호출할 때 마다 객체 생성
		MemberService memberService2 = appConfig.memberService();
		
		// 참조값이 다른지 확인
		System.out.println("memberService1 = " + memberService1);
		System.out.println("memberService2 = " + memberService2);
		
		// memberService1 ~= memberService2
		Assertions.assertThat(memberService1).isNotSameAs(memberService2);
	}
	
	@Test
	@DisplayName("싱글톤 패턴을 적용한 객체 사용")
	void sigletonServiceTest() {
		SingletonService ss1 = SingletonService.getInstance();
		SingletonService ss2 = SingletonService.getInstance();

		System.out.println("memberService1 = " + ss1);
		System.out.println("memberService2 = " + ss2);

		Assertions.assertThat(ss1).isSameAs(ss2);
	}
	
	@Test
	@DisplayName("스프링 컨테이너와 싱글톤")
	void springContainer() {

		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		
		MemberService memberService1 = ac.getBean("memberService", MemberService.class);
		MemberService memberService2 = ac.getBean("memberService", MemberService.class);
		
		// 참조값 확인
		System.out.println("memberService1 = " + memberService1);
		System.out.println("memberService2 = " + memberService2);
		
		Assertions.assertThat(memberService1).isSameAs(memberService2);
		
	}
	
}
