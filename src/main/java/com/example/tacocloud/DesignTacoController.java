package com.example.tacocloud;

import com.example.tacocloud.domain.Ingredient;
import com.example.tacocloud.domain.Ingredient.Type;
import com.example.tacocloud.domain.Taco;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j // lombok 어노테이션. Logger 생성.
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @PostMapping
    public String processDesign(Taco design) {
        // 이 지점에서 타코 디자인 (선택된 식자재 내역)을 저장한다.
        // 이 작업은 3장에서 진행됨
        log.info("Processing desing: "+ design);
        return "redirect:/orders/current";
    }
    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
                );
        Type[] types = Ingredient.Type.values();
        for(Type type: types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }

        model.addAttribute("taco", new Taco());
        return "design";
    }
    private List<Ingredient> filterByType( List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
