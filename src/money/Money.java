/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package money;

/**
 *Task 2/part1-2-3
 * 
 * @author Michael Fraschetti
 */
public class Money {
private long dollars; 
private long cents; 


/** 
Constructor 
@param amount The amount in decimal format. 
*/ 
public Money(double amount) 
{ 
if (amount < 0) 
{ 
System.out.println("Error: Negative amounts " + 
"of money are not allowed."); 
System.exit(0); 
} 
else 
{ 
long allCents = Math.round(amount * 100); 
dollars = allCents / 100; 
cents = allCents % 100; 
} 
} 
//Copy Constructor
public Money(Money original) {
    this.dollars = original.dollars;
    this.cents = original.cents;
}
//Add method
public Money add(Money otherAmount) 
{ 
Money sum = new Money(0); 
sum.cents = this.cents + otherAmount.cents; 
long carryDollars = sum.cents / 100; 
sum.cents = sum.cents % 100; 
sum.dollars = this.dollars + 
otherAmount.dollars + 
carryDollars; 
return sum; 
} 

public Money subtract (Money amount) 
{ 
Money difference = new Money(0); 
if (this.cents < amount.cents) 
{ 
this.dollars = this.dollars - 1; 
this.cents = this.cents + 100; 
} 
difference.dollars = this.dollars - amount.dollars; 
difference.cents = this.cents - amount.cents; 
return difference; 
} 
 
//The compareTo method 

public int compareTo(Money amount) 
{ 
int value; 
if(this.dollars < amount.dollars) 
value = -1; 
else if (this.dollars > amount.dollars) 
value = 1; 
else if (this.cents < amount.cents) 
value = -1; 
else if (this.cents > amount.cents) 
value = 1; 
else 
value = 0; 
return value; 
} 
// ADD LINES FOR TASK #2 HERE 
// Document and write an equals method 
// Document and write a toString method 

public boolean equals(Money other) {
    return this.dollars == other.dollars && this.cents == other.cents;
    
} 
//ToString method
public String toString() {
    return String.format("$%d.%02d", dollars, cents);
    
}
public static void main(String[] args) {
    Money money1 = new Money(500.00);
    Money money2 = new Money(10.02);
    Money money3 = new Money(10.88);
    
    System.out.println(" The current amount is "  + money1.toString());
    
    Money difference = money1.subtract(money3);
    System.out.println(" Subtracting $10.99 gives " + difference.toString());
    
    if (money2.equals(money2)) {
        System.out.println(money2.toString() + " equals " + money2.toString());
    } else {
        System.out.println(money2.toString()+ " does not equal " + money2);      
    }
    if (money3.equals(money2)) {
    } else {
        System.out.println(money3.toString() + " does not equal " + money2.toString());
    }
}

}


