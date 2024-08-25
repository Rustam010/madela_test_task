package com.konkov.CarApp.repositories;

import com.konkov.CarApp.entity.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Integer> {
    List<Subscriber> findAllByModel(String model);
    boolean existsByEmailAndModel(String email, String model);

}
