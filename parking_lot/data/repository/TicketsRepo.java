package parking_lot.data.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parking_lot.model.Ticket;

// Includes indexes on Ticket Table.
public class TicketsRepo {
    private List<Ticket> allTickets = new ArrayList<>();
    private Map<String, List<Ticket>> vehicleNumberToTicketsMap = new HashMap<>();
}