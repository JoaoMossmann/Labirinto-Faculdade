package Etapa3;

public class Etapa3 {

    public boolean checkBrackets(Stack<Character> s1) {
        if (s1 == null) {
            throw new IllegalArgumentException("Pilha não informada.");
        }

        StaticStack<Character> auxiliary =
            new StaticStack<>(s1.numElements());

        int pendingClosings = 0;
        boolean valid = true;

        // Percorre a expressão da direita para a esquerda.
        while (!s1.isEmpty()) {
            Character character = s1.pop();
            auxiliary.push(character);

            if (Character.valueOf(')').equals(character)) {
                pendingClosings++;
            } else if (Character.valueOf('(').equals(character)) {
                if (pendingClosings == 0) {
                    valid = false;
                } else {
                    pendingClosings--;
                }
            }
        }

        // Restaura o conteúdo e a ordem da pilha original.
        while (!auxiliary.isEmpty()) {
            s1.push(auxiliary.pop());
        }

        return valid && pendingClosings == 0;
    }
}