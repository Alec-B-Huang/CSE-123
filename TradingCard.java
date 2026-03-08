// Alec Huang
// CSE 123
// C3
// 03/07/2026
// Ishita Mundra

import java.util.*;

// Class Comment: represents a TradingCard object
public class TradingCard implements Comparable<TradingCard> {
    private String name;
    private String set;
    private String rarity;
    private double marketValue;

    // BEHAVIOR: Constructs TradingCard
    // EXCEPTIONS: none
    // RETURNS: none 
    // PARAMETERS: String name: name of card
    //             String set: set from which card is from
    //             String rarity: rarity of the card
    //             Double marketValue: market price of the card
    public TradingCard(String name, String set, String rarity, double marketValue) {
        this.name = name;
        this.set = set;
        this.rarity = rarity;
        this.marketValue = marketValue;
    }

    // BEHAVIOR: Returns string of the trading card's details
    // EXCEPTIONS: none
    // RETURNS: String representation of trading card's  details
    // PARAMETERS: none
    public String toString() {
        return "Name: " + name + " Set: " + set + " Rarity: " + rarity 
                                                + " Market value: " + marketValue;
    }

    // BEHAVIOR: Determine whether another object represents the same trading card
    // EXCEPTIONS: none
    // RETURNS: True: if given object that's an equivalent trading card, otherwise false
    // PARAMETERS: Object card: object to compare with this trading card
    public boolean equals(Object card) {
        if (this == card) {
            return true;
        }

        if (card == null) {
            return false;
        }

        if (card.getClass() != TradingCard.class) {
            return false;
        }

        TradingCard otherCard = (TradingCard) card;
        if (this.name.equals(otherCard.name) && this.set.equals(otherCard.set) && 
                                            this.rarity.equals(otherCard.rarity) && 
                                            this.marketValue == otherCard.marketValue) {
            return true;
        }

        return false;
    }

    // BEHAVIOR: Computes hash value for trading card based on details
    // EXCEPTIONS: none
    // RETURNS: hashNumber, a hashcode representing this trading card
    // PARAMETERS: none
    public int hashCode() {
        int hashNumber = 17;

        hashNumber = 31 * hashNumber + this.name.hashCode();
        hashNumber = 31 * hashNumber + this.set.hashCode();
        hashNumber = 31 * hashNumber + this.rarity.hashCode();
        hashNumber = 31 * hashNumber + Double.hashCode(this.marketValue);

        return hashNumber;
    }

    // BEHAVIOR: Compares this card with other card to determine ordering.
    // EXCEPTIONS: none
    // RETURNS: postive number if card comes before other
    //          negative number if card comes after other
    //          0 if cards are equivalent in ordering
    // PARAMETERS: TradingCard otherCard, card to be compared
    public int compareTo(TradingCard otherCard) {
        if (this.name.compareTo(otherCard.name) != 0) {
            return (this.name.compareTo(otherCard.name));
        }

        if (this.set.compareTo(otherCard.set) != 0) {
            return (this.set.compareTo(otherCard.set));
        }

        if (this.rarity.compareTo(otherCard.rarity) != 0) {
            return (this.rarity.compareTo(otherCard.rarity));
        }

        return Double.compare(this.marketValue, otherCard.marketValue);    
    }

    // BEHAVIOR: reads scanner input and constructs new trading Card.
    // EXCEPTIONS: none 
    // RETURNS: TradingCard created from input data
    // PARAMETERS: Scanner input: scanner with trading card data
    public static TradingCard parse(Scanner input) {
        String name = input.nextLine();
        String set = input.nextLine();
        String rarity = input.nextLine();
        double marketValue = input.nextDouble();
        input.nextLine();

        TradingCard newCard = new TradingCard(name, set, rarity, marketValue);
        return newCard;
    }

    // BEHAVIOR: Returns market value
    // EXCEPTIONS: none
    // RETURNS: Double market price of the card
    // PARAMETERS: none
    public double getMarketValue() {
        return marketValue;
    }

    // BEHAVIOR: Returns name of card
    // EXCEPTIONS: none
    // RETURNS: String name of the card
    // PARAMETERS: none
    public String getName() {
        return name;
    }

    // BEHAVIOR: Returns card set
    // EXCEPTIONS: none
    // RETURNS: String set from which the card is from
    // PARAMETERS: none
    public String getSet() {
        return set;
    }

    // BEHAVIOR: Returns rarity
    // EXCEPTIONS: none
    // RETURNS: String rarity of card
    // PARAMETERS: none
    public String getRarity() {
        return rarity;
    }
}