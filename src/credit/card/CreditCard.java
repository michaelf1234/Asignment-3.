package credit.card;

/**
 * Credit Card System
 */
public class CreditCard {
    private static class Address {
        private String street, city, state, zip;

        public Address(String street, String city, String state, String zip) {
            this.street = street;
            this.city = city;
            this.state = state;
            this.zip = zip;
        }

        @Override
        public String toString() {
            return street + ", " + city + ", " + state + " " + zip;
        }
    }

    private static class Person {
        private String firstName, lastName;
        private Address address;

        public Person(String firstName, String lastName, Address address) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
        }

        @Override
        public String toString() {
            return firstName + " " + lastName + ", " + address.toString();
        }
    }

    private static class Money {
        private long dollars, cents;

        public Money(double amount) {
            if (amount < 0) {
                System.out.println("Error: Negative amounts of money are not allowed.");
                System.exit(0);
            }
            long totalCents = Math.round(amount * 100);
            this.dollars = totalCents / 100;
            this.cents = totalCents % 100;
        }

        public Money(Money other) {
            this.dollars = other.dollars;
            this.cents = other.cents;
        }

        public Money add(Money other) {
            long totalCents = this.cents + other.cents + (this.dollars + other.dollars) * 100;
            return new Money(totalCents / 100.0);
        }

        public Money subtract(Money other) {
            long totalCents = (this.dollars * 100 + this.cents) - (other.dollars * 100 + other.cents);
            return new Money(Math.max(0, totalCents) / 100.0);
        }

        public int compareTo(Money other) {
            if (this.dollars != other.dollars) return Long.compare(this.dollars, other.dollars);
            return Long.compare(this.cents, other.cents);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Money other = (Money) obj;
            return dollars == other.dollars && cents == other.cents;
        }

        @Override
        public String toString() {
            return String.format("$%d.%02d", dollars, cents);
        }
    }

    // CreditCard class
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
            System.out.println("Exceeds credit limit");
        }
    }

    public void payme(Money amount) {
        balance = balance.subtract(amount);
        System.out.println("Payment: " + amount);
    }

    public static void main(String[] args) {
        Address address = new Address("237J Harvey Hall", "Menomonie", "WI", "54751");
        Person owner = new Person("Christie", "Diane", address);
        Money creditLimit = new Money(1000);
        CreditCard card = new CreditCard(owner, creditLimit);

        System.out.println(card.getPersonals());
        System.out.println("Balance: " + card.getBalance());
        System.out.println("Credit Limit: " + card.getCreditLimit());
        System.out.println();

        System.out.println("Attempting to charge $200.00");
        card.charge(new Money(200));
        System.out.println("Balance: " + card.getBalance());
        System.out.println();

        System.out.println("Attempting to charge $10.02");
        card.charge(new Money(10.02));
        System.out.println("Balance: " + card.getBalance());
        System.out.println();

        System.out.println("Attempting to pay $25.00");
        card.payme(new Money(25));
        System.out.println("Balance: " + card.getBalance());
        System.out.println();

        System.out.println("Attempting to charge $990.00");
        card.charge(new Money(990));
        System.out.println("Balance: " + card.getBalance());
    }
}


