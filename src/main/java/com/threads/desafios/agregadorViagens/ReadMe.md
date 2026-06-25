DESAFIO 2: O AGREGADOR DE VIAGENS ASSÍNCRONO (COMPLETABLEFUTURE)

Cenário: Você precisa criar um sistema que cota o preço de uma passagem aérea em 3 companhias
diferentes simultaneamente: Latam (demora 800ms), Azul (demora 1200ms) e Gol (demora 400ms).

Requisito técnico: Crie três instâncias de CompletableFuture.supplyAsync independentes
retornando os preços simulados. Utilize o método combinador CompletableFuture.allOf() para
disparar as buscas em paralelo e aguardar a conclusão de todas. Ao final do agregador, filtre e imprima
qual foi a companhia que retornou o menor preço válido.