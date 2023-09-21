package fundamentals;

import java.util.EmptyStackException;

/**
 * Author: Pierre Schaus
 *
 * You have to implement the interface using
 * - a simple linkedList as internal structure
 * - a growing array as internal structure
 */
public interface Stack<E> {

    /**
     * Looks at the object at the top of this stack
     * without removing it from the stack
     */
    public boolean empty();

    /**
     * Returns the first element of the stack, without removing it from the stack
     *
     * @throws EmptyStackException if the stack is empty
     */
    public E peek() throws EmptyStackException;

    /**
     * Remove the first element of the stack and returns it
     *
     * @throws EmptyStackException if the stack is empty
     */
    public E pop() throws EmptyStackException;

    /**
     * Adds an element to the stack
     *
     * @param item the item to add
     */
    public void push(E item);

}

/**
 * Implement the Stack interface above using a simple linked list.
 * You should have at least one constructor withtout argument.
 * You are not allowed to use classes from java.util
 * @param <E>
 */
class LinkedStack<E> implements Stack<E> {

    private Node<E> top;        // the node on the top of the stack

    private Node<E> bottom;
    private int size = 0;        // size of the stack

    // helper linked list class
    private class Node<E> {
        private E item;
        private Node<E> next;

        public Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

    @Override
    public boolean empty() {
        return size < 1;
    }

    @Override
    public E peek() throws EmptyStackException {
        if (size == 0) {
            throw new EmptyStackException();
        } else {
            return top.item;
        }
    }

    @Override
    public E pop() throws EmptyStackException {
        if (size == 0) {
            throw new EmptyStackException();
        } else {
            E removed = top.item;
            if (size == 1) {
                top = null;
                bottom = null;
            } else {
                // faire passer le top au node precedant et mettre le next de ce node precedant a null
                Node<E> current = bottom;
                for (int i = 0; i < size - 2; i++) {
                    current = current.next;
                }
                top = current;
                current.next = null;
            }
            size --;
            return removed;
        }
    }

    @Override
    public void push(E item) {
        Node<E> new_top = new Node<>(item, null);
        if (size == 0) {
            top = new_top;
            bottom = new_top;
        } else {
            top.next = new_top;
            top = new_top;
        }
        size ++;
    }
}


/**
 * Implement the Stack interface above using an array as internal representation
 * The capacity of the array should double when the number of elements exceed its length.
 * You should have at least one constructor withtout argument.
 * You are not allowed to use classes from java.util
 * @param <E>
 */
class ArrayStack<E> implements Stack<E> {

    private E[] array;        // array storing the elements on the stack
    private int size;        // size of the stack

    public ArrayStack() {
        array = (E[]) new Object[10];
    }

    @Override
    public boolean empty() {
        // TODO Implement empty method
         return false;
    }

    @Override
    public E peek() throws EmptyStackException {
        // TODO Implement peek method
         return null;
    }

    @Override
    public E pop() throws EmptyStackException {
        // TODO Implement pop method
         return null;
    }

    @Override
    public void push(E item) {
        // TODO Implement push method
    }
}

