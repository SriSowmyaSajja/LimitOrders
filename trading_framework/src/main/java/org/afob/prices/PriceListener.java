package org.afob.prices;


public interface PriceListener {

    /**
     *
     * @param productId the id of the product for which the price is given
     * @param price current price of the product
     */
    void priceTick(String productId, double price);

}
