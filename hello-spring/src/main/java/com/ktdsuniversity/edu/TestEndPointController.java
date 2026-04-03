package com.ktdsuniversity.edu;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/** 
 * End Point 를 생성하는 역할.
 */
@Controller // 해당클래스가 End Point 를 만들 수 있도록 지원해준다.
public class TestEndPointController {
    
	// Spring Application이 시작이 될 때,
    // @Controller가 적용된 모든 클래스를 찾아
    // 해당 클래스들을 인스턴스로 생성한다.
    // 생성된 인스턴스들은 Bean Container에 저장이 된다.
	public TestEndPointController() {
		System.out.println("TestEndPointController 인스턴스 만들어짐!");
		System.out.println(this);
	}
	/*
	 * "/jsp" 엔드포인트.
	 * -hellojsp.jsp 파일을 읽어서 HTML로 변환시킨 후 결과를 반환.
	 */
	@GetMapping("/jsp")
	public String viewHelloJspPage(Model model) {
		// Model model parameter ==> Template Engine(JSP)에게 데이터를 전송시키는 객체.
		// ????
		System.out.println("데이터 추가 전 모델" + model); // ==> {}
		// JAVA에서 중괄호 표현은 단 하나이다. ==> 맵 ==> 맵의 형태를 빌려 씀 ==> 맵처럼 쓸 수 있다.
		// myname이라는 키(변수명)로 "김선우" 할당해서 템플릿에게 전달.
		model.addAttribute("myname", "김선우");
		model.addAttribute("age", "28");
		System.out.println("데이터 추가된 모델" + model); // ==> {myname=김선우, age=28}
		// 데이터는 jsp에서 ${변수명}으로 사용할 수 있다.
		// <p>반갑습니다. ${age}살, ${myname}입니다.</p>
		
		// spring.mvc.view.prefix + hellojsp + spring.mvc.view.suffix
		// ==> /WEB-INF/views/ + hellojsp + .jsp
		// == /WEB-INF/views/hellojsp.jsp
		
		// Controller에서 String 타입을 반환시켰을 경우
		// prefix + 반환 값 + suffix의 경로를
		// /src/main/webapp 아래에서 탐색한다.
		
		// 탐색한 jsp 파일을 HTML로 변환시켜 브라우저에 전달.
		return "hellojsp";
	}
	
	/*
	 * 사용자가 "/root" 엔드포인트에 접근하면
	 * "첫 페이지입니다. 환영합니다"를 브라우저에 보내주는 코드 작성.
	 */
    // "/" 엔드포인트 생성.
	@GetMapping("/root")
	public ResponseEntity<String> viewWelcomePage(){
		// 사용자에게 보여줄 HTML 반환.
		return new ResponseEntity<>("첫 페이지입니다. 환영합니다.", HttpStatus.OK);
	}
	
	// "/hello"endPoint 생성.
	@GetMapping("/hello")
	// 사용자가 "/hello" endPoint를 요청할 경우, 사용자에게 보여줄 HTML 페이지 생성.
	public ResponseEntity<String> viewHelloHtml(){
		
		// 사용자에게 보여줄 HTML 반환.
		return new ResponseEntity<>("Hello!", HttpStatus.OK);
	}
}
