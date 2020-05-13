package leetcode.stringmanipulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/group-anagrams/
 *
 * @author z003xfbr
 *
 *         Given an array of strings, group anagrams together.
 *
 *         Example:
 *
 *         Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 *         Output:
 *         [
 *         ["ate","eat","tea"],
 *         ["nat","tan"],
 *         ["bat"]
 *         ]
 *         Note:
 *
 *         All inputs will be in lowercase.
 *         The order of your output does not matter.
 *
 */
public class GroupAnagrams {

  /**
   * Create an hash to each word, and dont take in account the order. Each hash is the key for a hash map and the values
   * are the word itself.
   * 
   * @param list
   * @return
   */
  public static List<List<String>> groupAnagrams(final List<String> list) {

    if (list.size() == 0) {
      return new ArrayList<>();
    }

    final Map<String, List<String>> ans = new HashMap<>();
    final int[] count = new int[26];

    for (final String s : list) {
      Arrays.fill(count, 0);

      for (final char c : s.toCharArray()) {
        count[c - 'a']++;
      }

      final StringBuilder sb = new StringBuilder("");
      for (int i = 0; i < 26; i++) {
        sb.append('#');
        sb.append(count[i]);
      }

      final String key = sb.toString();
      final List<String> temp = ans.getOrDefault(key, new ArrayList<>());
      temp.add(s);
      ans.put(key, temp);
    }

    return new ArrayList<>(ans.values());

  }

  public static void main(final String[] args) {

    final List<List<String>> groups = groupAnagrams(Arrays.asList("eat", "tea", "tan", "ate", "nat", "bat"));
    System.out.println(groups);

  }
}
