package com.rhanem.bookseller.service;


import com.rhanem.bookseller.model.PurchaseHistory;
import com.rhanem.bookseller.repository.IPurchaseHistoryRepository;
import com.rhanem.bookseller.repository.projection.IPurchaseItem;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class IPurchaseHistoryServiceImpl implements IPurchaseHistoryService {

    private IPurchaseHistoryRepository purchaseHistoryRepository;

    @Override
    public PurchaseHistory savePurchaseHistory(PurchaseHistory purchaseHistory) {
        purchaseHistory.setPurchaseTime(LocalDateTime.now());
        return purchaseHistoryRepository.save(purchaseHistory);
    }

    @Override
    public List<IPurchaseItem> finPurchaseItemOfUser(Long userId){
        return purchaseHistoryRepository.findAllPurchasesOfUser(userId);
    }
}
