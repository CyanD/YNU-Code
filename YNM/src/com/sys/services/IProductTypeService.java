package com.sys.services;

import java.util.List;

import com.core.paging.Page;
import com.sys.model.ProductType;

public interface IProductTypeService {
    public ProductType findById(long id);

    public void removeById(long id);

    public List<ProductType> findByClasuse(String clause);

    public ProductType findOneByClasuse(String clause);

    public List<ProductType> findPageByClasuse(String clause, Page page);

    public int getCount(String clause);

    public void addOrUpdateProduct(ProductType obj);
}
