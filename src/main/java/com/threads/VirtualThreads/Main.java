package com.threads.VirtualThreads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main (String[] args){
        System.out.println("Iniciando pool escalável com Virtual Threads...");
// Nova fábrica do Java 21: Aloca uma Thread Virtual para cada tarefa         submetida!
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
// Vamos submeter 10.000 tarefas simultâneas de mentira.
// Se fizéssemos isso com o FixedThreadPool tradicional, o computador travaria por falta de RAM.
            for (int i = 0; i < 10000; i++) {
                final int id = i;
                executor.submit(() -> {
// Execução extremamente rápida e ultra leve
                    if (id % 2000 == 0) {
                        System.out.println("Tarefa " + id + " processada por uma Virtual Thread.");
                    }
                    try { Thread.sleep(100); }
                    catch (InterruptedException e) {}
                });
            }
        } // O bloco try-with-resources faz o auto-close do executor, esperando tudo erminar.
                System.out.println("10.000 tarefas executadas sem sobrecarregar o hardware!");
    }
}
