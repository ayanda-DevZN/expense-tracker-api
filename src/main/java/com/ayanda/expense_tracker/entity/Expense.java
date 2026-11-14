package com.ayanda.expense_tracker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity 
@Table(name = "expenses")
public class Expense {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;

    @Column(name = "expense_date", nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY, optional = false) 
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Long getId(){
        return id;
    }

    public String getDescription(){
        return description;
    }

    public BigDecimal getAmount(){
        return amount;
    }

    public LocalDate getDate(){
        return date;
    }

    public Category getCategory(){
        return category;
    }

    public User getUser(){
        return user;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setAmount(BigDecimal amount){
        this.amount = amount;
    }
    
    public void setDate(LocalDate date){
        this.date = date;
    }

    public void setCategory(Category category){
        this.category = category;
    }

    public void setUser(User user){
        this.user = user;
    }

}
