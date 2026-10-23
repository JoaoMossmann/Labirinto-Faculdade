package Etapa1; 

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Labirinto {
    
    private static final char PAREDE = 'X';
    private static final char CAMINHO_ABERTO = ' ';
    private static final char SAIDA = 'D';
    private static final char CAMINHO_SOLUCAO = '#';

    private char [][] labirinto;



    public void criaLabirinto(String filename) {
        try {
            List<String> linhas = Files.readAllLines(Paths.get(filename));

            int numLinhas = linhas.size();
            int numColunas = 0;
            for (String linha : linhas) {
                numColunas = Math.max(numColunas, linha.length());
            }

            labirinto = new char[numLinhas][numColunas];

            for (int x = 0; x < numLinhas; x++) {
                String linha = linhas.get(x);
                for (int y = 0; y < numColunas; y++) {
                    if (y < linha.length()) {
                        labirinto[x][y] = linha.charAt(y);
                    } else {
                        labirinto[x][y] = CAMINHO_ABERTO;
                    }
                }
            }

        } catch (IOException e) {
            throw new IllegalArgumentException("Não foi possível ler o arquivo: " + filename, e);
        }
    }


    public boolean percorreLabirinto() {
        return resolverLabirinto(0, 0);
    }

    private boolean resolverLabirinto(int x, int y) {
        if (x < 0 || x >= labirinto.length || y < 0 || y >= labirinto[x].length) {
            return false;
        }

        if (labirinto[x][y] == PAREDE || labirinto[x][y] == CAMINHO_SOLUCAO) {
            return false;
        }

        if (labirinto[x][y] == SAIDA) {
            return true;
        }

        labirinto[x][y] = CAMINHO_SOLUCAO;

        if (resolverLabirinto(x + 1, y) ||
            resolverLabirinto(x - 1, y) ||
            resolverLabirinto(x, y + 1) ||
            resolverLabirinto(x, y - 1)) {
            return true;
        }

        labirinto[x][y] = CAMINHO_ABERTO;
        return false;
    }

    public void imprimeLabirinto() {
        for (int x = 0; x < labirinto.length; x++) {
            for (int y = 0; y < labirinto[x].length; y++) {
                System.out.print(labirinto[x][y]);
            }
            System.out.println();
        }
    }

}