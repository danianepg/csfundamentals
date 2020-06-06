package crackingthecodinginterview;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

/**
 * You have a large text file containing words. Given any two words, find the
 * shortest distance (in terms of number of words) between them in the file. If
 * the operation will be repeated many times for the same file (but different
 * pairs of words), can you optimize your solution?
 * 
 * @author Daniane P. Gomes
 *
 */
public class Exercise18_5 {

	public int shortest(String[] words, String word1, String word2) {

		int min = words.length;
		int lastPosW1 = -1;
		int lastPosW2 = -1;

		for (int i = 0; i < words.length; i++) {
			String currentWord = words[i];

			if (currentWord.equals(word1)) {
				lastPosW1 = i;

				// Comment the next 3 lines if order matters
				int distance = lastPosW1 - lastPosW2;
				if (lastPosW2 >= 0 && min > distance) {
					min = distance;
				}
			} else if (currentWord.equals(word2)) {
				lastPosW2 = i;
				int distance = lastPosW2 - lastPosW1;
				if (lastPosW1 >= 0 && min > distance) {
					min = distance;
				}
			}
		}

		if (lastPosW1 > 0 && lastPosW2 > 0) {
			return min;
		} else {
			return -1;
		}
	}

	public int shortestHash(HashMap<String, List<Integer>> wordsHash, String word1, String word2) {

		List<Integer> listA = wordsHash.get(word1);
		List<Integer> listB = wordsHash.get(word2);

		if (!Objects.isNull(listA) && !Objects.isNull(listB)) {

			TreeMap<Integer, String> merged = new TreeMap<>();
			listA.stream().forEach(elem -> {
				merged.put(elem, "A");
			});

			listB.stream().forEach(elem -> {
				merged.put(elem, "B");
			});

			System.out.println(merged);

			Integer posWord1 = -1;
			Integer posWord2 = Integer.MAX_VALUE;
			int min = Integer.MAX_VALUE;

			Set<Integer> keys = merged.keySet();
			for (Integer key : keys) {

				String curList = merged.get(key);

				if (curList.equals("A")) {
					posWord1 = key;
					int distance = Math.abs(posWord1 - posWord2);
					if (posWord1 >= 0 && min > distance) {
						min = distance;
					}
				} else {
					posWord2 = key;
					int distance = Math.abs(posWord1 - posWord2);
					if (posWord2 >= 0 && min > distance) {
						min = distance;
					}
				}

			}

			return min;
		}
		return -1;
	}

	public HashMap<String, List<Integer>> mountHashMap(String[] words) {

		HashMap<String, List<Integer>> wordsHash = new HashMap<>();

		for (int i = 0; i < words.length; i++) {

			List<Integer> positions = new ArrayList<>();
			if (wordsHash.containsKey(words[i])) {
				positions = wordsHash.get(words[i]);
			}
			positions.add(i);
			wordsHash.put(words[i], positions);
		}

		return wordsHash;

	}

	public static void main(String[] args) {

		String[] words = { "dog", "cat", "egg", "role", "a", "bird", "dog", "crew", "dog", "cat", "egg", "cat", "egg",
				"car", "pen", "apple", "cable", "phone", "erases", "glass", "water", "rubber", "duck", "bird", "dog",
				"crew", "bird", "role" };
		// String[] words = { "dog", "cat", "role", "crew", "cat", "role", "dog" };
		// String word1 = "dog";
		// String word2 = "role";

		Exercise18_5 e = new Exercise18_5();

		int distance = 0;
		Instant start = Instant.now();
		// for(int i=0; i<words.length-1; i++) {
		// distance = e.shortest(words, words[i], words[i+1]);
		// System.out.println("Shortest distance between " + words[i] + " and " +
		// words[i+1] + " = " + distance);
		// }
		Instant end = Instant.now();
		long duration = Duration.between(start, end).toMillis();

		System.out.println("Execution seq " + duration);

		start = Instant.now();
		HashMap<String, List<Integer>> wordsHash = e.mountHashMap(words);
		distance = e.shortestHash(wordsHash, "role", "bird");
		System.out.println("Shortest distance between role and bird = " + distance);

		// for(int i=0; i<words.length-1; i++) {
		// distance = e.shortestHash(wordsHash, words[i], words[i+1]);
		// System.out.println("Shortest distance between " + words[i] + " and " +
		// words[i+1] + " = " + distance);
		// }
		end = Instant.now();
		duration = Duration.between(start, end).toMillis();

		System.out.println("Execution hash " + duration);

	}

}
