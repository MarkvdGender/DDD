package nl.hu.asd.payment.domain.invoice;

import lombok.Value;

import java.time.LocalDate;

@Value
public class DueDate {
    LocalDate due;
}
