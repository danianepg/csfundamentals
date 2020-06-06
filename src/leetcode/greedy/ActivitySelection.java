package leetcode.greedy;

/**
 * PROBLEM: You are given n activities with their start and finish times. Select the maximum number of activities that
 * can be performed by a single person, assuming that a person can only work on a single activity at a time.
 *
 * The greedy choice is to always pick the next activity whose finish time is least among the remaining activities and
 * the start time is more than or equal to the finish time of previously selected activity. We can sort the activities
 * according to their finishing time so that we always consider the next activity as minimum finishing time activity.
 *
 * 1) Sort the activities according to their finishing time
 * 2) Select the first activity from the sorted array and print it.
 * 3) Do following for remaining activities in the sorted array.
 * …….a) If the start time of this activity is greater than or equal to the finish time of previously selected activity
 * then select this activity and print it.
 *
 * @author danianepg
 *
 */
public class ActivitySelection {

  // Prints a maximum set of activities that can be done by a single
  // person, one at a time.
  // n --> Total number of activities
  // s[] --> An array that contains start time of all activities
  // f[] --> An array that contains finish time of all activities
  public static void printMaxActivities(final int s[], final int f[], final int n) {
    int i, j;

    System.out.print("Following activities are selected : n");

    // The first activity always gets selected
    i = 0;
    System.out.print(i + " ");

    // Consider rest of the activities
    for (j = 1; j < n; j++) {
      // If this activity has start time greater than or
      // equal to the finish time of previously selected
      // activity, then select it
      if (s[j] >= f[i]) {
        System.out.print(j + "{" + s[j] + "} ");
        i = j;
      }
    }
  }

  // driver program to test above function
  public static void main(final String[] args) {
    final int s[] = { 1, 3, 0, 5, 8, 5 };
    final int f[] = { 2, 4, 6, 7, 9, 9 };
    final int n = s.length;

    printMaxActivities(s, f, n);
  }

}