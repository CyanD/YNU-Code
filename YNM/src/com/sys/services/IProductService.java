package com.sys.services;

import java.util.List;

import com.core.paging.Page;
import com.sys.model.Product;
import com.sys.model.Viewproduct;

public interface IProductService {
    public Product findById(long id);

    public void removeById(long id);

    public List<Product> findByClasuse(String clause);

    public Product findOneByClasuse(String clause);

    public List<Product> findPageByClasuse(String clause, Page page);

    public int getCount(String clause);

    public void addOrUpdateProduct(Product obj);
    public List<Viewproduct> findViewproductByClasuse(String clause);
}
