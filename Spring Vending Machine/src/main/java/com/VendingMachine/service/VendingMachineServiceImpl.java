package com.VendingMachine.service;

import com.VendingMachine.dao.*;
import com.VendingMachine.dto.Coins;
import com.VendingMachine.dto.VendingMachineItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
@Component
public class VendingMachineServiceImpl implements VendingMachineService{
@Autowired
    private VendingMachineAuditDao vendingMachineAuditDao = new VendingMachineAuditDaoFileImpl() ;
    private BigDecimal money = new BigDecimal("0.00");
    private VendingMachineDao vendingMachineDao = new VendingMachineDaoImpl();


    @Override
    public List<VendingMachineItems> listAll() throws VendingMachinePersistenceException {

        return vendingMachineDao.listAll();
    }

    @Override
    public VendingMachineItems selectedItem(String Name) throws VendingMachinePersistenceException,InsufficientFundsException,NoItemInventoryException {

        VendingMachineItems choice = vendingMachineDao.getItem(Name);

        if(choice == null){
            throw new NoItemInventoryException("No such Item");
        }
        if(choice.getNumberOfInventoryItems() < 1){
            throw new NoItemInventoryException("Sold out");
        }

        if((money).compareTo(choice.getItemCost()) < 0){
            throw new InsufficientFundsException("Insufficient funds");
        }

        money = money.subtract(choice.getItemCost());
        choice.setNumberOfInventoryItems(choice.getNumberOfInventoryItems()-1);

        vendingMachineDao.updateQuantity(Name,choice);

        vendingMachineAuditDao.writeAuditEntry("Item " + choice.getItemName() + " has been updated");
        return choice;
    }

    @Override
    public void inputMoney(String Money) {
        this.money = this.money.add(new BigDecimal(Money));
    }

    @Override
    public Map<Coins, Integer> getChange()  {
        Changes change = new Changes();
        return change.calculateChanges(money) ;
    }

    @Override
    public BigDecimal totalMoney() {
        return money;
    }
}
