package fr.exagone.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * classe représentant une carte.
 * @author rouault
 * Cette classe permet en outre de créer un jeu de carte. On crée un nouveau jeu en appelant la métdode static Carte.newDeck();
 * Les cartes sont immuables : final ==> même en appelant la méthode rank() ou suit(), on ne peut les modifier.
 * Un jeu est conçu de manière static et alimente une variable constante protoDeck : private static final.
 * Un jeu s'obtient par copie de cette liste de cartes.
 */
public class Card {
    // identificatieur de carte : numero et figure.  
	public enum Rank { DEUCE, THREE, FOUR, FIVE, SIX,
        SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE }
    // couleur des cartes.
    public enum Suit { CLUBS, DIAMONDS, HEARTS, SPADES }

    private final Rank rank;
    private final Suit suit;
    
    // constructeur en private.
    private Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank rank() { return rank; }
    public Suit suit() { return suit; }
    public String toString() { return rank + " of " + suit; }

    private static final List<Card> protoDeck = new ArrayList<Card>();

    // Initialize prototype deck
    static {
        for (Suit suit : Suit.values())
            for (Rank rank : Rank.values())
                protoDeck.add(new Card(rank, suit));
    }

    public static ArrayList<Card> newDeck() {
        return new ArrayList<Card>(protoDeck); // Return copy of prototype deck
    }
}
