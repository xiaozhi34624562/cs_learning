package com.kaikeba.controller;

import com.github.pagehelper.PageInfo;
import com.kaikeba.bean.Category;
import com.kaikeba.bean.Route;
import com.kaikeba.bean.RouteImg;
import com.kaikeba.bean.Seller;
import com.kaikeba.service.CategoryService;
import com.kaikeba.service.RouteImgService;
import com.kaikeba.service.RouteService;
import com.kaikeba.service.SellerService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/route/")
public class RouteController {
    @Resource
    private RouteService routeService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private SellerService sellerService;
    @Resource
    private RouteImgService routeImgService;

    @RequestMapping("/page")
    public String page(Route route, Model model,
                       @RequestParam(defaultValue = "1") int pageNum,
                       @RequestParam(defaultValue = "10") int pageSize) {
        PageInfo<Route> page = routeService.findPage(route, pageNum, pageSize);
        model.addAttribute("page", page);

        List<Category> categories = categoryService.categoryFindAll();
        model.addAttribute("categories", categories);

        List<Seller> sellers = sellerService.sellerFindAll();
        model.addAttribute("sellers", sellers);

        return "route/list";
    }

    @RequestMapping("/delete/{rid}")
    public String delete(@PathVariable("rid") Integer rid) {
        int delete = routeService.delete(rid);
        System.out.println("旅行路线删除 + " + delete);
        return "redirect:/admin/route/page";
    }

    @RequestMapping("/toadd")
    public String toAdd(Model model) {
        List<Category> categories = categoryService.categoryFindAll();
        model.addAttribute("categories", categories);
        List<Seller> sellers = sellerService.sellerFindAll();
        model.addAttribute("sellers", sellers);
        return "route/add";
    }

    @RequestMapping("/doadd")
    public String doAdd(Route route, @RequestParam("rimageFile") MultipartFile rimageFile,
                        HttpServletRequest request) throws IOException {
        performRImage(route, rimageFile, request);
        int add = routeService.add(route);
        System.out.println("旅游线路 + " + add);
        return "redirect:/admin/route/page";
    }

    @RequestMapping("/toupdate/{rid}")
    public String toUpdate(@PathVariable("rid") Integer rid, Model model) {
        List<Category> categories = categoryService.categoryFindAll();
        model.addAttribute("categories", categories);
        List<Seller> sellers = sellerService.sellerFindAll();
        model.addAttribute("sellers", sellers);
        Route route = routeService.findById(rid);
        model.addAttribute("route", route);
        return "route/update";
    }

    @RequestMapping("/doupdate")
    public String doUpdate(Route route, @RequestParam("rimageFile") MultipartFile rimageFile,
                           HttpServletRequest request) throws IOException {
        performRImage(route, rimageFile, request);
        int update = routeService.update(route);
        System.out.println("旅游公司更新 + " + update);
        return "redirect:/admin/route/page";
    }

    private void performRImage(Route route, @RequestParam("rimageFile") MultipartFile rimageFile, HttpServletRequest request) throws IOException {
        //项目的部署目录 + img/product/rimage/
        String savePath = request.getServletContext().getRealPath("img/product/rimage/");
        //处理随机文件名
        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." +
                FilenameUtils.getExtension(rimageFile.getOriginalFilename());
        //上传目录如果不存在,先创建
        File savePathDir = new File(savePath);
        if (!savePathDir.exists()) {
            savePathDir.mkdirs();
        }
        //保存文件
        rimageFile.transferTo(new File(savePathDir, fileName));
        //设置线路的rimage属性=文件的相对路径
        route.setRimage("img/product/rimage/" + fileName);
    }

    @RequestMapping("/toimage/{id}")
    public String toImage(@PathVariable("id") Integer id, Model model) {
        Route route = routeService.findById(id);
        System.out.println(route);
        model.addAttribute("route", route);
        return "route/image";
    }

    @RequestMapping("/doimage")
    public String doImage(Integer rid,
                          @RequestParam("bigPicFile") MultipartFile[] bigPicFile,
                          @RequestParam("smallPicFile") MultipartFile[] smallPicFile,
                          HttpServletRequest request) throws Exception {
        List<String> bigPic = new ArrayList<>();
        List<String> smallPic = new ArrayList<>();

        String path = request.getServletContext().getRealPath("/");
        for (MultipartFile f : bigPicFile) {
            File bigPath = new File(path + "img/product/big-pic");
            if (!bigPath.exists()) {
                bigPath.mkdirs();
            };
            String fileName = UUID.randomUUID().toString().replace("-", "") + "."
                    + FilenameUtils.getExtension(f.getOriginalFilename());
            f.transferTo(new File(bigPath, fileName));
            bigPic.add("img/product/big-pic/" + fileName);
        }
        for (MultipartFile f : smallPicFile) {
            File smallPath = new File(path + "img/product/small-pic");
            if (!smallPath.exists()) {
                smallPath.mkdirs();
            };
            String fileName = UUID.randomUUID().toString().replace("-", "") + "."
                    + FilenameUtils.getExtension(f.getOriginalFilename());
            f.transferTo(new File(smallPath, fileName));
            smallPic.add("img/product/small-pic/" + fileName);
        }

        List<RouteImg> ris = new ArrayList<>();
        for (int i = 0; i<bigPic.size(); i++) {
            RouteImg img = new RouteImg();
            img.setRid(rid);
            img.setBigpic(bigPic.get(i));
            img.setSmallpic(smallPic.get(i));
            ris.add(img);
        }
        routeImgService.saveImg(rid, ris);
        return "redirect:/admin/route/page";
    }
}
