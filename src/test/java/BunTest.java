import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunTest {

    private final String name;
    private final float price;
    private Bun bun;

    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Before
    public void init() {
        bun = new Bun(name, price);
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {"black bun", 100},
                {"white bun", 200},
                {"red bun", 300},
                {"", 50},
                {null, 0},
        };
    }

    @Test
    public void checkBunName() {
        Assert.assertEquals("Имя не корректное", name, bun.getName());
    }

    @Test
    public void checkBunPrice() {
        Assert.assertEquals("Цена не корректная", price, bun.getPrice(), 0.0f);
    }
}
