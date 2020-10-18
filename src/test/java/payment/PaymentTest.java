package payment;

import nl.hu.asd.payment.application.invoice.InvoiceApplicationService;
import nl.hu.asd.payment.domain.invoice.InvoiceId;
import nl.hu.asd.payment.domain.orderline.OrderLineId;
import nl.hu.asd.payment.domain.price.Price;
import org.junit.Assert;
import org.junit.Test;

public class PaymentTest {
    InvoiceApplicationService invoiceApplicationService = new InvoiceApplicationService();

// Test voor update orderline quantity
    @Test
    public void updateOrderLineAvailableQuantity(){
        Assert.assertTrue(invoiceApplicationService.updateOrderLineQuantity(new InvoiceId(1), new OrderLineId(1), 2));
    }

    @Test
    public void updateOrderLineNotAvailableQuantity(){
        Assert.assertFalse(invoiceApplicationService.updateOrderLineQuantity(new InvoiceId(1), new OrderLineId(1), 9));
    }

//    Test voor complete-invoice
    @Test
    public void completeValidInvoice(){
        Assert.assertTrue(invoiceApplicationService.completeInvoice(new InvoiceId(2)));
    }

    @Test
    public void completeInvalidInvoice(){
        Assert.assertFalse(invoiceApplicationService.completeInvoice(new InvoiceId(1)));
    }

//    Test voor calculate-invoice-Price
    @Test
    public void calculateAvailableInvoicePrice() {
        Assert.assertNotNull(invoiceApplicationService.calculateInvoicePriceWithInvoiceId(new InvoiceId(2)));
    }

    @Test
    public void calculateUnavailableInvoicePrice() {
        Assert.assertNull(invoiceApplicationService.calculateInvoicePriceWithInvoiceId(new InvoiceId(5)));
    }

    @Test
    public void calculateCorrectInvoicePrice() {
        Assert.assertEquals(29.72, invoiceApplicationService.calculateInvoicePriceWithInvoiceId(new InvoiceId(1)).getTotalNetAmount(), 0);
        Assert.assertEquals(24.56, invoiceApplicationService.calculateInvoicePriceWithInvoiceId(new InvoiceId(1)).getTotalGrossAmount(), 0.1);
        Assert.assertEquals(5.16, invoiceApplicationService.calculateInvoicePriceWithInvoiceId(new InvoiceId(1)).getTotalTaxAmount(), 0);
    }
}