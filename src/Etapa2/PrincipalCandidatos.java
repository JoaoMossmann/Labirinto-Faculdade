package Etapa2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PrincipalCandidatos {

    public static void main(String[] args) {
        try {
            List<String> nomes = lerOpcoes("src/Etapa2/nomes.txt");
            List<String> partidos = lerOpcoes("src/Etapa2/partidos.txt");

            Random random = new Random();

            // Quantidade aleatória entre 1 e 100.
            Candidato[] candidatos =
                new Candidato[random.nextInt(100) + 1];

            for (int i = 0; i < candidatos.length; i++) {
                candidatos[i] = new Candidato(
                    nomes.get(random.nextInt(nomes.size())),
                    partidos.get(random.nextInt(partidos.size())),
                    random.nextInt(1001)
                );
            }

            // A ordem das chamadas é importante para os desempates.
            OrdenarCandidatos.ordenaCandidatosPorPartido(candidatos);
            OrdenarCandidatos.ordenaCandidatosPorVotos(candidatos);
            OrdenarCandidatos.ordenaCandidatosPorNome(candidatos);

            System.out.println(
                "RELATÓRIO DE VOTAÇÃO — candidatos ordenados por nome"
            );

            System.out.printf(
                "%-25s %-15s %s%n",
                "Nome", "Partido", "Intenções de votos"
            );

            for (Candidato candidato : candidatos) {
                System.out.println(candidato);
            }

            System.out.println(
                "Primeiro candidato: " + candidatos[0]
            );

            System.out.println(
                "Último candidato: " + candidatos[candidatos.length - 1]
            );

            Scanner entrada = new Scanner(System.in, "UTF-8");

            System.out.print(
                "Digite o nome para pesquisar "
                + "(respeitando maiúsculas e minúsculas): "
            );

            if (!entrada.hasNextLine()) {
                System.out.println("Nenhum nome informado.");
                return;
            }

            String nome = entrada.nextLine().trim();

            int posicao =
                OrdenarCandidatos.pesquisaBinariaCandidatos(
                    candidatos, nome
                );

            if (posicao == -1) {
                System.out.println("Candidato não encontrado: " + nome);
            } else {
                System.out.println(
                    "Candidato encontrado na posição "
                    + posicao + ": " + candidatos[posicao]
                );
            }

        } catch (IOException e) {
            throw new IllegalStateException(
                "Não foi possível carregar os dados. "
                + "Execute pela raiz do projeto.",
                e
            );
        }
    }

    private static List<String> lerOpcoes(String caminho)
            throws IOException {

        List<String> linhas = Files.readAllLines(
            Paths.get(caminho),
            StandardCharsets.UTF_8
        );

        linhas.replaceAll(String::trim);
        linhas.removeIf(String::isEmpty);

        if (linhas.isEmpty()) {
            throw new IOException("Arquivo sem opções: " + caminho);
        }

        return linhas;
    }
}