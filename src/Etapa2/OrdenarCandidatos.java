package Etapa2;

public class OrdenarCandidatos {

    // Nome em ordem alfabética.
    public static void ordenaCandidatosPorNome(Candidato[] candidatos) {
        for (int i = 1; i < candidatos.length; i++) {
            Candidato atual = candidatos[i];
            int j = i - 1;

            while (j >= 0
                    && candidatos[j].getNome()
                        .compareTo(atual.getNome()) > 0) {
                candidatos[j + 1] = candidatos[j];
                j--;
            }

            candidatos[j + 1] = atual;
        }
    }

    // Votos do maior para o menor.
    public static void ordenaCandidatosPorVotos(Candidato[] candidatos) {
        for (int i = 1; i < candidatos.length; i++) {
            Candidato atual = candidatos[i];
            int j = i - 1;

            while (j >= 0
                    && candidatos[j].getIntencoesVotos()
                        < atual.getIntencoesVotos()) {
                candidatos[j + 1] = candidatos[j];
                j--;
            }

            candidatos[j + 1] = atual;
        }
    }

    // Partido em ordem alfabética.
    public static void ordenaCandidatosPorPartido(Candidato[] candidatos) {
        for (int i = 1; i < candidatos.length; i++) {
            Candidato atual = candidatos[i];
            int j = i - 1;

            while (j >= 0
                    && candidatos[j].getPartido()
                        .compareTo(atual.getPartido()) > 0) {
                candidatos[j + 1] = candidatos[j];
                j--;
            }

            candidatos[j + 1] = atual;
        }
    }

    // O array precisa estar ordenado por nome.
    // Retorna a primeira ocorrência do nome ou -1 se não existir.
    public static int pesquisaBinariaCandidatos(
            Candidato[] candidatos, String nome) {

        int inicio = 0;
        int fim = candidatos.length - 1;
        int encontrado = -1;

        while (inicio <= fim) {
            int meio = inicio + (fim - inicio) / 2;
            int comparacao = candidatos[meio].getNome().compareTo(nome);

            if (comparacao < 0) {
                inicio = meio + 1;
            } else {
                if (comparacao == 0) {
                    encontrado = meio;
                }

                // Continua à esquerda para localizar o primeiro homônimo.
                fim = meio - 1;
            }
        }

        return encontrado;
    }
}