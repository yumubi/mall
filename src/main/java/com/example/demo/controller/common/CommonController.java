package com.example.demo.controller.common;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import com.example.demo.common.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class CommonController {


    @GetMapping("/common/kaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception{
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/png");

        ShearCaptcha shearCaptcha= CaptchaUtil.createShearCaptcha(110, 40, 4, 2);

        //验证吗存入session
        //httpServletRequest.getSession().setAttribute("verifyCode", shearCaptcha);
        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute("verifyCode", shearCaptcha);
        //输出图片流
        shearCaptcha.write(httpServletResponse.getOutputStream());

    }

}
