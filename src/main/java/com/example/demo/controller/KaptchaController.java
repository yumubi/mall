package com.example.demo.controller;

import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class KaptchaController {

    @GetMapping("/kaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception{
        httpServletResponse.setHeader("Cache-Control", "no-store");;
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/gif");

        SpecCaptcha captcha = new SpecCaptcha(75, 30, 4);
        captcha.setCharType(Captcha.TYPE_ONLY_CHAR);
        captcha.setCharType(Captcha.FONT_9);

        httpServletRequest.getSession().setAttribute("verifyCode", captcha.text().toLowerCase());

        captcha.out(httpServletResponse.getOutputStream());
    }

    @GetMapping("/verify")
    @ResponseBody
    public String verify(@RequestParam("code") String code, HttpSession session) {
        if(!StringUtils.hasLength(code)) {
            System.out.println("验证码不能为空");
            return "验证码不能为空";
        }

        String kaptchaCode = session.getAttribute("verifyCode") + "";
        if(!StringUtils.hasLength(kaptchaCode) || !code.toLowerCase().equals(kaptchaCode)) {
            System.out.println("验证码错误");
            return "验证码错误";
        }
        return "验证成功";

    }




}
