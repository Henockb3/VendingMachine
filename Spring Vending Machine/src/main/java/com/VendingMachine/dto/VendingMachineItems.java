package com.VendingMachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class VendingMachineItems {
   private  String itemName;
   private BigDecimal itemCost;
   private int numberOfInventoryItems;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendingMachineItems that = (VendingMachineItems) o;
        return numberOfInventoryItems == that.numberOfInventoryItems && Objects.equals(itemName, that.itemName) && Objects.equals(itemCost, that.itemCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName, itemCost, numberOfInventoryItems);
    }

    public VendingMachineItems(String itemName, BigDecimal itemCost) {
        this.itemName = itemName;
        this.itemCost = itemCost;
    }

    public String getItemName() {
        return itemName;
    }

    public BigDecimal getItemCost() {
        return itemCost;
    }


    public int getNumberOfInventoryItems() {
        return numberOfInventoryItems;
    }

    public void setNumberOfInventoryItems(int numberOfInventoryItems) {
        this.numberOfInventoryItems = numberOfInventoryItems;
    }
}
