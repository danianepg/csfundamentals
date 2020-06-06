package leetcode.linkedlist;

import java.util.Stack;

import leetcode.util.ListNode;

/**
 * https://leetcode.com/problems/add-two-numbers-ii/
 *
 * @author danianepg
 *
 */
public class AddTwoNumbersII {

  public static ListNode addTwoNumbers(final ListNode l1, final ListNode l2) {

    final Stack<Integer> s1 = new Stack<>();
    final Stack<Integer> s2 = new Stack<>();

    ListNode tempNode = l1;
    while (tempNode != null) {
      s1.add(tempNode.val);
      tempNode = tempNode.next;
    }

    tempNode = l2;
    while (tempNode != null) {
      s2.add(tempNode.val);
      tempNode = tempNode.next;
    }

    int carry = 0;
    ListNode lastDigit = null;

    while (!s1.isEmpty() || !s2.isEmpty()) {
      int op1 = 0;
      int op2 = 0;

      if (!s1.isEmpty() && s1.peek() != null) {
        op1 = s1.pop();
      }

      if (!s2.isEmpty() && s2.peek() != null) {
        op2 = s2.pop();
      }

      int result = op1 + op2 + carry;

      if (result > 9) {
        carry = result / 10;
        result = result % 10;
      } else {
        carry = 0;
      }

      if (lastDigit == null) {
        lastDigit = new ListNode(result);
      } else {
        final ListNode temp = new ListNode(result);
        temp.next = lastDigit;
        lastDigit = temp;
      }

    }

    if (carry > 0) {
      if (lastDigit == null) {
        lastDigit = new ListNode(carry);
      } else {
        final ListNode temp = new ListNode(carry);
        temp.next = lastDigit;
        lastDigit = temp;
      }
    }

    return lastDigit;

  }

  public static void main(final String[] args) {

    final ListNode l1 = new ListNode(7);
    final ListNode l2 = new ListNode(2);
    final ListNode l3 = new ListNode(4);
    final ListNode l4 = new ListNode(3);

    l1.next = l2;
    l2.next = l3;
    l3.next = l4;

    final ListNode l5 = new ListNode(5);
    final ListNode l6 = new ListNode(6);
    final ListNode l7 = new ListNode(4);

    l5.next = l6;
    l6.next = l7;

    System.out.println(addTwoNumbers(l1, l5));

  }

}
