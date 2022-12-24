package service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;
import java.util.Calendar;

@FieldDefaults(level = AccessLevel.PUBLIC)
public class DateComparator {

    static boolean isOldContainsNew(LocalDateTime oldPriceBegin, LocalDateTime oldPriceEnd, LocalDateTime newPriceBegin, LocalDateTime newPriceEnd) {
        boolean check = oldPriceBegin.isBefore(newPriceBegin) && newPriceEnd.isBefore(oldPriceEnd);
        return check;
    }

    static boolean isNewContainsOld(LocalDateTime oldPriceBegin, LocalDateTime oldPriceEnd, LocalDateTime newPriceBegin, LocalDateTime newPriceEnd) {
        boolean check = newPriceBegin.isBefore(oldPriceBegin) && newPriceEnd.isAfter(oldPriceEnd);
        return check;
    }

    static boolean isNewRight(LocalDateTime oldPriceBegin, LocalDateTime oldPriceEnd, LocalDateTime newPriceBegin, LocalDateTime newPriceEnd) {
        boolean check = oldPriceBegin.isBefore(newPriceBegin) && newPriceBegin.isBefore(oldPriceEnd) && oldPriceEnd.isBefore(newPriceEnd);
        return check;
    }

    static boolean isNewLeft(LocalDateTime oldPriceBegin, LocalDateTime oldPriceEnd, LocalDateTime newPriceBegin, LocalDateTime newpriceEnd) {
        boolean check = oldPriceBegin.isAfter(newPriceBegin) && newpriceEnd.isAfter(oldPriceBegin) && oldPriceEnd.isAfter(newpriceEnd);
        return check;
    }
}
