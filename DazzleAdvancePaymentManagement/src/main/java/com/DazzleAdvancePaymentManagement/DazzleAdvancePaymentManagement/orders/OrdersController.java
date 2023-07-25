package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.orders;

import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.customer.Customer;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goods.Goods;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goods.GoodsService;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goodsLog.GoodsLogService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/orders")
@RequiredArgsConstructor
@Controller
public class OrdersController {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private final OrdersService ordersService;
    private final GoodsService goodsService;

    @GetMapping("/list")
    @ResponseBody
    public String list(){
        return "order list";
    }


    @GetMapping("/create")
    public String ordersCreate(Model model){
        List<Goods> COFFEE = this.goodsService.getGoodsByCategory("COFFEE");
        List<Goods> BUBBLE = this.goodsService.getGoodsByCategory("BUBBLE");
        List<Goods> EXTRA_ORDERS = this.goodsService.getGoodsByCategory("EXTRA_ORDERS");
        List<Goods> SIDE_COOKIE = this.goodsService.getGoodsByCategory("SIDE_COOKIE");
        List<Goods> SIDE_BOTTLE = this.goodsService.getGoodsByCategory("SIDE_BOTTLE");
        List<Goods> LATTE = this.goodsService.getGoodsByCategory("LATTE");
        List<Goods> TEA = this.goodsService.getGoodsByCategory("TEA");
        List<Goods> FRAPPUCCINO = this.goodsService.getGoodsByCategory("FRAPPUCCINO");
        List<Goods> SMOOTHIE = this.goodsService.getGoodsByCategory("SMOOTHIE");
        List<Goods> JUICE = this.goodsService.getGoodsByCategory("JUICE");
        List<Goods> ICED_TEA = this.goodsService.getGoodsByCategory("ICED_TEA");
        List<Goods> ADE = this.goodsService.getGoodsByCategory("ADE");
        model.addAttribute("COFFEE",COFFEE);//1
        model.addAttribute("BUBBLE",BUBBLE);//2
        model.addAttribute("EXTRA_ORDERS",EXTRA_ORDERS);
        model.addAttribute("SIDE_COOKIE",SIDE_COOKIE);
        model.addAttribute("SIDE_BOTTLE",SIDE_BOTTLE);
        model.addAttribute("LATTE",LATTE);//1
        model.addAttribute("TEA",TEA);//1
        model.addAttribute("FRAPPUCCINO",FRAPPUCCINO);//2
        model.addAttribute("SMOOTHIE",SMOOTHIE);//2
        model.addAttribute("JUICE",JUICE);//2
        model.addAttribute("ICED_TEA",ICED_TEA);
        model.addAttribute("ADE",ADE);//1층
        return "orders_form";
    }

    @PostMapping("/create")
    public String ordersCreate(@RequestParam String customerName,@RequestParam String customerJob, @RequestParam(value="goodsIds", required=true) List<Integer> goodsIds, @RequestParam(value="amount", required=true) List<Integer> amounts){
        if(!amounts.isEmpty()){
            for(int i = 0 ; i<amounts.size();i++){
                if(amounts.get(i)<1){continue;}
                this.ordersService.createNewOrders(customerName, customerJob,goodsIds.get(i),amounts.get(i));
            }
        }
        return "redirect:/orders/create";
    }
}
