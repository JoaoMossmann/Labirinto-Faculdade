package Etapa4;

public class PrincipalEtapa4 {

    public static void main(String[] args) {
        System.out.println("RELATÓRIO DE EXECUÇÃO - Etapa 4");

        // Teste 1: pilha
        System.out.println("\nTeste 1: Operações com Pilha");

        LinkedStack<Integer> stack = new LinkedStack<>();

        System.out.println("Pilha está vazia? " + stack.isEmpty());

        verificar(stack.isEmpty(), "A pilha deve iniciar vazia.");
        verificar(stack.numElements() == 0, "Quantidade inicial da pilha.");
        verificar(!stack.isFull(), "A pilha dinâmica não tem limite fixo.");

        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Elemento no topo da pilha: " + stack.top());

        verificar(stack.top() == 30, "O topo deve ser 30.");
        verificar(stack.numElements() == 3, "Consultar o topo não remove.");

        int removidoPilha = stack.pop();

        System.out.println("Desempilhando: " + removidoPilha);
        System.out.println(
            "Número de elementos na pilha: " + stack.numElements()
        );

        verificar(removidoPilha == 30, "A primeira remoção deve retornar 30.");
        verificar(stack.numElements() == 2, "Devem restar dois elementos.");
        verificar(stack.pop() == 20, "A segunda remoção deve retornar 20.");
        verificar(stack.pop() == 10, "A terceira remoção deve retornar 10.");
        verificar(stack.isEmpty(), "A pilha deve estar vazia.");
        verificar(stack.numElements() == 0, "A quantidade deve voltar a zero.");

        esperarUnderflow(() -> stack.pop(), "pop em pilha vazia");
        esperarUnderflow(() -> stack.top(), "top em pilha vazia");

        // Verifica se a pilha pode ser utilizada novamente.
        stack.push(40);
        verificar(stack.top() == 40, "Topo após reutilizar a pilha.");
        verificar(stack.pop() == 40, "Remoção após reutilizar a pilha.");
        verificar(stack.isEmpty(), "A pilha deve ficar vazia novamente.");

        System.out.println("[OK] Verificações da pilha concluídas.");

        // Teste 2: fila
        System.out.println("\nTeste 2: Operações com Fila");

        LinkedQueue<Integer> queue = new LinkedQueue<>();

        System.out.println("Fila está vazia? " + queue.isEmpty());

        verificar(queue.isEmpty(), "A fila deve iniciar vazia.");
        verificar(queue.numElements() == 0, "Quantidade inicial da fila.");
        verificar(!queue.isFull(), "A fila dinâmica não tem limite fixo.");

        queue.enqueue(100);
        queue.enqueue(200);
        queue.enqueue(300);

        System.out.println("Primeiro da fila: " + queue.front());

        verificar(queue.front() == 100, "O primeiro deve ser 100.");
        verificar(queue.first() == 100, "first deve retornar o primeiro.");
        verificar(queue.back() == 300, "O último deve ser 300.");
        verificar(queue.numElements() == 3, "As consultas não removem.");

        int removidoFila = queue.dequeue();

        System.out.println("Desenfileirando: " + removidoFila);
        System.out.println(
            "Número de elementos na fila: " + queue.numElements()
        );
        System.out.println("Último da fila (back): " + queue.back());

        verificar(removidoFila == 100, "A primeira remoção deve retornar 100.");
        verificar(queue.numElements() == 2, "Devem restar dois elementos.");
        verificar(queue.front() == 200, "O novo primeiro deve ser 200.");
        verificar(queue.dequeue() == 200, "A segunda remoção deve retornar 200.");

        verificar(queue.front() == 300, "Resta apenas o elemento 300.");
        verificar(queue.back() == 300, "Primeiro e último devem ser iguais.");
        verificar(queue.dequeue() == 300, "A última remoção deve retornar 300.");

        verificar(queue.isEmpty(), "A fila deve estar vazia.");
        verificar(queue.numElements() == 0, "A quantidade deve voltar a zero.");

        esperarUnderflow(() -> queue.dequeue(), "dequeue em fila vazia");
        esperarUnderflow(() -> queue.front(), "front em fila vazia");
        esperarUnderflow(() -> queue.first(), "first em fila vazia");
        esperarUnderflow(() -> queue.back(), "back em fila vazia");

        // Verifica as referências após esvaziar e reutilizar a fila.
        queue.enqueue(400);
        queue.enqueue(500);

        verificar(queue.front() == 400, "Primeiro após reutilizar a fila.");
        verificar(queue.back() == 500, "Último após reutilizar a fila.");
        verificar(queue.dequeue() == 400, "Ordem após reutilizar a fila.");
        verificar(queue.dequeue() == 500, "Última remoção após reutilização.");
        verificar(queue.isEmpty(), "A fila deve ficar vazia novamente.");

        System.out.println("[OK] Verificações da fila concluídas.");
        System.out.println("\nTodos os testes funcionais passaram.");
    }

    private static void verificar(boolean condicao, String mensagem) {
        if (!condicao) {
            throw new AssertionError(mensagem);
        }
    }

    private static void esperarUnderflow(
            Runnable operacao, String descricao) {

        try {
            operacao.run();
        } catch (UnderflowException e) {
            return;
        }

        throw new AssertionError(
            "Era esperada UnderflowException: " + descricao
        );
    }
}