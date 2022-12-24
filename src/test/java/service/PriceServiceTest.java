package service;

import entity.Price;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;

public class PriceServiceTest {

    private final DateTimeFormatter fmt = new DateTimeFormatterBuilder()
            .appendPattern("dd.MM.yyyy HH:mm:ss")
            .toFormatter();

    @Test
    public void Test() {
        //current
        ArrayList<Price> oldPrice = new ArrayList<>();
        Price first = new Price("2345", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("28.01.2013 00:00:00", fmt), 80);
        oldPrice.add(first);
        //input
        Price firstNew = new Price("2345", 1, 1,
                LocalDateTime.parse("05.01.2013 00:00:00", fmt), LocalDateTime.parse("16.01.2013 00:00:00", fmt), 90);
        ArrayList<Price> currentNew = new ArrayList<>();
        currentNew.add(firstNew);
        //expect
        ArrayList<Price> expect = new ArrayList<>(
                List.of(
                        new Price( "2345", 1, 1,
                                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("05.01.2013 00:00:00", fmt), 80),
                        new Price("2345", 1, 1,
                                LocalDateTime.parse("16.01.2013 00:00:00", fmt), LocalDateTime.parse("28.01.2013 00:00:00", fmt), 80),
                        new Price( "2345", 1, 1,
                                LocalDateTime.parse("05.01.2013 00:00:00", fmt), LocalDateTime.parse("16.01.2013 00:00:00", fmt), 90)
                )
        );
        ArrayList<Price> result = PriceService.updatePriceList(oldPrice, currentNew);
        Assert.assertThat(result, is(expect));
    }
}