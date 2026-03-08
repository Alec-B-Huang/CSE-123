// Alec Huang
// CSE 123
// C3
// 03072026
// Ishita Mundra

import java.io.*;
import java.util.*;

// Class Comment: A collectionManager class that 
//                allows you to manage your collection.
public class CollectionManager {
    private tcgNode root;

    // BEHAVIOR: Constructs collectionManager
    // EXCEPTIONS: none
    // RETURNS: none
    // PARAMETERS: none 
    public CollectionManager() {
        root = null;
    }

    // BEHAVIOR: Constructs collection manager by reading cards
    // EXCEPTIONS: none
    // RETURNS: none 
    // PARAMETERS: Scanner input: source of trading card data
    public CollectionManager(Scanner input) {
        while (input.hasNext()) {
            add(TradingCard.parse(input));
        }
    }

    // BEHAVIOR: adds given trading card
    // EXCEPTIONS: none
    // RETURNS: none 
    // PARAMETERS: TradingCard card: card to be added
    public void add(TradingCard card) {
        root = add(root, card);
    }

    // BEHAVIOR: Inserts trading card into appropriate position
    // EXCEPTIONS: none
    // RETURNS: root of updated subtree after adding
    // PARAMETERS: tcgNode root: current node being examined
    //             TradingCard card: card to be added
    private tcgNode add(tcgNode root, TradingCard card) {
        if (root == null) {
            return new tcgNode(card);
        }

        if (card.compareTo(root.card) < 0) {
            root.left = add(root.left, card);

        } else {
            root.right = add(root.right, card);
        }

        return root;    
    }

    // BEHAVIOR: Checks if root contains card
    // EXCEPTIONS: none
    // RETURNS: Returns true if root contains card, otherwise false
    // PARAMETERS: TradingCard card: card to be looked for
    public boolean contains(TradingCard card) {
        return contains(root, card);
    }

    // BEHAVIOR: Searchs collection to see if card exists yet
    // EXCEPTIONS: none
    // RETURNS: true if card exists in root, false otherwise
    // PARAMETERS: tcgNode root: current node being examined
    //             TradingCard card: card being added
    private boolean contains(tcgNode root, TradingCard card) {
        if (root == null) {
            return false;
        }

        int comparison = card.compareTo(root.card);
        if (comparison == 0) {
            return true;

        } else if (comparison < 0) {
            return contains(root.left, card);

        } else {
            return contains(root.right, card);

        }
    }

    // BEHAVIOR: Returns string representation of all cards in collection
    // EXCEPTIONS: none
    // RETURNS: String containing each card in collection
    // PARAMETERS: none
    public String toString() {
        return toString(root);
    }

    // BEHAVIOR: builds collection by checking nodes in sorted order
    // EXCEPTIONS: none
    // RETURNS: String representing cards in subtree
    // PARAMETERS: tcgNode root: node being processed
    private String toString(tcgNode root) {
        if (root == null) {
            return "";
        }

        String leftString = toString(root.left);
        String currentString = root.card.toString();
        String rightString = toString(root.right);

        return leftString + currentString + "\n" + rightString;
    }

    // BEHAVIOR: Writes contents of collection to given output
    // EXCEPTIONS: none
    // RETURNS: none
    // PARAMETERS: PrintStream output: destination for saved data
    public void save(PrintStream output) {
        save(root, output);
    }

    // BEHAVIOR: writes trading card data from subtree to output
    // EXCEPTIONS: none
    // RETURNS: none
    // PARAMETERS: tcgNode root: current node being processed
    //             PrintStream output: destination of saved data
    private void save(tcgNode root, PrintStream output) {
        if (root == null) {
            return;
        }

        output.println(root.card.getName());
        output.println(root.card.getSet());
        output.println(root.card.getRarity());
        output.println(root.card.getMarketValue());
        save(root.left, output);
        save(root.right, output);
    }

    // BEHAVIOR: returns list of cards whose market value is equal or greater tahn minimumValue
    // EXCEPTIONS: none
    // RETURNS: list of trading cards that meet requirement.
    // PARAMETERS: double minimumValue: minimum market value allowed
    public List<TradingCard> filter(double minimumValue) {
        List<TradingCard> resultList = new ArrayList<>();
        return filter(root, minimumValue, resultList);
    }

    // BEHAVIOR: searchs subtree and collects cards that meet minimumValue
    // EXCEPTIONS: none
    // RETURNS: list containing matching cards
    // PARAMETERS: tcgNode root: node being examined
    //             double minimumValue: minimum market value allowed
    //             List<TradingCard> list: list used to collect matching cards
    private List<TradingCard> filter(tcgNode root, double minimumValue, List<TradingCard> list) {
        if (root == null) {
            return list;
        }

        if (root.card.getMarketValue() >= minimumValue) {
            list.add(root.card);
        }

        filter(root.left, minimumValue, list);
        filter(root.right, minimumValue, list);
        return list;
     }

    // BEHAVIOR: represents node in collection that stores trading cards and child nodes
    // EXCEPTIONS: none
    // RETURNS: none
    // PARAMETERS: none
    private class tcgNode {
        public TradingCard card;
        public tcgNode left;
        public tcgNode right;

        // BEHAVIOR: constructs tcgNode
        // EXCEPTIONS: none
        // RETURNS: none
        // PARAMETERS: TradingCard card
        public tcgNode(TradingCard card) {
            this.card = card;
            this.left = null;
            this.right = null;
        }
    }
}