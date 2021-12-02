package com.VendingMachine.service;

import com.VendingMachine.dao.InsufficientFundsException;
import com.VendingMachine.dao.NoItemInventoryException;
import com.VendingMachine.dao.VendingMachinePersistenceException;
import com.VendingMachine.dto.Coins;
import com.VendingMachine.dto.VendingMachineItems;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface VendingMachineService {
    List<VendingMachineItems> listAll () throws VendingMachinePersistenceException;

    VendingMachineItems selectedItem (String Name) throws VendingMachinePersistenceException, InsufficientFundsException, NoItemInventoryException;

    void inputMoney(String Money);

    Map<Coins,Integer> getChange();

    BigDecimal totalMoney();



}
