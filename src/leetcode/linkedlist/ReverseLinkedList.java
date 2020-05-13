package leetcode.linkedlist;

import leetcode.util.ListNode;

/**
 * https://leetcode.com/problems/reverse-linked-list/
 *
 * @author Daniane P. Gomes
 *
 *         Reverse a singly linked list.
 *
 *         Example:
 *
 *         Input: 1->2->3->4->5->NULL
 *         Output: 5->4->3->2->1->NULL
 *
 *         Follow up:
 *
 *         A linked list can be reversed either iteratively or recursively.
 *         Could you implement both?
 *
 */
public class ReverseLinkedList {

  public static ListNode reverseList(final ListNode head) {

    // ListNode temp = reverseListIteative(head);
    final ListNode temp2 = reverseListRecursive(head, null);
    return temp2;
  }

  public static ListNode reverseListIteative(final ListNode head) {

    ListNode prev = null;
    ListNode current = head;

    while (current != null) {

      final ListNode temp = current.next;
      current.next = prev;
      prev = current;
      current = temp;

    }

    return prev;

  }

  public static ListNode reverseListRecursive(final ListNode head, final ListNode newHead) {
    if (head == null) {
      return newHead;
    }

    final ListNode next = head.next;
    head.next = newHead;
    return reverseListRecursive(next, head);
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

    final ListNode lst = reverseList(head);
    System.out.println(lst);
  }

}
