package money;

/**
 *Part 2, CreditCard class
 * 
 * @author Michael Fraschetti
 */

public class CreditCard {
    private Person owner;
    private Money balance;
    private Money creditLimit;

    // Constructor
    public CreditCard(Person owner, Money creditLimit) {
        this.owner = owner;
        this.creditLimit = new Money(creditLimit);
        this.balance = new Money(0);
    }

    // Accessor for balance
    public Money getBalance() {
        return new Money(balance); 
    }

    // Accessor for credit limit
    public Money getCreditLimit() {
        return new Money(creditLimit); 
    }

    // Accessor for owner 
    public String getPersonals() {
        return owner.toString();
    }

    // Charge method
    public void charge(Money amount) {
        Money newBalance = balance.add(amount);
        if (newBalance.compareTo(creditLimit) <= 0) {
            balance = newBalance;
            System.out.println("Charge: " + amount);
        } else {
            System.out.println("Exceeds credit limit.");
        }
    }

    // Payment method
    public void payment(Money amount) {
        balance = balance.subtract(amount);
        System.out.println("Payment: " + amount);
    }
    public static class Person {
        private String firstName, lastName;
        private Address address;

    // Constructor
    public Person(String firstName, String lastName, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    // toString method
    public String toString() {
        return firstName + " " + lastName + ", " + address.toString();
    }
}
        public static class Address {
            private String street, city, state, zip;

        // Constructor
        public Address(String street, String city, String state, String zip) {
            this.street = street;
            this.city = city;
            this.state = state;
            this.zip = zip;
        }

        // toString method
        public String toString() {
            return street + ", " + city + ", " + state + " " + zip;
        }
    }
        public static void main(String[] args) {
            CreditCard.Address address = new CreditCard.Address("237J Harvey Hall", "Menomonie", "WI", "54751");
            CreditCard.Person owner = new CreditCard.Person("Diane", "Christie", address);

            CreditCard card = new CreditCard(owner, new Money(1000));

            System.out.println(owner);
            System.out.println("Balance: " + card.getBalance());
            System.out.println("Credit Limit: " + card.getCreditLimit());

            Money charge1 = new Money(200);
            System.out.println("\nAttempting to charge: " + charge1);
            card.charge(charge1);

            Money charge2 = new Money(10.02);
            System.out.println("\nAttempting to charge: " + charge2);
            card.charge(charge2);

            Money payment = new Money(25);
            System.out.println("\nMaking a payment of: " + payment);
            card.payment(payment);
        
            Money overLimitCharge = new Money(990);
            System.out.println("\nAttempting to charge: " + overLimitCharge);
            card.charge(overLimitCharge);
    }
}

