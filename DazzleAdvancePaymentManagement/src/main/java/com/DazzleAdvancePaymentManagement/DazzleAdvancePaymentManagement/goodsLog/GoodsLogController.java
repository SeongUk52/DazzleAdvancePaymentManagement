package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goodsLog;

import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.customer.Customer;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.orders.Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/goodsLog")
@RequiredArgsConstructor
@Controller
public class GoodsLogController {
    private final GoodsLogService goodsLogService;

    @GetMapping(value = "/{id}")
    public String goodsLogList(Model model, @PathVariable("id") Integer id) {
        List<GoodsLog> goodsLogList = this.goodsLogService.getGoodsLogList(id);
        model.addAttribute("goodsLogList",goodsLogList);
        return "goodsLog_list";
    }


}
