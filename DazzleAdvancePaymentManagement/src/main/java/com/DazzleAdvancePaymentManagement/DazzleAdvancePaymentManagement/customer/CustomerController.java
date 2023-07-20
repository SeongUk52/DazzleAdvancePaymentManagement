package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.customer;

import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goods.Goods;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.orders.Orders;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.orders.OrdersService;
import org.springframework.web.bind.annotation.PathVariable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CustomerController {

    private final CustomerService customerService;
    private final OrdersService ordersService;
    @GetMapping("/customer/list")
    //@ResponseBody
    public String list(Model model, @RequestParam(value="page", defaultValue="0") int page){
        Page<Customer> paging = this.customerService.getCustomerList(page);
        model.addAttribute("paging",paging );
        return "customer_list";
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/customer/list";
    }

    @GetMapping(value = "/customer/detail/{id}")
    public String customerDetail(Model model, @PathVariable("id") Integer id) {
        List<Customer> customerList = this.customerService.getPersonalList(id);
        List<Orders> ordersList = this.ordersService.getPersonalOrderList(id);
        model.addAttribute("customerList",customerList);
        model.addAttribute("ordersList",ordersList);
        return "customer_detail";
    }
/*
    @GetMapping(value = "/customer/detail/{id}")
    public String ordersDetail(Model model, @PathVariable("id") Integer id) {
        List<Orders> orderList = this.ordersService.getPersonalOrderList(id);
        model.addAttribute("orderList",orderList);
        return "customer_detail";
    }

 */
    @PostMapping("/customer/create/{id}")
    public String createPaymentChange(Model model,  @PathVariable("id") Integer id, @RequestParam Integer changePaymentBalance){
        List<Customer> customerList = this.customerService.getPersonalList(id);
        Customer customer = customerList.get(customerList.size()-1);
        this.customerService.create(customer,changePaymentBalance);
        return String.format("redirect:/customer/detail/%s", id);
    }

    @GetMapping("/customer/create")
    public String customerCreate(CustomerForm customerForm){
        return "customer_form";
    }

    @PostMapping("/customer/create")
    public String customerCreate(@Valid CustomerForm customerForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "customer_form";
        }
        this.customerService.createNewCustomer(customerForm.getCustomerId(),customerForm.getCustomerName(),customerForm.getCustomerJob(),customerForm.getCustomerPaymentBalance());
        return "redirect:/customer/list";//customerId,customerName,customerJob,customerPaymentBalance
    }

    @GetMapping("/customer/delete/{id}")
    public String customerDelete(@PathVariable("id") Integer id) {
        List<Customer> customerList = this.customerService.getPersonalList(id);
        for(int i=0; i < customerList.toArray().length;i++){
            this.customerService.delete(customerList.get(i));
        }
        return "redirect:/";
    }
}
