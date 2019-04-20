package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.IProductDao;
import com.itheima.ssm.domian.Product;
import com.itheima.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional      //必须写注解来管理事务
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Override
    public void save(Product product) throws Exception {
        productDao.save(product);
    }

    @Override
    public Product findById(String id) throws Exception {
        return productDao.findById(id);
    }

    @Override
    public void update(Product product) throws Exception {
        productDao.update(product);
    }

    @Override
    public List<Product> findAll() throws Exception {
        return productDao.findAll();
    }
}
