package com.VendingMachine.dao;

import com.VendingMachine.dto.VendingMachineItems;

import java.util.List;

public interface VendingMachineDao {

    List<VendingMachineItems> listAll()throws VendingMachinePersistenceException;

    VendingMachineItems updateQuantity (String item,VendingMachineItems Item)throws VendingMachinePersistenceException;

    void save() throws VendingMachinePersistenceException;

    VendingMachineItems getItem (String name) throws VendingMachinePersistenceException;


}
