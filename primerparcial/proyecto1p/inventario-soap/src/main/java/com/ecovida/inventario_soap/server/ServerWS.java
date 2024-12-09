package com.ecovida.inventario_soap.server;


import com.ecovida.inventario_soap.impl.ProductServiceImpl;
import jakarta.xml.ws.Endpoint;

public class ServerWS {

    public static void main(String[] args) {

        String urlSuscripcion = "http://0.0.0.0:8088/suscripcion";


        Endpoint.publish(urlSuscripcion, new ProductServiceImpl());
        System.out.println("Server running on: "  + urlSuscripcion);
    }
}