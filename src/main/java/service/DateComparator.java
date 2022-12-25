package service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;

@FieldDefaults(level = AccessLevel.PUBLIC)
public class DateComparator {

    static boolean isOldContainsNew(LocalDateTime oldPriceBegin, LocalDateTime oldPriceEnd, LocalDateTime newPriceBegin, LocalDateTime newPriceEnd) {
        boolean check = newPriceBegin.isAfter(oldPriceBegin) && newPriceEnd.isBefore(oldPriceEnd);
        return check;
    }

    static boolean isNewContainsOld(LocalDateTime oldPriceBegin, LocalDateTime oldPriceEnd, LocalDateTime newPriceBegin, LocalDateTime newPriceEnd) {
        boolean check = oldPriceBegin.isAfter(newPriceBegin) && newPriceEnd.isAfter(oldPriceEnd);
        return check;
    }

    static boolean isNewRight(LocalDateTime oldPriceBegin, LocalDateTime oldPriceEnd, LocalDateTime newPriceBegin, LocalDateTime newPriceEnd) {
        boolean check = (newPriceBegin.isAfter(oldPriceBegin)||newPriceBegin.equals(oldPriceBegin)) && oldPriceEnd.isAfter(newPriceBegin) && newPriceEnd.isAfter(oldPriceEnd);
        return check;
    }

    static boolean isNewLeft(LocalDateTime oldPriceBegin, LocalDateTime oldPriceEnd, LocalDateTime newPriceBegin, LocalDateTime newpriceEnd) {
        boolean check = oldPriceBegin.isAfter(newPriceBegin) && newpriceEnd.isAfter(oldPriceBegin) && oldPriceEnd.isAfter(newpriceEnd);
        return check;
    }
}
