package com.example.demo.repository;

import com.example.demo.model.InventoryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InventoryTransactionRepository extends JpaRepository<InventoryTransaction, Long> {

    /**
     * Tổng số lượng của tất cả giao dịch có type (ví dụ "IN" hoặc "OUT")
     */
    @Query("SELECT COALESCE(SUM(tx.quantity), 0) "
         + "FROM InventoryTransaction tx "
         + "WHERE tx.type = :type")
    Long sumQuantityByType(@Param("type") String type);

    /**
     * Tổng số lượng của giao dịch theo type và theo 1 sản phẩm cụ thể
     */
    @Query("SELECT COALESCE(SUM(tx.quantity), 0) "
         + "FROM InventoryTransaction tx "
         + "WHERE tx.type = :type "
         + "  AND tx.product.id = :pid")
    Long sumQuantityByTypeAndProduct(@Param("type") String type,
                                     @Param("pid") Long productId);
}
