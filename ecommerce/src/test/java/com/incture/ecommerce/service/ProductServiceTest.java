package com.incture.ecommerce.service;

import com.incture.ecommerce.dto.ProductDTO;
import com.incture.ecommerce.entity.Product;
import com.incture.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void testGetAllProducts() {

        Product p1 = Product.builder()
                .id(1L)
                .name("Laptop")
                .description("Gaming Laptop")
                .price(50000.0)
                .stock(10)
                .category("Electronics")
                .imageUrl("laptop.jpg")
                .rating(4.5)
                .build();

        Product p2 = Product.builder()
                .id(2L)
                .name("Mobile")
                .description("Android Phone")
                .price(20000.0)
                .stock(20)
                .category("Electronics")
                .imageUrl("mobile.jpg")
                .rating(4.2)
                .build();

        when(productRepository.findAll())
                .thenReturn(List.of(p1,p2));
        when(modelMapper.map(any(Product.class), eq(ProductDTO.class)))
                .thenReturn(new ProductDTO());

        List<ProductDTO> products = productService.getAllProducts();

        assertEquals(2, products.size());
        verify(productRepository, times(1)).findAll();
    }
}