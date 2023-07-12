package com.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FelineParamTest {
    private final int numberOfKittens;

    public FelineParamTest(int numberOfKittens) {
        this.numberOfKittens = numberOfKittens;
    }
    @Parameterized.Parameters(name = "Количество котят: {0}")
    public static Object[][] getOrderFormData() {
        return new Object[][]{
                {1},
                {10},
                {100},
                {0}
        };
    }
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    @Spy
    Feline feline = new Feline();

    @Test
    public void eatMeatReturnCorrectFood() throws Exception {
        Feline feline = new Feline();
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        List<String> actualFood = feline.eatMeat();
        assertEquals(expectedFood, actualFood);
    }

    @Test
    public void getFamilyReturnFeline() {
        Feline feline = new Feline();
        String expectedFamily = "Кошачьи";
        String actualFamily = feline.getFamily();
        assertEquals(expectedFamily, actualFamily);
    }

    @Test
    public void getKittensWithOutParametersReturnOne() {
        int expectedKittens = 1;
        int actualKittens = feline.getKittens();
        Mockito.verify(feline, Mockito.times(1)).getKittens(expectedKittens);
        assertEquals(expectedKittens, actualKittens);
    }

    @Test
    public void getKittensReturnCorrectValue() {
        int expectedKittens = 10;
        Feline feline = new Feline();
        int actualKittens = feline.getKittens(10);
        Assert.assertEquals(expectedKittens, actualKittens);
    }

    @Test
    public void getKittensWithParametersReturnCorrectValue() {
        Feline feline = new Feline();
        int expectedKittens = numberOfKittens;
        int actualKittens = feline.getKittens(numberOfKittens);
        assertEquals(expectedKittens, actualKittens);
    }

}

