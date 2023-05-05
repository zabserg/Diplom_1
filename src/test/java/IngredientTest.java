import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(Parameterized.class)
public class IngredientTest {

    private final IngredientType type;
    private final String name;
    private final float price;
    private Ingredient ingredient;

    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Before
    public void init() {
        ingredient = new Ingredient(type, name, price);
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {SAUCE, "hot sauce", 100},
                {SAUCE, "sour cream", 200},
                {SAUCE, "chili sauce", 300},
                {FILLING, "cutlet", 100},
                {FILLING, "dinosaur", 200},
                {FILLING, "sausage", 300},
                {null, "", 0},
        };
    }

    @Test
    public void checkIngredientType() {
        Assert.assertEquals("Тип не корректный", type, ingredient.getType());
    }

    @Test
    public void checkIngredientName() {
        Assert.assertEquals("Имя не корректное", name, ingredient.getName());
    }

    @Test
    public void checkIngredientPrice() {
        Assert.assertEquals("Цена не корректная", price, ingredient.getPrice(), 0.0f);
    }
}
