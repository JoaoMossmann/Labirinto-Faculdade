package Etapa3;

public class StaticStack<E> implements Stack<E> {
    private final Object[] elements;
    private int top = -1;

    public StaticStack(int maxSize) {
        if (maxSize < 0) {
            throw new IllegalArgumentException("Capacidade negativa.");
        }

        elements = new Object[maxSize];
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public boolean isFull() {
        return top == elements.length - 1;
    }

    @Override
    public int numElements() {
        return top + 1;
    }

    @Override
    public void push(E element) {
        if (isFull()) {
            throw new OverflowException();
        }

        elements[++top] = element;
    }

    @Override
    public E pop() {
        E removed = top();

        elements[top--] = null;
        return removed;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E top() {
        if (isEmpty()) {
            throw new UnderflowException();
        }

        return (E) elements[top];
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");

        for (int i = 0; i <= top; i++) {
            if (i > 0) {
                result.append(", ");
            }

            result.append(elements[i]);
        }

        return result.append("]").toString();
    }
}