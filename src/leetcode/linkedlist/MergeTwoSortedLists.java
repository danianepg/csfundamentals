package leetcode.linkedlist;

import leetcode.util.ListNode;

/**
 * https://leetcode.com/problems/merge-two-sorted-lists/
 *
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes
 * of the first two lists.
 *
 * Example:
 *
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 *
 * @author danianepg
 *
 */
public class MergeTwoSortedLists {

  public static ListNode mergeTwoLists(final ListNode l1, final ListNode l2) {

    if (l1 != null && l2 == null) {
      return l1;
    }

    if (l1 == null && l2 != null) {
      return l2;
    }

    ListNode newHead = null;
    ListNode newCur = null;
    ListNode cur1 = l1;
    ListNode cur2 = l2;

    while (cur1 != null || cur2 != null) {

      int curVal = 0;

      if (cur1 != null && cur2 == null) {
        curVal = cur1.val;
        cur1 = cur1.next;
      } else if (cur1 == null && cur2 != null) {
        curVal = cur2.val;
        cur2 = cur2.next;
      } else if (cur1.val < cur2.val) {
        curVal = cur1.val;
        cur1 = cur1.next;
      } else {
        curVal = cur2.val;
        cur2 = cur2.next;
      }

      final ListNode aux = new ListNode(curVal);

      if (newHead == null) {
        newHead = aux;
        newCur = newHead;
      } else {
        newCur.next = aux;
        newCur = aux;
      }

    }

    return newHead;
  }

  public static void main(final String[] args) {
    final ListNode head = new ListNode(1);
    final ListNode n1 = new ListNode(2);
    final ListNode n2 = new ListNode(4);
    head.next = n1;
    n1.next = n2;

    final ListNode head2 = new ListNode(1);
    final ListNode n3 = new ListNode(3);
    final ListNode n4 = new ListNode(4);
    head2.next = n3;
    n3.next = n4;

    final ListNode lst = mergeTwoLists(head, head2);
    System.out.println(lst);
  }

}
