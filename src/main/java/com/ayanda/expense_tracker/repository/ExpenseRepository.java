package com.ayanda.expense_tracker.repository;

import com.ayanda.expense_tracker.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    Page<Expense> findByUserId(Long userId, Pageable pageable);
    List<Expense> findByUserIdAndDateBetween(Long userId, LocalDate from, LocalDate to);

}
