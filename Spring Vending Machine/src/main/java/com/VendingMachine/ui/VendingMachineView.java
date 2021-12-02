package com.VendingMachine.ui;

import com.VendingMachine.dto.Coins;
import com.VendingMachine.dto.VendingMachineItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
@Component
public class VendingMachineView {
@Autowired
    private UserIO io = new UserIOConsoleImpl();


    public String printMenuAndGetSelection(List<VendingMachineItems> items) {
        io.print("Main Menu");
        for(int i=0;i<items.size();i++) {

            if(items.get(i).getNumberOfInventoryItems()>0) {
                io.print((i + 1) + ". " + items.get(i).getItemName() + "---" + items.get(i).getItemCost() + "---" + items.get(i).getNumberOfInventoryItems());
            }
        }

        io.print((items.size()+1)+". Exit");
     return io.readString("Please select from the above choices.");

    }

    public void displaySelection(VendingMachineItems item){
        io.print("Selected Item "+item.getItemName()+"---"+" Price: "+item.getItemCost());
    }

    public void displayExit(Map<Coins,Integer> change, BigDecimal totalChange){
        io.print("Change "+totalChange);
        for(Coins i: change.keySet()) {
            io.print(i + ": " + change.get(i));
        }
    }


    public String putMoney() {
       return io.readString("How much Money would you like to put ?");
    }
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
