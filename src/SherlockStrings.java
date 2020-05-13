package hackerrank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class SherlockStrings {

  public class Letters {

    private String letter;

    private Integer count;

    public Letters(final String letter, final Integer count) {
      this.letter = letter;
      this.count = count;
    }

    public String getLetter() {
      return this.letter;
    }

    public void setLetter(final String letter) {
      this.letter = letter;
    }

    public Integer getCount() {
      return this.count;
    }

    public void setCount(final Integer count) {
      this.count = count;
    }

  }

  static String isValid(final String s) {

    final String GOOD = "YES";
    final String BAD = "NO";

    if (s.isEmpty()) {
      return BAD;
    }
    if (s.length() <= 3) {
      return GOOD;
    }

    final int[] letters = new int[26];
    for (int i = 0; i < s.length(); i++) {
      letters[s.charAt(i) - 'a']++;
    }
    Arrays.sort(letters);
    int i = 0;
    while (letters[i] == 0) {
      i++;
    }
    // System.out.println(Arrays.toString(letters));
    final int min = letters[i]; // the smallest frequency of some letter
    final int max = letters[25]; // the largest frequency of some letter
    String ret = BAD;
    if (min == max) {
      ret = GOOD;
    } else {
      // remove one letter at higher frequency or the lower frequency
      if (((max - min == 1) && (max > letters[24])) || (min == 1) && (letters[i + 1] == max)) {
        ret = GOOD;
      }
    }
    return ret;
  }

  public static void main(final String[] args) throws IOException {

    final File file = new File(
        "E:\\workspace\\csfundamentals\\src\\csfundamentals\\hackerrank\\sherlock-and-valid-string-testcases\\input\\input00.txt");

    final BufferedReader br = new BufferedReader(new FileReader(file));

    final String myStr = br.readLine();
    final String result = isValid(myStr);
    System.out.println("RESULT " + result);

    br.close();

  }
}
