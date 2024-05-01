package com.project.shopappbackend.services;


import com.project.shopappbackend.dtos.ProductDTO;
import com.project.shopappbackend.dtos.ProductImageDTO;
import com.project.shopappbackend.models.Product;
import com.project.shopappbackend.models.ProductImage;
import com.project.shopappbackend.responses.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IProductService {
    Product createProduct(ProductDTO productDTO) throws Exception;
    Product getProductById(long id) throws Exception;
    Page<ProductResponse> getAllProducts(PageRequest pageRequest);
    Product updateProduct(long id, ProductDTO productDTO) throws Exception;
    void deleteProduct(long id);
    boolean existsByName(String name);
    ProductImage createProductImage(
            Long productId,
            ProductImageDTO productImageDTO) throws Exception;

}
