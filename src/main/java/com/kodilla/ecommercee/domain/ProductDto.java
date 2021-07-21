package com.kodilla.ecommercee.domain;

public class ProductDto {

    private Integer id;
    private String name;
    private String description;
    private Integer price;
    private Integer groupId;

    public ProductDto(Integer id, String name, String description, Integer price, Integer groupId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.groupId = groupId;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getGroupId() {
        return groupId;
    }
}
