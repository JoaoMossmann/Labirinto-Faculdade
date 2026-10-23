package Etapa3;

public class PrincipalEtapa3 {

    public static void main(String[] args) {
        System.out.println(
            "Teste 1: Contagem de Elementos em uma Lista Estática"
        );

        StaticList<Integer> lista = new StaticList<>(10);

        lista.insert(1, 0);
        lista.insert(2, 1);
        lista.insert(3, 2);
        lista.insert(1, 3);
        lista.insert(2, 4);

        System.out.println(
            "O elemento 1 aparece "
            + lista.contaElementos(1)
            + " vezes na lista."
        );

        System.out.println(
            "Teste 2: Verificação de Parênteses Agrupados"
        );

        Etapa3 etapa3 = new Etapa3();

        String[] expressions = {
            "(A+B)",
            "((A+B)-(C+D))",
            "((A+B)",
            "A+B(",
            ")A+B(-C",
            "(A+B))-(C+D"
        };

        for (String expression : expressions) {
            StaticStack<Character> pilha =
                new StaticStack<>(expression.length());

            for (int i = 0; i < expression.length(); i++) {
                pilha.push(expression.charAt(i));
            }

            System.out.println(
                expression + " -> " + etapa3.checkBrackets(pilha)
            );
        }
    }
}