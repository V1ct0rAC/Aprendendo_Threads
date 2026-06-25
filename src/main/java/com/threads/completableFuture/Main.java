package com.threads.completableFuture;

import java.util.concurrent.CompletableFuture;

import java.util.concurrent.TimeUnit;



public class Main {

        public static void main (String[]args){

            System.out.println("[Thread Principal] Iniciando processamento do pedido...");

            // Etapa 1: Inicia um fluxo assíncrono para buscar informações do usuário
            // Removeu-se o ponto e vírgula do final de cada bloco para permitir o encadeamento fluído (.thenApply)
            CompletableFuture<String> pipeline = CompletableFuture.supplyAsync(() -> {
                        System.out.println("[" + Thread.currentThread().getName() + "] Buscando usuário no DB...");
                        simularEspera(1000);
                        return "Victor Araujo"; // Retorno da etapa
                    })
                    // Etapa 2: Recebe o nome do usuário e processa o pagamento de forma encadeada
                    .thenApply(nomeUsuario -> {
                        System.out.println("[" + Thread.currentThread().getName() + "] Processando pagamento de " + nomeUsuario + "...");
                        simularEspera(1500);
                        return "PEDIDO_PAGO_ID_9982";
                    })
                    // Etapa 3: Recebe a confirmação do pagamento e gera a Nota Fiscal
                    .thenApply(statusPedido -> {
                        System.out.println("[" + Thread.currentThread().getName() + "] Emitindo Nota Fiscal para: " + statusPedido);
                        simularEspera(800);
                        return "NF-e #4521 emitida com sucesso!";
                    })
                    // Tratamento de Erros Integrado
                    .exceptionally(excecao -> {
                        System.err.println("Erro crítico detectado no pipeline: " + excecao.getMessage());
                        return "Fallback: Processamento falhou. Tente novamente mais tarde.";
                    });

            // O fluxo principal continua executando sem ser bloqueado pelas etapas acima!
            System.out.println("[Thread Principal] Continuando a carregar interface do app livremente...");

            // Usamos o .join() apenas para travar a execução do main no fim do exemplo e ver o resultado impresso

            String resultadoFinal = pipeline.join();
            System.out.println("[Resultado Final]: " + resultadoFinal);
        }

        // Método auxiliar para simular latência de rede/banco de dados
        public static void simularEspera(int milissegundos){
            try {
                TimeUnit.MILLISECONDS.sleep(milissegundos);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        }
    }





