package com.complete.todayspace.domain.wish.controller;

import com.complete.todayspace.domain.product.dto.ProductResponseDto;
import com.complete.todayspace.domain.wish.service.WishService;
import com.complete.todayspace.global.dto.DataResponseDto;
import com.complete.todayspace.global.dto.StatusResponseDto;
import com.complete.todayspace.global.entity.SuccessCode;
import com.complete.todayspace.global.security.UserDetailsImpl;
import com.complete.todayspace.global.valid.PageValidation;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class WishController {

    private final WishService wishService;

    @PostMapping("/products/{productsId}/wish")
    public ResponseEntity<StatusResponseDto> toggleWish(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @PathVariable @Min(1) Long productsId
    ) {
        boolean isWish = wishService.toggleWish(userDetails.getUser(), productsId);

        StatusResponseDto response;
        HttpStatus status;

        if (isWish) {
            response = new StatusResponseDto(SuccessCode.PRODUCTS_WISHS);
            status = HttpStatus.CREATED;
        } else {
            response = new StatusResponseDto(SuccessCode.PRODUCTS_WISHS_DELETE);
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(response, status);
    }

    @GetMapping("/my/wishs")
    public ResponseEntity<DataResponseDto<Page<ProductResponseDto>>> getMyWishList(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam Map<String, String> params
    ) {

        int page = PageValidation.pageValidationInParams(params);

        Page<ProductResponseDto> responseDto = wishService.getMyWishList(userDetails.getUser().getId(), page - 1);

        DataResponseDto<Page<ProductResponseDto>> dataResponseDto = new DataResponseDto<>(SuccessCode.PROFILE_WISHS_GET, responseDto);

        return new ResponseEntity<>(dataResponseDto, HttpStatus.OK);
    }

}
