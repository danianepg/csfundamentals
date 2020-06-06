package leetcode.linkedlist;

/**
 * https://leetcode.com/problems/add-two-numbers/
 *
 * @author danianepg
 *
 */
public class AddTwoNumbers {

  static class ListNode {

    int val;

    ListNode next;

    ListNode(final int x) {
      this.val = x;
    }

    @Override
    public String toString() {
      return "ListNode [val=" + this.val + ", next=" + this.next + "]";
    }

  }

  public static ListNode addTwoNumbers(final ListNode l1, final ListNode l2) {

    Boolean reachedEndl1 = Boolean.FALSE;
    Boolean reachedEndl2 = Boolean.FALSE;

    ListNode tempNode1 = l1;
    ListNode tempNode2 = l2;

    while (!reachedEndl1 || !reachedEndl2) {

      final Integer tempSum = tempNode1.val + tempNode2.val;
      final Integer carry = tempSum / 10;
      final Integer rest = tempSum % 10;

      if (tempNode1.next != null) {
        tempNode1.next.val = tempNode1.next.val + carry;
      } else {
        if (carry > 0 || (tempNode2.next != null || tempNode1.next != null)) {
          tempNode1.next = new ListNode(carry);
        }
        reachedEndl1 = Boolean.TRUE;
      }
      tempNode1.val = rest;

      if (tempNode1.next != null) {
        tempNode1 = tempNode1.next;

      } else {
        tempNode2 = new ListNode(0);
        reachedEndl1 = Boolean.TRUE;
      }

      if (tempNode2.next != null) {
        tempNode2 = tempNode2.next;
      } else {
        tempNode2 = new ListNode(0);
        reachedEndl2 = Boolean.TRUE;
      }

    }

    return l1;
  }

  public static void writeNumber(final ListNode total) {

    String myNumber = "";

    ListNode temp = total;
    while (temp.next != null) {
      myNumber = String.valueOf(temp.val) + myNumber;
      temp = temp.next;
    }

    myNumber = String.valueOf(temp.val) + myNumber;
    System.out.println(myNumber);

  }

  public static void main(final String[] args) {

//    final ListNode l1 = new ListNode(2);
//    final ListNode l2 = new ListNode(4);
//    final ListNode l3 = new ListNode(6);
//    l1.next = l2;
//    l2.next = l3;
//
//    final ListNode l4 = new ListNode(5);
//    final ListNode l5 = new ListNode(6);
//    final ListNode l6 = new ListNode(6);
//    l4.next = l5;
//    l5.next = l6;
//    final ListNode total = addTwoNumbers(l1, l4);

//    final ListNode l1 = new ListNode(0);
//
//    final ListNode l2 = new ListNode(3);
//    final ListNode l3 = new ListNode(7);
//    l2.next = l3;
//    final ListNode total = addTwoNumbers(l1, l2);

    final ListNode l1 = new ListNode(9);
    final ListNode l2 = new ListNode(8);
    l1.next = l2;

    final ListNode l3 = new ListNode(1);
    final ListNode total = addTwoNumbers(l1, l3);

    writeNumber(total);
  }

}
