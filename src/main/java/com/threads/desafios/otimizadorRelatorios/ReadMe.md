DESAFIO 1: O OTIMIZADOR DE RELATÓRIOS (EXECUTOR FRAMEWORK)

Cenário: Você tem uma lista contendo 10 IDs de relatórios que precisam ser gerados. Cada relatório
demora exatamente 2 segundos para ser calculado. De forma sequencial, o programa demora 20
segundos totais.

Requisito técnico: Utilize um ExecutorService com um pool fixo de 3 threads. Submeta as tarefas
simulando o delay de 2 segundos. O programa completo deve processar os 10 relatórios de forma
segura e finalizar em aproximadamente 6 a 8 segundos no total.