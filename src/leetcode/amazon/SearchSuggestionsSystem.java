package leetcode.amazon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/search-suggestions-system/
 *
 * Given an array of strings products and a string searchWord. We want to design a system that suggests at most three
 * product names from products after each character of searchWord is typed. Suggested products should have common prefix
 * with the searchWord. If there are more than three products with a common prefix return the three lexicographically
 * minimums products.
 *
 * Return list of lists of the suggested products after each character of searchWord is typed.
 *
 * Example 1:
 *
 * Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
 * Output: [
 * ["mobile","moneypot","monitor"],
 * ["mobile","moneypot","monitor"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"]
 * ]
 *
 * Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
 * After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
 * After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
 * Example 2:
 *
 * Input: products = ["havana"], searchWord = "havana"
 * Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
 * Example 3:
 *
 * Input: products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
 * Output: [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
 * Example 4:
 *
 * Input: products = ["havana"], searchWord = "tatiana"
 * Output: [[],[],[],[],[],[],[]]
 *
 * @author z003xfbr
 *
 */
public class SearchSuggestionsSystem {

  public List<List<String>> suggestedProducts(final String[] products, final String searchWord) {

    if (products == null || products.length == 0 || searchWord == null || searchWord.isEmpty()) {
      return new ArrayList<>();
    }

    final List<List<String>> answer = new ArrayList<>();

    for (int i = 1; i <= searchWord.length(); i++) {

      final String toSearch = searchWord.substring(0, i);
      final List<String> matchesForCurrentLetter = new ArrayList<>();

      for (final String product : products) {
        if (product.toLowerCase().startsWith(toSearch)) {
          matchesForCurrentLetter.add(product);

          if (matchesForCurrentLetter.size() == 3) {
            break;
          }
        }
      }

      Collections.sort(matchesForCurrentLetter);
      answer.add(matchesForCurrentLetter);
    }

    return answer;
  }

  public static void main(final String[] args) {

    final SearchSuggestionsSystem sss = new SearchSuggestionsSystem();

    final String[] products = { "mobile", "mouse", "moneypot", "monitor", "mousepad" };
    final String searchWord = "mouse";
    System.out.println(sss.suggestedProducts(products, searchWord) + "\n\n");

    final String[] products2 = { "havana" };
    final String searchWord2 = "havana";
    System.out.println(sss.suggestedProducts(products2, searchWord2) + "\n\n");

    final String[] products3 = { "bags", "baggage", "banner", "box", "cloths" };
    final String searchWord3 = "bags";
    System.out.println(sss.suggestedProducts(products3, searchWord3) + "\n\n");

    final String[] products4 = { "havana" };
    final String searchWord4 = "tatiana";
    System.out.println(sss.suggestedProducts(products4, searchWord4) + "\n\n");

  }

}
