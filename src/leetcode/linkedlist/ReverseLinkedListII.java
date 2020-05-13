package leetcode.linkedlist;

import leetcode.util.ListNode;

/**
 * https://leetcode.com/problems/reverse-linked-list-ii/
 *
 * @author Daniane P. Gomes
 *
 *         Reverse a linked list from position m to n. Do it in one-pass.
 * 
 *         Note: 1 ≤ m ≤ n ≤ length of list.
 *
 *         Example:
 *
 *         Input: 1->2->3->4->5->NULL, m = 2, n = 4
 *         Output: 1->4->3->2->5->NULL
 *
 */
public class ReverseLinkedListII {

  public static ListNode reverseBetween(ListNode head, int m, int n) {

    ListNode prev = null;
    ListNode current = head;

    while (m > 1) {
      prev = current;
      current = current.next;
      m--;
      n--;
    }

    final ListNode ini = prev;
    final ListNode tail = current;

    ListNode aux = null;
    while (n > 0) {
      aux = current.next;
      current.next = prev;
      prev = current;
      current = aux;
      n--;
    }

    if (ini != null) {
      ini.next = prev;
    } else {
      head = prev;
    }

    tail.next = current;
    return head;
  }

  public static void main(final String[] args) {
    final ListNode head = new ListNode(1);
    final ListNode n1 = new ListNode(2);
    final ListNode n2 = new ListNode(3);
    final ListNode n3 = new ListNode(4);
    final ListNode n4 = new ListNode(5);

    head.next = n1;
    n1.next = n2;
    n2.next = n3;
    n3.next = n4;

    final int m = 2;
    final int n = 4;
    final ListNode lst = reverseBetween(head, m, n);
    System.out.println(lst);
  }
}
