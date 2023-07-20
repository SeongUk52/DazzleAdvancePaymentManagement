package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goods;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@RequestMapping("/goods")
@RequiredArgsConstructor
@Controller
public class GoodsController {

    private final GoodsService goodsService;

    @GetMapping("/list")
    public String list(Model model,@RequestParam(value="page", defaultValue="0") int page){
        Page<Goods> paging = this.goodsService.getList(page);
        model.addAttribute("paging", paging);
        return "goods_list";
    }

    @GetMapping("/create")
    public String goodsCreate(GoodsForm goodsForm){
        return "goods_form";
    }

    @PostMapping("/create")
    public String goodsCreate(@Valid GoodsForm goodsForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "goods_form";
        }
        this.goodsService.createNewGoods(goodsForm.getGoodsName(),goodsForm.getGoodsCategory(),goodsForm.getGoodsIce(),goodsForm.getGoodsAmount(),goodsForm.getGoodsPrice());
        //goodsName,goodsCategory,goodsIce,goodsAmount,goodsPrice
        return "redirect:/goods/list";
    }

    @GetMapping("/modify/{id}")
    public String goodsModify(GoodsForm goodsForm,@PathVariable("id") Integer id){
        Goods goods = this.goodsService.getGoods(id);
        goodsForm.setGoodsName(goods.getGoodsName());
        goodsForm.setGoodsCategory(goods.getGoodsCategory());
        goodsForm.setGoodsIce(goods.getGoodsIce());
        goodsForm.setGoodsAmount(goods.getGoodsAmount());
        goodsForm.setGoodsPrice(goods.getGoodsPrice());

        return "goods_form";
    }

    @PostMapping("/modify/{id}")
    public String goodsModify(@Valid GoodsForm goodsForm, BindingResult bindingResult,@PathVariable("id") Integer id){
        if (bindingResult.hasErrors()) {
            return "goods_form";
        }
        Goods goods = this.goodsService.getGoods(id);
        this.goodsService.modify(goods,goodsForm.getGoodsName(),goodsForm.getGoodsCategory(),goodsForm.getGoodsIce(),goodsForm.getGoodsAmount(),goodsForm.getGoodsPrice());
        return "redirect:/goods/list";
    }

    @GetMapping("/delete/{id}")
    public String goodsDelete(@PathVariable("id") Integer id) {
        Goods goods = this.goodsService.getGoods(id);
        this.goodsService.delete(goods);
        return "redirect:/goods/list";
    }

}
