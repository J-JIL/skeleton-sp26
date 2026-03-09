import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class ArrayDeque61BTest {

    @Test
    @DisplayName("Test addFirst from empty")
    public void testAddFirstFromEmpty() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        deque.addFirst("a");
        assertEquals(1, deque.size());
        assertEquals("a", deque.get(0));
    }

    @Test
    @DisplayName("Test addLast from empty")
    public void testAddLastFromEmpty() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        deque.addLast("a");
        assertEquals(1, deque.size());
        assertEquals("a", deque.get(0));
    }

    @Test
    @DisplayName("Test addFirst non-empty")
    public void testAddFirstNonEmpty() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        deque.addLast("b");
        deque.addLast("c");
        deque.addFirst("a");
        assertEquals(3, deque.size());
        assertEquals("a", deque.get(0));
        assertEquals("b", deque.get(1));
        assertEquals("c", deque.get(2));
    }

    @Test
    @DisplayName("Test addLast non-empty")
    public void testAddLastNonEmpty() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        deque.addFirst("a");
        deque.addFirst("b");
        deque.addLast("c");
        assertEquals(3, deque.size());
        assertEquals("b", deque.get(0));
        assertEquals("a", deque.get(1));
        assertEquals("c", deque.get(2));
    }

    @Test
    @DisplayName("Test addFirst trigger resize")
    public void testAddFirstTriggerResize() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        // 添加超过8个元素触发扩容
        for (int i = 0; i < 20; i++) {
            deque.addFirst(i);
        }
        assertEquals(20, deque.size());
        // 验证元素都在
        for (int i = 0; i < 20; i++) {
            assertEquals(19 - i, deque.get(i));
        }
    }

    @Test
    @DisplayName("Test addLast trigger resize")
    public void testAddLastTriggerResize() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        // 添加超过8个元素触发扩容
        for (int i = 0; i < 20; i++) {
            deque.addLast(i);
        }
        assertEquals(20, deque.size());
        // 验证元素都在
        for (int i = 0; i < 20; i++) {
            assertEquals(i, deque.get(i));
        }
    }

    @Test
    @DisplayName("Test addFirst after remove to empty")
    public void testAddFirstAfterRemoveToEmpty() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        // 添加然后移除所有
        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("c");
        deque.removeFirst();
        deque.removeFirst();
        deque.removeFirst();
        assertTrue(deque.isEmpty());

        // 再添加应该正常工作
        deque.addFirst("x");
        assertEquals(1, deque.size());
        assertEquals("x", deque.get(0));
    }

    @Test
    @DisplayName("Test addLast after remove to empty")
    public void testAddLastAfterRemoveToEmpty() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        // 添加然后移除所有
        deque.addFirst("a");
        deque.addFirst("b");
        deque.addFirst("c");
        deque.removeLast();
        deque.removeLast();
        deque.removeLast();
        assertTrue(deque.isEmpty());

        // 再添加应该正常工作
        deque.addLast("x");
        assertEquals(1, deque.size());
        assertEquals("x", deque.get(0));
    }

    @Test
    @DisplayName("Test removeFirst")
    public void testRemoveFirst() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("c");

        assertEquals("a", deque.removeFirst());
        assertEquals(2, deque.size());
        assertEquals("b", deque.get(0));
    }

    @Test
    @DisplayName("Test removeLast")
    public void testRemoveLast() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        deque.addFirst("a");
        deque.addFirst("b");
        deque.addFirst("c");

        assertEquals("a", deque.removeLast());
        assertEquals(2, deque.size());
        assertEquals("c", deque.get(0));
        assertEquals("b", deque.get(1));
    }

    @Test
    @DisplayName("Test removeFirst to empty")
    public void testRemoveFirstToEmpty() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("c");

        assertEquals("a", deque.removeFirst());
        assertEquals("b", deque.removeFirst());
        assertEquals("c", deque.removeFirst());  // 最后一个
        assertTrue(deque.isEmpty());
    }

    @Test
    @DisplayName("Test removeLast to empty")
    public void testRemoveLastToEmpty() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        deque.addFirst("a");
        deque.addFirst("b");
        deque.addFirst("c");

        assertEquals("a", deque.removeLast());
        assertEquals("b", deque.removeLast());
        assertEquals("c", deque.removeLast());  // 最后一个
        assertTrue(deque.isEmpty());
    }

    @Test
    @DisplayName("Test removeFirst to one")
    public void testRemoveFirstToOne() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("c");

        assertEquals("a", deque.removeFirst());  // 剩 [b,c]
        assertEquals("b", deque.removeFirst());  // 剩 [c]
        assertEquals(1, deque.size());
        assertEquals("c", deque.get(0));
    }

    @Test
    @DisplayName("Test removeLast to one")
    public void testRemoveLastToOne() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        deque.addFirst("a");
        deque.addFirst("b");
        deque.addFirst("c");

        assertEquals("a", deque.removeLast());  // 剩 [c,b]
        assertEquals("b", deque.removeLast());  // 剩 [c]
        assertEquals(1, deque.size());
        assertEquals("c", deque.get(0));
    }

    @Test
    @DisplayName("Test removeFirst trigger resize")
    public void testRemoveFirstTriggerResize() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        // 先添加很多元素触发扩容
        for (int i = 0; i < 20; i++) {
            deque.addLast(i);
        }

        // 然后移除直到触发缩容 (使用率 <= 25%)
        // 初始size=20, 需要移除到 <=5 个元素
        for (int i = 0; i < 15; i++) {
            deque.removeFirst();
        }

        // 此时size=5，应该已经触发缩容
        assertEquals(5, deque.size());
        // 验证剩余元素正确
        for (int i = 0; i < 5; i++) {
            assertEquals(i + 15, deque.get(i));
        }
    }

    @Test
    @DisplayName("Test removeLast trigger resize")
    public void testRemoveLastTriggerResize() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        // 先添加很多元素触发扩容
        for (int i = 0; i < 20; i++) {
            deque.addFirst(i);
        }

        // 然后移除直到触发缩容
        for (int i = 0; i < 15; i++) {
            deque.removeLast();
        }

        // 此时size=5，应该已经触发缩容
        assertEquals(5, deque.size());
        // 验证剩余元素正确 (添加顺序是逆序)
        for (int i = 0; i < 5; i++) {
            assertEquals(19 - i, deque.get(i));
        }
    }

    @Test
    @DisplayName("Test getFirst empty")
    public void testGetFirstEmpty() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        assertNull(deque.get(0));  // 空deque返回null
    }

    @Test
    @DisplayName("Test getFirst valid")
    public void testGetFirstValid() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        deque.addLast("a");
        deque.addLast("b");
        assertEquals("a", deque.get(0));
    }

    @Test
    @DisplayName("Test getLast empty")
    public void testGetLastEmpty() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        assertNull(deque.get(0));  // 空deque返回null
    }

    @Test
    @DisplayName("Test getLast valid")
    public void testGetLastValid() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        deque.addFirst("a");
        deque.addFirst("b");
        assertEquals("a", deque.get(deque.size() - 1));
    }

    @Test
    @DisplayName("Test get valid index")
    public void testGetValid() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        for (int i = 0; i < 10; i++) {
            deque.addLast(i);
        }
        for (int i = 0; i < 10; i++) {
            assertEquals(i, deque.get(i));
        }
    }

    @Test
    @DisplayName("Test get out of bounds large")
    public void testGetOobLarge() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        deque.addLast("a");
        deque.addLast("b");
        assertNull(deque.get(100));
    }

    @Test
    @DisplayName("Test get out of bounds negative")
    public void testGetOobNeg() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        deque.addLast("a");
        deque.addLast("b");
        assertNull(deque.get(-1));
    }

    @Test
    @DisplayName("Test size")
    public void testSize() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        assertEquals(0, deque.size());
        deque.addLast("a");
        assertEquals(1, deque.size());
        deque.addLast("b");
        assertEquals(2, deque.size());
    }

    @Test
    @DisplayName("Test size after remove to empty")
    public void testSizeAfterRemoveToEmpty() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("c");
        assertEquals(3, deque.size());

        deque.removeFirst();
        deque.removeFirst();
        deque.removeFirst();
        assertEquals(0, deque.size());
    }

    @Test
    @DisplayName("Test size after remove from empty")
    public void testSizeAfterRemoveFromEmpty() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        assertEquals(0, deque.size());

        deque.removeFirst();
        assertEquals(0, deque.size());

        deque.removeLast();
        assertEquals(0, deque.size());
    }

    @Test
    @DisplayName("Test isEmpty true")
    public void testIsEmptyTrue() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
    }

    @Test
    @DisplayName("Test isEmpty false")
    public void testIsEmptyFalse() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        deque.addLast("a");
        assertFalse(deque.isEmpty());
        assertTrue(deque.size() > 0);
    }

    @Test
    @DisplayName("Test toList empty")
    public void testToListEmpty() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        List<String> list = deque.toList();
        assertNotNull(list);
        assertTrue(list.isEmpty());
    }

    @Test
    @DisplayName("Test toList non-empty")
    public void testToListNonEmpty() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addFirst(0);

        List<Integer> list = deque.toList();
        assertEquals(4, list.size());
        assertEquals(0, list.get(0));
        assertEquals(1, list.get(1));
        assertEquals(2, list.get(2));
        assertEquals(3, list.get(3));
    }

    @Test
    @DisplayName("Test resize up and resize down")
    public void testResizeUpAndResizeDown() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();

        // 扩容：添加很多元素
        for (int i = 0; i < 30; i++) {
            deque.addLast(i);
        }
        assertEquals(30, deque.size());

        // 缩容：移除到使用率低于25%
        // 30 * 0.25 = 7.5, 所以移除到 <=7 个
        for (int i = 0; i < 23; i++) {
            deque.removeFirst();
        }
        assertEquals(7, deque.size());

        // 验证剩余元素正确
        for (int i = 0; i < 7; i++) {
            assertEquals(i + 23, deque.get(i));
        }

        // 再次扩容
        for (int i = 0; i < 20; i++) {
            deque.addLast(i + 30);
        }
        assertEquals(27, deque.size());

        // 验证所有元素
        for (int i = 0; i < 7; i++) {
            assertEquals(i + 23, deque.get(i));
        }
        for (int i = 0; i < 20; i++) {
            assertEquals(i + 30, deque.get(i + 7));
        }
    }
    @Test
    @DisplayName("Test removeFirst trigger resize down")
    public void testRemoveFirstTriggerResizeDown() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();

        // 1. 先扩容：添加足够多的元素让数组变大
        for (int i = 0; i < 20; i++) {
            deque.addLast(i);
        }
        assertEquals(20, deque.size());

        // 2. 记录当前数组大小（无法直接访问，只能通过后续操作验证）
        // 移除元素直到使用率 <= 25%
        // 20 * 0.25 = 5，所以需要移除到 <= 5 个元素

        // 移除 15 个元素，剩下 5 个（刚好 25%）
        for (int i = 0; i < 15; i++) {
            deque.removeFirst();
        }
        assertEquals(5, deque.size());

        // 3. 验证剩余元素正确
        for (int i = 0; i < 5; i++) {
            assertEquals(i + 15, deque.get(i));
        }

        // 4. 再添加一些元素，验证数组已经缩容（通过性能间接验证）
        // 如果没缩容，现在数组很大；如果缩容了，继续添加会再次触发扩容
        long startTime = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            deque.addLast(i + 100);
        }
        long endTime = System.nanoTime();

        // 添加操作应该很快（如果之前缩容了，现在会再次扩容，稍微慢一点）
        // 但不能直接断言时间，只是作为参考
        assertEquals(15, deque.size());
    }

    @Test
    @DisplayName("Test removeLast trigger resize down")
    public void testRemoveLastTriggerResizeDown() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();

        // 1. 先扩容
        for (int i = 0; i < 20; i++) {
            deque.addFirst(i);
        }
        assertEquals(20, deque.size());

        // 2. 移除到使用率 <= 25%
        // 需要移除 15 个，剩下 5 个
        for (int i = 0; i < 15; i++) {
            deque.removeLast();
        }
        assertEquals(5, deque.size());

        // 3. 验证剩余元素（addFirst是逆序）
        for (int i = 0; i < 5; i++) {
            assertEquals(19 - i, deque.get(i));
        }

        // 4. 再添加，验证
        for (int i = 0; i < 10; i++) {
            deque.addFirst(i + 100);
        }
        assertEquals(15, deque.size());
    }

    @Test
    @DisplayName("Test resize down with multiple cycles")
    public void testResizeDownWithMultipleCycles() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();

        // 多次扩容缩容循环
        for (int cycle = 0; cycle < 3; cycle++) {
            // 扩容阶段
            for (int i = 0; i < 30; i++) {
                deque.addLast(i + cycle * 100);
            }

            // 缩容阶段：移除到 <= 25%
            int targetSize = (int)(30 * 0.25);  // 7
            for (int i = 0; i < 30 - targetSize; i++) {
                deque.removeFirst();
            }

            assertEquals(targetSize, deque.size());

            // 验证剩余元素
            for (int i = 0; i < targetSize; i++) {
                assertEquals(i + cycle * 100 + (30 - targetSize), deque.get(i));
            }

            // 清空，准备下一个循环
            while (!deque.isEmpty()) {
                deque.removeFirst();
            }
            assertEquals(0, deque.size());
        }
    }

    @Test
    @DisplayName("Test resize down at exactly 25%")
    public void testResizeDownAtExactly25Percent() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();

        // 先让数组变大（假设初始8，扩容后16、32...）
        for (int i = 0; i < 30; i++) {
            deque.addLast(i);
        }

        // 移除到恰好 25%
        // 30 的 25% 是 7.5，所以 8 个是 >25%，7 个是 <25%
        // 应该缩容的是 7 个的时候

        // 先移除到 8 个（>25%，不应缩容）
        for (int i = 0; i < 22; i++) {  // 30→8
            deque.removeFirst();
        }
        assertEquals(8, deque.size());

        // 再移除一个到 7 个（<25%，应该缩容）
        deque.removeFirst();
        assertEquals(7, deque.size());

        // 再添加元素验证缩容后的数组正常工作
        deque.addLast(100);
        assertEquals(8, deque.size());
        assertEquals(100, deque.get(7));
    }

    @Test
    @DisplayName("Test no resize down when length <= 15")
    public void testNoResizeDownWhenLengthSmall() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();

        // 初始数组长度8，即使使用率很低也不应该缩容（因为<16）

        // 添加一些元素
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        // 移除到只剩1个（使用率 1/8 = 12.5% < 25%）
        deque.removeFirst();
        deque.removeFirst();
        assertEquals(1, deque.size());

        // 再添加，应该正常工作（不会因为缩容而出错）
        deque.addLast(4);
        assertEquals(2, deque.size());
        assertEquals(4, deque.get(1));
    }

    @Test
    @DisplayName("Test resize down with alternating add/remove")
    public void testResizeDownWithAlternating() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();

        // 先扩容到较大
        for (int i = 0; i < 30; i++) {
            deque.addLast(i);
        }

        // 交替移除，触发缩容
        for (int i = 0; i < 10; i++) {
            deque.removeFirst();
            deque.removeLast();
        }

        // 剩余 10 个 (30 - 20 = 10)
        // 10/30 ≈ 33.3% > 25%，应该还没缩容

        assertEquals(10, deque.size());

        // 再移除2个，到8个 (8/30 ≈ 26.7% > 25%)
        deque.removeFirst();
        deque.removeLast();
        assertEquals(8, deque.size());

        // 再移除1个，到7个 (7/30 ≈ 23.3% < 25%) → 应该缩容
        deque.removeFirst();
        assertEquals(7, deque.size());

        // 添加元素验证
        deque.addLast(999);
        assertEquals(8, deque.size());
        assertEquals(999, deque.get(7));
    }
    @Test
    @DisplayName("Test remove from empty returns null")
    public void testRemoveFromEmptyReturnsNull() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        assertNull(deque.removeFirst());
        assertNull(deque.removeLast());
        assertEquals(0, deque.size());
        assertTrue(deque.isEmpty());
    }

    @Test
    @DisplayName("Test multiple operations on empty deque")
    public void testMultipleOperationsOnEmpty() {
        Deque61B<String> deque = new ArrayDeque61B<>();

        // 连续从空deque移除
        assertNull(deque.removeFirst());
        assertNull(deque.removeLast());
        assertNull(deque.removeFirst());

        // 空deque时get
        assertNull(deque.get(0));
        assertNull(deque.get(5));

        // 还是空的
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());

        // 然后添加应该正常工作
        deque.addFirst("a");
        assertEquals(1, deque.size());
        assertEquals("a", deque.get(0));
    }

    @Test
    @DisplayName("Test add after empty then remove to empty cycle")
    public void testAddRemoveEmptyCycle() {
        Deque61B<String> deque = new ArrayDeque61B<>();

        // 多次空→添加→移除→空的循环
        for (int cycle = 0; cycle < 5; cycle++) {
            assertTrue(deque.isEmpty());

            deque.addFirst("x");
            assertEquals(1, deque.size());

            assertEquals("x", deque.removeFirst());
            assertTrue(deque.isEmpty());
        }
    }

    @Test
    @DisplayName("Test toList on empty returns empty list")
    public void testToListEmptyReturnsEmptyList() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        List<String> list = deque.toList();

        assertNotNull(list);
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());

        // 验证返回的list是新的，修改不影响deque
        list.add("test");  // 如果能添加，说明是新list
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
    }
}