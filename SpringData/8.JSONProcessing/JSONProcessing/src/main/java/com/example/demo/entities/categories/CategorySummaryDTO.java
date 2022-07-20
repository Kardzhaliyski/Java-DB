package com.example.demo.entities.categories;

import java.math.BigDecimal;

public class CategorySummaryDTO {
    private String name;
    private Long count;
    private Double averagePrice;
    private BigDecimal totalRevenue;

    public CategorySummaryDTO() {
    }

    public CategorySummaryDTO(String name, Long count, Double averagePrice, BigDecimal totalRevenue) {
        this.name = name;
        this.count = count;
        this.averagePrice = averagePrice;
        this.totalRevenue = totalRevenue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
