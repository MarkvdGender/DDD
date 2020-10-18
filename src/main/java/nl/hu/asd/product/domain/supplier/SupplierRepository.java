package nl.hu.asd.product.domain.supplier;

import nl.hu.asd.product.domain.product.Product;
import nl.hu.asd.product.domain.product.ProductId;

public interface SupplierRepository {
    public SupplierId nextIdentity();
    public Supplier findById(SupplierId sId);

    boolean notExistsByName(String name);
}