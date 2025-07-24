package parking_lot.data.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parking_lot.model.Payment;

public class PaymentsRepo {
    private List<Payment> allPayments = new ArrayList<>();
    private Map<String, Payment> ticketIdToPaymentMap = new HashMap<>();
    private Map<String, List<Payment>> vehicleNumberToPaymentsMap = new HashMap<>();
}