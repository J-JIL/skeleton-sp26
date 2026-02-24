import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {

    private Node sentinel;
    private int size;

    public class Node {
        T x;
        Node prev;
        Node next;
        public Node(T xi, Node prev, Node next){
            this.x = xi;
            this.prev = prev;
            this.next = next;
        }
    }

    public LinkedListDeque61B() {
        sentinel = new Node(null,null,null);
        sentinel.prev=sentinel;
        sentinel.next=sentinel;
        size = 0;
    }

    /**
     * Add {@code x} to the front of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addFirst(T x) {
        Node add = new Node(x,sentinel,sentinel.next);
        size += 1;
        sentinel.next.prev = add;
        sentinel.next = add;
    }


    /**
     * Add {@code x} to the back of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addLast(T x) {
        Node add = new Node(x,sentinel.prev,sentinel);
        size += 1;
        sentinel.prev.next = add;
        sentinel.prev = add;
    }


    /**
     * Returns a List copy of the deque. Does not alter the deque.
     *
     * @return a new list copy of the deque.
     */
    @Override
    public List<T> toList() {
        List<T> returnlist = new ArrayList<>();
        Node i = sentinel.next;
        while(i!=sentinel){
            returnlist.add(i.x);
            i = i.next;
        }
        return returnlist;
    }


    /**
     * Returns if the deque is empty. Does not alter the deque.
     *
     * @return {@code true} if the deque has no elements, {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return sentinel.next == sentinel;
    }


    /**
     * Returns the size of the deque. Does not alter the deque.
     *
     * @return the number of items in the deque.
     */
    @Override
    public int size() {
        return size;
    }


    /**
     * Return the element at the front of the deque, if it exists.
     *
     * @return element, otherwise {@code null}.
     */
    @Override
    public T getFirst() {
        if(sentinel.next == sentinel){
            return null;
        }
        return sentinel.next.x;
    }


    /**
     * Return the element at the back of the deque, if it exists.
     *
     * @return element, otherwise {@code null}.
     */
    @Override
    public T getLast() {
        if(sentinel.next == sentinel){
            return null;
        }return sentinel.prev.x;
    }

    /**
     * Remove and return the element at the front of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeFirst() {
        if(isEmpty()){
            return null;
        } else{
            sentinel.next = sentinel.next.next;
            T x = sentinel.next.prev.x;
            sentinel.next.prev = sentinel;
            return x;
        }
    }

    /**
     * Remove and return the element at the back of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeLast() {
        if(isEmpty()){
            return null;
        } else{
            sentinel.prev = sentinel.prev.prev;
            T x = sentinel.prev.next.x;
            sentinel.prev.next = sentinel;
            return x;
        }
    }

    /**
     * The Deque61B abstract data type does not typically have a get method,
     * but we've included this extra operation to provide you with some
     * extra programming practice. Gets the element, iteratively. Returns
     * null if index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T get(int index) {
        Node p = sentinel;
        if(index >= size || index < 0){
            return null;
        }
        int times = 0;
        while(times <= index){
            p=p.next;
            times += 1;
        }
        return p.x;
    }

    /**
     * This method technically shouldn't be in the interface, but it's here
     * to make testing nice. Gets an element, recursively. Returns null if
     * index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T getRecursive(int index) {
        if(index >= size || index < 0){
            return null;
        }
        return getRecursiveHelper(sentinel.next,index);
    }
    private T getRecursiveHelper(Node current,int index){
        if(index == 0){
            return current.x;
        }else{
            return getRecursiveHelper(current.next,index-1);
        }
    }

    public static void main(String[] args) {
        Deque61B<Integer> lld = new LinkedListDeque61B<>();
        lld.addLast(0);   // [0]
        lld.addLast(1);   // [0, 1]
        lld.addFirst(-1); // [-1, 0, 1]
    }

}
