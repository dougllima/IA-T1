package com.company;

public class Sala {
    private static int[][] matriz;

    public Sala(int n, int qntLix, int qntRec, int taxaLixo) {
        matriz = new int[n][n];

        //
        if (n < 7){
            n = 7;
        }
    }
}
