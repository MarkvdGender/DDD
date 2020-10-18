package nl.hu.asd.payment.domain.orderline;

import lombok.*;
import nl.hu.asd.payment.adapter.orderLine.OrderLineServiceImpl;
import nl.hu.asd.payment.domain.invoice.InvoiceId;
import nl.hu.asd.payment.domain.price.Currency;
import nl.hu.asd.payment.domain.price.Price;
import nl.hu.asd.product.adapter.product.ProductRepositoryImpl;
import nl.hu.asd.product.domain.product.Product;
import nl.hu.asd.product.domain.product.ProductId;
import nl.hu.asd.product.domain.product.ProductRepository;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class OrderLine {
    @NonNull
    private OrderLineId id;
    @NonNull
    private int quantity;
    private Price price;

    @NonNull
    private ProductId productId;
    @NonNull
    private InvoiceId invoiceId;

    private ProductRepository productRepository = ProductRepositoryImpl.getInstance();
    private OrderLineService orderLineService = new OrderLineServiceImpl();

    public boolean updateQuantity(int newQuantity) {
        if (orderLineService.productIsAvailable(productId, newQuantity)) {
            orderLineService.reduceProductStock(productId, newQuantity);
            this.quantity = newQuantity;
            return true;
        }

        return false;
    }

    public Price calculateOrderLinePrice() {
        Product product = productRepository.findById(productId);
        double totalGrossAmount = product.getPricePerUnit() * quantity;
        double totalTaxAmount = product.getTax().getRate() * product.getPricePerUnit() * quantity;
        double totalNetAmount = totalGrossAmount + totalTaxAmount;
        return new Price(totalNetAmount, totalGrossAmount, totalTaxAmount, Currency.EUR);
    }
}