package nl.hu.asd.product.adapter.supplier;

import nl.hu.asd.product.domain.product.Product;
import nl.hu.asd.product.domain.product.ProductId;
import nl.hu.asd.product.domain.supplier.Supplier;
import nl.hu.asd.product.domain.supplier.SupplierId;
import nl.hu.asd.product.domain.supplier.SupplierRepository;

import java.util.ArrayList;
import java.util.List;

public class SupplierRepositoryImpl implements SupplierRepository {
    private List<Supplier> suppliers = new ArrayList<>();

    public SupplierRepositoryImpl() {
        suppliers.add(new Supplier(new SupplierId(1), "Hollands Nieuwe", 23, 89433049, 39293956));
        suppliers.add(new Supplier(new SupplierId(2), "Ziggo", 67, 56456234, 24231523));
    }

    @Override
    public SupplierId nextIdentity() {
        int id = 1;
        for (Supplier supplier : suppliers) {
            if (supplier.getId().getId() >= id) {
                id = supplier.getId().getId() + 1;
            }
        }
        return new SupplierId(id);
    }

    @Override
    public Supplier findById(SupplierId sId) {
        for (Supplier supplier : suppliers)
            if (supplier.getId().equals(sId))
                return supplier;

        return null;
    }

    @Override
    public boolean notExistsByName(String name) {
        boolean result = true;
        for (Supplier supplier : suppliers) {
            if (supplier.getName().equals(name)) {
                result = false;
            }
        }
        return result;
    }
}
