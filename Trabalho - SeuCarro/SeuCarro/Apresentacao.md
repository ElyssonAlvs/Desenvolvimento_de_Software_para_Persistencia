# SeuCarro - Primeira Etapa

Para o projeto que acompanhará todo o curso, nós, eu e meu colega Gustavo Almeida, optamos por criar uma aplicação relacionada à venda de carros. Nosso objetivo é persistir dados sobre as vendas de veículos em uma loja de automóveis. Por enquanto, estamos limitando as funcionalidades da aplicação às que foram requisitadas para a primeira etapa do trabalho:

## Funcionalidades da Primeira Etapa

1. **Definir uma Entidade e Criar uma Classe Java:** Escolhemos a entidade "Carro" para representar os veículos que serão vendidos. A entidade "Carro" é composta pelos seguintes atributos: 
   - ID do Carro
   - Marca
   - Modelo
   - Configuração (Manual ou Automático)
   - Ano de Fabricação
   - Tipo de combustível
   - Preço

2. **Criar uma Classe Principal com um Menu:** Implementamos uma classe principal que contém um menu com as seguintes funcionalidades:

   - **F1. Inserir Entidade no Arquivo CSV:** Permite cadastrar dados relacionados à entidade "Carro" digitados via teclado e adicioná-los a um arquivo CSV.

   - **F2. Mostrar a Quantidade de Entidades no Arquivo CSV:** Mesmo que a aplicação seja reiniciada, esta funcionalidade sempre mostrará a quantidade real de carros no arquivo CSV, mesmo que os dados tenham sido inseridos ou removidos externamente através de um editor de texto.

   - **F3. Converter Dados para JSON e XML:** Utilizamos a biblioteca Jackson para converter os dados do arquivo CSV em arquivos JSON e XML.

   - **F5. Compactar o Arquivo CSV:** Utilizamos a API Java para comprimir o arquivo CSV em um arquivo ZIP com o mesmo nome.

   - **F6. Mostrar o Hash SHA256 do Arquivo CSV:** Utilizamos a API Java para calcular e exibir o hash SHA256 do arquivo CSV.

3. **Divisão de Tarefas:**
   Criamos um arquivo chamado "divisao_tarefas.txt" detalhando a divisão de tarefas e descrevendo o que cada membro da equipe contribuiu para o projeto.

## Observações:

1. Cada funcionalidade foi implementada em classes específicas, seguindo uma abordagem de modularização.

2. A classe principal é capaz de executar todas as funcionalidades da aplicação, eliminando a necessidade de sair do programa para executar outra classe com o método principal. Portanto, a aplicação possui um único método main para executar todas as funcionalidades.

3. Mantivemos a separação entre a interface com o usuário (UI) e as demais funcionalidades da aplicação.

4. Gerenciamos as dependências do projeto utilizando um gerenciador de build, no nosso caso, o Maven.

5. Para a entrega, incluímos o código-fonte do projeto e o arquivo CSV com dados reais. Não fornecemos arquivos compilados ou JAR das bibliotecas utilizadas.

6. Evitamos preencher atributos com valores fictícios, priorizando o uso de dados próximos a situações reais.

Com estas implementações, nosso projeto está pronto para a primeira etapa do trabalho da disciplina.