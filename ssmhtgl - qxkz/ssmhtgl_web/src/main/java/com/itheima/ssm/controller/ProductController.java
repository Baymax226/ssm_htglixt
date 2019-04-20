package com.itheima.ssm.controller;


import com.itheima.ssm.domian.Product;
import com.itheima.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    /**
     * 产品添加
     * @param product
     */
    @RequestMapping("/save.do")
    public String save(Product product) throws Exception {
        productService.save(product);
        return "redirect:findAll.do";
    }

    /**
     * 查询全部产品
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    @Secured("ROLE_ADMIN")
    public ModelAndView findAll() throws Exception {
        ModelAndView mov = new ModelAndView();
        List<Product> products = productService.findAll();
        mov.addObject("productList",products);
        mov.setViewName("product-list");
        return mov;
    }

    //根据id查询产品信息并保存到编辑产品页面
    @RequestMapping("/edit.do")
    public ModelAndView edit(String id) throws Exception {    //根据product.id查询product
        ModelAndView mv = new ModelAndView();
        Product product = findById(id);
        mv.addObject("product",product);
        mv.setViewName("product-edit");
        return mv;
    }

    //根据id查询产品
    @RequestMapping("/findById.do")
    private Product findById(String id) throws Exception {
        return productService.findById(id);
    }

    //编辑产品信息并上传数据库
    @RequestMapping("/update.do")
    public String update(Product product) throws Exception {
        productService.update(product);
        return "redirect:findAll.do";
    }
}
