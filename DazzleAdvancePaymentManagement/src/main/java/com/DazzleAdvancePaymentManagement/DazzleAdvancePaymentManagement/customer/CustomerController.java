package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.customer;

import org.springframework.web.bind.annotation.PathVariable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CustomerController {

    private final CustomerService customerService;
    @GetMapping("/customer/list")
    //@ResponseBody
    public String list(Model model){
        List<Customer> customerList = this.customerService.getCustomerList();
        model.addAttribute("customerList",customerList);
        return "customer_list";
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/customer/list";
    }

    @GetMapping(value = "/customer/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        List<Customer> customerList = this.customerService.getPersonalList(id);
        model.addAttribute("customerList",customerList);
        return "customer_detail";
    }

    @PostMapping("/customer/create/{id}")
    public String createPaymentChange(Model model,  @PathVariable("id") Integer id, @RequestParam Integer changePaymentBalance){
        Customer customer = this.customerService.getCustomer(id);
        this.customerService.create(customer,changePaymentBalance);
        return String.format("redirect:/customer/detail/%s", id);
    }
}
