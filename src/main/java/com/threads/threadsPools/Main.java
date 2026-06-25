package com.threads.threadsPools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);
// Submetemos 5 tarefas distintas para serem processadas
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;

            executor.execute(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("[Tarefa " + taskId + "] Executando na thread: " +
                        threadName);
                try {
// Simulando uma operação de processamento/I/O de 1.5 segundos
                    TimeUnit.MILLISECONDS.sleep(1500);
                } catch (InterruptedException e) {
                    System.err.println("Thread interrompida.");
                }
                System.out.println("[Tarefa " + taskId + "] Finalizada.");
            });
        }
// SEMPRE feche o executor para liberar os recursos do sistema
        executor.shutdown();
        try {
// Aguarda até 10 segundos para que todas as tarefas da fila finalizem
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow(); // Força o encerramento se estourar o tempo
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
        System.out.println("Processamento finalizado com sucesso.");
    }
}
