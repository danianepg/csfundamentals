package leetcode.stringmanipulation;

/**
 * https://leetcode.com/discuss/interview-question/396769/google-oa-2019-maximum-time
 *
 * @author z003xfbr
 *
 */
public class MaximumTime {

  public static String maxTime(final String time) {

    final StringBuilder timeStr = new StringBuilder();
    final char[] maxTime = "23:59".toCharArray();
    final char[] timeArr = time.toCharArray();
    Boolean isHours = Boolean.TRUE;
    int i = 0;

    while (i < timeArr.length - 1) {

      final char curChar = (timeArr[i]);
      final char nextChar = (timeArr[i + 1]);

      if (curChar != '?') {
        maxTime[i] = curChar;
      }

      if (nextChar != '?') {
        maxTime[i + 1] = nextChar;
      }

      if (isHours && curChar == '?') {
        maxTime[i] = (nextChar != '?' && nextChar > 3) ? '1' : '2';
        maxTime[i + 1] = nextChar;
      }

      if (isHours && nextChar == '?') {
        maxTime[i + 1] = (curChar == '2' || curChar == '?') ? '3' : '9';
      }

      isHours = Boolean.FALSE;
      i = i + 3;

    }

    for (i = 0; i < maxTime.length; i++) {
      timeStr.append(maxTime[i]);
    }

    return time + " -> " + timeStr.toString();
  }

  public static void main(final String[] args) {
    System.out.println(maxTime("?4:5?"));
    System.out.println(maxTime("?3:??"));
    System.out.println(maxTime("23:5?"));
    System.out.println(maxTime("2?:22"));
    System.out.println(maxTime("0?:??"));
    System.out.println(maxTime("1?:??"));
    System.out.println(maxTime("??:??"));
    System.out.println(maxTime("?4:0?"));
    System.out.println(maxTime("?9:4?"));
  }

}
