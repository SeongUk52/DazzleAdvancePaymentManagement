package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CustomerController {

    private final CustomerRepository customerRepository;
    @GetMapping("/customer/list")
    //@ResponseBody
    public String list(Model model){
        List<Customer> customerList = this.customerRepository.findAll();
        model.addAttribute("customerList",customerList);
        return "customer_list";
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/customer/list";
    }
}
