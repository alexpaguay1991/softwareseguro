package ec.edu.espe.services.impl;

import ec.edu.espe.converter.ProductConverter;
import ec.edu.espe.gen.*;
import ec.edu.espe.model.CategoryModel;
import ec.edu.espe.model.ProductModel;
import ec.edu.espe.repository.ProductRepository;
import ec.edu.espe.sevices.ProductoService;
import ec.edu.espe.utils.JWTUtil;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface = "ec.edu.espe.sevices.ProductoService")
@Service
public class ProductoServiceImpl implements ProductoService {

    private static final String NAMESPACE_URI = "http://www.ecovida.com/soapinventario/gen";
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");

    // Método para validar el token JWT
    private boolean validarToken(String token)  {
        JWTUtil.validarToken(token); // Si el token es válido, no se lanza excepción
        return true;
    }

    @Override
    public GetProductResponse getProduct(Long id, String token) {
        if (!validarToken(token)) {
            throw new SecurityException("Token inválido o expirado");
        }

        EntityManager em = emf.createEntityManager();
        ProductModel producto = em.find(ProductModel.class, id);
        GetProductResponse response = new GetProductResponse();
        response.setProduct(convertProductModelToProduct(producto));
        return response;
    }

    @Override
    public PostProductResponse postProduct(Product product, String token) {
        if (!validarToken(token)) {
            throw new SecurityException("Token inválido o expirado");
        }

        ProductModel productModel = convertProductToProductModel(product);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        if (productModel.getId() == null) {
            em.persist(productModel);  // Guardar el nuevo producto
        } else {
            em.merge(productModel);  // Actualizar la entidad existente
        }

        em.getTransaction().commit();
        em.close();

        PostProductResponse postProductResponse = new PostProductResponse();
        postProductResponse.setProduct(product);
        return postProductResponse;
    }

    @Override
    public PostProductResponse putProduct(Long id, Product product, String token) {
        if (!validarToken(token)) {
            throw new SecurityException("Token inválido o expirado");
        }

        ProductModel productModel = convertProductToProductModel(product);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        ProductModel existingProduct = em.find(ProductModel.class, id);
        if (existingProduct != null) {
            existingProduct.setName(productModel.getName());
            existingProduct.setDescription(productModel.getDescription());
            existingProduct.setPrice(productModel.getPrice());
            existingProduct.setStockQuantity(productModel.getStockQuantity());
            existingProduct.setCategory(productModel.getCategory());
            em.merge(existingProduct);
        } else {
            em.getTransaction().rollback();
            em.close();
            throw new EntityNotFoundException("Producto con ID " + id + " no encontrado.");
        }

        em.getTransaction().commit();
        em.close();

        PostProductResponse postProductResponse = new PostProductResponse();
        postProductResponse.setProduct(product);
        return postProductResponse;
    }

    @Override
    public void eliminarProducto(Long id, String token) {
        if (!validarToken(token)) {
            throw new SecurityException("Token inválido o expirado");
        }

        EntityManager em = emf.createEntityManager();
        ProductModel p = em.find(ProductModel.class, id);
        if (p != null) {
            em.getTransaction().begin();
            em.remove(p);  // Eliminar el producto
            em.getTransaction().commit();
        }
        em.close();
    }

    @Override
    public ProductModel obtenerProductoPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        ProductModel producto = em.find(ProductModel.class, id);
        em.close();
        return producto;
    }

    @Override
    public List<ProductModel> listarProductos() {
        EntityManager em = emf.createEntityManager();
        List<ProductModel> productos = em.createQuery("SELECT p FROM ProductModel p", ProductModel.class).getResultList();
        em.close();
        return productos;
    }

    @Override
    @WebMethod
    public GetProductsResponse getProducts(String token) {
        if (!validarToken(token)) {
            throw new SecurityException("Token inválido o expirado");
        }

        EntityManager em = emf.createEntityManager();
        List<ProductModel> productos = em.createQuery("SELECT p FROM ProductModel p", ProductModel.class).getResultList();
        GetProductsResponse getProductsResponse = new GetProductsResponse();
        getProductsResponse.setProducts(convertProductModelsToProducts(productos));
        em.close();

        return getProductsResponse;
    }

    // Métodos de conversión (sin cambios)
    public ProductModel convertProductToProductModel(Product product) {
        ProductModel productModel = new ProductModel();
        productModel.setId(product.getId());
        productModel.setName(product.getName());
        productModel.setDescription(product.getDescription());
        productModel.setPrice(product.getPrice());
        productModel.setImageUrl(product.getImageUrl());
        productModel.setStockQuantity(product.getStockQuantity());

        if (product.getCategory() != null) {
            CategoryModel categoryModel = convertCategoryToCategoryModel(product.getCategory());
            productModel.setCategory(categoryModel);
        }

        return productModel;
    }

    public Product convertProductModelToProduct(ProductModel productModel) {
        Product product = new Product();
        product.setId(productModel.getId());
        product.setName(productModel.getName());
        product.setDescription(productModel.getDescription());
        product.setPrice(productModel.getPrice());
        product.setImageUrl(productModel.getImageUrl());
        product.setStockQuantity(productModel.getStockQuantity());

        if (productModel.getCategory() != null) {
            Category category = convertCategoryModelToCategory(productModel.getCategory());
            product.setCategory(category);
        }

        return product;
    }

    public List<ProductModel> convertProductsToProductModels(List<Product> products) {
        List<ProductModel> productModels = new ArrayList<>();
        for (Product product : products) {
            productModels.add(convertProductToProductModel(product));
        }
        return productModels;
    }

    public List<Product> convertProductModelsToProducts(List<ProductModel> productModels) {
        List<Product> products = new ArrayList<>();
        for (ProductModel productModel : productModels) {
            products.add(convertProductModelToProduct(productModel));
        }
        return products;
    }

    private CategoryModel convertCategoryToCategoryModel(Category category) {
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setId(category.getId());
        categoryModel.setName(category.getName());
        categoryModel.setDescription(category.getDescription());
        return categoryModel;
    }

    private Category convertCategoryModelToCategory(CategoryModel categoryModel) {
        Category category = new Category();
        category.setId(categoryModel.getId());
        category.setName(categoryModel.getName());
        category.setDescription(categoryModel.getDescription());
        return category;
    }
    @Override
    public ProductModel crearProducto(ProductModel producto) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(producto);  // Guardar el nuevo producto
        em.getTransaction().commit();
        em.close();
        return producto;
    }

    @Override
    public ProductModel modificarProducto(Long id, ProductModel producto) {  // Cambiar tipo de id a Long
        EntityManager em = emf.createEntityManager();
        ProductModel p = em.find(ProductModel.class, id);  // Buscar producto por ID
        if (p != null) {
            em.getTransaction().begin();
            p.setName(producto.getName());
            p.setDescription(producto.getDescription());
            p.setPrice(producto.getPrice());
            p.setStockQuantity(producto.getStockQuantity());
            p.setCategory(producto.getCategory());  // Corregir el nombre de la propiedad
            em.getTransaction().commit();
        }
        em.close();
        return p;
    }
}
