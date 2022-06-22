package com.cha.serverproductmanagement.model;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class ProductPage {

    private int PageNumber = 0;
    private int PageSize = 3;
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    private String sortBy = "name";
}
