package com.threads.desafios.contadorAnti_Inconsistencia;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;



public class ContadorAnti_Inconsistencia {
    private static int contadorComum = 0;
    private static AtomicInteger contadorAtomico = new AtomicInteger();
    public static void main (String[] args) throws InterruptedException{

        System.out.println("Iniciando ...");



        try (ExecutorService executor = Executors.newFixedThreadPool(2)) {

            executor.submit(() -> {
                for (int i = 0; i < 100000; i++) {
                    contadorComum++;
                }

            });

            executor.submit(() -> {
                for (int i = 0; i < 100000; i++) {
                    contadorComum++;
                }
            });
        }
            System.out.println("====== RESULTADO INTEIRO COMUM ======");
            System.out.println("Esperado: 20000");
            System.out.println("Obtido:   " + contadorComum);
            System.out.println("Inconsistência detectada? " + (contadorComum != 20000 ? "⚠️ SIM!" : "❌ Não (Raro)"));
            System.out.println("-------------------------------------\n");

            try (ExecutorService executor = Executors.newFixedThreadPool(2)) {

                executor.submit(() -> {
                    for (int i = 0; i < 100000; i++) {
                        contadorAtomico.incrementAndGet();
                    }

                });

                executor.submit(() -> {
                    for (int i = 0; i < 100000; i++) {
                        contadorAtomico.incrementAndGet();
                    }
                });
            }

                System.out.println("====== RESULTADO ATOMIC INTEGER ======");
                System.out.println("Esperado: 20000");
                System.out.println("Obtido:   " + contadorAtomico.get());
                System.out.println("Integridade total garantida? " + (contadorAtomico.get() == 20000 ? "✅ SIM!" : "❌ Não"));
            }


                
            };

//








