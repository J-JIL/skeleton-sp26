import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;
import java.util.Iterator;

public class EnhancementTest {

    // ============ 迭代器测试 ============

    @Test
    public void testIterator() {
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();
        deque.addLast("front");
        deque.addLast("middle");
        deque.addLast("back");

        String[] expected = {"front", "middle", "back"};
        int i = 0;
        for (String s : deque) {
            assertThat(s).isEqualTo(expected[i]);
            i++;
        }
        assertThat(i).isEqualTo(3);
    }

    @Test
    public void testIteratorWithEmptyDeque() {
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();
        Iterator<String> iter = deque.iterator();

        assertThat(iter.hasNext()).isFalse();
        assertThat(iter.next()).isNull();
    }

    @Test
    public void testAddLastWithTruth() {
        ArrayDeque61B<String> ad = new ArrayDeque61B<>();

        ad.addLast("front");
        ad.addLast("middle");
        ad.addLast("back");

        assertThat(ad).containsExactly("front", "middle", "back");
    }

    @Test
    public void testIteratorWithMixedOperations() {
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();
        deque.addLast("b");
        deque.addFirst("a");
        deque.addLast("c");
        deque.addFirst("z");

        assertThat(deque).containsExactly("z", "a", "b", "c");
    }

    // ============ equals 方法测试 ============

    @Test
    public void testEquals() {
        // 1. 测试同一个对象
        ArrayDeque61B<String> deque1 = new ArrayDeque61B<>();
        deque1.addLast("a");
        deque1.addLast("b");
        assertThat(deque1.equals(deque1)).isTrue();

        // 2. 测试 null
        assertThat(deque1.equals(null)).isFalse();

        // 3. 测试不同类型
        String notADeque = "not a deque";
        assertThat(deque1.equals(notADeque)).isFalse();

        // 4. 测试两个空deque
        ArrayDeque61B<String> empty1 = new ArrayDeque61B<>();
        ArrayDeque61B<String> empty2 = new ArrayDeque61B<>();
        assertThat(empty1.equals(empty2)).isTrue();

        // 5. 测试相同元素相同顺序
        ArrayDeque61B<String> deque2 = new ArrayDeque61B<>();
        deque2.addLast("a");
        deque2.addLast("b");
        assertThat(deque1.equals(deque2)).isTrue();
        assertThat(deque2.equals(deque1)).isTrue(); // 对称性

        // 6. 测试不同大小
        ArrayDeque61B<String> deque3 = new ArrayDeque61B<>();
        deque3.addLast("a");
        assertThat(deque1.equals(deque3)).isFalse();

        // 7. 测试相同大小不同元素
        ArrayDeque61B<String> deque4 = new ArrayDeque61B<>();
        deque4.addLast("a");
        deque4.addLast("c");
        assertThat(deque1.equals(deque4)).isFalse();

        // 8. 测试相同元素不同顺序
        ArrayDeque61B<String> deque5 = new ArrayDeque61B<>();
        deque5.addLast("b");
        deque5.addLast("a");
        assertThat(deque1.equals(deque5)).isFalse();

        // 9. 测试使用 addFirst 构建的相同结果
        ArrayDeque61B<String> deque6 = new ArrayDeque61B<>();
        deque6.addFirst("b");
        deque6.addFirst("a"); // 结果是 [a, b]
        assertThat(deque1.equals(deque6)).isTrue();

        // 10. 测试包含 null 元素
        ArrayDeque61B<String> deque7 = new ArrayDeque61B<>();
        deque7.addLast("a");
        deque7.addLast(null);
        deque7.addLast("b");

        ArrayDeque61B<String> deque8 = new ArrayDeque61B<>();
        deque8.addLast("a");
        deque8.addLast(null);
        deque8.addLast("b");
        assertThat(deque7.equals(deque8)).isTrue();

        // 11. 测试 null 和非 null 的比较
        ArrayDeque61B<String> deque9 = new ArrayDeque61B<>();
        deque9.addLast("a");
        deque9.addLast("b");
        deque9.addLast("c");
        assertThat(deque7.equals(deque9)).isFalse();
    }
}