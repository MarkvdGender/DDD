package nl.hu.asd.payment.domain.orderline;

import nl.hu.asd.payment.domain.invoice.InvoiceId;

import java.util.List;

public interface OrderLineRepository {
    public OrderLine findById(OrderLineId olId);
    public void save(OrderLine orderLine);
    public List<OrderLineId> findOrderLineIdsByInvoiceId(InvoiceId iId);
}
