package leetcode.backtracking_dfs_bfs;

/**
 * https://leetcode.com/problems/flood-fill/
 * Easy
 *
 * @author z003xfbr
 *
 *         An image is represented by a 2-D array of integers, each integer representing the pixel value of the image
 *         (from 0 to 65535).
 *
 *         Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel
 *         value newColor, "flood fill" the image.
 *
 *         To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the
 *         starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those
 *         pixels (also with the same color as the starting pixel), and so on. Replace the color of all of the
 *         aforementioned pixels with the newColor.
 *
 *         At the end, return the modified image.
 *
 *         Example 1:
 *         Input:
 *         image = [[1,1,1],[1,1,0],[1,0,1]]
 *         sr = 1, sc = 1, newColor = 2
 *         Output: [[2,2,2],[2,2,0],[2,0,1]]
 *         Explanation:
 *         From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected
 *         by a path of the same color as the starting pixel are colored with the new color.
 *         Note the bottom corner is not colored 2, because it is not 4-directionally connected
 *         to the starting pixel.
 *         Note:
 *
 *         The length of image and image[0] will be in the range [1, 50].
 *         The given starting pixel will satisfy 0 <= sr < image.length and 0 <= sc < image[0].length.
 *         The value of each color in image[i][j] and newColor will be an integer in [0, 65535].
 *
 */
public class FloodFill {

  /**
   * An example of the use of DFS but taking in account the previous and next row and the previous and next column.
   *
   * We create an extra method to make the actual changes, since we receive and starting point (row and column). If at
   * this point the colour is different from the new colour, we trigger the recursion taking in account the original
   * colour. The recursion will be called for the the 4 dimensions recursively only if the element we are looking at has
   * the same colour as the element that triggered the recursion.
   *
   * Start if the colours are different and triggers the recursion if the colours are the same.
   *
   * @param image
   * @param sr
   * @param sc
   * @param newColor
   * @return
   */
  public static int[][] floodFill(final int[][] image, final int sr, final int sc, final int newColor) {

    if (image == null) {
      return new int[0][0];
    }

    final int color = image[sr][sc];
    if (color != newColor) {
      floodFillChange(image, sr, sc, color, newColor);
    }

    return image;
  }

  public static void floodFillChange(final int[][] image, final int sr, final int sc, final int color,
      final int newColor) {

    if (image[sr][sc] == color) {
      image[sr][sc] = newColor;

      final int prevRow = sr - 1;
      if (prevRow >= 0) {
        floodFillChange(image, prevRow, sc, color, newColor);
      }

      final int prevCol = sc - 1;
      if (prevCol >= 0) {
        floodFillChange(image, sr, prevCol, color, newColor);
      }

      final int nextRow = sr + 1;
      if (nextRow < image.length) {
        floodFillChange(image, sr + 1, sc, color, newColor);
      }

      final int nextCol = sc + 1;
      if (nextCol < image[0].length) {
        floodFillChange(image, sr, nextCol, color, newColor);
      }
    }

  }

  public static void printFloodFill(final int[][] newImage) {

    for (final int[] element : newImage) {
      for (int col = 0; col < newImage.length; col++) {
        System.out.print(" | " + element[col] + " | ");
      }
      System.out.println("");
    }

  }

  public static void main(final String[] args) {
    final int[][] image = { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } };
    printFloodFill(image);

    System.out.println("\n");

    final int[][] newImage = floodFill(image, 1, 1, 2);
    printFloodFill(newImage);

  }

}
