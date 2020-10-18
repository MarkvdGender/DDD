package product;

import nl.hu.asd.product.application.product.ProductApplicationService;
import nl.hu.asd.product.domain.product.ProductId;
import org.junit.Assert;
import org.junit.Test;

public class ProductTest {

    ProductApplicationService productApplicationService = new ProductApplicationService();

//    Test voor create-supplier-and-update-product
    @Test
    public void createSupplierAndUpdateProduct(){
        Assert.assertEquals("Kaasboer", productApplicationService.createSupplierAndUpdateProduct(new ProductId(1), 9, "Kaasboer", 192738, 293842).getName());
        Assert.assertNotNull(productApplicationService.createSupplierAndUpdateProduct(new ProductId(1), 4, "KPN", 2039482, 304928));
    }

    @Test
    public void duplicatedSupplier(){
        Assert.assertNull(productApplicationService.createSupplierAndUpdateProduct(new ProductId(1), 3, "Ziggo", 30495, 29384));
    }

//    Test voor validate-product-price
    @Test
    public void validateProductPrice() {
        Assert.assertTrue(productApplicationService.validateProductPrice(new ProductId(1)));
    }
}