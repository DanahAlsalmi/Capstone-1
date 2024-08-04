package com.example.capstone1.Service;

import com.example.capstone1.Model.MerchantStock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantStockService {

    //Array for Merchant Stock
    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();

    //Get Merchant Stock
    public ArrayList<MerchantStock> getMerchantStocks() {
        return merchantStocks;
    }

    //Add Merchant Stock
    public void addMerchantStock(MerchantStock merchantStock) {
        merchantStocks.add(merchantStock);
    }

    //Update Merchant Stock
    public boolean updateMerchantStock(int id ,MerchantStock merchantStock) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId() == id) {
                merchantStocks.set(i,merchantStock);
                return true;
            }
        }
        return false;
    }

    //Delete Merchant Stock
    public boolean deleteMerchantStock(int id) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId() == id) {
                merchantStocks.remove(i);
                return true;
            }
        }
        return false;
    }

    // Add Stock to Existing MerchantStock
    public boolean addStock(int productId, int merchantId, int additionalStock) {
        for (MerchantStock stock : merchantStocks) {
            if (stock.getProductId() == productId && stock.getMerchantId() == merchantId) {
                stock.setStock(stock.getStock() + additionalStock);
                return true;
            }
        }
        return false;
    }

    // Reduce Stock from Existing MerchantStock
    public boolean reduceStock(int productId, int merchantId, int quantity) {
        for (MerchantStock stock : merchantStocks) {
            if (stock.getProductId() == productId && stock.getMerchantId() == merchantId) {
                if (stock.getStock() >= quantity) {
                    stock.setStock(stock.getStock() - quantity);
                    return true;
                } else {
                    return false; // Not enough stock
                }
            }
        }
        return false; // Invalid product ID or merchant ID
    }


    // Find MerchantStock by Product and Merchant ID
    public MerchantStock findMerchantStock(int productId, int merchantId) {
        for (MerchantStock stock : merchantStocks) {
            if (stock.getProductId() == productId && stock.getMerchantId() == merchantId) {
                return stock;
            }
        }
        return null;
    }
}
