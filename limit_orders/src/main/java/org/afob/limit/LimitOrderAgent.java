package org.afob.limit;
import org.afob.prices.PriceListener;
import java.util.ArrayList;
import java.util.List;

public class LimitOrderAgent implements PriceListener {
    private final List<Order> orders = new ArrayList<>();
    private final org.afob.limit.ExecutionClient executionClient;

    public LimitOrderAgent(org.afob.limit.ExecutionClient executionClient) {
        this.executionClient = executionClient;
    }

    @Override
    public void priceTick(String productId, double price) {
        // Check if price is for IBM and place a buy order if conditions are met
        if ("IBM".equals(productId) && price < 100) {
            addOrder(true, productId, 1000, 100);
        }
        executeOrders(productId, price);
    }

    // Method to add orders
    public void addOrder(boolean isBuy, String productId, int amount, double limit) {
        orders.add(new Order(isBuy, productId, amount, limit));
    }

    // Method to execute orders based on current market price
    private void executeOrders(String productId, double currentPrice) {
        for (Order order : new ArrayList<>(orders)) {
            if (order.productId.equals(productId) &&
                    ((order.isBuy && currentPrice <= order.limit) ||
                            (!order.isBuy && currentPrice >= order.limit))) {
                try {
                    if (order.isBuy) {
                        executionClient.buy(order);
                    } else {
                        executionClient.sell(order);
                    }
                    orders.remove(order); // Remove the executed order
                } catch (Exception e) {
                    System.err.println("Error executing order: " + e.getMessage());
                }
            }
        }
    }


    // Inner class to represent an order
    static class Order {
        boolean isBuy;
        String productId;
        int amount;
        double limit;

        Order(boolean isBuy, String productId, int amount, double limit) {
            this.isBuy = isBuy;
            this.productId = productId;
            this.amount = amount;
            this.limit = limit;
        }
    }
}