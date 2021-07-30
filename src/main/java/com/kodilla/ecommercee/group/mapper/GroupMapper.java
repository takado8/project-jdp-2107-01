package com.kodilla.ecommercee.group.mapper;

import com.kodilla.ecommercee.group.domain.Group;
import com.kodilla.ecommercee.group.domain.GroupDto;
import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.repository.ProductDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupMapper {
    ProductDao productDao;

    public GroupDto mapGroupToDto(Group group) {
        return new GroupDto(group.getId(), group.getName(),
                group.getProducts()
                        .stream()
                        .map(Product::getId)
                        .collect(Collectors.toList()));
    }

    public Group mapDtoToGroup(GroupDto dto) {
        return new Group(dto.getId(), dto.getName(),
                dto.getProductsIds()
                        .stream()
                        .map(productId -> productDao.findById(productId))
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.toList()));
    }

    public List<GroupDto> mapGroupListToDtoList(List<Group> groups) {
        return groups.stream().map(this::mapGroupToDto).collect(Collectors.toList());
    }
}
