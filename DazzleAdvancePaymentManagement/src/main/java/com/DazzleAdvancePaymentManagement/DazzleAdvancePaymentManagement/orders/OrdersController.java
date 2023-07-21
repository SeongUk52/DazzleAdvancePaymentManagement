package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.orders;

import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.customer.Customer;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goodsLog.GoodsLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String ordersCreate(@RequestParam String customerName,@RequestParam String customerJob,@RequestParam String goodsName, @RequestParam String goodsCategory, @RequestParam Boolean ice, @RequestParam Integer amount){
        this.ordersService.createNewOrders(customerName,customerJob,goodsName,goodsCategory,ice,amount);
        return "redirect:/orders/create";
    }
}
