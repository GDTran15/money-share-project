package com.share_money.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "share_requests")
@Builder
public class ShareRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long shareRequestId;

    private ShareRequestStatus shareRequestStatus;

    private double amountToPay;

    private ShareRequestAnswer shareRequestAnswer;

    @ManyToOne()
    @JoinColumn(name = "owner_id")
    private User shareOwner;

    @ManyToOne()
    @JoinColumn(name = "expense_receiver_id")
    private User shareReceiver;


    @ManyToOne()
    @JoinColumn(name = "expense")
    private Expense  expense;



}
