package cecilia.tacocloud.tacos.entity;

import lombok.Data;

/**
 * @author cecilia
 * taco 配料
 */
@Data
public class Ingredient {
    private final String id;
    private final String name;
    private final Type type;
    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
