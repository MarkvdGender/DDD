package nl.hu.asd.product.domain.product;

import lombok.*;
import nl.hu.asd.product.adapter.supplier.SupplierRepositoryImpl;
import nl.hu.asd.product.domain.supplier.Supplier;
import nl.hu.asd.product.domain.supplier.SupplierId;
import nl.hu.asd.product.domain.supplier.SupplierRepository;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Product {
    @NonNull
    private ProductId id;
    @NonNull
    private String code;
    @NonNull
    private String name;
    @NonNull
    private double pricePerUnit;
    @NonNull
    private Tax tax;
    @NonNull
    private Unit unit;
    @NonNull
    private int stock;

    @NonNull
    private SupplierId supplierId;

    private SupplierRepository supplierRepository = new SupplierRepositoryImpl();

    public boolean isAvailable(int quantity) {
        return stock >= quantity;
    }

    public boolean validatePrice() {
        return ( pricePerUnit >= 0 && tax.getRate() >= 0 && tax.getCode() != null );
    }

    public void reduceStock(int quantity) { this.stock -= quantity;}

    public Supplier createSupplierAndUpdateProduct(int number, String name, int cocNumber, int vatNumber){

        if(supplierRepository.notExistsByName(name)){
            Supplier supplier = new Supplier(supplierRepository.nextIdentity(), name, number, cocNumber, vatNumber);
            supplierId = supplier.getId();
            return supplier;
        }
        return null;
    }
}
