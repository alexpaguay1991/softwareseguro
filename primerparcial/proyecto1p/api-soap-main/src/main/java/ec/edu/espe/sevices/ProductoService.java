package ec.edu.espe.sevices;

import ec.edu.espe.gen.*;
import ec.edu.espe.model.ProductModel;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import java.util.List;

@WebService
public interface ProductoService {

    @WebMethod
    ProductModel crearProducto(ProductModel producto);

    @WebMethod
    ProductModel modificarProducto(Long id, ProductModel producto);  // Cambié el tipo a Long

    @WebMethod
    void eliminarProducto(Long id, String token);  // Cambié el tipo a Long

    @WebMethod
    ProductModel obtenerProductoPorId(Long id);  // Cambié el tipo a Long

    @WebMethod
    List<ProductModel> listarProductos();

    @WebMethod
    GetProductsResponse getProducts(String token);
    @WebMethod
    GetProductResponse getProduct(@RequestPayload Long id, String token);
    @WebMethod
    PostProductResponse postProduct(Product product, String token);
    @WebMethod
    PostProductResponse putProduct(Long id, Product product, String token);

}
