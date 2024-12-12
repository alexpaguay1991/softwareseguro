package com.ecovida.soapinventario;

import com.ecovida.soapinventario.endpoint.ProductEndpoint;
import jakarta.xml.ws.Endpoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SoapinventarioApplication {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8089/ProductoService", new ProductEndpoint());
	}

}
