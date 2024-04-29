package org.example;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class FindBestHandHelper {
    private static final Map<Rank, Integer> CARD_RANKS = Map.ofEntries(
            Map.entry(Rank.TWO, 2), Map.entry(Rank.THREE, 3), Map.entry(Rank.FOUR, 4), Map.entry(Rank.FIVE, 5),
            Map.entry(Rank.SIX, 6), Map.entry(Rank.SEVEN, 7), Map.entry(Rank.EIGHT, 8), Map.entry(Rank.NINE, 9),
            Map.entry(Rank.TEN, 10), Map.entry(Rank.JACK, 11), Map.entry(Rank.QUEEN, 12), Map.entry(Rank.KING, 13),
            Map.entry(Rank.ACE, 14)
    );
    private static int getHighestRank(List<Card> hand) {
        return hand.stream()
                .map(card -> CARD_RANKS.get(card.getRank()))
                .max(Comparator.naturalOrder())
                .orElse(0);
    }

    public static List<Card> findHigherStraight(List<Card> hand1, List<Card> hand2) {
        int highCard1 = getHighestRank(hand1);
        int highCard2 = getHighestRank(hand2);

        if (highCard1 > highCard2) {
            return hand1;
        } if (highCard2 > highCard1) {
            return hand2;
        }
        return null; // Both hands have the same high card

    }

    private static Integer findPairRank(List<Card> hand) {
        Map<Rank, Integer> rankCount = new HashMap<>();
        for (Card card : hand) {
            Rank rank = card.getRank();
            rankCount.put(rank, rankCount.getOrDefault(rank, 0) + 1);
        }

        for (Map.Entry<Rank, Integer> entry : rankCount.entrySet()) {
            if (entry.getValue() == 2) { // Check if there's a pair
                return CARD_RANKS.get(entry.getKey());
            }
        }
        return null; // No pair found
    }

    public static List<Card> findHigherPair(List<Card> hand1, List<Card> hand2) {
        Integer pairRank1 = findPairRank(hand1);
        Integer pairRank2 = findPairRank(hand2);

        if (pairRank1 != null && pairRank2 != null) {
            if (pairRank1 > pairRank2) {
                return hand1;
            } else if (pairRank2 > pairRank1) {
                return hand2;
            }
        }
        return null; // Either no pairs found or both pairs are of the same rank
    }

    private static Integer findThreeOfAKindRank(List<Card> hand) {
        Map<Rank, Integer> rankCount = new HashMap<>();
        for (Card card : hand) {
            Rank rank = card.getRank();
            rankCount.put(rank, rankCount.getOrDefault(rank, 0) + 1);
        }

        for (Map.Entry<Rank, Integer> entry : rankCount.entrySet()) {
            if (entry.getValue() == 3) { // Check for three of a kind
                return CARD_RANKS.get(entry.getKey());
            }
        }
        return null; // No three of a kind found
    }

    public static List<Card> findHigherThreeOfAKind(List<Card> hand1, List<Card> hand2) {
        Integer threeRank1 = findThreeOfAKindRank(hand1);
        Integer threeRank2 = findThreeOfAKindRank(hand2);

        if (threeRank1 != null && threeRank2 != null) {
            if (threeRank1 > threeRank2) {
                return hand1;
            } else if (threeRank2 > threeRank1) {
                return hand2;
            }
        }
        return null; // Either no three of a kinds found or both are of the same rank
    }

}
