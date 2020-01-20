package com.progressoft.induction;

import java.math.BigDecimal;

public class Money {
    private BigDecimal value;

    public static final Money ZERO = new Money(BigDecimal.valueOf(0));
    public static final Money QUARTER_DINAR = new Money(BigDecimal.valueOf(0.25));
    public static final Money HALF_DINAR = new Money(BigDecimal.valueOf(0.5));
    public static final Money DINAR = new Money(BigDecimal.valueOf(1));
    public static final Money FIVE_DINAR = new Money(BigDecimal.valueOf(5));
    public static final Money TEN_DINAR = new Money(BigDecimal.valueOf(10));

    /*
     * @return   Boolean returns true of the value of the param BigDecimal is equal of the object BigDecimal
     * */
    public BigDecimal getValue() {
        return value;
    }
    /**
     * Class constructor.
     */
    public Money(BigDecimal value) {
        if(value.signum()==-1){
            throw new IllegalArgumentException("Invalid money value");
        }

        this.value = new BigDecimal(value.toString());

    }

    /*
    * @param    o  an object instance, if the instance is not of type Money, the return will be false
    * @return   Boolean returns true of the value of the param BigDecimal is equal of the object BigDecimal
    * */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Money)) {
            return false;
        }
        Money m = (Money) o;
        return this.getValue().compareTo(m.getValue())==0;
    }
    /*
     * @param   addedValue   an instance of Money
     * @return  Money   returns Money with the added value of the parameter with the object
     * */
    public Money add(Money addedValue){
      return new Money(value.add(addedValue.value));
    }
    /*
     * @param    comparedValue  an instance of Money
     * @return   Boolean returns true of the value of the param BigDecimal is less than the object BigDecimal
     * */
    public boolean isLessThan(Money comparedValue){
        if(comparedValue==null)
            return false;
        return getValue().compareTo(comparedValue.getValue()) ==-1 ;
   }
    /*
     * @param   subtractedValue   an instance of Money
     * @return  Money   returns Money with the added value of the parameter with the object
     * */
    public Money subtract(Money subtractedValue){
        if(this.isLessThan(subtractedValue)){
            throw new IllegalArgumentException("Insufficient Money, can't subtract");
        }
        else {
            return new Money(value.subtract(subtractedValue.value));
        }
    }

}
