package org.example;

import java.util.*;
import java.util.stream.Collectors;

import static org.example.FindBestHandHelper.*;
import static org.example.GenerateHandCombinations.generateCombinations;

/**
 * A class to determine the best poker hand from a set of hands.
 */
public class FindBestHand {
    private static final Map<Integer, String> HAND_TYPES = Map.of(
            9, "straight-flush",
            8, "four-of-a-kind",
            7, "full-house",
            6, "flush",
            5, "straight",
            4, "three-of-a-kind",
            3, "two-pairs",
            2, "one-pair",
            1, "highest-card"
    );

    private static final Map<Rank, Integer> CARD_RANKS = Map.ofEntries(
            Map.entry(Rank.TWO, 2), Map.entry(Rank.THREE, 3), Map.entry(Rank.FOUR, 4), Map.entry(Rank.FIVE, 5),
            Map.entry(Rank.SIX, 6), Map.entry(Rank.SEVEN, 7), Map.entry(Rank.EIGHT, 8), Map.entry(Rank.NINE, 9),
            Map.entry(Rank.TEN, 10), Map.entry(Rank.JACK, 11), Map.entry(Rank.QUEEN, 12), Map.entry(Rank.KING, 13),
            Map.entry(Rank.ACE, 14)
    );

    /**
     * Stores the hands of a list of players.
     * @param players List of players.
     * @return List of player hands.
     */
    public List<List<Card>> storePlayerHands(List<Player> players) {
        return players.stream()
                .map(Player::getHand)
                .collect(Collectors.toList());
    }



    /**
     * Gets the maximum rank value from a list of cards.
     * @param hand List of cards.
     * @return Maximum rank value.
     */
    private static int getMaxRank(List<Card> hand) {
        return hand.stream()
                .map(card -> CARD_RANKS.get(card.getRank()))
                .max(Comparator.naturalOrder())
                .orElse(0);
    }

    /**
     * Compares hands to find the highest valued hand.
     * @param handsDict Map of hand names to their corresponding values.
     */
    public static void compareHands(Map<String, List<Card>> handsDict) {
        List<Card> highestHand = Collections.emptyList();

        for (List<Card> hand : handsDict.values()) {
            if (highestHand.isEmpty() || compareHands(highestHand, hand) < 0) {
                highestHand = hand;
            }
        }

        for (Map.Entry<String, List<Card>> entry : handsDict.entrySet()) {
            if (entry.getValue().equals(highestHand)) {
                System.out.println(entry.getKey() + " has the highest hand with: " + HAND_TYPES.get(checkHand(highestHand)));
            }
        }
    }

    /**
     * Compares two hands to determine which is better.
     * @param hand1 First hand.
     * @param hand2 Second hand.
     * @return Positive if the first hand is better, negative otherwise.
     */
    private static int compareHands(List<Card> hand1, List<Card> hand2) {
        return Integer.compare(checkHand(hand1), checkHand(hand2));
    }

    /**
     * Finds the best hand given a set of cards and a river.
     * @param cards List of cards.
     * @param river List of river cards.
     * @return Best hand as a list of cards.
     */
    public static List<Card> bestHandRiver(List<Card> cards, List<Card> river) {
        List<Card> bestHand = Collections.emptyList();
        int bestHandValue = 0;
        List<Card> allCards = new ArrayList<>(cards);
        allCards.addAll(river);
        System.out.println(allCards.size());
        List<List<Card>> allCombinations = generateCombinations(allCards);


        // Iterate through all combinations to find the best hand
        for (List<Card> hand : allCombinations) {
            int currentHandValue = checkHand(hand);  // Get the value of the current hand

            if (currentHandValue > bestHandValue) {
                bestHand = new ArrayList<>(hand);  // New best hand
                bestHandValue = currentHandValue;
            } else if (currentHandValue == bestHandValue) {
                // Tie-breaking logic, if needed
                if (currentHandValue == 5) {  // For straights, find which is higher
                    bestHand = findHigherStraight(bestHand, hand);  // Compare straights
                }
                if (currentHandValue == 2) {  // For one pair, find which pair is higher
                    bestHand = findHigherPair(bestHand, hand);  // Compare pairs
                }
                if (currentHandValue == 4) {  // For three of a kind, find which is higher
                    bestHand = findHigherThreeOfAKind(bestHand, hand);  // Compare three of a kind
                }
            }
        }

        return bestHand;


    }


