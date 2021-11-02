package com.company;

import lombok.Builder;
import lombok.ToString;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.company.Workload.*;

public class CostOfDeliveryTest {

    @Test(description = "Проверка функции расчёта стоимости доставки", dataProvider = "TestDataProvider")
    public static void calculateOfCostTest(TestData testData) {
        CostOfDelivery costOfDelivery = new CostOfDelivery();
        costOfDelivery.calculate(
                testData.distance,
                testData.length,
                testData.width,
                testData.height,
                testData.fragility,
                testData.workload);
        Assert.assertEquals(costOfDelivery.getCost(), testData.expectedResult);
    }

    @DataProvider(name = "TestDataProvider")
    private Object[][] dataProviderMethod() {
        return new Object[][]{
                {
                        TestData.builder()
                                .requirement("Расстояние 1 км: +50 рублей к доставке")
                                .distance(1)
                                .length(100)
                                .width(100)
                                .height(100)
                                .fragility(true)
                                .workload(OTHER)
                                .expectedResult(550)
                                .build()
                },
                {
                        TestData.builder()
                                .requirement("Расстояние 2 км: +50 рублей к доставке")
                                .distance(2)
                                .length(100)
                                .width(100)
                                .height(100)
                                .fragility(true)
                                .workload(OTHER)
                                .expectedResult(550)
                                .build()
                },
                {
                        TestData.builder()
                                .requirement("Расстояние 3 км: +100 рублей к доставке")
                                .distance(3)
                                .length(100)
                                .width(100)
                                .height(100)
                                .fragility(true)
                                .workload(OTHER)
                                .expectedResult(600)
                                .build()
                },
                {
                        TestData.builder()
                                .requirement("Расстояние 10 км: +100 рублей к доставке")
                                .distance(10)
                                .length(100)
                                .width(100)
                                .height(100)
                                .fragility(true)
                                .workload(OTHER)
                                .expectedResult(600)
                                .build()
                },
                {
                        TestData.builder()
                                .requirement("Расстояние 11 км: +200 рублей к доставке")
                                .distance(11)
                                .length(100)
                                .width(100)
                                .height(100)
                                .fragility(true)
                                .workload(OTHER)
                                .expectedResult(700)
                                .build()
                },
                {
                        TestData.builder()
                                .requirement("Расстояние 30 км: +200 рублей к доставке")
                                .distance(30)
                                .length(100)
                                .width(100)
                                .height(100)
                                .fragility(true)
                                .workload(OTHER)
                                .expectedResult(700)
                                .build()
                },
                {
                        TestData.builder()
                                .requirement("Расстояние 31 км: +300 рублей к доставке")
                                .distance(31)
                                .length(100)
                                .width(100)
                                .height(100)
                                .fragility(false)
                                .workload(OTHER)
                                .expectedResult(500)
                                .build()
                },
                {
                        TestData.builder()
                                .requirement("Большие габариты: +200 рублей к доставке")
                                .distance(11)
                                .length(33)
                                .width(33)
                                .height(34)
                                .fragility(true)
                                .workload(OTHER)
                                .expectedResult(700)
                                .build()
                },
                {
                        TestData.builder()
                                .requirement("Маленькие габариты: +100 рублей к доставке")
                                .distance(11)
                                .length(33)
                                .width(33)
                                .height(33)
                                .fragility(true)
                                .workload(OTHER)
                                .expectedResult(600)
                                .build()
                },
                {
                        TestData.builder()
                                .requirement("Груз хрупкий: +300 рублей к доставке")
                                .distance(29)
                                .length(100)
                                .width(100)
                                .height(100)
                                .fragility(true)
                                .workload(OTHER)
                                .expectedResult(700)
                                .build()
                },
                {
                        TestData.builder()
                                .requirement("Груз нехрупкий")
                                .distance(29)
                                .length(100)
                                .width(100)
                                .height(100)
                                .fragility(false)
                                .workload(OTHER)
                                .expectedResult(400)
                                .build()
                },
                {
                        TestData.builder()
                                .requirement("Хрупкие грузы нельзя возить на расстояние более 30 км")
                                .distance(31)
                                .length(100)
                                .width(100)
                                .height(100)
                                .fragility(true)
                                .workload(OTHER)
                                .expectedResult(0)
                                .build()
                },
                {
                        TestData.builder()
                                .requirement("Очень высокая загруженность - Стоимость умножается на коэффициент 1.6")
                                .distance(20)
                                .length(1)
                                .width(1)
                                .height(1)
                                .fragility(true)
                                .workload(VERY_HIGH)
                                .expectedResult(960)
                                .build()
                },
                {
                        TestData.builder()
                                .requirement("Высокая загруженность - Стоимость умножается на коэффициент 1.4")
                                .distance(20)
                                .length(1)
                                .width(1)
                                .height(1)
                                .fragility(true)
                                .workload(HIGH)
                                .expectedResult(840)
                                .build()
                },
                {
                        TestData.builder()
                                .requirement("Повышенная загруженность - Стоимость умножается на коэффициент 1.2")
                                .distance(20)
                                .length(1)
                                .width(1)
                                .height(1)
                                .fragility(true)
                                .workload(INCREASED)
                                .expectedResult(720)
                                .build()
                },
                {
                        TestData.builder()
                                .requirement("Загруженность во всех остальных случаях - Стоимость умножается на коэффициент 1")
                                .distance(20)
                                .length(1)
                                .width(1)
                                .height(1)
                                .fragility(true)
                                .workload(OTHER)
                                .expectedResult(600)
                                .build()
                },
                {
                        TestData.builder()
                                .requirement("Если сумма доставки меньше минимальной, выводится минимальная сумма 400")
                                .distance(1)
                                .length(1)
                                .width(1)
                                .height(1)
                                .fragility(false)
                                .workload(OTHER)
                                .expectedResult(400)
                                .build()
                }
        };
    }

    @Builder
    @ToString
    private static class TestData {
        String requirement;
        int distance;
        int length;
        int width;
        int height;
        boolean fragility;
        Workload workload;
        double expectedResult;
    }
}
