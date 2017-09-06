package com.company;

import com.company.auxiliares.Posicao;

public class Sala {
    private static int[][] matriz;
    private int tamanho;

    public Sala(int n, int qntLix, int qntRec, int taxaLixo) {
        matriz = new int[n][n];

        if (n < 12) {
            n = 12;
        }

        n = (n / 3) * 3;

        tamanho = n;

        matriz = new int[n][n];

        GerarParede();

        System.out.print(toString());
    }

    private void GerarParede() {
        int quadrante = (tamanho / 3);
        int parede = quadrante - 1;
        int limiteAcima = parede - parede / 2;
        int limiteAbaixo = (tamanho - 1) - parede / 2;

        for (int i = limiteAcima; i < limiteAbaixo; i++) {
            matriz[i][parede] = Posicao.Parede.valor();
            matriz[i][parede + quadrante + 1] = Posicao.Parede.valor();
        }
        matriz[limiteAcima][parede - 1] = Posicao.Parede.valor();
        matriz[limiteAbaixo - 1][parede - 1] = Posicao.Parede.valor();

        matriz[limiteAcima][parede + quadrante + 2] = Posicao.Parede.valor();
        matriz[limiteAbaixo - 1][parede + quadrante + 2] = Posicao.Parede.valor();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                result.append(matriz[i][j]).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }
}
