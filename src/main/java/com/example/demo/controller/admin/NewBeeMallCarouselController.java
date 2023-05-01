package com.example.demo.controller.admin;

import com.example.demo.common.ServiceResultEnum;
import com.example.demo.entity.Carousel;
import com.example.demo.service.NewBeeMallCarouselService;
import com.example.demo.util.PageQueryUtil;
import com.example.demo.util.Result;
import com.example.demo.util.ResultGenerator;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class NewBeeMallCarouselController {

    @Resource
    NewBeeMallCarouselService newBeeMallCarouselService;


    @GetMapping("/carousels")
    public String carouselPage(HttpServletRequest request) {
        //在返回视图前设置path字段为newbee_mall_carousel
        // 然后跳转到admin目录下的newbee_mall_carousel.html模板页面中。
        request.setAttribute("path", "newbee_mall_carousel");
        return "admin/newbee_mall_carousel";
    }


    @RequestMapping(value = "/carousels/list", method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if(StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(newBeeMallCarouselService.getCarouselPage(pageUtil));
    }

    @PostMapping("/carousels/save")
    @ResponseBody
    public Result save(@RequestBody Carousel carousel) {
        if(StringUtils.isEmpty(carousel.getCarouselUrl()) || Objects.isNull(carousel.getCarouselRank())) {
            return ResultGenerator.genFailResult("参数异常!");
        }
        String result = newBeeMallCarouselService.saveCarousel(carousel);
        if(ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }



    @PostMapping("/carousel/update")
    @ResponseBody
    public Result update(@RequestBody Carousel carousel) {
        if(Objects.isNull(carousel.getCarouselId()) || StringUtils.isEmpty(carousel.getCarouselUrl())
        || Objects.isNull(carousel.getCarouselRank())) {
            return ResultGenerator.genFailResult("参数异常!");
        }
        String result = newBeeMallCarouselService.updateCarousel(carousel);
        if(ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }


    @GetMapping("/carousels/info/{id}")
    @ResponseBody
    public Result info(@PathVariable("id") Integer id) {
        Carousel carousel = newBeeMallCarouselService.getCarouselById(id);
        if(carousel == null) {
            return ResultGenerator.genFailResult(ServiceResultEnum.DATA_NOT_EXIST.getResult());
        }
        return ResultGenerator.genSuccessResult(carousel);
    }


    @PostMapping("/carousels/delete")
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids) {
        if(ids.length < 1) {
            return ResultGenerator.genFailResult("参数异常!");
        }
        if(newBeeMallCarouselService.deleteBatch(ids)) {
            return ResultGenerator.genSuccessResult();
        }
        else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }


}
