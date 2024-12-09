package ec.edu.espe.server;

import ec.edu.espe.services.impl.ProductoServiceImpl;
import jakarta.xml.ws.Endpoint;


public class ServerWS {

    public static void main(String[] args) {



        Endpoint.publish("http://localhost:8080/ProductoService", new ProductoServiceImpl());

    }
}
