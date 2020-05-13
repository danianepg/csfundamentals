package leetcode.stack;

/**
 * https://leetcode.com/problems/min-stack/
 *
 * @author z003xfbr
 *
 *         Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 *         push(x) -- Push element x onto stack.
 *         pop() -- Removes the element on top of the stack.
 *         top() -- Get the top element.
 *         getMin() -- Retrieve the minimum element in the stack.
 *
 *
 *         Example:
 *
 *         MinStack minStack = new MinStack();
 *         minStack.push(-2);
 *         minStack.push(0);
 *         minStack.push(-3);
 *         minStack.getMin(); --> Returns -3.
 *         minStack.pop();
 *         minStack.top(); --> Returns 0.
 *         minStack.getMin(); --> Returns -2.
 *
 */
public class MinStack {

  class Node {

    int value;

    int min;

    Node next;

    Node(final int value, final int min) {
      this.value = value;
      this.min = min;
      this.next = null;
    }

    @Override
    public String toString() {
      return "Node [val=" + this.value + "]";
    }

  }

  private Node minStack;

  public MinStack() {

  }

  public void push(final int x) {

    if (this.minStack == null) {
      this.minStack = new Node(x, x);
    } else {

      // Always add the elements at the beginning of the list, in order to create a stack
      // Always save the minimum element of the hole stack so its possible to retrieve it in O(1) time
      final Node n = new Node(x, Math.min(x, this.minStack.min));
      n.next = this.minStack;
      this.minStack = n;
    }

    System.out.println(this.minStack);

  }

  public void pop() {

    // As the elements are in a stack format, the next element is the previous on the stack, so I move the pointer to
    // the previous and discard the last
    if (this.minStack != null) {
      this.minStack = this.minStack.next;
    }

  }

  public int top() {

    if (this.minStack != null) {
      return this.minStack.value;
    }

    return Integer.MAX_VALUE;
  }

  public int getMin() {

    if (this.minStack != null) {
      return this.minStack.min;
    }

    return Integer.MIN_VALUE;
  }

  public static void main(final String[] args) {

    final MinStack minStack = new MinStack();
    minStack.push(2);
    minStack.push(4);
    minStack.push(1);
    System.out.println("MIN " + minStack.getMin());
    minStack.push(-1);
    minStack.push(3);
    System.out.println("MIN " + minStack.getMin());
    minStack.push(5);
    System.out.println("TOP " + minStack.top());
    minStack.pop();
    System.out.println("TOP After pop " + minStack.top());
    minStack.pop();
    System.out.println("TOP After pop " + minStack.top());

//    System.out.println("Min " + minStack.getMin()); // Returns -3.
//    minStack.pop();
//    System.out.println("Top " + minStack.top()); // Returns 0.
//    System.out.println("Min " + minStack.getMin()); // Returns -2.

  }

}
