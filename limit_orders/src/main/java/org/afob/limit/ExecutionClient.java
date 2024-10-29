package org.afob.limit;

import static org.afob.limit.LimitOrderAgent.*;

public class ExecutionClient {
    // Method to execute a buy order
    public void buy(Order order) {
        try {
            // Simulate order execution
            if (order.amount <= 0) {
                throw new IllegalArgumentException("Amount must be greater than zero for buy orders.");
            }
            System.out.println("Executed buy order: " + order.amount + " shares of " + order.productId + " at limit " + order.limit);
        } catch (Exception e) {
            handleExecutionError(e, order);
        }
    }

    // Method to execute a sell order
    public void sell(Order order) {
        try {
            // Simulate order execution
            if (order.amount <= 0) {
                throw new IllegalArgumentException("Amount must be greater than zero for sell orders.");
            }
            System.out.println("Executed sell order: " + order.amount + " shares of " + order.productId + " at limit " + order.limit);
        } catch (Exception e) {
            handleExecutionError(e, order);
        }
    }

    // Method to handle execution errors
    private void handleExecutionError(Exception e, Order order) {
        System.err.println("Failed to execute order for " + order.productId + ": " + e.getMessage());
        // Additional logging or handling could be done here
    }
}