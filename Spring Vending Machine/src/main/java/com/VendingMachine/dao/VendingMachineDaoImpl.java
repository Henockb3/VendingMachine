package com.VendingMachine.dao;

import com.VendingMachine.dto.VendingMachineItems;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
@Component
public class VendingMachineDaoImpl implements VendingMachineDao{
    private final String ROSTER_FILE ;
    public static final String DELIMITER = "::";
    private Map<String, VendingMachineItems> items = new HashMap<>();

    public VendingMachineDaoImpl() {
        ROSTER_FILE = "VendingItems.txt";
    }

    public VendingMachineDaoImpl(String roster_file) {
        ROSTER_FILE = roster_file;
    }

    private String marshallItems(VendingMachineItems aVendingMachineItem){
        String itemsAsText = aVendingMachineItem.getItemName() + DELIMITER;

        itemsAsText += aVendingMachineItem.getItemCost() + DELIMITER;


        itemsAsText += aVendingMachineItem.getNumberOfInventoryItems() ;

        return itemsAsText;
    }

    private void writeVendingMachineItems() throws VendingMachinePersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ROSTER_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "Could not save Item data.", e);
        }

        String itemAsText;

        for (VendingMachineItems currentItem : listAll()) {

            itemAsText = marshallItems(currentItem);

            out.println(itemAsText);

            out.flush();
        }

        out.close();
    }

    private VendingMachineItems unmarshallItems(String itemAsText){

        String[] itemTokens = itemAsText.split(DELIMITER);


        String itemName = itemTokens[0];
        BigDecimal price = new BigDecimal(itemTokens[1]);

        VendingMachineItems itemFromFile = new VendingMachineItems(itemName,price);


        itemFromFile.setNumberOfInventoryItems(Integer.valueOf(itemTokens[2]));

        return itemFromFile;
    }

    private void loadItem() throws VendingMachinePersistenceException  {
        Scanner scanner;

        try {

            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ROSTER_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "-_- Could not load Item data into memory.", e);
        }

        String currentLine;

        VendingMachineItems currentItem;

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();

            currentItem = unmarshallItems(currentLine);


            items.put(currentItem.getItemName(), currentItem);
        }

        scanner.close();
    }

    @Override
    public List<VendingMachineItems> listAll() throws VendingMachinePersistenceException {
       loadItem();
       return new ArrayList<>(items.values());
    }

    @Override
    public VendingMachineItems updateQuantity(String item, VendingMachineItems Item) throws VendingMachinePersistenceException {
        loadItem();

        VendingMachineItems vendingMachineItems = items.get(item);
        items.replace(item,Item);
        save();
        return items.replace(item,vendingMachineItems);
    }


    @Override
    public void save() throws VendingMachinePersistenceException {
        try {
            writeVendingMachineItems();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public VendingMachineItems getItem(String name) throws VendingMachinePersistenceException {
        return items.get(name);
    }
}
