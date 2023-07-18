package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goods;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

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
}