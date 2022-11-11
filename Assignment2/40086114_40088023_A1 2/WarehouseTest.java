package ADTDictionary;

public class WarehouseTest { //initialising database in main function

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        WarehouseInventory wareHouse1 = new WarehouseInventory();

        wareHouse1.insertRecord("MYDB001", "Item 1", "Jeans", 20f, 50, 1000.0f, 5, 7, 35, "NO");
        wareHouse1.insertRecord("MYDB002", "Item 2", "T-Shirt", 20f, 50, 1000.0f, 6, 7, 40, "NO");
        wareHouse1.insertRecord("MYDB003", "Item 3", "Shorts", 20f, 50, 1000.0f, 7, 7, 31, "YES");
        wareHouse1.displayAllItems();

        wareHouse1.FindRecord("MYDB001");
        wareHouse1.FindRecord("MYDB002");
        wareHouse1.numberOfItems();
        wareHouse1.entireDatabaseValue();

        wareHouse1.removeThisRecord("MYDB0004");
        wareHouse1.clear();
        wareHouse1.displayAllItems();

    }

}

