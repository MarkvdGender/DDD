package nl.hu.asd.payment.adapter.orderLine;

import nl.hu.asd.payment.domain.orderline.OrderLineService;
import nl.hu.asd.product.adapter.product.ProductRESTService;
import nl.hu.asd.product.domain.product.ProductId;

public class OrderLineServiceImpl implements OrderLineService {
    private ProductRESTService productRESTService;

    public OrderLineServiceImpl() {
        this.productRESTService = new ProductRESTService();
    }

    public boolean productIsAvailable(ProductId productId, int quantity) {
        return productRESTService.productIsAvailable(productId, quantity);
    }

    public void reduceProductStock(ProductId pID, int quantity) {
        productRESTService.reduceProductStock(pID, quantity);
    }

}
