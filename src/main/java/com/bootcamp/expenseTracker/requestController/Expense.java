package com.bootcamp.expenseTracker.requestController;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class Expense {
    private float amount;
    private Date date;
    private String id;
}
