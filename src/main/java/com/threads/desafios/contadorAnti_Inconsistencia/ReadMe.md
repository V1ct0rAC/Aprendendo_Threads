DESAFIO 3: O CONTADOR ANTI-INCONSISTÊNCIA (THREAD SAFETY)

Cenário: Duas threads estão compartilhando uma classe contadora comum e incrementando o mesmo
número em loop 10.000 vezes cada uma.

Requisito técnico: Crie um cenário de teste onde as duas threads manipulam um inteiro comum puro.
Note que o resultado final quase nunca baterá 20.0000 devido à falta de sincronia. Corrija o algoritmo
utilizando AtomicInteger para provar que a contagem passará a atingir exatamente 20.000 com total
integridade de dados.


🧠 Por que o AtomicInteger funcionou?

Diferente do int comum (onde as threads lêem e escrevem ao mesmo tempo gerando bagunça),
o AtomicInteger usa uma operação a nível de processador chamada CAS (Compare-And-Swap). É como se ele colocasse uma 
catraca eletrônica na variável: apenas uma thread consegue alterar o valor por vez. Se duas tentarem no mesmo milissegundo, 
uma passa e a outra espera sua vez automaticamente, sem perder nenhuma contagem!