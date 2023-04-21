package com.example.demo.controller.admin;

import cn.hutool.captcha.ShearCaptcha;
import com.example.demo.entity.AdminUser;
import com.example.demo.service.AdminUserService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminUserService adminUserService;


    @GetMapping("/indexAll")
    public String indexAll(){
        return "admin/index-all";
    }

    @GetMapping({"/login"})
    public String login() {
        return "admin/login";
    }



    @PostMapping(value = "/login")
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        @RequestParam("verifyCode") String verifyCode,
                        HttpSession session) {
        System.out.println("mySession=" + session);
        if(!StringUtils.hasText(verifyCode)) {
            session.setAttribute("errorMsg", "验证码不能为空");
            return "admin/login";
        }
        if(!StringUtils.hasText(userName) || !StringUtils.hasText(password)) {
            session.setAttribute("errorMsg", "用户名或密码不能为空");
            System.out.println("用户名或密码不能为空");
            return "admin/login";
        }
        System.out.println("myShearCaptcha=" + session.getAttribute("verifyCode"));
        ShearCaptcha shearCaptcha = (ShearCaptcha) session.getAttribute("verifyCode");
        System.out.println("验证码为:" + shearCaptcha);
        System.out.println("我的输入为:" + verifyCode);

        if(shearCaptcha == null || !shearCaptcha.verify(verifyCode)) {
            session.setAttribute("errorMsg", "验证码错误");
            System.out.println("验证码错误");
            return "admin/login";
        }
        AdminUser adminUser = adminUserService.login(userName, password);
        if(adminUser != null) {
            session.setAttribute("loginUser", adminUser.getNickName());
            session.setAttribute("loginUserId", adminUser.getAdminUserId());
            System.out.println("登录成功");
            return "redirect:/admin/index";
        } else {
            session.setAttribute("errorMsg", "登录失败");
            System.out.println("登陆失败");
            return "admin/login";
        }

    }


}
