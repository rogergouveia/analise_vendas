# analise_vendas

Sistema que lê arquivos na pasta de entrada, processa seus dados, e gera relatórios na pasta de saída.

Funcionamento
 - A aplicação continua funcionando até que o processo seja derrubado
 1. A cada 5 seg é disparada uma nova thread que reexecuta o processo. Se um relatório já existir com mesmo nome, ele é sobreescrito.
 2.Os arquivos de entrada não são apagados.
 -A aplicação não aceita parâmetros de execução
 -É gerado um relatório para cada arquivo


Pasta entrada: %HOMEPATH%/data/in
Pasta saída: %HOMEPATH%/data/out

Arquivo de Entrada
-Arquivos devem possuir extensão ".dat"
-Arquivos devem estar codificados em UTF8(Sem BOM)

Arquivo Saída
-Possuirá o mesmo nome do arquivo entrada, exceto que a extensão será ".done.dat"


Dados arquivo de Entrada
Arquivo de entrada podem possuir 3 tipos de dados (vendedor, cliente, venda)
Cada linha representa um tipo de dado

Dado Vendedor <CódigoTipoDado>ç<CPF>ç<Nome>ç<Salário>
-CódigoTipoDado de vendedor é sempre 001
-CPF pode possuir ou não máscara. Não deve possuir mais do que 11 dígitos. Não é validado se o CPF é válido.
-Nome qualquer string de qualquer tamanho
-Salário deve ser um número com ponto marcando o início das casas decimais

Dado Cliente <CódigoTipoDado>ç<CNPJ>ç<Nome>ç<Área>
-CódigoTipoDado de vendedor é sempre 002
-CNPJ pode possuir ou não máscara. Não deve possuir mais do que 14 dígitos. Não é validado se CNPJ é válido.
-Nome pode ser qualquer string de qualquer tamanho
-Área pode ser qualquer string de qualquer tamanho

Dado Venda <CódigoTipoDado>ç<IDVenda>ç[<DadoVendaItem>]ç<NomeVendedor>
-CódigoTipoDado de vendedor é sempre 003
-ID venda é número inteiro.
-DadoVendaItem é outro Tipo de Dado conforme abaixo
-NomeVendedor pode ser qualquer string de qualquer tamanho

Dado Venda Item <IDItem>-<QuantidadeItem>-<PrecoItem>,...
-IDItem qualquer inteiro
-QuantidadeItem qualquer inteiro
-PrecoItem deve ser um número com ponto marcando o início das casas decimais


Relatório
O relatório impresso apenas apresenta 4 informações
1.Quantidade de clientes do arquivo de entrada
2.Quantidade de vendedores do arquivo de entrada
3.ID da venda mais cara
4.Nome e CPF do vendedor que arrecadou menos dinheiro considerando-se todas as vendas efetivadas por ele no arquivo de entrada


