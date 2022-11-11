package ADTDictionary;

public class WarehouseInventory {
    LList<String> inventoryID;
    LinkLDictionary<String, String> n; //name
    LinkLDictionary<String, String> des; // description
    LinkLDictionary<String, Float> unitPrice;
    LinkLDictionary<String, Integer> amountInStock;
    LinkLDictionary<String, Float> totalValue;
    LinkLDictionary<String, Integer> reorderLevel;
    LinkLDictionary<String, Integer> reorderTime;
    LinkLDictionary<String, Integer> quantityInReorder;
    LinkLDictionary<String, String> discontinuedYesOrNo;

    public WarehouseInventory() {
        inventoryID = new LList<String>(); //just a key, dont need dictionary
        n = new LinkLDictionary<String, String>(50); // need LLDictionary for the other values 
        des = new LinkLDictionary<String, String>(50);
        unitPrice = new LinkLDictionary<String, Float>(50);
        amountInStock = new LinkLDictionary<String, Integer>(50);
        totalValue = new LinkLDictionary<String, Float>(50);
        reorderLevel = new LinkLDictionary<String, Integer>(50);
        reorderTime = new LinkLDictionary<String, Integer>(50);
        quantityInReorder = new LinkLDictionary<String, Integer>(50);
        discontinuedYesOrNo = new LinkLDictionary<String, String>(50);

    }

    public void insertRecord(String inventoryID, String n, String des, Float uPrice,
        Integer amountInStock, Float totalValue, Integer reorderLevel, Integer reorderTime,
        Integer quantityInReorder, String discontinuedYesOrNo) {
            this.inventoryID.insert(inventoryID);
            this.n.insert(inventoryID, n);
            this.des.insert(inventoryID, des);
            this.unitPrice.insert(inventoryID, uPrice);
            this.amountInStock.insert(inventoryID, amountInStock);
            this.totalValue.insert(inventoryID, totalValue);
            this.reorderLevel.insert(inventoryID, reorderLevel);
            this.reorderTime.insert(inventoryID, reorderTime);
            this.quantityInReorder.insert(inventoryID, quantityInReorder);
            this.discontinuedYesOrNo.insert(inventoryID, discontinuedYesOrNo);
    }

    public void removeThisRecord(String inventoryID) {
        if (this.FindRecord(inventoryID)) {
            this.inventoryID.remove();
            this.n.remove(inventoryID);
            this.des.remove(inventoryID);
            this.unitPrice.remove(inventoryID);
            this.amountInStock.remove(inventoryID);
            this.totalValue.remove(inventoryID);
            this.reorderLevel.remove(inventoryID);
            this.reorderTime.remove(inventoryID);
            this.quantityInReorder.remove(inventoryID);
            this.discontinuedYesOrNo.remove(inventoryID);
        } else {
            System.out.println("There is nothing to be removed" + "/n");
        }

    }

    public void clear() {
        this.inventoryID.clear();
        this.n.clear();
        this.des.clear();
        this.unitPrice.clear();
        this.amountInStock.clear();
        this.totalValue.clear();
        this.reorderLevel.clear();
        this.reorderTime.clear();
        this.quantityInReorder.clear();
        this.discontinuedYesOrNo.clear();
    }

    public boolean FindRecord(String inventoryID) {
        this.inventoryID.moveToStart();
        boolean recordFound = false;
        for (int i = 0; i < this.inventoryID.length(); i++) {
            if (inventoryID == this.inventoryID.getValue()) {
                recordFound = true;
                System.out.println(
                        "Inventory ID \t Name \t Description \t Unit Price \t Quantity in Stock \t Inventory Value \t Reorder Level  \t Reorder Time in Days \t Quantity in Reorder  \t Discontinued?");
                System.out.print(inventoryID);
                System.out.print("     " + "     "  + "     "  + this.n.find(inventoryID));
                System.out.print("     " + this.des.find(inventoryID));
                System.out.print("     $" + this.unitPrice.find(inventoryID));
                System.out.print("          " + this.amountInStock.find(inventoryID));
                System.out.print("          $" + this.totalValue.find(inventoryID));
                System.out.print("          " + this.reorderLevel.find(inventoryID));
                System.out.print("                    " + this.reorderTime.find(inventoryID));
                System.out.print("               " + this.quantityInReorder.find(inventoryID));
                System.out.println("               " + this.discontinuedYesOrNo.find(inventoryID + "/n"));
                return true;
            }
            this.inventoryID.next();
        }
        System.out.println("No items found. Please try again" + "/n");
        return false;
    }

    public int numberOfItems() {
        System.out.println("number of items in the database: " + inventoryID.length() + "/n");
        return this.inventoryID.length();
    }

    public float entireDatabaseValue() {
        float sum = 0;
        this.inventoryID.moveToStart();
        for (int i = 0; i < inventoryID.length(); i++) {
            sum = sum + this.totalValue.find(inventoryID.getValue());
            inventoryID.next();
        }
        System.out.println("Entire value of all items in the databse: " + sum + "/n");
        return sum;
    }

    public void displayAllItems() {
        System.out.println(
                "Inventory ID \t Name \t Description \t Unit Price \t Quantity in Stock \t Inventory Value \t Reorder Level  \t Reorder Time in Days \t Quantity in Reorder  \t Discontinued?");
        this.inventoryID.moveToStart();
        for (int i = 0; i < this.inventoryID.length(); i++) {
            System.out.print(this.inventoryID.getValue());
            System.out.print("          " + this.n.find(inventoryID.getValue()));
            System.out.print("     " + this.des.find(inventoryID.getValue()));
            System.out.print("     $" + this.unitPrice.find(inventoryID.getValue()));
            System.out.print("          " + this.amountInStock.find(inventoryID.getValue()));
            System.out.print("          $" + this.totalValue.find(inventoryID.getValue()));
            System.out.print("          " + this.reorderLevel.find(inventoryID.getValue()));
            System.out.print("                    " + this.reorderTime.find(inventoryID.getValue()));
            System.out.print("               " + this.quantityInReorder.find(inventoryID.getValue()));
            System.out.println("               " + this.discontinuedYesOrNo.find(inventoryID.getValue()));
            this.inventoryID.next();
        }
    }

    public int[] createIndex() {
        int[] result = ADTdictionary.createIndex();
        return result;
    }

    void printListFromDictionary(){
        System.out.printf("\n");
        ALList<KVpair<String,Inventory>> list = dictionary.getList();
        list.moveToStart();
        while(list.getValue() != null){
            System.out.println(list.getValue().value());
            list.next();
        }
    }

    public void E query(BSTNode<Key,E> rt, Key k) { //serarch for an element that has been indexed
        if (rt == null){ 
            return null;}
        if (rt.key().compareTo(k) > 0){
            return query(rt.left(), k);
            else if (rt.key().compareTo(k) == 0) return rt.element();
            else return query(rt.right(), k);
        }
    }
}
