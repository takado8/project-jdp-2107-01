package com.kodilla.ecommercee.product.mapper;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.cart.repository.CartDao;
import com.kodilla.ecommercee.group.repository.GroupDao;
import com.kodilla.ecommercee.order.domain.Order;
import com.kodilla.ecommercee.order.repository.OrderDao;
import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.domain.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ProductMapper {

    private GroupDao groupDao;

    @Autowired
    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    private OrderDao orderDao;

    @Autowired
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    private CartDao cartDao;

    @Autowired
    public void setCartDao(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    public Product mapToProduct(final ProductDto productDto) {
        return new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                groupDao.getOne(productDto.getGroupId()),
                productDto.getOrdersId().stream()
                        .map(orderDao::getOne)
                        .collect(Collectors.toList()),
                productDto.getCartsId().stream()
                        .map(cartDao::getOne)
                        .collect(Collectors.toList())
        );
    }

    public ProductDto mapToProductDto(Product product) {
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
}
