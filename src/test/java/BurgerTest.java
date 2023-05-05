import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    Bun bun;
    @Mock
    Ingredient sauce;
    @Mock
    Ingredient filling;

    Burger burger;

    @Before
    public void init() {
        burger = new Burger();
    }

    @Test
    public  void checkAddIngredient() {
        burger.addIngredient(sauce);
        int ingredientCount = burger.ingredients.size();
        boolean actual = ingredientCount == 1;

        assertTrue("Ингридиенты отсутствуют", actual);
    }

    @Test
    public void chechRemoveIngredient() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.removeIngredient(0);
        int ingredientCount = burger.ingredients.size();
        boolean actual = ingredientCount == 1;

        assertTrue("Количество ингридиентов не совпадает", actual);
    }

    @Test
    public void moveIngredientTest() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.moveIngredient(0, 1);
        Ingredient actual = burger.ingredients.get(1);

        assertEquals("Ингредиент не перемещен",sauce, actual);
    }

    @Test
    public void getPriceTest() {
        Mockito.when(bun.getPrice()).thenReturn(120.0f);
        Mockito.when(sauce.getPrice()).thenReturn(100.0f);
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        float actual = burger.getPrice();
        float expected = 340.0f;

        assertEquals("Цена не совпала с ожидаемой", expected, actual, 0.01f);
    }

    @Test
    public void getReceiptTest() {
        Mockito.when(bun.getName()).thenReturn("black bun");
        Mockito.when(bun.getPrice()).thenReturn(100.0f);
        burger.setBuns(bun);

        Mockito.when(filling.getType()).thenReturn(FILLING);
        Mockito.when(filling.getName()).thenReturn("sausage");
        Mockito.when(filling.getPrice()).thenReturn(300.0f);
        burger.addIngredient(filling);

        StringBuilder expected = new StringBuilder();
        expected.append(String.format("(==== %s ====)%n", bun.getName()));
        expected.append(String.format("= %s %s =%n", burger.ingredients.get(0).getType().toString().toLowerCase(),
            burger.ingredients.get(0).getName()));

        expected.append(String.format("(==== %s ====)%n", "black bun"));
        expected.append(String.format("%nPrice: %f%n", burger.getPrice()));
        String expectedReceipt = expected.toString();
        String actual = burger.getReceipt();

        assertEquals(expectedReceipt, actual);
    }
}
