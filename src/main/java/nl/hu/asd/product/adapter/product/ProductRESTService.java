package nl.hu.asd.product.adapter.product;

import nl.hu.asd.product.application.product.ProductApplicationService;
import nl.hu.asd.product.domain.product.ProductId;

public class ProductRESTService {
    private ProductApplicationService productApplicationService;

    public ProductRESTService() {
        this.productApplicationService = new ProductApplicationService();
    }

    public boolean productIsAvailable(ProductId productID, int quantity) {
        return productApplicationService.productIsAvailable(productID, quantity);
    }

    public void reduceProductStock(ProductId pID, int quantity) {
        productApplicationService.reduceProductStock(pID, quantity);
    }

    public boolean validateProductPrice(ProductId pID) {
        return productApplicationService.validateProductPrice(pID);
    }
}