package nl.hu.asd.payment.domain.invoice;

import lombok.Value;

import java.time.LocalDate;

@Value
public class InvoiceDate {
    LocalDate invoiceDate;
}
