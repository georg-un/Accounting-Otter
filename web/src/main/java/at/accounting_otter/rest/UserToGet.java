package at.accounting_otter.rest;

import lombok.Data;

@Data
public class UserToGet {

    private int userId;
    private String username;
    private double sumDebitAmounts;

}
