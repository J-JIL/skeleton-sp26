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
    @Test
    public void testToString() {
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();

        // 测试空deque
        assertThat(deque.toString()).isEqualTo("[]");

        // 测试单个元素
        deque.addLast("front");
        assertThat(deque.toString()).isEqualTo("[front]");

        // 测试多个元素
        deque.addLast("middle");
        deque.addLast("back");
        assertThat(deque.toString()).isEqualTo("[front,middle,back]");

        // 测试使用 addFirst 构建
        ArrayDeque61B<String> deque2 = new ArrayDeque61B<>();
        deque2.addFirst("c");
        deque2.addFirst("b");
        deque2.addFirst("a");
        assertThat(deque2.toString()).isEqualTo("[a,b,c]");
    }

    @Test
    public void testToStringWithMixedOperations() {
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();

        // 混合使用 addFirst 和 addLast
        deque.addLast("middle");
        deque.addFirst("front");
        deque.addLast("back");
        deque.addFirst("start");

        assertThat(deque.toString()).isEqualTo("[start,front,middle,back]");
    }

    @Test
    public void testToStringWithNullElements() {
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();

        deque.addLast("first");
        deque.addLast(null);
        deque.addLast("last");

        // null 应该显示为 "null"
        assertThat(deque.toString()).isEqualTo("[first,null,last]");
    }

    @Test
    public void testToStringWithWraparound() {
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();

        // 添加足够多的元素触发循环数组
        for (int i = 0; i < 10; i++) {
            deque.addLast("item" + i);
        }

        // 移除一些元素来制造循环
        deque.removeFirst();
        deque.removeFirst();
        deque.addLast("new1");
        deque.addLast("new2");

        // 构建期望的字符串
        StringBuilder expected = new StringBuilder("[");
        for (int i = 2; i < 10; i++) {
            expected.append("item").append(i);
            if (i < 9) expected.append(",");
        }
        expected.append(",new1,new2]");

        assertThat(deque.toString()).isEqualTo(expected.toString());
    }

    @Test
    public void testToStringConsistency() {
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();
        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("c");

        // 多次调用应该返回相同结果
        String first = deque.toString();
        String second = deque.toString();
        assertThat(first).isEqualTo(second);
        assertThat(first).isEqualTo("[a,b,c]");

        // 修改deque后，toString应该更新
        deque.addLast("d");
        assertThat(deque.toString()).isEqualTo("[a,b,c,d]");
    }

    @Test
    public void testToStringFormat() {
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();
        deque.addLast("hello");
        deque.addLast("world");

        String result = deque.toString();

        // 检查格式：应该以 [ 开头，以 ] 结尾
        assertThat(result).startsWith("[");
        assertThat(result).endsWith("]");

        // 检查逗号和空格的格式
        assertThat(result).contains(",");

        // 不应该有多余的空格
        assertThat(result).doesNotContain("  "); // 两个空格
        assertThat(result).doesNotMatch(".*,\\s\\s.*"); // 逗号后两个空格
    }

    @Test
    public void testToStringWithLargeDeque() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();

        // 添加100个元素
        for (int i = 0; i < 100; i++) {
            deque.addLast(i);
        }

        String result = deque.toString();

        // 验证开头和结尾
        assertThat(result).startsWith("[0");
        assertThat(result).endsWith("99]");

        // 验证中间某个元素
        assertThat(result).contains("50");

        // 验证逗号数量 (100个元素有99个逗号)
        int commaCount = result.length() - result.replace(",", "").length();
        assertThat(commaCount).isEqualTo(99);
    }
}