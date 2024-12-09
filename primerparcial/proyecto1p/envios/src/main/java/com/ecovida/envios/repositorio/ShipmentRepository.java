package com.ecovida.envios.repositorio;

import com.ecovida.envios.entidades.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    Optional<Shipment> findByTrackingNumber(String trackingNumber);

    Optional<Shipment> findByOrderId(long orderId);
}
