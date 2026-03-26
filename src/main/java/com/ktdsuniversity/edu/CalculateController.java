package com.ktdsuniversity.edu;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculateController {

	/**
	 * <pre>
	 * endPoint = /calc
	 * 반환시키는 템플릿 이름 = calc.jsp
	 * 템플릿으로 반환시키는 데이터
	 *   - 1. firstNum
	 *   - 2. secondNum
	 *   - 3. result : firstNum + secondNum 의 결과
	 * 템플릿의 결과
	 *   - <div>
	 *       <span> firstNum의 값
	 *       <span> + secondNum의 값
	 *       <span> = result의 값
	 * </pre>
	 */
	@GetMapping("/calc")
	public String viewCalcPage(Model model) {
		int firstNum = 3;
		int secondNum = 5;
		
		model.addAttribute("firstNum", firstNum);
		model.addAttribute("secondNum", secondNum);
		model.addAttribute("result", firstNum + secondNum);
		
		return "calc";
	}
	
	/**
	 * <pre>
	 * Brouwser에서 Endpoint로 파라미터를 보내는 3가지 방법.
	 *   1. Query String Parameter => endpoint 뒤에 "?"를 이용해 보내는 방법
	 *      ex) /endpoint?key=value&key2=value2&...
	 *      ex) /calc2?firstNum=3&secondNum=10
	 *   2. Form Parameter => <form></form>태그를 이용해서 보내는 방법.
	 *      ex) <form action="/endpoint">
	 *            <input name="key" value="value"/>
	 *            <select name="key2">
	 *              <option value="value2">Text</option>
	 *              <option value="value3">Other Text</option>
	 *              ==> 선택한게 Text다 = key2에 valeu2전달
	 *            </select>
	 *            <textarea name="key3">Value</textarea>
	 *            ==> textarea 안에 작성한 내용(Value)이 key3으로 전달.
	 *          </form>
	 *   3. Requset Body => Http Request Body 영역에 파라미터를 보내는 방법
	 *      ex) 파일 업로드, AJAX요청
	 *   4. Hybrid(Alternative, Complex => 여러가지를 복합적으로 섞어 사용하는 것.
	 *      => Query String Parameter + Form Parameter
	 *      => Query String Parameter + Request Body
	 *   *  Spring 전용 Parameter
	 *      => Path Variable
	 *         => Query String Parameter 외의 파라미터를 URL로 보내는 방법.
	 *         
	 *   Spring Endpoint 에서 파라미터를 받아오는 4가지 방법.
	 *      1. HttpServletRequest 객체를 이용하는 방법( 스프링에서 잘 사용되지않음.)
	 *         => Query String Parameter, Form Parameter, Request Body
	 *         => HTTP Header 정보 취득 가능. 요청자(브라우저)의 IP 취득 가능.
	 *            => Endpoiont를 호출한 위치(Referrer) 취득 가능.
	 *      2. @RequestParam 을 이용하는 방법( 종종 사용됨 ==> 파라미터의 개수가 적을 때(2개이하))
	 *         => Query String Parameter, Form Parameter
	 *      3. @ModelAttribute 를 이용하는 방법( 가장 많이 사용됨 - 파라미터의 개수가 많을 때)
	 *         => Commend Object, @ModelAttribute Annotation은 생략 가능.
	 *         => Query String Parameter, Form Parameter
	 *      4. @RequestBody 를 이용하는 방법( API-AJAX 기반의 프로젝트에서 주로 사용됨.)
	 *         Request Body 취득.
	 *    번외. @PathVariable 을 이용하는 방법 (가장 많이 사용됨)
	 *         => Path(URL), Variable 취득)
	 *</pre>
	 * @return
	 */
	
	//calc2?f=1&s=3
	@GetMapping("/calc2")
	public String viewParamClacPage
	(@RequestParam(required = false, defaultValue = "0") int f, 
			@RequestParam(required = false, defaultValue = "0") int s, 
			Model model)
	// required는 기본적으로 true로 설정되어있다. ==> 필수 값
	// 이걸 false로 바꿔주면 필수 값이 아니게 되어 값을 넣지 않아도 된다.
	// 하지만 오류 발생 ==> null값이 들어가게되므로 int에 null값이 들어가서 오류 발생.
	// 값이 없을때의 값을 넣어준다.(defaultValue), defaultValue는 String 값을 받음. ==> String으로 넣어줌.
	{
		int result = f + s;
		model.addAttribute("firstNum", f);
		model.addAttribute("secondNum", s);
		model.addAttribute("result", result);
		
		return "calc";
	}
}
