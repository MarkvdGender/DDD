package nl.hu.asd.product.domain.product;

import lombok.Value;

@Value
public class Tax {
    double rate;
    TaxCode code;
}
