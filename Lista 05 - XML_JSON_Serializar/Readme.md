## Lista 05 - XML, JSON e Serialização
Embora esta lista tenha mais questões, há muita semelhança entre algumas delas, permitindo o reaproveitamento das soluções das questões anteriores.
Os slides e exemplos de código no GitHub de Serialização, XML e JSON já estão disponibilizados e possuem exemplos bastante semelhantes ao que se pede nesta lista.
As bibliotecas necessárias para executar a lista estão disponíveis em: https://github.com/regispires/praticas_dspersist_2023/tree/main/modulo1/libs
1. Crie uma classe Java de entidade. Escolha uma entidade que você já propôs para seu Trabalho Prático. Exemplo: classe Filme (id, titulo, sinopse, diretor). A classe deve implementar a interface java.io.Serializable. Crie também uma classe que possua uma lista de objetos da entidade escolhida. Exemplo: classe Filmes, possuindo uma lista de Filme (List<Filme> filmes). Veja, nos slides sobre XML, os exemplos das classes Pessoa e Pessoas.

2. Crie uma classe Java de nome SerializaJava para instanciar objetos da classe definida na Questão 1 e adicionar esses objetos em uma Lista. Depois percorrer a lista e Serializar os objetos em disco/ssd. Serialize usando a Serialização de objetos da própria API Java.

3. Crie uma classe java de nome DesserializaJava para ler / desserializar os objetos Serializados na Questão 2 e exibi-los.

4. Crie uma classe Java de nome SerializaXML para instanciar objetos da classe definida na Questão 1 e adicionar esses objetos em uma Lista. Depois percorrer a lista e Serializar os objetos em disco/ssd. Serialize usando a Serialização de objetos da biblioteca Jackson.

5. Crie uma classe java de nome DesserializaXML para ler / desserializar os objetos Serializados na Questão 4 e exibi-los.

6.  Crie uma classe Java de nome SerializaJSON para instanciar objetos da classe definida na Questão 1 e adicionar esses objetos em uma Lista. Depois percorrer a lista e Serializar os objetos em disco/ssd. Serialize usando a Serialização de objetos da biblioteca Jackson. 

7. Crie uma classe java de nome DesserializaJSON para ler / desserializar os objetos Serializados na Questão 6 e exibi-los.