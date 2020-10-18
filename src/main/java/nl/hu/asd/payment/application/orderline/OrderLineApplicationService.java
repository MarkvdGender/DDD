package nl.hu.asd.payment.application.orderline;

import nl.hu.asd.payment.adapter.orderLine.OrderLineRepositoryImpl;
import nl.hu.asd.payment.domain.orderline.OrderLine;
import nl.hu.asd.payment.domain.orderline.OrderLineId;
import nl.hu.asd.payment.domain.orderline.OrderLineRepository;

public class OrderLineApplicationService {
    private static OrderLineRepository orderLineRepository;

    public OrderLineApplicationService() {
        this.orderLineRepository = OrderLineRepositoryImpl.getInstance();
    }

    public boolean updateOrderLineQuantity(OrderLineId orderLineId, int newQuantity) {
        OrderLine orderLine = orderLineRepository.findById(orderLineId);

        if (orderLine.updateQuantity(newQuantity)) {
            orderLineRepository.save(orderLine);
            return true;
        }
        return false;
    }
}
