import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static com.google.common.truth.Truth.assertThat;
import java.util.List;

public class LinkedListDeque61BTest {

    // ========== 课程提供的测试 ==========

    @Test
    public void addFirstTestBasic() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addFirst("back");
        assertThat(lld1.toList()).containsExactly("back").inOrder();

        lld1.addFirst("middle");
        assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

        lld1.addFirst("front");
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    public void addLastTestBasic() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    public void addFirstAndAddLastTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

        assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
    }

    // ========== 我的测试 ==========

    @Test
    public void isemptyTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        assertThat(lld1.isEmpty()).isEqualTo(true);
        lld1.addFirst("WhatImdoingnow");
        assertThat(lld1.isEmpty()).isEqualTo(false);
    }

    @Test
    public void size_getFirst_getLast_Test() {
        Deque61B<Integer> lld = new LinkedListDeque61B<>();
        assertThat(lld.size()).isEqualTo(0);
        assertThat(lld.getFirst()).isEqualTo(null);
        assertThat(lld.getLast()).isEqualTo(null);
        lld.addFirst(1);
        assertThat(lld.size()).isEqualTo(1);
        lld.addLast(6);
        assertThat(lld.getLast()).isEqualTo(6);
        lld.addFirst(8);
        assertThat(lld.size()).isEqualTo(3);
        assertThat(lld.getFirst()).isEqualTo(8);
    }

    @Test
    public void getRecursiveOrNotTest() {
        Deque61B<Integer> lld = new LinkedListDeque61B<>();
        assertThat(lld.get(0)).isEqualTo(null);
        assertThat(lld.getRecursive(0)).isEqualTo(null);
        lld.addLast(0);   // [0]
        lld.addLast(1);   // [0, 1]
        lld.addFirst(-1); // [-1, 0, 1]
        lld.addLast(2);   // [-1, 0, 1, 2]
        lld.addFirst(-2); // [-2, -1, 0, 1, 2]
        assertThat(lld.get(4)).isEqualTo(2);
        assertThat(lld.getRecursive(4)).isEqualTo(2);
        assertThat(lld.get(1)).isEqualTo(-1);
        assertThat(lld.getRecursive(1)).isEqualTo(-1);
        assertThat(lld.get(5)).isEqualTo(null);
        assertThat(lld.getRecursive(5)).isEqualTo(null);
        assertThat(lld.get(-1)).isEqualTo(null);
        assertThat(lld.getRecursive(-2)).isEqualTo(null);
    }

    @Test
    public void removeFirstLastTest() {
        Deque61B<Integer> lld = new LinkedListDeque61B<>();
        assertThat(lld.removeFirst()).isEqualTo(null);
        assertThat(lld.removeFirst()).isEqualTo(null);
        lld.addLast(0);   // [0]
        lld.addLast(1);   // [0, 1]
        lld.addFirst(-1); // [-1, 0, 1]
        lld.addLast(2);   // [-1, 0, 1, 2]
        lld.addFirst(-2); // [-2,-1, 0, 1, 2]
        assertThat(lld.removeLast()).isEqualTo(2);
        assertThat(lld.toList()).containsExactly(-2, -1, 0, 1).inOrder();
        assertThat(lld.removeFirst()).isEqualTo(-2);
        assertThat(lld.toList()).containsExactly(-1, 0, 1).inOrder();
    }

    @Test
    public void finalTest() {
        Deque61B<String> lld = new LinkedListDeque61B<>();
        assertThat(lld.size()).isEqualTo(0);
        assertThat(lld.removeFirst()).isEqualTo(null);
        assertThat(lld.removeLast()).isEqualTo(null);
        assertThat(lld.isEmpty()).isEqualTo(true);
        assertThat(lld.getLast()).isEqualTo(null);
        assertThat(lld.getFirst()).isEqualTo(null);
        assertThat(lld.toList()).isEmpty();
    }

    // ========== addFirst 测试 ==========

    @Test
    public void testAddFirstFromEmpty() {
        LinkedListDeque61B<String> deque = new LinkedListDeque61B<>();

        deque.addFirst("first");

        List<String> expected = List.of("first");
        assertEquals(expected, deque.toList());
        assertEquals("first", deque.get(0));
        assertNull(deque.get(1));
    }

    @Test
    public void testAddFirstNonEmpty() {
        LinkedListDeque61B<String> deque = new LinkedListDeque61B<>();
        deque.addLast("second");
        deque.addLast("third");

        deque.addFirst("first");

        List<String> expected = List.of("first", "second", "third");
        assertEquals(expected, deque.toList());
        assertEquals("first", deque.get(0));
        assertEquals("second", deque.get(1));
        assertEquals("third", deque.get(2));
    }

    // ========== addLast 测试 ==========

    @Test
    public void testAddLastFromEmpty() {
        LinkedListDeque61B<String> deque = new LinkedListDeque61B<>();

        deque.addLast("first");

        List<String> expected = List.of("first");
        assertEquals(expected, deque.toList());
        assertEquals("first", deque.get(0));
    }

    @Test
    public void testAddLastNonEmpty() {
        LinkedListDeque61B<String> deque = new LinkedListDeque61B<>();
        deque.addFirst("first");
        deque.addFirst("second");

        deque.addLast("third");

        List<String> expected = List.of("second", "first", "third");
        assertEquals(expected, deque.toList());
        assertEquals("second", deque.get(0));
        assertEquals("first", deque.get(1));
        assertEquals("third", deque.get(2));
    }

    // ========== remove 后 add 测试 ==========

    @Test
    public void testAddFirstAfterRemoveToEmpty() {
        LinkedListDeque61B<String> deque = new LinkedListDeque61B<>();

        deque.addFirst("first");
        deque.addLast("second");
        deque.removeFirst();
        deque.removeFirst();

        assertTrue(deque.toList().isEmpty());

        deque.addFirst("new");
        List<String> expected = List.of("new");
        assertEquals(expected, deque.toList());
    }

    @Test
    public void testAddLastAfterRemoveToEmpty() {
        LinkedListDeque61B<String> deque = new LinkedListDeque61B<>();

        deque.addFirst("first");
        deque.addLast("second");
        deque.removeLast();
        deque.removeLast();

        assertTrue(deque.toList().isEmpty());

        deque.addLast("new");
        List<String> expected = List.of("new");
        assertEquals(expected, deque.toList());
    }

    // ========== remove 测试 ==========

    @Test
    public void testRemoveFirst() {
        LinkedListDeque61B<String> deque = new LinkedListDeque61B<>();
        deque.addLast("first");
        deque.addLast("second");
        deque.addLast("third");

        String removed = deque.removeFirst();
        assertEquals("first", removed);

        List<String> expected1 = List.of("second", "third");
        assertEquals(expected1, deque.toList());

        assertEquals("second", deque.removeFirst());
        assertEquals("third", deque.removeFirst());

        assertNull(deque.removeFirst());
    }

    @Test
    public void testRemoveLast() {
        LinkedListDeque61B<String> deque = new LinkedListDeque61B<>();
        deque.addFirst("first");
        deque.addFirst("second");
        deque.addFirst("third");

        String removed = deque.removeLast();
        assertEquals("first", removed);

        List<String> expected1 = List.of("third", "second");
        assertEquals(expected1, deque.toList());

        assertEquals("second", deque.removeLast());
        assertEquals("third", deque.removeLast());

        assertNull(deque.removeLast());
    }

    // ========== get 测试 ==========

    @Test
    public void testGetValidIndex() {
        LinkedListDeque61B<String> deque = new LinkedListDeque61B<>();
        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("c");

        assertEquals("a", deque.get(0));
        assertEquals("b", deque.get(1));
        assertEquals("c", deque.get(2));
    }

    @Test
    public void testGetInvalidIndex() {
        LinkedListDeque61B<String> deque = new LinkedListDeque61B<>();
        deque.addLast("a");

        assertNull(deque.get(-1));
        assertNull(deque.get(1));
        assertNull(deque.get(100));
    }

    // ========== getRecursive 测试 ==========

    @Test
    public void testGetRecursiveValid() {
        LinkedListDeque61B<String> deque = new LinkedListDeque61B<>();
        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("c");

        assertEquals("a", deque.getRecursive(0));
        assertEquals("b", deque.getRecursive(1));
        assertEquals("c", deque.getRecursive(2));
    }

    @Test
    public void testGetRecursiveInvalid() {
        LinkedListDeque61B<String> deque = new LinkedListDeque61B<>();
        deque.addLast("a");

        assertNull(deque.getRecursive(-1));
        assertNull(deque.getRecursive(1));
    }

    @Test
    public void testGetRecursiveEmpty() {
        LinkedListDeque61B<String> deque = new LinkedListDeque61B<>();
        assertNull(deque.getRecursive(0));
    }

    // ========== toList 测试 ==========

    @Test
    public void testToListEmpty() {
        LinkedListDeque61B<String> deque = new LinkedListDeque61B<>();
        assertTrue(deque.toList().isEmpty());
    }

    @Test
    public void testToListNonEmpty() {
        LinkedListDeque61B<String> deque = new LinkedListDeque61B<>();
        deque.addLast("x");
        deque.addLast("y");
        deque.addFirst("w");

        List<String> expected = List.of("w", "x", "y");
        assertEquals(expected, deque.toList());
    }

    // ========== 混合操作测试 ==========

    @Test
    public void testMixedOperations() {
        LinkedListDeque61B<String> deque = new LinkedListDeque61B<>();

        deque.addLast("b");
        deque.addFirst("a");
        deque.addLast("c");
        assertEquals(List.of("a", "b", "c"), deque.toList());

        deque.removeLast();
        assertEquals(List.of("a", "b"), deque.toList());

        deque.addFirst("0");
        assertEquals(List.of("0", "a", "b"), deque.toList());

        deque.removeFirst();
        deque.removeFirst();
        deque.removeFirst();
        assertTrue(deque.toList().isEmpty());

        deque.addLast("new");
        assertEquals(List.of("new"), deque.toList());
    }

    // ========== 边界测试 ==========

    @Test
    public void testRemoveFirstFromSingleElement() {
        LinkedListDeque61B<String> deque = new LinkedListDeque61B<>();
        deque.addFirst("only");

        assertEquals("only", deque.removeFirst());
        assertTrue(deque.toList().isEmpty());
        assertNull(deque.removeFirst());
    }

    @Test
    public void testRemoveLastFromSingleElement() {
        LinkedListDeque61B<String> deque = new LinkedListDeque61B<>();
        deque.addLast("only");

        assertEquals("only", deque.removeLast());
        assertTrue(deque.toList().isEmpty());
        assertNull(deque.removeLast());
    }

    @Test
    public void testAlternatingAddRemove() {
        LinkedListDeque61B<String> deque = new LinkedListDeque61B<>();
        deque.addFirst("a");
        deque.addLast("b");
        deque.removeFirst();
        deque.addFirst("c");
        deque.removeLast();

        assertEquals(List.of("c"), deque.toList());
    }
}