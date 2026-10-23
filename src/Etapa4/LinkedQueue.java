package Etapa4;

public class LinkedQueue<E> implements Queue<E> {
    private Node<E> firstNode;
    private Node<E> lastNode;
    private int size;

    public LinkedQueue() {
        firstNode = null;
        lastNode = null;
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
    public void enqueue(E element) {
        Node<E> newNode = new Node<>(element);

        if (isEmpty()) {
            firstNode = newNode;
        } else {
            lastNode.setNext(newNode);
        }

        lastNode = newNode;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new UnderflowException();
        }

        E element = firstNode.getElement();
        firstNode = firstNode.getNext();
        size--;

        // Ao remover o último elemento, ambas as referências ficam vazias.
        if (size == 0) {
            lastNode = null;
        }

        return element;
    }

    @Override
    public E front() {
        if (isEmpty()) {
            throw new UnderflowException();
        }

        return firstNode.getElement();
    }

    public E first() {
        return front();
    }

    @Override
    public E back() {
        if (isEmpty()) {
            throw new UnderflowException();
        }

        return lastNode.getElement();
    }
}
