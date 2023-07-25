package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goodsLog;

import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.customer.Customer;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goods.Goods;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goods.GoodsService;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.orders.Orders;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@RequestMapping("/goodsLog")
@RequiredArgsConstructor
@Controller
public class GoodsLogController {
    private final GoodsLogService goodsLogService;
    private final GoodsService goodsService;

    @GetMapping(value = "/{id}")
    public String goodsLogList(Model model, @PathVariable("id") Integer id) {
        List<GoodsLog> goodsLogList = this.goodsLogService.getGoodsLogList(id);
        model.addAttribute("goodsLogList",goodsLogList);
        return "goodsLog_list";
    }

    //쿠키,병음료,베이커리 판매량 출력
    @GetMapping("/excel/CBB")
    public void excelDownload(HttpServletResponse response, HttpServletRequest req) throws IOException {
        //List<GoodsLog> goodsLogList = this.goodsLogService.getGoodsLogCBB();
        List<Goods> goodsList = this.goodsService.getGoodsCBB();
        this.goodsLogService.excelDownloadCBB(response,req,goodsList);
    }


}
