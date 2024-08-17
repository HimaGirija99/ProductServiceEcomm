package com.example.eCommerceSite.clients.fakestore.Dto;

import com.example.eCommerceSite.dtos.RatingDto;
import com.example.eCommerceSite.clients.fakestore.IClientProductDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FakeStoreProductDto implements IClientProductDto {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;
    private RatingDto rating;
}
