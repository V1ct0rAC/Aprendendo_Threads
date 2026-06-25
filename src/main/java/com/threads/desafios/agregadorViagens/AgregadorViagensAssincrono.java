package com.threads.desafios.agregadorViagens;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static com.threads.completableFuture.Main.simularEspera;

public class AgregadorViagensAssincrono {
    public static void main (String[] args) {
        System.out.println("Iniciando programa de cotagem de preço para passagens.");

        CompletableFuture<String> valoresPassagem = CompletableFuture.supplyAsync(() -> {
                    System.out.println("[" + Thread.currentThread().getName() + "] Cotando valor passagem na companhia aérea Azul:....");
                    simularEspera(1000);
                    return "500,00R$";
                })
                .thenApply(valoresPassagemAzul -> {
                    System.out.println("[" + Thread.currentThread().getName() + "] Valor da passagem aérea na Azul é de: " + valoresPassagemAzul);
                    simularEspera(500);
                    return "750,00R$";
                })
                .thenApply(valorPassagemCVC -> {
                    System.out.println("[" + Thread.currentThread().getName() + "] Valor da passagem da CVC é de: " + valorPassagemCVC);
                    simularEspera(600);
                    return "Passagem com o menor valor corresponde a da companhia Azul, valor de 500,00R$";
                })
                .exceptionally(execao -> {
                    System.err.println("Erro encontrado: " + execao.getMessage());
                    return "Tente mais tarde";
                });

        // Como o CompletableFuture roda de forma assíncrona (em Background/Daemon Threads),
        // precisamos travar o método main temporariamente para ver o resultado no console.
        System.out.println("Resultado final: " + valoresPassagem.join());
    }

    // Movido para fora do método main e adicionado o 'static'
    public static void simularEspera(int milisegundos) {
        try {
            TimeUnit.MILLISECONDS.sleep(milisegundos);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}