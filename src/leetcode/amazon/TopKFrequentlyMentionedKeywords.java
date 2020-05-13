package leetcode.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * Given a list of reviews, a list of keywords and an integer k. Find the most popular k keywords in order of most to
 * least frequently mentioned.
 *
 * The comparison of strings is case-insensitive.
 * Multiple occurances of a keyword in a review should be considred as a single mention.
 * If keywords are mentioned an equal number of times in reviews, sort alphabetically.
 *
 * https://leetcode.com/discuss/interview-question/542597/
 *
 * @author z003xfbr
 *
 */
public class TopKFrequentlyMentionedKeywords {

  public List<String> findMostPopular(final List<String> reviews, final List<String> keywords, final int k) {

    if (reviews == null || reviews.isEmpty() || keywords == null || keywords.isEmpty()) {
      return new ArrayList<>();
    }

    final Map<String, Integer> popularKeywords = new HashMap<>();

    for (final String review : reviews) {
      for (final String keyword : keywords) {
        if (review.toLowerCase().contains(keyword.toLowerCase())) {
          popularKeywords.put(keyword, popularKeywords.getOrDefault(keyword, 0) + 1);
        }
      }
    }

    final Comparator<Map.Entry<String, Integer>> MAX_HEAP = ((a,
        b) -> a.getValue() == b.getValue() ? b.getKey().compareTo(a.getKey()) : b.getValue() - a.getValue());

    final Queue<Map.Entry<String, Integer>> maxHeap = new PriorityQueue<>(MAX_HEAP);
    maxHeap.addAll(popularKeywords.entrySet());

    return maxHeap.stream().limit(k).map(key -> key.getKey()).collect(Collectors.toList());

    // int quantity = k;
    // while (!queue.isEmpty() && quantity-- > 0) {
    // popularLst.add(queue.poll().getKeyword());
    // }
  }

  public static void main(final String[] args) {
    // final int k = 2;
    // final List<String> keywords = Arrays.asList("anacell", "cetracular", "betacellular");
    // final List<String> reviews = Arrays.asList("Anacell provides the best services in the city",
    // "betacellular has awesome services", "Best services provided by anacell, everyone should use anacell");

    final int k = 3;
    final List<String> keywords = Arrays.asList("anacell", "betacellular", "cetracular", "deltacellular", "eurocell");
    final List<String> reviews = Arrays.asList("I love anacell Best services; Best services provided by anacell",
        "betacellular has great services", "deltacellular provides much better services than betacellular",
        "cetracular is worse than anacell", "Betacellular is better than deltacellular.");

    final TopKFrequentlyMentionedKeywords topK = new TopKFrequentlyMentionedKeywords();
    System.out.println(topK.findMostPopular(reviews, keywords, k));
  }

}
