package nl.hu.asd.payment.domain.price;

import lombok.Value;

@Value
public class Price {
    double totalNetAmount;
    double totalGrossAmount;
    double totalTaxAmount;
    Currency currency;
}
