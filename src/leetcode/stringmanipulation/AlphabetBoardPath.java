package leetcode.stringmanipulation;

/**
 * https://leetcode.com/problems/alphabet-board-path/
 *
 * @author Daniane P. Gomes
 *
 *         On an alphabet board, we start at position (0, 0), corresponding to character board[0][0].
 * 
 *         Here, board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"], as shown in the diagram below.
 * 
 * 
 * 
 *         We may make the following moves:
 * 
 *         'U' moves our position up one row, if the position exists on the board;
 *         'D' moves our position down one row, if the position exists on the board;
 *         'L' moves our position left one column, if the position exists on the board;
 *         'R' moves our position right one column, if the position exists on the board;
 *         '!' adds the character board[r][c] at our current position (r, c) to the answer.
 *         (Here, the only positions that exist on the board are positions with letters on them.)
 * 
 *         Return a sequence of moves that makes our answer equal to target in the minimum number of moves. You may
 *         return any path that does so.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: target = "leet"
 *         Output: "DDR!UURRR!!DDD!"
 *         Example 2:
 * 
 *         Input: target = "code"
 *         Output: "RR!DDRR!UUL!R!"
 * 
 * 
 *         Constraints:
 * 
 *         1 <= target.length <= 100
 *         target consists only of English lowercase letters.
 *
 */
public class AlphabetBoardPath {

  public static String alphabetBoardPath(final String target) {

    int curRow = 0;
    int curCol = 0;

    final StringBuilder command = new StringBuilder();

    for (final char s : target.toCharArray()) {

      final StringBuilder rowSb = new StringBuilder();
      final StringBuilder colSb = new StringBuilder();

      final int posS = s - 'a';
      final int targetRow = posS / 5;
      final int targetCol = posS % 5;

      System.out.println(s + " pos  " + posS + " target " + targetRow + " - " + targetCol);

      String addRow = "";
      if (curRow < targetRow) {
        addRow = "D";
      } else if (curRow > targetRow) {
        addRow = "U";
      }

      String addCol = "";
      if (curCol > targetCol) {
        addCol = "L";
      } else if (curCol < targetCol) {
        addCol = "R";
      }

      if (!addRow.isEmpty()) {
        for (int i = 0; i < Math.abs(curRow - targetRow); i++) {
          rowSb.append(addRow);
        }
      }

      if (!addCol.isEmpty()) {
        for (int i = 0; i < Math.abs(curCol - targetCol); i++) {
          colSb.append(addCol);
        }
      }

      if (s == 'z' && curCol > 0) {
        String tempRow = rowSb.toString();
        tempRow = tempRow.substring(0, tempRow.length() - 1);
        command.append(tempRow).append(colSb).append("D");

      } else {
        command.append(rowSb).append(colSb);
      }
      command.append("!");

      System.out.println(command);

      curRow = targetRow;
      curCol = targetCol;

    }

    return command.toString();

  }

  public static void main(final String[] args) {
    final String command = alphabetBoardPath("zdz");
    System.out.println(command);
    System.out.println("DDDDD!UUUUURRR!DDDDLLLD!");
    System.out.println(command.equals("DDDDD!UUUUURRR!DDDDLLLD!"));
  }
}
