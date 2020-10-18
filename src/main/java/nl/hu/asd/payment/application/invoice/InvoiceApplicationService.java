package nl.hu.asd.payment.application.invoice;

import nl.hu.asd.payment.adapter.invoice.InvoiceRepositoryImpl;
import nl.hu.asd.payment.application.orderline.OrderLineApplicationService;
import nl.hu.asd.payment.domain.invoice.Invoice;
import nl.hu.asd.payment.domain.invoice.InvoiceId;
import nl.hu.asd.payment.domain.invoice.InvoiceRepository;
import nl.hu.asd.payment.domain.orderline.OrderLineId;
import nl.hu.asd.payment.domain.price.Price;

public class InvoiceApplicationService {
    private InvoiceRepository invoiceRepository;
    private OrderLineApplicationService orderLineApplicationService;

    public InvoiceApplicationService() {
        this.invoiceRepository = InvoiceRepositoryImpl.getInstance();
        this.orderLineApplicationService = new OrderLineApplicationService();
    }

    public boolean updateOrderLineQuantity(InvoiceId invoiceId, OrderLineId orderLineId, int newQuantity) {
        if (orderLineApplicationService.updateOrderLineQuantity(orderLineId, newQuantity)) {
            Invoice invoice = invoiceRepository.findById(invoiceId);
            invoice.calculateInvoicePrice();
            invoiceRepository.save(invoice);
            return true;
        }

        return false;
    }

    public boolean completeInvoice(InvoiceId invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId);
        if (invoice.completeInvoice()) {
            invoiceRepository.save(invoice);
            return true;
        }
        return false;
    }

    public Price calculateInvoicePriceWithInvoiceId(InvoiceId invoiceId) {
        Price price = null;
        Invoice i = invoiceRepository.findById(invoiceId);
        if (i != null) {
            price = i.calculateInvoicePrice();
        }
        return price;
    }
}
