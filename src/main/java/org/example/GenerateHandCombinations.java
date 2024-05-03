package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenerateHandCombinations {

    /**
     * Generates all combinations of 5 cards from a list of 7 cards.
     *
     * @param allCards List of all 7 cards.
     * @return A list of lists containing all possible 5-card combinations.
     */
    public static List<List<Card>> generateCombinations(List<Card> allCards) {
        if (allCards.size() != 7 && allCards.size() != 9) {
            throw new IllegalArgumentException("The input list must contain exactly 7 or 9 cards.");
        }

        List<List<Card>> combinations = new ArrayList<>();
        generateCombinationsRecursive(allCards, 5, 0, new ArrayList<>(), combinations);
        return combinations;
    }

    /**
     * Recursive helper method to generate combinations.
     *
     * @param allCards     The original list of cards.
     * @param comboSize    The required size of combinations.
     * @param start        The start index for the combination.
     * @param currentCombo The current combination being built.
     * @param combinations The list to store all combinations.
     */
    private static void generateCombinationsRecursive(List<Card> allCards, int comboSize, int start,
                                                      List<Card> currentCombo, List<List<Card>> combinations) {
        if (currentCombo.size() == comboSize) {
            combinations.add(new ArrayList<>(currentCombo));  // Add a copy of the current combination
            return;
        }

        for (int i = start; i < allCards.size(); i++) {
            currentCombo.add(allCards.get(i));  // Add the current card to the combination
            generateCombinationsRecursive(allCards, comboSize, i + 1, currentCombo, combinations);  // Recursive call
            currentCombo.remove(currentCombo.size() - 1);  // Backtrack by removing the last card
        }
    }

}
