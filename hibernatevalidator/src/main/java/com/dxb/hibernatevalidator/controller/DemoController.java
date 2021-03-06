package com.dxb.hibernatevalidator.controller;

import com.dxb.hibernatevalidator.model.DemoModel;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

@Controller
@Validated
public class DemoController {

    //    使用这种方式，当Bean（本例的TestController）初始化时，Spring并没有注入一个request对象，而是注入了一个代理（proxy）；当Bean中需要使用request对象时，通过该代理获取request对象。最后会发现有个ThreadLocal
// AutowireUtils.ObjectFactoryDelegatingInvocationHandler
// WebApplicationContextUtils.RequestObjectFactory
    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/thread_safe")
    @ResponseBody
    public String request() {
        System.out.println(request);
        return "ok";
    }

    @InitBinder
//    @InitBinder("demo")
    public void initBinder(WebDataBinder binder, HttpServletRequest request) {
        binder.setFieldDefaultPrefix("demo.");
        binder.registerCustomEditor(Date.class, new DateEditor());
    }


    @RequestMapping("/demo")
    @ResponseBody
    public Object demo(
//            @ModelAttribute("demo")
            @Valid
                    DemoModel demo, BindingResult result,
            //
            @Range(min = 1, max = 9, message = "年级只能从1-9")
            @RequestParam(name = "grade", required = true)
                    int grade,
            //
            @Min(value = 1, message = "班级最小只能1")
            @Max(value = 99, message = "班级最大只能99")
            @RequestParam(name = "classroom", required = true)
                    int classroom) {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }
        }
        System.out.println(grade);
        System.out.println(classroom);
        return demo;
    }
}
