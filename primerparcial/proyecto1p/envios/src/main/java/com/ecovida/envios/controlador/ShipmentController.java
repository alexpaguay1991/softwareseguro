package com.ecovida.envios.controlador;

import com.ecovida.envios.entidades.Shipment;
import com.ecovida.envios.entidades.ShipmentStatus;
import com.ecovida.envios.servicio.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    // Endpoint para crear un nuevo envío
    @PreAuthorize("hasRole('admin')")  // Solo los administradores pueden crear envíos
    @PostMapping
    public ResponseEntity<Shipment> createShipment(@RequestBody Shipment shipment) {
        Shipment createdShipment = shipmentService.createShipment(shipment);
        return new ResponseEntity<>(createdShipment, HttpStatus.CREATED);
    }

    // Endpoint para obtener un envío por tracking number
    @PreAuthorize("hasAnyRole('admin', 'customer')")  // ADMIN y USER pueden acceder a los envíos por tracking number
    @GetMapping("/tracking/{trackingNumber}")
    public ResponseEntity<Shipment> getShipmentByTrackingNumber(@PathVariable String trackingNumber) {
        Optional<Shipment> shipment = shipmentService.getShipmentByTrackingNumber(trackingNumber);
        return shipment.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para obtener el estado de un envío por orderId
    @PreAuthorize("hasAnyRole('admin', 'customer')")  // ADMIN y USER pueden acceder a los envíos por orderId
    @GetMapping("/order/{orderId}")
    public ResponseEntity<Shipment> getShipmentByOrderId(@PathVariable long orderId) {
        Optional<Shipment> shipment = shipmentService.getShipmentByOrderId(orderId);
        return shipment.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('admin')")
    @PutMapping("/status/{shipmentId}/{shipmentStatus}")
    public ResponseEntity<Shipment> updateShipmentStatus(
            @PathVariable long shipmentId,
            @PathVariable String shipmentStatus) {
        try {
            // Convertir la cadena a ShipmentStatus (enum)
            ShipmentStatus status = ShipmentStatus.valueOf(shipmentStatus.toLowerCase());
            Shipment updatedShipment = shipmentService.updateShipmentStatus(shipmentId, status);
            return new ResponseEntity<>(updatedShipment, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
