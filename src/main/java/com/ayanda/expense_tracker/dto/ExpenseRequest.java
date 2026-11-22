package com.ayanda.expense_tracker.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseRequest(

    @NotBlank(message = "The description cannot be empty")
    @Size(max = 255, message = "Not more than 255 characters are allowed")
    String description,

    @NotNull(message = "The amount must not be empty")
    @Positive(message = "Only positive amounts are allowed") 
    @Digits(integer = 10, fraction = 2, message = "At most 10 digits before the decimal point and at most 2 after it")
    BigDecimal amount,

    @NotNull(message = "The date must not be empty")
    LocalDate date,

    Long categoryId


) {}
