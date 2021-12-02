package com.VendingMachine.dao;

import com.VendingMachine.dto.Coins;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class Changes {
    private Map<Coins,Integer> coin = new HashMap<>();


    public BigDecimal calculateQuarterChange(BigDecimal Change){

        int count = 0;
        count=Change.divide(new BigDecimal("0.25"),0, RoundingMode.DOWN).intValue();

        Change = Change.remainder(new BigDecimal("0.25"));
        coin.put(Coins.QUARTER,count);
        return Change;

    }
    public BigDecimal calculateNickelChange(BigDecimal Change){

        int count = 0;
        count=Change.divide(new BigDecimal("0.05"),0, RoundingMode.DOWN).intValue();


        Change = Change.remainder((new BigDecimal("0.05")));
        coin.put(Coins.NICKEL,count);
        return Change;

    }
    public BigDecimal calculateDimeChange(BigDecimal Change){

        int count = 0;
        count=Change.divide(new BigDecimal("0.10"),0, RoundingMode.DOWN).intValue();


        Change = Change.remainder(new BigDecimal("0.10"));
        coin.put(Coins.DIME,count);
        return Change;

    }
    public BigDecimal calculatePennyChange(BigDecimal Change){

        int count = 0;
        count=Change.divide(new BigDecimal("0.01"),0, RoundingMode.DOWN).intValue();


        Change = Change.remainder((new BigDecimal("0.01")));
        coin.put(Coins.PENNY,count);
        return Change;

    }

   public Map<Coins,Integer>calculateChanges(BigDecimal Change){

        Change = calculateQuarterChange(Change);
        Change = calculateDimeChange(Change);
        Change = calculateNickelChange(Change);
        Change = calculatePennyChange(Change);



        return coin;
   }




}
