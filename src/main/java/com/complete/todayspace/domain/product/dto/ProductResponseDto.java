package com.complete.todayspace.domain.product.dto;

import com.complete.todayspace.domain.product.entity.Address;
import com.complete.todayspace.domain.product.entity.Product;
import com.complete.todayspace.domain.product.entity.State;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ProductResponseDto {

    private final Long id;
    private final Long price;
    private final String title;
    private final String content;
    private final Address address;
    private final State state;
    private final LocalDateTime upDateAt;

    public ProductResponseDto(Long id, Long price, String title, String content, Address address,
        State state, LocalDateTime upDateAt) {
        this.id = id;
        this.price = price;
        this.title = title;
        this.content = content;
        this.address = address;
        this.state = state;
        this.upDateAt = upDateAt;
    }
}
