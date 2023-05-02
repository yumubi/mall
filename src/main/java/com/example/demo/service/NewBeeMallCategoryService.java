package com.example.demo.service;

import com.example.demo.entity.GoodsCategory;
import com.example.demo.util.PageQueryUtil;
import com.example.demo.util.PageResult;

import java.util.List;

public interface NewBeeMallCategoryService {
    PageResult getCategoriesPage(PageQueryUtil pageUtil);

    String saveCategory(GoodsCategory goodsCategory);

    String updateGoodsCategory(GoodsCategory goodsCategory);

    GoodsCategory getGoodsCategoryById(Long id);

    Boolean deleteBatch(Integer[] ids);

    List<GoodsCategory> selectByLevelAndParentIdsAndNumber(List<Long> parentsIds, int categoryLevel);
}
