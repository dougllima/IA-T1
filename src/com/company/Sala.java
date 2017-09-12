package com.company;

import com.company.auxiliares.Posicao;

import java.util.Random;

public class Sala {
    private static int[][] matriz;
    private int tamanho;
    private Random gerador = new Random();

    private int limiteAbaixo, limiteAcima;

    Sala(int n, int qntLix, int qntRec, int taxaLixo) {

        if (n < 12) {
            n = 12;
        }
        if (qntLix < 1) {
            qntLix = 1;
        }
        if (qntRec < 1) {
            qntRec = 1;
        }
        if (taxaLixo < 40) {
            taxaLixo = 40;
        } else if (taxaLixo > 85) {
            taxaLixo = 85;
        }

        n = (n / 3) * 3;

        tamanho = n;

        matriz = new int[n][n];

        gerarParede();

        gerarRecargaLixeira(qntLix, qntRec);

        System.out.print(toString());

        gerarLixo(taxaLixo);
    }

    private void gerarParede() {
        int quadrante = (tamanho / 3);
        int parede = quadrante - 1;
        limiteAcima = parede - parede / 2;
        limiteAbaixo = (tamanho - 1) - parede / 2;

        for (int i = limiteAcima; i < limiteAbaixo; i++) {
            matriz[i][parede] = Posicao.Parede.valor();
            matriz[i][parede + quadrante + 1] = Posicao.Parede.valor();
        }
        matriz[limiteAcima][parede - 1] = Posicao.Parede.valor();
        matriz[limiteAbaixo - 1][parede - 1] = Posicao.Parede.valor();

        matriz[limiteAcima][parede + quadrante + 2] = Posicao.Parede.valor();
        matriz[limiteAbaixo - 1][parede + quadrante + 2] = Posicao.Parede.valor();
    }

    private void gerarRecargaLixeira(int qntLix, int qntRec) {
        for (int i = 0; i <= qntLix; i++) {
            boolean gerar = true;
            int x = 0,
                    y = 0;
            while (gerar) {
                x = gerador.nextInt(tamanho);
                if (x > limiteAcima && x < limiteAbaixo) {
                    if (i < qntLix / 2)
                        y = gerador.nextInt(tamanho / 3);
                    else
                        y = gerador.nextInt(tamanho / 3) + (2 * tamanho / 3);
                    if (matriz[x][y] == Posicao.Limpa.valor()) {
                        gerar = verificaPosicaoRL(x, y);
                    }
                }
            }
            matriz[x][y] = Posicao.Lixeira.valor();
        }

        for (int i = 0; i <= qntRec; i++) {
            boolean gerar = true;
            int x = 0,
                    y = 0;
            while (gerar) {
                x = gerador.nextInt(tamanho);
                if (x > limiteAcima && x < limiteAbaixo) {
                    if (i < qntRec / 2)
                        y = gerador.nextInt(tamanho / 3);
                    else
                        y = gerador.nextInt(tamanho / 3) + (2 * tamanho / 3);
                    if (matriz[x][y] == Posicao.Limpa.valor()) {
                        gerar = verificaPosicaoRL(x, y);
                    }
                }
            }
            matriz[x][y] = Posicao.Carregador.valor();
        }
    }

    private void gerarLixo(int taxaLixo) {
        int count = 0;
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                if (matriz[i][j] == Posicao.Limpa.valor())
                    count++;
            }
        }

        count = (int) (count * (taxaLixo / 100.0));

        for (int i = 0; i <= count; i++) {
            boolean gerar = true;
            int x = 0,
                    y = 0;
            while (gerar) {
                x = gerador.nextInt(tamanho);
                y = gerador.nextInt(tamanho);
                if (matriz[x][y] == Posicao.Limpa.valor())
                    gerar = false;
            }
            matriz[x][y] = Posicao.Suja.valor();
        }
    }

    private boolean verificaPosicaoRL(int x, int y) {
        if (x > 0 && !Posicao.CheckInserirLC(matriz[x - 1][y])) return true;
        if (y > 0 && !Posicao.CheckInserirLC(matriz[x][y - 1])) return true;
        if (y < tamanho - 2 && !Posicao.CheckInserirLC(matriz[x][y + 1])) return true;
        if (x < tamanho - 2 && !Posicao.CheckInserirLC(matriz[x + 1][y])) return true;
        return false;
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
