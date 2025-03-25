package cecilia.tacocloud.tacos.entity;

import lombok.Data;

import java.util.List;

/**
 * @author cecilia
 * Taco
 */
@Data
public class Taco {
    private String name;
    private List<Ingredient> ingredients;
}
