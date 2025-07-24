package parking_lot.model;

import java.util.UUID;

import parking_lot.model.enums.PaymentMethod;

public class Payment {
    // Unique across all payments.
    private final UUID id;

    private final Ticket ticket;

    private final Integer amount;

    private final PaymentMethod method;

    public UUID getId() {
        return this.id;
    }

    public Ticket getTicket() {
        return this.ticket;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public PaymentMethod getMethod() {
        return this.method;
    }

    private Payment(Builder builder) {
        this.id = builder.id;
        this.ticket = builder.ticket;
        this.amount = builder.amount;
        this.method = builder.method;
    }

    public static class Builder {
        private UUID id;
        private Ticket ticket;
        private Integer amount;
        private PaymentMethod method;

        public void setTicket(Ticket ticket) {
            this.ticket = ticket;
        }

        public void setAmount(Integer amount) {
            this.amount = amount;
        }

        public void setPaymentMethod(PaymentMethod method) {
            this.method = method;
        }

        public Payment build() {
            this.id = UUID.randomUUID();
            return new Payment(this);
        }
    }

}