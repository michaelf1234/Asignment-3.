
package money;

/**
 *Task 2/part1-2-3
 * 
 * @author Michael Fraschetti
 */
private  class Money {
private long dollars, cents; 

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
    // Address Class
    public static class Address {
        private String street, city, state, zip;

        public Address(String street, String city, String state, String zip) {
            this.street = street;
            this.city = city;
            this.state = state;
            this.zip = zip;
        }

        public String toString() {
            return street + ", " + city + ", " + state + " " + zip;
        }
    }

    // Person Class
    public static class Person {
        private String firstName, lastName;
        private Address address;

        public Person(String firstName, String lastName, Address address) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
        }

        public String toString() {
            return firstName + " " + lastName + ", " + address.toString();
        }
    }
    //credit card class
public static class CreditCard {
    private Person owner;
    private Money balance, creditLimit;

    public CreditCard(Person owner, Money creditLimit) {
        this.owner = owner;
        this.creditLimit = new Money(creditLimit);
        this.balance = new Money(0);
    }

    public Money getBalance() {
        return new Money(balance);
    }

    public Money getCreditLimit() {
        return new Money(creditLimit);
    }

    public String getPersonals() {
        return owner.toString();
    }

    public void charge(Money amount) {
        Money newBalance = balance.add(amount);
        if (newBalance.compareTo(creditLimit) <= 0) {
            balance = newBalance;
            System.out.println("Charge: " + amount);
        } else {
            System.out.println("Exceeds credit limit.");
        }
    }

    public void payment(Money amount) {
        balance = balance.subtract(amount);
        System.out.println("Payment: " + amount);
    }
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
public int compareTo(Money amount) { 
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
//TASK #2  

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
            // Task 3
        Address address = new Address("237J Harvey Hall", "Menomonie", "WI", "54751");
        Person owner = new Person("Diane", "Christie", address);
        CreditCard card = new CreditCard(owner, new Money(1000));

        System.out.println("\nOwner Details: " + card.getPersonals());
        System.out.println("Initial Balance: " + card.getBalance());
        System.out.println("Credit Limit: " + card.getCreditLimit());

        Money chargeAmount1 = new Money(200);
        System.out.println("\nCharging " + chargeAmount1);
        card.charge(chargeAmount1);

        Money paymentAmount = new Money(25);
        System.out.println("\nPaying " + paymentAmount);
        card.payment(paymentAmount);

        Money overLimitCharge = new Money(1000);
        System.out.println("\nCharging " + overLimitCharge);
        card.charge(overLimitCharge);
    }

}


