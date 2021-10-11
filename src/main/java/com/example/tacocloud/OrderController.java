package com.example.tacocloud;

import com.example.tacocloud.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

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
    public String processOrder(@Valid Order order, Errors errors) {
        if(errors.hasErrors()) return "orderForm";

        log.info("Order submitted: " + order);
        return "redirect:/";
    }
}
