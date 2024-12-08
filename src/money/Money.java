
package money;

/**
 *Part 2, money class
 * 
 * @author Michael Fraschetti
 */
public class Money {
    private long dollars;
    private long cents;

    // Constructor
    public Money(double amount) {
        if (amount < 0) {
            System.out.println("Error: Negative amounts of money are not allowed.");
            System.exit(0);
        } else {
            long allCents = Math.round(amount * 100);
            dollars = allCents / 100;
            cents = allCents % 100;
        }
    }

    // Copy Constructor
    public Money(Money other) {
        this.dollars = other.dollars;
        this.cents = other.cents;
    }

    // Add method
    public Money add(Money otherAmount) {
        Money sum = new Money(0);
        sum.cents = this.cents + otherAmount.cents;
        long carryDollars = sum.cents / 100;
        sum.cents = sum.cents % 100;
        sum.dollars = this.dollars + otherAmount.dollars + carryDollars;
        return sum;
    }

    // Subtract method
    public Money subtract(Money amount) {
        Money difference = new Money(0);
        if (this.cents < amount.cents) {
            this.dollars = this.dollars - 1;
            this.cents = this.cents + 100;
        }
        difference.dollars = this.dollars - amount.dollars;
        difference.cents = this.cents - amount.cents;
        return difference;
    }

    // CompareTo method
    public int compareTo(Money amount) {
        if (this.dollars < amount.dollars) return -1;
        if (this.dollars > amount.dollars) return 1;
        if (this.cents < amount.cents) return -1;
        if (this.cents > amount.cents) return 1;
        return 0;
    }

    // Equals method
    public boolean equals(Money other) {
        return this.dollars == other.dollars && this.cents == other.cents;
    }

    // toString method
    public String toString() {
        return String.format("$%d.%02d", dollars, cents);
    }
}


