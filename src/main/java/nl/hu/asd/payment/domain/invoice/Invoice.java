package nl.hu.asd.payment.domain.invoice;

import lombok.*;
import nl.hu.asd.payment.adapter.orderLine.OrderLineRepositoryImpl;
import nl.hu.asd.payment.domain.orderline.OrderLine;
import nl.hu.asd.payment.domain.orderline.OrderLineId;
import nl.hu.asd.payment.domain.orderline.OrderLineRepository;
import nl.hu.asd.payment.domain.price.Currency;
import nl.hu.asd.payment.domain.price.Price;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Invoice {
    @NonNull
    private InvoiceId id;
    @NonNull
    private DueDate dueDate;
    @NonNull
    private InvoiceDate invoiceDate;
    @NonNull
    private Status status;
    @NonNull
    private Classfication classfication;
    private Price price;

    private List<OrderLineId> orderLineIds = new ArrayList<>();
    private OrderLineRepository orderLineRepository = OrderLineRepositoryImpl.getInstance();

    public void addOrderLine(OrderLine orderLine) {
        if (orderLine.getInvoiceId().equals(id))
            orderLineIds.add(orderLine.getId());
    }

    private boolean validateInvoice() {
//        hier nog wat logica bedenken voor het valideren
        if (status == Status.PAYED) {
            return true;
        }
        return false;
    }


    public boolean completeInvoice(){
        if(this.validateInvoice()){
            status = Status.FINISHED;
            return true;
        }
        return false;
    }

    public Price calculateInvoicePrice() {
        double totalNetAmount = 0;
        double totalGrossAmount = 0;
        double totalTaxAmount = 0;

        for (OrderLineId orderLineId : orderLineIds) {
            OrderLine orderLine = orderLineRepository.findById(orderLineId);
            Price orderLinePrice = orderLine.calculateOrderLinePrice();

            totalNetAmount += Double.parseDouble(String.format(Locale.US, "%.2f", orderLinePrice.getTotalNetAmount()));
            totalGrossAmount +=  Double.parseDouble(String.format(Locale.US,"%.2f",orderLinePrice.getTotalGrossAmount()));
            totalTaxAmount += Double.parseDouble(String.format(Locale.US,"%.2f",orderLinePrice.getTotalTaxAmount()));
        }

        return new Price(totalNetAmount, totalGrossAmount, totalTaxAmount, Currency.EUR);
    }
}
