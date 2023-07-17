package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.orders;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrdersController {

    @GetMapping("/orders/list")
    @ResponseBody
    public String list(){
        return "order list";
    }

}
