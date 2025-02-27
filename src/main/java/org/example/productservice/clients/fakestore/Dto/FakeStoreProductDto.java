package org.example.productservice.clients.fakestore.Dto;

import org.example.productservice.dtos.RatingDto;
import org.example.productservice.clients.fakestore.DummyStore.IClientProductDto;
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
