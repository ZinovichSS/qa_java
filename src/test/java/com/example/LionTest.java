package com.example;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class LionTest {


    @Spy
    Feline feline = new Feline();
//    Feline feline;

    @Test
    public void doesHaveManeReturnTrue() throws Exception {
        Lion lion = new Lion(new Feline(), "Самец");
        boolean actualResult = lion.doesHaveMane();
        assertTrue(actualResult);
    }

    @Test
    public void doesHaveManeReturnFalse() throws Exception {
        Lion lion = new Lion(feline, "Самка");
        boolean actualResult = lion.doesHaveMane();
        assertFalse(actualResult);
    }

    @Test
    public void unknownSexReturnException() {
        try {
            Lion lion = new Lion(feline, "sex");
            assertTrue("Значение должно быть true", lion.doesHaveMane());
        } catch (Exception exception) {
            String expectedException = "Используйте допустимые значения пола животного - самец или самка";
            String actualException = exception.getMessage();
            assertEquals(expectedException, actualException);
        }
    }

    @Test
    public void getKittensReturnOne() throws Exception {
        Lion lion = new Lion(feline, "Самец");
        int expectedKittens = 1;
        int actualKittens = lion.getKittens();
        Mockito.verify(feline, Mockito.times(1)).getKittens(expectedKittens);
        assertEquals(expectedKittens, actualKittens);
    }

    @Test
    public void getFoodReturnCorrectFoodLList() throws Exception {
        Lion lion = new Lion(feline, "Самец");
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        Mockito.when(feline.getFood("Хищник")).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        List<String> actualFood = lion.getFood();
        Mockito.verify(feline, Mockito.times(1)).getFood("Хищник");

        assertEquals(expectedFood, actualFood);
    }

}
