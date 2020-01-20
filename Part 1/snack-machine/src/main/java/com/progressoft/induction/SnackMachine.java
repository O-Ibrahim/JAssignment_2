package com.progressoft.induction;

import java.math.BigDecimal;

public class SnackMachine {
    public static final int DEFAULT_QUANTITY = 10;

    private Money availableMoney;
    private Money transaction;
    private Snack chips = new Snack(SnackType.CHIPS, DEFAULT_QUANTITY);
    private Snack chocolate = new Snack(SnackType.CHOCOLATE,DEFAULT_QUANTITY);
    private Snack chewingGum = new Snack(SnackType.CHEWING_GUM,DEFAULT_QUANTITY);

    //Make these an enum
    private Money[] ACCEPTED_VALUES = {
            Money.QUARTER_DINAR,
            Money.HALF_DINAR,
            Money.DINAR,
            Money.FIVE_DINAR,
            Money.TEN_DINAR
    };
    /**
     * Class Constructor
     * */

    public SnackMachine() {
        this.availableMoney = new Money(BigDecimal.valueOf(0));
        this.transaction = new Money(BigDecimal.valueOf(0));
    }

    /**
     * @param value Money value to be validated against the accepted values
     * */
    private boolean acceptedMoneyValues(Money value){
        for(Money money: ACCEPTED_VALUES){
            if(money.equals(value))
                return true;
        }
        return false;
    }
    /**
     * @return Money returns the amount of money currently stored in the transaction
     * */
    public Money moneyInTransaction(){
        return this.transaction;
    }
    /**
     * @param Money the money object to be added to the machine transaction
     * */
    public void insertMoney(Money money){
        if(!acceptedMoneyValues(money)){
            throw new IllegalArgumentException("Can not accept Money value");
        }
        if(money==null)
            throw new IllegalArgumentException("Money can't be null");
        this.transaction=  this.transaction.add(money);
    }

    /**
     * @param snack Instance of SnackType that will be purchased
     * @return Money returns the change after purchase
     * */
    public Money buySnack(SnackType snack){
        //check if the user has inserted money
        if(transaction.equals(Money.ZERO)){
            throw new IllegalStateException("No money inserted");
        }
        //check if the user has inserted enough money
        if(this.transaction.isLessThan(snack.getValue())){
            throw new IllegalStateException("Not enough money");
        }
        //not extendable, change to better approach after they provide and email for questions.
        //Find selected snack
        Snack selectedSnack;
        switch(snack){
            case CHEWING_GUM: selectedSnack = chewingGum;break;
            case CHIPS: selectedSnack = chips;break;
            case CHOCOLATE: selectedSnack = chocolate;break;
            default: selectedSnack =null;
        }
        //
        if(selectedSnack==null)
            throw new IllegalStateException("Snack not registered");
        //Check snack availability
        if(selectedSnack.quantity()==0){
            throw new IllegalStateException("Snack out of stock");
        }
        Money change = this.transaction.subtract(snack.getValue());
        this.availableMoney = this.availableMoney.add(snack.getValue());
        this.transaction = Money.ZERO;
        decreaseSnackQuantity(selectedSnack,1);
        return change;
    }
    /**
     * @param snack instance of snack that will have its quantity decreased
     * @param subtractedQuantity Integer value that represents the subtraction amount
     * @return Snack returns the chocolate snack instance
     * */
    private void decreaseSnackQuantity(Snack snack, int subtractedQuantity){
        if(subtractedQuantity < 1){
            throw new IllegalArgumentException("Quantity bought can't be less than 1");
        }
        snack.setQuantity(snack.quantity() - subtractedQuantity);
    }
    /**
     * @return Money returns available money in the machine
     * */
    public Money moneyInside() {
        return availableMoney;
    }
    /**
     * @return Snack returns the chips snack instance
     * */
    public Snack chips(){
        return chips;
    }
    /**
     * @return Snack returns the chewing gum snack instance
     * */
    public Snack chewingGums(){
        return chewingGum;
    }
    /**
     * @return Snack returns the chocolate snack instance
     * */
    public Snack chocolates(){
        return chocolate;
    }
}
