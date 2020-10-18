package nl.hu.asd.payment.adapter.invoice;

import nl.hu.asd.payment.adapter.orderLine.OrderLineRepositoryImpl;
import nl.hu.asd.payment.domain.invoice.*;
import nl.hu.asd.payment.domain.orderline.OrderLineId;
import nl.hu.asd.payment.domain.orderline.OrderLineRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InvoiceRepositoryImpl implements InvoiceRepository {
    private List<Invoice> invoices = new ArrayList<>();
    private static InvoiceRepository instance;
    private OrderLineRepository orderLineRepository;

    private InvoiceRepositoryImpl() {
        orderLineRepository = OrderLineRepositoryImpl.getInstance();

        List<OrderLineId> oIds1 = orderLineRepository.findOrderLineIdsByInvoiceId(new InvoiceId(1));
        List<OrderLineId> oIds2 = orderLineRepository.findOrderLineIdsByInvoiceId(new InvoiceId(2));
        List<OrderLineId> oIds3 = orderLineRepository.findOrderLineIdsByInvoiceId(new InvoiceId(3));
        List<OrderLineId> oIds4 = orderLineRepository.findOrderLineIdsByInvoiceId(new InvoiceId(4));

        Invoice i1 = new Invoice(new InvoiceId(1), new DueDate(LocalDate.now()), new InvoiceDate(LocalDate.now().plusMonths(2)), Status.READY, Classfication.REGULAR);
        Invoice i2 = new Invoice(new InvoiceId(2), new DueDate(LocalDate.now().minusDays(2)), new InvoiceDate(LocalDate.now().plusMonths(2)), Status.PAYED, Classfication.REGULAR);
        Invoice i3 = new Invoice(new InvoiceId(3), new DueDate(LocalDate.now().minusWeeks(1)), new InvoiceDate(LocalDate.now().plusMonths(2)), Status.READY, Classfication.REGULAR);
        Invoice i4 = new Invoice(new InvoiceId(4), new DueDate(LocalDate.now().minusMonths(2)), new InvoiceDate(LocalDate.now().plusMonths(2)), Status.READY, Classfication.REGULAR);

        i1.setOrderLineIds(oIds1); invoices.add(i1);
        i2.setOrderLineIds(oIds2); invoices.add(i2);
        i3.setOrderLineIds(oIds3); invoices.add(i3);
        i4.setOrderLineIds(oIds4); invoices.add(i4);
    }

    public static InvoiceRepository getInstance(){
        if(instance==null){
            instance = new InvoiceRepositoryImpl();
        }
        return instance;
    }

    @Override
    public Invoice findById(InvoiceId iId) {
        for (Invoice invoice : invoices)
            if (invoice.getId().equals(iId))
                return invoice;

        return null;
    }

    @Override
    public void save(Invoice invoice) {
        boolean found = false;
        int counter = 0;
        for (Invoice i : invoices) {
            if (i.getId().equals(invoice.getId())) {
                found = true;
                break;
            }
            counter ++;
        }
        if (found) {
            invoices.set(counter, invoice);
        } else {
            invoices.add(invoice);
        }

    }
}