    /**
     * Checks the type of hand given a list of cards.
     * @param hand List of cards.
     * @return The hand type as an integer.
     */
    private static int checkHand(List<Card> hand) {
        if (checkStraightFlush(hand)) return 9;
        if (checkFourOfAKind(hand)) return 8;
        if (checkFullHouse(hand)) return 7;
        if (checkFlush(hand)) return 6;
        if (checkStraight(hand)) return 5;
        if (checkThreeOfAKind(hand)) return 4;
        if (checkTwoPairs(hand)) return 3;
        if (checkOnePair(hand)) return 2;
        return 1;
    }

    /**
     * Checks if a given hand is a straight flush.
     * @param hand List of cards.
     * @return True if it is a straight flush, false otherwise.
     */
    public static boolean checkStraightFlush(List<Card> hand) {
        return checkFlush(hand) && checkStraight(hand);
    }

    /**
     * Checks if a given hand is four-of-a-kind.
     * @param hand List of cards.
     * @return True if it is four-of-a-kind, false otherwise.
     */
    private static boolean checkFourOfAKind(List<Card> hand) {
        Map<Rank, Long> rankCounts = hand.stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));
        return rankCounts.values().contains(4L);
    }

    /**
     * Checks if a given hand is a full house.
     * @param hand List of cards.
     * @return True if it is a full house, false otherwise.
     */
    private static boolean checkFullHouse(List<Card> hand) {
        Map<Rank, Long> rankCounts = hand.stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));
        return rankCounts.containsValue(3L) && rankCounts.containsValue(2L);
    }

    /**
     * Checks if a given hand is a flush.
     * @param hand List of cards.
     * @return True if it is a flush, false otherwise.
     */
    public static boolean checkFlush(List<Card> hand) {
        Set<Suit> suits = hand.stream()
                .map(Card::getSuit)
                .collect(Collectors.toSet());
        return suits.size() == 1;
    }

    /**
     * Checks if a given hand is a straight.
     * @param hand List of cards.
     * @return True if it is a straight, false otherwise.
     */
    public static boolean checkStraight(List<Card> hand) {
        List<Integer> rankValues = hand.stream()
                .map(card -> CARD_RANKS.get(card.getRank()))
                .sorted()
                .collect(Collectors.toList());
        int rankRange = rankValues.get(rankValues.size() - 1) - rankValues.get(0);

        boolean hasAceToFive = rankValues.contains(2) && rankValues.contains(3) && rankValues.contains(4) && rankValues.contains(5) && rankValues.contains(14);

        return (rankRange == 4 && rankValues.size() == 5) || hasAceToFive;
    }

    /**
     * Checks if a given hand is three-of-a-kind.
     * @param hand List of cards.
     * @return True if it is three-of-a-kind, false otherwise.
     */
    private static boolean checkThreeOfAKind(List<Card> hand) {
        Map<Rank, Long> rankCounts = hand.stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));
        return rankCounts.values().contains(3L);
    }

    /**
     * Checks if a given hand has two pairs.
     * @param hand List of cards.
     * @return True if it has two pairs, false otherwise.
     */
    private static boolean checkTwoPairs(List<Card> hand) {
        Map<Rank, Long> rankCounts = hand.stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));
        return rankCounts.values().stream().filter(count -> count == 2).count() >= 2;
    }

    /**
     * Checks if a given hand has one pair.
     * @param hand List of cards.
     * @return True if it has one pair, false otherwise.
     */
    private static boolean checkOnePair(List<Card> hand) {
        Map<Rank, Long> rankCounts = hand.stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));
        return rankCounts.containsValue(2L);
    }



}
