package org.chaidev.cexdemo.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

import org.chaidev.cexdemo.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CexController {
    private Logger logger = Logger.getLogger(CexController.class.getName());

    private List<Order> bidOrders = new ArrayList<>();
    private List<Order> offerOrders = new ArrayList<>();
    private DecimalFormat df = new DecimalFormat("#.##");   
    private DecimalFormat df2 = new DecimalFormat("#.00000000");
    @GetMapping("/getBid")
    public ResponseEntity<List<Order>> getBid(@RequestParam int orders) {
        //bidOrders.clear();
        double[] bids = getBidPrices(orders);
        for (double bid : bids) {
            Order o = new Order(Double.valueOf(df.format(bid)).doubleValue(), Double.valueOf(df2.format(Math.random())).doubleValue());
            logger.info("Bid: " + o.toString());
            //logger.info("Bid: " + o.getPrice() + ":" + Double.valueOf(df2.format(Math.random())).doubleValue());
            bidOrders.add(o);
        }

        bidOrders.sort(Comparator.comparingDouble(Order::getPrice));
        return ResponseEntity.ok(bidOrders);
    }

    @GetMapping("/getOffer")
    public ResponseEntity<List<Order>> getOffer(@RequestParam int orders) {
        //offerOrders.clear();
        double[] offers = getOfferPrices(orders);
        for (double offer : offers) {
            Order o = new Order(Double.valueOf(df.format(offer)).doubleValue(), Double.valueOf(df2.format(Math.random())).doubleValue());
            logger.info("Offer: " + o.getPrice() + ":" + Double.valueOf(df2.format(Math.random())).doubleValue());
            offerOrders.add(o);
        }

        offerOrders.sort(Comparator.comparingDouble(Order::getPrice));
        return ResponseEntity.ok(offerOrders);
    }

    private double[] getBidPrices(@RequestParam int orders) {
        double[] randoms = ThreadLocalRandom.current().doubles(orders, 61500, 62000).toArray();
        Arrays.sort(randoms);
        return randoms;
    }

    private double[] getOfferPrices(@RequestParam int orders) {
        double[] randoms = ThreadLocalRandom.current().doubles(orders, 62000, 62500).toArray();
        Arrays.sort(randoms);
        return randoms;
    }

    @PostMapping("/buyCrypto")
    public ResponseEntity<Object> buyCrypto(@RequestParam int orders) {
        //2 case
        //1) Transfer from other exchanges
        //2) Transfer from internal exchange
        //+ Fee
        return null;
    }

    @PostMapping("/sellCrypto")
    public ResponseEntity<Object> sellCrypto(@RequestParam int orders) {
        //2 case
        //1) Transfer from other exchanges
        //2) Transfer from internal exchange
        //+Fee
        return null;
    }
}