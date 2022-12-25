package service;

import entity.Price;
import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

public class PriceServiceTest {

    private final DateTimeFormatter fmt = new DateTimeFormatterBuilder()
            .appendPattern("dd.MM.yyyy HH:mm:ss")
            .toFormatter();

    @Test
    public void TestOne() {

        ArrayList<Price> oldPrice = new ArrayList<>();
        Price first = new Price("2345", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("28.01.2013 00:00:00", fmt), 80);
        oldPrice.add(first);

        Price firstNew = new Price("2345", 1, 1,
                LocalDateTime.parse("05.01.2013 00:00:00", fmt), LocalDateTime.parse("16.01.2013 00:00:00", fmt), 90);
        ArrayList<Price> currentNew = new ArrayList<>();
        currentNew.add(firstNew);

        ArrayList<Price> expect = new ArrayList<>(
                List.of(
                        new Price( "2345", 1, 1,
                                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("05.01.2013 00:00:00", fmt), 80),
                        new Price("2345", 1, 1,
                                LocalDateTime.parse("05.01.2013 00:00:00", fmt), LocalDateTime.parse("16.01.2013 00:00:00", fmt), 90),
                        new Price( "2345", 1, 1,
                                LocalDateTime.parse("16.01.2013 00:00:00", fmt), LocalDateTime.parse("28.01.2013 00:00:00", fmt), 80)
                )
        );
        ArrayList<Price> result = PriceService.updatePriceList(oldPrice, currentNew);
        boolean check = result.equals(expect);
        Assert.assertEquals(true, check);
    }
    @Test
    public void TestTwo() {

        ArrayList<Price> current = new ArrayList<>(
                List.of(
                        new Price( "2345", 1, 1,
                                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("16.01.2013 00:00:00", fmt), 100),
                        new Price( "2345", 1, 1,
                                LocalDateTime.parse("16.01.2013 00:00:00", fmt), LocalDateTime.parse("31.01.2013 00:00:00", fmt), 120)
                )
        );

        Price firstNew = new Price( "2345", 1, 1,
                LocalDateTime.parse("07.01.2013 00:00:00", fmt), LocalDateTime.parse("24.01.2013 00:00:00", fmt), 110);
        ArrayList<Price> currentNew = new ArrayList<>();
        currentNew.add(firstNew);

        ArrayList<Price> expect = new ArrayList<>(
                List.of(
                        new Price( "2345", 1, 1,
                                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("07.01.2013 00:00:00", fmt), 100),
                        new Price( "2345", 1, 1,
                                LocalDateTime.parse("07.01.2013 00:00:00", fmt), LocalDateTime.parse("24.01.2013 00:00:00", fmt), 110),
                        new Price( "2345", 1, 1,
                                LocalDateTime.parse("24.01.2013 00:00:00", fmt), LocalDateTime.parse("31.01.2013 00:00:00", fmt), 120)
                )
        );
        ArrayList<Price> result = PriceService.updatePriceList(current, currentNew);
        boolean check = result.equals(expect);
        Assert.assertEquals(true, check);
    }

    @Test
    public void TestThree() {

        ArrayList<Price> current = new ArrayList<>(
                List.of(
                        new Price( "2345", 1, 1,
                                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("06.01.2013 00:00:00", fmt), 80),
                        new Price("2345", 1, 1,
                                LocalDateTime.parse("06.01.2013 00:00:00", fmt), LocalDateTime.parse("16.01.2013 00:00:00", fmt), 87),
                        new Price("2345", 1, 1,
                                LocalDateTime.parse("16.01.2013 00:00:00", fmt), LocalDateTime.parse("31.01.2013 00:00:00", fmt), 90)
                )
        );

        ArrayList<Price> input = new ArrayList<>(
                List.of(
                        new Price( "2345", 1, 1,
                                LocalDateTime.parse("02.01.2013 00:00:00", fmt), LocalDateTime.parse("11.01.2013 00:00:00", fmt), 80),
                        new Price("2345", 1, 1,
                                LocalDateTime.parse("11.01.2013 00:00:00", fmt), LocalDateTime.parse("21.01.2013 00:00:00", fmt), 85)
                )
        );
        //expect
        ArrayList<Price> expect = new ArrayList<>(
                List.of(
                        new Price("2345", 1, 1,
                                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("11.01.2013 00:00:00", fmt), 80),
                        new Price("2345", 1, 1,
                                LocalDateTime.parse("11.01.2013 00:00:00", fmt), LocalDateTime.parse("21.01.2013 00:00:00", fmt), 85),
                        new Price( "2345", 1, 1,
                                LocalDateTime.parse("21.01.2013 00:00:00", fmt), LocalDateTime.parse("31.01.2013 00:00:00", fmt), 90)

                )
        );
        ArrayList<Price> result = PriceService.updatePriceList(current, input);
        boolean check = result.equals(expect);
        Assert.assertEquals(true, check);
    }
}