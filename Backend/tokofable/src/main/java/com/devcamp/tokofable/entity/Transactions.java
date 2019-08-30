package com.devcamp.tokofable.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Transactions {
    @Id
    private String id ;
    @ManyToOne
    private Users user ;
    private Date date ;
    private String noTransaction ;
    private Double weight ;
    private Boolean transactionType ;
    private Double price ;
    @ManyToOne
    private PaymentMethods paymentMethods ;

    public Transactions() {
    }

    public Transactions(String id, Users user, Date date, String noTransaction, Double weight, Boolean transactionType, Double price, PaymentMethods paymentMethods) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.noTransaction = noTransaction;
        this.weight = weight;
        this.transactionType = transactionType;
        this.price = price;
        this.paymentMethods = paymentMethods;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNoTransaction() {
        return noTransaction;
    }

    public void setNoTransaction(String noTransaction) {
        this.noTransaction = noTransaction;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Boolean getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(Boolean transactionType) {
        this.transactionType = transactionType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public PaymentMethods getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(PaymentMethods paymentMethods) {
        this.paymentMethods = paymentMethods;
    }
}
