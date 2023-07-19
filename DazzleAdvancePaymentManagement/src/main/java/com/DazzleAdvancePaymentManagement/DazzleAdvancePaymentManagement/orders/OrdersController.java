package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.orders;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/orders")
@RequiredArgsConstructor
@Controller
public class OrdersController {

    private final OrdersService ordersService;

    @GetMapping("/list")
    @ResponseBody
    public String list(){
        return "order list";
    }
    @GetMapping("/create")
    public String ordersCreate(){
        return "orders_form";
    }

    @PostMapping("/create")
    public String ordersCreate(@RequestParam String customerName,@RequestParam String goodsName, @RequestParam String goodsCategory, @RequestParam Boolean ice, @RequestParam Integer amount){
        this.ordersService.createNewOrders(customerName,goodsName,goodsCategory,ice,amount);
        return "redirect:/goods/create";
    }
}
