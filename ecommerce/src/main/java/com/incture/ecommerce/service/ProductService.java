package com.incture.ecommerce.service;

import com.incture.ecommerce.dto.ProductDTO;
import java.util.List;
import org.springframework.data.domain.Page;

public interface ProductService {

    ProductDTO addProduct(ProductDTO productDTO);

    List<ProductDTO> getAllProducts();

    ProductDTO getProductById(Long id);

    ProductDTO updateProduct(Long id, ProductDTO productDTO);

    void deleteProduct(Long id);

    Page<ProductDTO> getProductsWithPagination(int page, int size);
}