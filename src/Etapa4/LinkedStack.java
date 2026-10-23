package Etapa4;

public class LinkedStack<E> implements Stack<E> {
    private Node<E> topNode;
    private int size;

    public LinkedStack() {
        topNode = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        // A estrutura não possui uma capacidade fixa.
        return false;
    }

    @Override
    public int numElements() {
        return size;
    }

    @Override
    public void push(E element) {
        Node<E> newNode = new Node<>(element);
        newNode.setNext(topNode);

        topNode = newNode;
        size++;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new UnderflowException();
        }

        E element = topNode.getElement();
        topNode = topNode.getNext();
        size--;

        return element;
    }

    @Override
    public E top() {
        if (isEmpty()) {
            throw new UnderflowException();
        }

        return topNode.getElement();
    }
}