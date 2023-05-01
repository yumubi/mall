package com.example.demo.service;

import com.example.demo.entity.Carousel;
import com.example.demo.util.PageQueryUtil;
import com.example.demo.util.PageResult;

public interface NewBeeMallCarouselService {
    PageResult getCarouselPage(PageQueryUtil pageUtil);

    String saveCarousel(Carousel carousel);

    String updateCarousel(Carousel carousel);

    Carousel getCarouselById(Integer id);

    Boolean deleteBatch(Integer[] ids);


}
