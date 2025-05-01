package com.example.superApp.payUrFren.dto;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class PayUrFrenUserDTO extends BaseUserDTO {
    private String defaultCurrency;
    private BigDecimal totalBalance;
    private String notificationPreference;
    private int groupCount;
}
