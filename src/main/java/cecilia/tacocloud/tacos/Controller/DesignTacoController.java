package cecilia.tacocloud.tacos.Controller;


import cecilia.tacocloud.tacos.entity.Ingredient;
import cecilia.tacocloud.tacos.entity.Ingredient.Type;
import cecilia.tacocloud.tacos.entity.Taco;
import cecilia.tacocloud.tacos.entity.TacoOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cecilia
 * 設置自己的taco
 */
@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {
    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "Luttuce", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        );
        Type[] types = Ingredient.Type.values();
        for (Type type: types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "tacoOrder") // 将 tacoOrder 对象添加到 Model 中，属性名为 "tacoOrder"，可以在thymeleaf模版中使用${tacoOrder}訪問
    public TacoOrder tacoOrder() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco") // 将 taco 对象添加到 Model 中，属性名为 "taco"
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    } // 返回視圖名

    @PostMapping
    // 使用 Model 中的 TacoOrder對象
    public String processTaco(Taco taco, @ModelAttribute TacoOrder tacoOrder) {
        tacoOrder.addTaco(taco);
        log.info("Processing Taco: {}", taco);
        return "redirect:/orders/current";   // redirect 重定向視圖
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
