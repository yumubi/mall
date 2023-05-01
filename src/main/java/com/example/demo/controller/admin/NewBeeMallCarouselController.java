package com.example.demo.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class NewBeeMallCarouselController {
    @GetMapping("/carousels")
    public String carouselPage(HttpServletRequest request) {
        //在返回视图前设置path字段为newbee_mall_carousel
        // 然后跳转到admin目录下的newbee_mall_carousel.html模板页面中。
        request.setAttribute("path", "newbee_mall_carousel");
        return "admin/newbee_mall_carousel";
    }
}
