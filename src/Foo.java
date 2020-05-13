package hackerrank;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Foo {

  public PriorityQueue<InternalFoo> getHeapOrderAsc() {
    final Comparator<InternalFoo> SORT_BY_START = Comparator.comparingInt(InternalFoo::getStart);
    final PriorityQueue<InternalFoo> heapOrderByAttributeStart = new PriorityQueue<>(SORT_BY_START);
    return heapOrderByAttributeStart;
  }

  public PriorityQueue<InternalFoo> getHeapOrderDesc() {
    final Comparator<InternalFoo> SORT_BY_END = Comparator.comparingInt(InternalFoo::getEnd);
    final PriorityQueue<InternalFoo> heapOrderByAttributeEnd = new PriorityQueue<>(SORT_BY_END);
    return heapOrderByAttributeEnd;
  }

  public static class InternalFoo {

    private int start;

    private int end;

    public int getStart() {
      return this.start;
    }

    public int getEnd() {
      return this.end;
    }

  }

}