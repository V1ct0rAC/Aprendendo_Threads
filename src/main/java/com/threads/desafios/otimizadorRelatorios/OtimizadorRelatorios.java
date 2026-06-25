package com.threads.desafios.otimizadorRelatorios;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class OtimizadorRelatorios {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 10; i++) {
            int idRelatorio = i;
            executor.execute(() -> {
                String funcionarioNome = Thread.currentThread().getName();
                System.out.println("Tarefa: " + idRelatorio + "sendo executada por:" + funcionarioNome);
                try {
                    TimeUnit.SECONDS.sleep(2);

                } catch (InterruptedException e) {
                    System.err.println("Operação finalizada, tempo ultrapassado.");
                    //esse err indica erro ai a letra sai vermelha
                }
                System.out.println("Tarefa: " + idRelatorio + " finalizada com sucesso.");
            });


        }
        executor.shutdown();

        try {
            if (!executor.awaitTermination(8, TimeUnit.SECONDS)) {
                System.out.println("Sistema finalizada com sucesso.");
                executor.shutdown();

            }

        }

        catch(InterruptedException e){
            executor.shutdown();



        }
        System.out.println("Operação finalizada.");

    }
}
