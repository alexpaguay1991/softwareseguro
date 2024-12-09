package com.ecovida.envios.servicio;

import com.ecovida.envios.entidades.Shipment;
import com.ecovida.envios.entidades.ShipmentStatus;
import com.ecovida.envios.excepciones.ResourceNotFoundException;
import com.ecovida.envios.repositorio.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    // Método para crear un nuevo envío
    public Shipment createShipment(Shipment shipment) {
        // Asegurarse de que se tenga un estado válido (puede ser un valor predeterminado)
        if (shipment.getStatus() == null) {
            shipment.setStatus(ShipmentStatus.in_transit); // Estado predeterminado
        }
        return shipmentRepository.save(shipment);
    }

    // Método para obtener un envío por tracking number
    public Optional<Shipment> getShipmentByTrackingNumber(String trackingNumber) {
        return shipmentRepository.findByTrackingNumber(trackingNumber);
    }

    // Método para obtener el estado de un envío por Order ID
    public Optional<Shipment> getShipmentByOrderId(long orderId) {
        return shipmentRepository.findByOrderId(orderId);  // Asegúrate que esto esté bien en tu repositorio
    }

    // Método para actualizar el estado de un envío
    public Shipment updateShipmentStatus(long shipmentId, ShipmentStatus newStatus) {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Envío", "id", shipmentId));

        shipment.setStatus(newStatus);  // Usamos el Enum para el nuevo estado
        return shipmentRepository.save(shipment);
    }
}
