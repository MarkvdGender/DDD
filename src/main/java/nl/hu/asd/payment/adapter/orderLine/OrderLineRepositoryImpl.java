package nl.hu.asd.payment.adapter.orderLine;

import nl.hu.asd.payment.adapter.invoice.InvoiceRepositoryImpl;
import nl.hu.asd.payment.domain.invoice.Invoice;
import nl.hu.asd.payment.domain.invoice.InvoiceId;
import nl.hu.asd.payment.domain.invoice.InvoiceRepository;
import nl.hu.asd.payment.domain.orderline.OrderLine;
import nl.hu.asd.payment.domain.orderline.OrderLineId;
import nl.hu.asd.payment.domain.orderline.OrderLineRepository;
import nl.hu.asd.product.domain.product.ProductId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderLineRepositoryImpl implements OrderLineRepository {
    private List<OrderLine> orderLines = new ArrayList<>();
    private static OrderLineRepository instance;

    private OrderLineRepositoryImpl() {
        orderLines.add(new OrderLine(new OrderLineId(1), 1, new ProductId(1), new InvoiceId(1)));
        orderLines.add(new OrderLine(new OrderLineId(2), 2, new ProductId(3), new InvoiceId(1)));
        orderLines.add(new OrderLine(new OrderLineId(3), 1, new ProductId(1), new InvoiceId(2)));
        orderLines.add(new OrderLine(new OrderLineId(4), 4, new ProductId(2), new InvoiceId(2)));
        orderLines.add(new OrderLine(new OrderLineId(5), 2, new ProductId(3), new InvoiceId(2)));
        orderLines.add(new OrderLine(new OrderLineId(6), 1, new ProductId(2), new InvoiceId(3)));
        orderLines.add(new OrderLine(new OrderLineId(7), 5, new ProductId(4), new InvoiceId(3)));
        orderLines.add(new OrderLine(new OrderLineId(8), 6, new ProductId(5), new InvoiceId(3)));
        orderLines.add(new OrderLine(new OrderLineId(9), 2, new ProductId(3), new InvoiceId(4)));
    }

    public static OrderLineRepository getInstance(){
        if(instance==null){
            instance = new OrderLineRepositoryImpl();
        }
        return instance;
    }

    @Override
    public OrderLine findById(OrderLineId olId) {
        for (OrderLine orderLine : orderLines)
            if (orderLine.getId().equals(olId))
                return orderLine;

        return null;
    }

    @Override
    public void save(OrderLine orderLine) {
        boolean found = false;
        int counter = 0;
        for (OrderLine o : orderLines) {
            if (o.getId().equals(orderLine.getId())) {
                found = true;
                break;
            }
            counter++;
        }
        if (found) {
            orderLines.set(counter, orderLine);
        } else {
            orderLines.add(orderLine);
        }

    }

    @Override
    public List<OrderLineId> findOrderLineIdsByInvoiceId(InvoiceId iId) {
        List<OrderLineId> orderLineIds = new ArrayList<>();
        for (OrderLine orderLine : orderLines) {
            if (orderLine.getInvoiceId().equals(iId)) {
                orderLineIds.add(orderLine.getId());
            }
        }
        return orderLineIds;
    }
}