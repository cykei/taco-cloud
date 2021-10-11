package com.example.tacocloud.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {
    @GetMapping("/current")
    public String orderForm(Model model) {
        model.addAttribute("order", new Order());
        return "orderForm";
    }

    // orderForm.html의 form태그 반환값이 여기로 들어온다.
    @PostMapping
    public String processOrder(Order order) {
        log.info("Order submitted: " + order);
        return "redirect:/";
    }
}
