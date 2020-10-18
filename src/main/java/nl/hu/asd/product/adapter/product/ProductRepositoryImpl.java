package nl.hu.asd.product.adapter.product;

import nl.hu.asd.product.domain.product.*;
import nl.hu.asd.product.domain.supplier.SupplierId;

import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
    private List<Product> products = new ArrayList<>();
    private static ProductRepository instance;

    private ProductRepositoryImpl() {
        Tax tax = new Tax(0.21, TaxCode.REGULAR);
        products.add(new Product(new ProductId(1), "JBK", "Jong Belegen Kaas", 5.49, tax, Unit.KILOGRAM, 10, new SupplierId(1)));
        products.add(new Product(new ProductId(2), "OK", "Oude Kaas", 9.49, tax, Unit.KILOGRAM, 10, new SupplierId(1)));
        products.add(new Product(new ProductId(3), "GK", "Gouda Kaas", 6.79, tax, Unit.KILOGRAM, 10, new SupplierId(2)));
        products.add(new Product(new ProductId(4), "CK", "Cheddar Kaas", 4.19, tax, Unit.KILOGRAM, 10, new SupplierId(2)));
        products.add(new Product(new ProductId(5), "SK", "Schimmel Kaas", 12.99, tax, Unit.KILOGRAM, 5, new SupplierId(2)));
    }

    public static ProductRepository getInstance(){
        if(instance==null){
            instance = new ProductRepositoryImpl();
        }
        return instance;
    }

    @Override
    public Product findById(ProductId pID) {
        for (Product product : products)
            if (product.getId().equals(pID))
                return product;

        return null;
    }

    public void save(Product product) {
        boolean found = false;
        int counter = 0;
        for (Product p : products) {
            if (p.getId().equals(product.getId())) {
                found = true;
                break;
            }
            counter ++;
        }
        if (found) {
            products.set(counter, product);
        } else {
            products.add(product);
        }

    }
}
