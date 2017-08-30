package com.company.auxiliares;

public enum Posicao {
    Limpa(0),
    Suja(1),
    Carregador(2),
    Lixeira(3),
    Parede(4),
    Ocupado(5);

    public final int status;
    Posicao(int i) {
        status = i;
    }
}
