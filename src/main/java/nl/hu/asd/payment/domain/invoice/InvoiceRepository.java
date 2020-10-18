package nl.hu.asd.payment.domain.invoice;

public interface InvoiceRepository {

    public Invoice findById(InvoiceId invoiceId);

    public void save(Invoice invoice);
}
