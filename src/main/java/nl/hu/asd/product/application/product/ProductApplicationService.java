package nl.hu.asd.product.application.product;

import nl.hu.asd.product.adapter.product.ProductRepositoryImpl;
import nl.hu.asd.product.adapter.supplier.SupplierRepositoryImpl;
import nl.hu.asd.product.domain.product.Product;
import nl.hu.asd.product.domain.product.ProductId;
import nl.hu.asd.product.domain.product.ProductRepository;

import nl.hu.asd.product.domain.supplier.Supplier;
import nl.hu.asd.product.domain.supplier.SupplierRepository;

public class ProductApplicationService {
    private ProductRepository productRepository;
    private SupplierRepository supplierRepository;

    public ProductApplicationService(){
        this.productRepository = ProductRepositoryImpl.getInstance();
        this.supplierRepository = new SupplierRepositoryImpl();
    }

    public boolean productIsAvailable(ProductId pID, int nQ) {
        Product product = productRepository.findById(pID);

        return product.isAvailable(nQ);
    }

    public boolean validateProductPrice(ProductId pID) {
        Product product = productRepository.findById(pID);
        return product.validatePrice();
    }

    public void reduceProductStock(ProductId pID, int quantity) {
        Product product = productRepository.findById(pID);

        product.reduceStock(quantity);

        productRepository.save(product);
    }


    public Supplier createSupplierAndUpdateProduct(ProductId productId, int number, String name, int cocNumber, int vatNumber){
        Product product =  productRepository.findById(productId);

        return product.createSupplierAndUpdateProduct(number, name, cocNumber, vatNumber);
    }
}
