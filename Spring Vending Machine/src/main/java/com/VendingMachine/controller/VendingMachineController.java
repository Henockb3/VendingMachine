package com.VendingMachine.controller;

import com.VendingMachine.dao.*;
import com.VendingMachine.dto.Coins;
import com.VendingMachine.dto.VendingMachineItems;
import com.VendingMachine.service.VendingMachineService;
import com.VendingMachine.service.VendingMachineServiceImpl;
import com.VendingMachine.ui.UserIO;
import com.VendingMachine.ui.UserIOConsoleImpl;
import com.VendingMachine.ui.VendingMachineView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
@Component
public class VendingMachineController {

    VendingMachineView vendingMachineView = new VendingMachineView();
    VendingMachineService vendingMachineService = new VendingMachineServiceImpl();
    VendingMachineDao vendingMachineDao = new VendingMachineDaoImpl();
    private VendingMachineView view;
    private VendingMachineDao dao;
@Autowired
    public VendingMachineController(VendingMachineView view, VendingMachineDao dao) {
        this.view = view;
        this.dao = dao;
    }

    private UserIO io = new UserIOConsoleImpl();


    public void run(){
        boolean keepGoing = true;
        String menuSelection = "";
        try{

            List<VendingMachineItems>allItems = allItems();
            inputMoney();

            while (keepGoing){
                try {

                    menuSelection = getMenuSelection();
                    VendingMachineItems Item = getItem(menuSelection);
                    if (allItems.contains(Item)) {
                        Sell(menuSelection);
                    } else if (menuSelection.equalsIgnoreCase("Exit")) {
                        Exit();
                        keepGoing = false;
                    } else {
                        io.print("Unknown Command");
                    }
                } catch
                (VendingMachinePersistenceException|NoItemInventoryException e) {

                    vendingMachineView.displayErrorMessage(e.getMessage());
                }catch (InsufficientFundsException e){
                    vendingMachineView.displayErrorMessage(e.getMessage());
                    inputMoney();
                }


            }

        } catch (VendingMachinePersistenceException e) {
            vendingMachineView.displayErrorMessage(e.getMessage());
        }

    }
    private void inputMoney(){
        String input = vendingMachineView.putMoney();
        vendingMachineService.inputMoney(input);

    }

    private void Sell(String Name) throws VendingMachinePersistenceException, NoItemInventoryException, InsufficientFundsException {
        VendingMachineItems choice = vendingMachineService.selectedItem(Name);
        vendingMachineView.displaySelection(choice);

    }
    private VendingMachineItems getItem(String Name)throws VendingMachinePersistenceException{

        return vendingMachineDao.getItem(Name);
    }

    private List<VendingMachineItems> allItems() throws VendingMachinePersistenceException {

        return vendingMachineService.listAll();
    }


    private String getMenuSelection() throws VendingMachinePersistenceException {
        List<VendingMachineItems>allItems = vendingMachineDao.listAll();

        return vendingMachineView.printMenuAndGetSelection(allItems);
    }
    private void Exit()  {
        BigDecimal totalMoney = vendingMachineService.totalMoney();
        Map<Coins,Integer> change = vendingMachineService.getChange();

        vendingMachineView.displayExit(change,totalMoney);
    }



}
