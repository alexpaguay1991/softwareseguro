package ec.edu.espe.converter;



import ec.edu.espe.model.CategoryModel;
import ec.edu.espe.model.ProductModel;
import ec.edu.espe.gen.*;

import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class ProductConverter {

    // Convertir de Product a ProductModel
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

    // Convertir de ProductModel a Product
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

    // Convertir lista de Product a ProductModel
    public List<ProductModel> convertProductsToProductModels(List<Product> products) {
        List<ProductModel> productModels = new ArrayList<>();
        for (Product product : products) {
            productModels.add(convertProductToProductModel(product));
        }
        return productModels;
    }

    // Convertir lista de ProductModel a Product
    public List<Product> convertProductModelsToProducts(List<ProductModel> productModels) {
        List<Product> products = new ArrayList<>();
        for (ProductModel productModel : productModels) {
            products.add(convertProductModelToProduct(productModel));
        }
        return products;
    }

    // Convertir de Category a CategoryModel
    private CategoryModel convertCategoryToCategoryModel(Category category) {
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setId(category.getId());
        categoryModel.setName(category.getName());
        categoryModel.setDescription(category.getDescription());
        return categoryModel;
    }

    // Convertir de CategoryModel a Category
    private Category convertCategoryModelToCategory(CategoryModel categoryModel) {
        Category category = new Category();
        category.setId(categoryModel.getId());
        category.setName(categoryModel.getName());
        category.setDescription(categoryModel.getDescription());
        return category;
    }
}
