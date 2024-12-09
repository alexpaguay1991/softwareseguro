package com.ecovida.inventario.server;

//import com.ecovida.inventario.impl.ProductoServiceImpl;
//import com.ecovida.inventario.repository.ProductoRepository;
import jakarta.xml.ws.Endpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ecovida")
public class ServerWS {

    public static void main(String[] args) {


        String url = "http://localhost:8088/ws/inventario"; // URL del servicio SOAP
        //Endpoint.publish(url, new ProductoServiceImpl());
        System.out.println("Servicio SOAP publicado en: " + url);
    }
}



