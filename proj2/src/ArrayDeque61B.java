import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T>{
    private T[] items;
    private int nextLast;
    private int nextFirst;
    private int size;
    private int capacity;

    public ArrayDeque61B(){
        capacity = 8;
        items = (T[]) new Object[capacity];
        nextLast = 1;
        nextFirst = 0;
        size = 0;
    }
    /**
     * Add {@code x} to the front of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addFirst(T x) {
        items[nextFirst] = x;
        nextFirst = (nextFirst+capacity-1)%capacity;
        size += 1;
        if(size == capacity){
            this.resizeup();
        }
    }

    /**
     * Add {@code x} to the back of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addLast(T x) {
        items[nextLast] = x;
        nextLast = (nextLast+1)%capacity;
        size += 1;
        if(size == capacity){
            this.resizeup();
        }
    }

    /**
     * Returns a List copy of the deque. Does not alter the deque.
     *
     * @return a new list copy of the deque.
     */
    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        int nexti = (nextFirst+1)%capacity;
        for(int i = 0;i < size; i++){
            returnList.add(items[nexti]);
            nexti = (nexti+1)%capacity;
        }
        return returnList;
    }

    /**
     * Returns if the deque is empty. Does not alter the deque.
     *
     * @return {@code true} if the deque has no elements, {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
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
        return items[(nextFirst+1)%capacity];
    }

    /**
     * Return the element at the back of the deque, if it exists.
     *
     * @return element, otherwise {@code null}.
     */
    @Override
    public T getLast() {
        return items[(nextLast+capacity-1)%capacity];
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
        }
        nextFirst = (nextFirst+1)%capacity;
        size -=1;
        if(size <= capacity/4 && capacity > 15){
            this.resizedown();
        }
        return items[nextFirst];
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
        }
        nextLast = (nextLast+capacity-1)%capacity;
        size -= 1;
        if(size <= capacity/4 && capacity > 15){
            this.resizedown();
        }
        return items[nextLast];
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
        return items[(nextFirst+index+1)%capacity];
    }

    public void resizeup(){
        int newcapacity = 2*capacity;
        T[] items2 = (T[]) new Object[newcapacity];
        System.arraycopy(items,(nextFirst+1)%capacity,items2,1,capacity-nextFirst-1);
        System.arraycopy(items,0,items2,1+capacity-(nextFirst+1),size-(capacity-nextFirst-1));
        items = items2;
        nextFirst = 0;
        nextLast = size+1;
        capacity = newcapacity;
    }

    public void resizedown(){
        int newcapacity = capacity/2;
        T[] items2 = (T[]) new Object[newcapacity];
        if(capacity-nextFirst-1 > size){
            System.arraycopy(items,(nextFirst+1)%capacity,items2,0,size);
        }else{
            System.arraycopy(items,(nextFirst+1)%capacity,items2,0,capacity-nextFirst-1);
            System.arraycopy(items,0,items2,capacity-(nextFirst+1),size-(capacity-nextFirst-1));
        }
        items = items2;
        nextFirst = newcapacity-1;
        nextLast = size;
        capacity = newcapacity;
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
        return null;
    }
}
