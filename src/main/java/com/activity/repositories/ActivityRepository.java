package com.activity.repositories;

import com.activity.entities.Activity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long>, JpaSpecificationExecutor<Activity> {
    @Query(value = "SELECT * FROM activity WHERE id_document=?1", nativeQuery = true)
    Optional<Activity> findByIdDocument(String idDocument);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE activity  \n" +
            "SET id_document =?2 , full_name=?3, client_full_name=?4, client_phone=?5, amount=?6, \n" +
            "customer_payment=?7, description=?8, note=?9, status=?10 WHERE id=?1 ")
    void update(Long id, String idDocument, String fullName, String clientFullName, String clientPhone,
            Double amount, Double customerPayment, String description, String note, String status);
}
