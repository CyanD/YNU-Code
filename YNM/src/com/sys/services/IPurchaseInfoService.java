package com.sys.services;

import java.util.List;

import com.core.paging.Page;
import com.sys.model.PurchaseInfo;
import com.sys.model.Viewpurchase;

public interface IPurchaseInfoService {
      public PurchaseInfo findById(long id);
      
      public void removeById(long id);
      
      public List<PurchaseInfo> findByClasuse(String clause);
      
      public PurchaseInfo findOneByClasuse(String clause);
      
      public List<Viewpurchase> findPageByClasuse(String clause, Page page);
      
      public int getCount(String clause);
      
      public void addOrUpdateInfo(PurchaseInfo obj);
      public List<Viewpurchase> findViewpurchaseByClasuse(String clause);
}
