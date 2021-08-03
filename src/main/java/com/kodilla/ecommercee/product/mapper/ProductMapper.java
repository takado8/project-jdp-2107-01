package com.kodilla.ecommercee.product.mapper;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.cart.repository.CartDao;
import com.kodilla.ecommercee.group.repository.GroupDao;
import com.kodilla.ecommercee.order.domain.Order;
import com.kodilla.ecommercee.order.repository.OrderDao;
import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.domain.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductMapper {

    private final GroupDao groupDao;
    private final OrderDao orderDao;
    private final CartDao cartDao;

    public Product mapToProduct(final ProductDto productDto) {
        return new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                groupDao.findById(productDto.getGroupId())
                        .orElseThrow(() -> new RuntimeException("Group of id '" + productDto.getGroupId() + "' does not exist")),
                productDto.getOrdersId().stream()
                        .map(orderDao::findById)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.toList()),
                productDto.getCartsId().stream()
                        .map(cartDao::findById)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.toList())
        );
    }

    public ProductDto mapToProductDto(final Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getGroupId().getId(),
                product.getOrders().stream()
                        .map(Order::getId)
                        .collect(Collectors.toList()),
                product.getCarts().stream()
                        .map(Cart::getId)
                        .collect(Collectors.toList())
        );
    }

    public List<ProductDto> mapToProductDtoList(final List<Product> productList) {
        return productList.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }
}

