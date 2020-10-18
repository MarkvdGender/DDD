package nl.hu.asd.payment.adapter.invoice;

import nl.hu.asd.payment.application.invoice.InvoiceApplicationService;
import nl.hu.asd.payment.domain.invoice.InvoiceId;
import nl.hu.asd.payment.domain.invoice.InvoiceRepository;
import nl.hu.asd.payment.domain.orderline.OrderLineId;

public class InvoiceRESTService {
    private static InvoiceApplicationService invoiceApplicationService;

    InvoiceRESTService(InvoiceApplicationService invoiceApplicationService) {
        this.invoiceApplicationService = invoiceApplicationService;
    }

    public void completeInvoice(InvoiceId invoiceId){
        invoiceApplicationService.completeInvoice(invoiceId);
    }

    public void updateOrderLineQuantity(InvoiceId invoiceId, OrderLineId orderLineId, int newQuantity) {
        if (invoiceApplicationService.updateOrderLineQuantity(invoiceId, orderLineId, newQuantity)) {
            System.out.println("The quantity of orderline " + orderLineId + " is updated successfully");
        } else {
            System.out.println("The quantity of orderline is not updated");
        }
    }
}
