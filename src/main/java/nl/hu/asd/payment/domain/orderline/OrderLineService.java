package nl.hu.asd.payment.domain.orderline;

import nl.hu.asd.product.domain.product.ProductId;

public interface OrderLineService {
    public boolean productIsAvailable(ProductId productId, int quantity);

    public void reduceProductStock(ProductId pID, int quantity);
}
