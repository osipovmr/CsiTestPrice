package entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Price implements Cloneable {
    long id; // идентификатор в БД
    String productCode; // код товара
    Integer number; // номер цены
    Integer depart; // номер отдела
    LocalDateTime begin; // начало действия
    LocalDateTime end; // конец действия
    Integer value; // значение цены в копейках

    public Price clone() throws CloneNotSupportedException {
        return (Price) super.clone();
    }

    public Price(String productCode, Integer number, Integer depart, LocalDateTime begin, LocalDateTime end, Integer value) {
        this.productCode = productCode;
        this.number = number;
        this.depart = depart;
        this.begin = begin;
        this.end = end;
        this.value = value;
    }
}
