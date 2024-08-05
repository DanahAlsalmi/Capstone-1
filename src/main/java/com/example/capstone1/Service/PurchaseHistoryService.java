package com.example.capstone1.Service;

import com.example.capstone1.Model.PurchaseHistory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PurchaseHistoryService {

     ArrayList<PurchaseHistory> purchaseHistories = new ArrayList<>();

    public void recordPurchase(int userId, int productId, int merchantId, int quantity, double totalPrice) {
        PurchaseHistory history = new PurchaseHistory(userId, productId, merchantId, quantity, totalPrice, LocalDateTime.now());
        purchaseHistories.add(history);
    }

    public ArrayList<PurchaseHistory> getUserPurchaseHistory(int userId) {
        ArrayList<PurchaseHistory> userPurchaseHistory = new ArrayList<>();
        for (PurchaseHistory history : purchaseHistories) {
            if (history.getUserId() == userId) {
                userPurchaseHistory.add(history);
            }
        }
        return userPurchaseHistory;
    }
}
