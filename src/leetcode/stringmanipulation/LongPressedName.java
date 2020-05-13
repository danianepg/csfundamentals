package leetcode.stringmanipulation;

public class LongPressedName {

  public static boolean isLongPressedName(final String name, final String typed) {

    if (name.equals(typed)) {
      return true;
    }

    if (name.length() > typed.length()) {
      return false;
    }

    int index = 0;

    for (final char c : name.toCharArray()) {

      if (index == typed.length()) {
        return false;
      }

      final char temp = typed.charAt(index);

      if (c != temp) {

        if (index == 0 || typed.charAt(index - 1) != temp) {
          return false;
        }

        while (index < typed.length() && temp == typed.charAt(index)) {
          index++;
        }

        if (index == typed.length() || typed.charAt(index) != c) {
          return false;
        }

      }
      index++;

    }

    return true;
  }

  public static void main(final String[] args) {

    // dani
    // dannia

//    final String name = "alex";
//    final String typed = "aaleex"; // true

//    final String name = "saeed";
//    final String typed = "ssaaedd"; // false
//
//    final String name = "leelee";
//    final String typed = "lleeelee"; // true

//    final String name = "laiden";
//    final String typed = "laiden"; // true

//    final String name = "xnhtq";
//    final String typed = "xhhttqq"; // false

    final String name = "vtkgn";
    final String typed = "vttkgnn"; // true

//    final String name = "pyplrz";
//    final String typed = "ppyypllr"; // false

//    final String name = "dfuyalc";
//    final String typed = "fuuyallc"; // false

    System.out.println(isLongPressedName(name, typed));

  }

}
