package com.ayanda.expense_tracker.exception;

import java.time.LocalDateTime;

public record ErrorResponse(

    LocalDateTime timestamp,
    int status,
    String message
) {}
