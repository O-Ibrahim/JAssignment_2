package com.progressoft.induction;

public class Snack {
private SnackType snackType;
private int snackQuantity;

    /**
     * Class constructor.
     * @param type Snacktype enum value
     * @param quantity Integer value that represents the quantity of the snack
     */
    public Snack(SnackType type, int quantity) {
        this.snackType = type;
        this.snackQuantity = quantity;
    }
    /**
     * return Integer returns the snack quantity
     * */
    public int quantity (){
        return snackQuantity;
    }
    /**
     * @param quantity Integer value to be set as the new quantity
     * @return Integer returns the new quantity value
     * */
    public int setQuantity(int quantity){
        if(quantity<0)
            throw new IllegalArgumentException("Quantity can't be negative");
        this.snackQuantity = quantity;
        return this.snackQuantity;
    }



    //snack name getter (not in requirements)
}
