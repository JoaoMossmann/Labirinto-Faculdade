package Etapa3;

import java.util.Objects;

public class StaticList<E> implements List<E> {
    private final Object[] elements;
    private int size;

    public StaticList(int maxSize) {
        if (maxSize < 0) {
            throw new IllegalArgumentException("Capacidade negativa.");
        }

        elements = new Object[maxSize];
    }

    @Override
    public int numElements() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == elements.length;
    }

    @Override
    public void insert(E element, int pos) {
        if (pos < 0 || pos > size) {
            throw new IndexOutOfBoundsException(
                "Posição inválida: " + pos
            );
        }

        if (isFull()) {
            throw new OverflowException();
        }

        // Abre espaço para inserir na posição solicitada.
        for (int i = size; i > pos; i--) {
            elements[i] = elements[i - 1];
        }

        elements[pos] = element;
        size++;
    }

    @Override
    public E remove(int pos) {
        if (isEmpty()) {
            throw new UnderflowException();
        }

        E removed = get(pos);

        // Desloca os elementos para preencher a posição removida.
        for (int i = pos; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        elements[--size] = null;
        return removed;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException(
                "Posição inválida: " + pos
            );
        }

        return (E) elements[pos];
    }

    @Override
    public int search(E element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], element)) {
                return i;
            }
        }

        return -1;
    }

    public int contaElementos(E el) {
        return contaElementosRecursivo(el, 0);
    }

    private int contaElementosRecursivo(E el, int pos) {
        // Caso base: todos os elementos foram examinados.
        if (pos == size) {
            return 0;
        }

        int occurrence = Objects.equals(elements[pos], el) ? 1 : 0;

        return occurrence + contaElementosRecursivo(el, pos + 1);
    }
}