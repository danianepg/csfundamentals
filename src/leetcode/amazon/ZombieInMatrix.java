package leetcode.amazon;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/discuss/interview-question/411357/
 *
 * Given a 2D grid, each cell is either a zombie 1 or a human 0. Zombies can turn adjacent (up/down/left/right) human
 * beings into zombies every hour. Find out how many hours does it take to infect all humans?
 *
 * @author z003xfbr
 *
 */
public class ZombieInMatrix {

  // O( (row * col)^2 )
  public int minHours(final int[][] grid) {

    if (grid == null || grid.length <= 0 || grid[0] == null || grid[0].length <= 0) {
      return -1;
    }

    int hours = 0;
    final int population = grid.length * grid[0].length;
    final int[][] directions = new int[][] { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
    final Queue<int[]> queue = this.getQueue(grid);
    int numberHumans = population - queue.size();

    // Everybody is infected
    if (numberHumans == 0) {
      return 0;
    }

    // No zombies found
    if (numberHumans == population) {
      return -1;
    }

    // While zombies and humans already exist
    while (!queue.isEmpty() && numberHumans > 0) {

      // Increments processing time only when the queue grows, meaning that new zombies where transformed, so on the
      // next round they will transform someone else.
      hours++;

      for (int i = 0; i < queue.size(); i++) {

        // Where the zombie is sitting
        final int[] zombie = queue.poll();
        System.out.println("\nZOMBIE [" + zombie[0] + ", " + zombie[1] + "]");

        // Calculates all surroundings
        for (final int[] direction : directions) {

          // Up or down
          final int newX = zombie[0] + direction[0];

          // left or right
          final int newY = zombie[1] + direction[1];

          if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && grid[newX][newY] == 0) {
            grid[newX][newY] = 1;
            System.out.println("TRANSFORMADO [" + newX + ", " + newY + "]");

            // Process this new zombie on the next iteration
            queue.offer(new int[] { newX, newY });
            numberHumans--;

            // A little optimization. If there aren't humans anymore, we can return hours.
            if (numberHumans == 0) {
              return hours;
            }
          }

        }

      }

    }

    return numberHumans == 0 ? hours : -1;

  }

  private Queue<int[]> getQueue(final int[][] grid) {
    final Queue<int[]> queue = new LinkedList<>();

    // Find all zombies and save its positions to the queue.
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 1) {
          queue.offer(new int[] { i, j });
        }
      }
    }
    return queue;
  }

  public static void main(final String[] args) {

//    final int[][] grid = { { 0, 1, 1, 0, 1 }, { 0, 1, 0, 1, 0 }, { 0, 0, 0, 0, 1 }, { 0, 1, 0, 0, 0 } };
//    final int[][] grid1 = { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 } };
//    final int[][] grid2 = { { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 } };
//    final int[][] grid3 = { {}, {}, {}, {} };
//    final int[][] grid4 = { { 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 } };
//    final int[][] grid5 = { { 0, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 } };
    final int[][] grid6 = { { 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 } };

    final ZombieInMatrix zombie = new ZombieInMatrix();
    System.out.println("HOURS to Apocalipse: " + zombie.minHours(grid6));

  }

}
