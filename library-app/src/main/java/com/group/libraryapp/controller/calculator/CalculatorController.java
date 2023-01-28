package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.request.CalculatorAddRequest;
import com.group.libraryapp.dto.request.CalculatorMultiplyRequest;
import org.springframework.web.bind.annotation.*;

@RestController // api 의 진입 지점
public class CalculatorController {

    /** API 명세
     * HTTP Method = GET
     *  HTTP Path = /add
     *  Query = int number1, int number2
     *  API의 반환결과 = 숫자 - 두 숫자의 덧셈결과
     *
     */

    // query = int number1, int number2 @RequestParam 을 붙여줘야한다.
    // 주어지는 쿼리를 함수 매개변수에 넣겠다.
    // 하지만 받는 쿼리값들이 많아지게되면 하나하나 다 적어줘야하지만 되게 길어지기때문에 객체를 받아온다.

    @GetMapping("/add") // method = GET, path = /add
    public int addTwoNumbers(CalculatorAddRequest request) {
        return request.getNumber1() + request.getNumber2();
    }

    /** API 명세
     * HTTP Method = POST
     *  HTTP Path = /multiply
     *  request body = int number1, int number2 json 형식
     *  API 의 반환결과 = 숫자 - 두 숫자의 곱셈결과
     *
     */

    // post api 에서는 매개변수 앞에 @RequestBody 를 붙여줘야 제대로 작동한다.

    @PostMapping("/multiply") // method = POST, path = /multiply
    public int multiplyTwoNumbers(@RequestBody CalculatorMultiplyRequest request) {
        return request.getNumber1() * request.getNumber2();
    }
}
