package nl.hu.asd.product.domain.supplier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    private SupplierId id;
    private String name;
    private int number;
    private int cocNumber;
    private int vatNumber;
}



