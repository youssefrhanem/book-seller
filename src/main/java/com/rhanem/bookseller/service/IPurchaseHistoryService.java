package com.rhanem.bookseller.service;

import com.rhanem.bookseller.model.PurchaseHistory;
import com.rhanem.bookseller.repository.projection.IPurchaseItem;

import java.util.List;

public interface IPurchaseHistoryService {
    PurchaseHistory savePurchaseHistory(PurchaseHistory purchaseHistory);

    List<IPurchaseItem> finPurchaseItemOfUser(Long userId);
}
