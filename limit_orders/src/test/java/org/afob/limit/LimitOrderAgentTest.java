package org.afob.limit;
import org.junit.Assert;
import org.junit.Test;


public class LimitOrderAgentTest {
    public static void main(String[] args) {
        org.afob.limit.ExecutionClient executionClient = new org.afob.limit.ExecutionClient();
        LimitOrderAgent limitOrderAgent = new LimitOrderAgent(executionClient);

        // Simulating price ticks
        System.out.println("Simulating price ticks...");
        limitOrderAgent.priceTick("IBM", 99.5); // This should trigger a buy order for IBM
        limitOrderAgent.priceTick("IBM", 100.5); // No orders should execute
        limitOrderAgent.priceTick("IBM", 99); // This should trigger another buy order for IBM
        limitOrderAgent.priceTick("IBM", 101); // No sell orders to execute yet
        limitOrderAgent.addOrder(false, "IBM", 500, 101); // Adding a sell order
        limitOrderAgent.priceTick("IBM", 101); // This should execute the sell order
    }
    @Test
    public void addTestsHere() {
        Assert.fail("not implemented");
    }
}

