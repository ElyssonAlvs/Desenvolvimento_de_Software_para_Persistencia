# Operações e Variáveis em Java

Este guia fornece uma explicação breve sobre operações comuns em Java e os dois tipos de variáveis: valor e referência.

## Operações Aritméticas

As operações aritméticas são usadas para realizar cálculos numéricos. Alguns operadores aritméticos comuns incluem:

- **Adição (+)**: Soma dois valores.
- **Subtração (-)**: Subtrai um valor de outro.
- **Multiplicação (*)**: Multiplica dois valores.
- **Divisão (/)**: Divide um valor por outro.
- **Módulo (%)**: Calcula o resto da divisão de um valor por outro.

## Operadores de Comparação

Os operadores de comparação são usados para comparar valores e produzir resultados booleanos (verdadeiro ou falso). Alguns operadores de comparação incluem:

- **Igual a (==)**: Verifica se dois valores são iguais.
- **Diferente de (!=)**: Verifica se dois valores são diferentes.
- **Maior que (>)**: Verifica se um valor é maior que outro.
- **Menor que (<)**: Verifica se um valor é menor que outro.
- **Maior ou igual a (>=)**: Verifica se um valor é maior ou igual a outro.
- **Menor ou igual a (<=)**: Verifica se um valor é menor ou igual a outro.

## Operadores Lógicos

Os operadores lógicos são usados para realizar operações lógicas em valores booleanos. Alguns operadores lógicos incluem:

- **E lógico (&&)**: Retorna verdadeiro se ambas as expressões forem verdadeiras.
- **OU lógico (||)**: Retorna verdadeiro se pelo menos uma das expressões for verdadeira.
- **NÃO lógico (!)**: Inverte o valor booleano da expressão.

## Operadores de Incremento e Decremento

Os operadores de incremento (`++`) e decremento (`--`) são usados para aumentar ou diminuir o valor de uma variável numérica em uma unidade.

## Operador Ternário

O operador ternário é uma forma concisa de expressar uma decisão com base em uma condição. A sintaxe é: `condição ? valor_se_verdadeiro : valor_se_falso`.

## Variáveis em Java

Em Java, existem dois tipos principais de variáveis: variáveis de valor (ou primitivas) e variáveis de referência.

### Variáveis de Valor (Primitivas)

As variáveis de valor armazenam diretamente o valor dos dados. Alguns exemplos de tipos primitivos em Java incluem `int`, `double`, `boolean`, etc.

```java
int numero = 42;
double temperatura = 25.5;
boolean ativo = true;
```
### Variáveis de Referência
O tipo referência,elas armazenam um endereço de memória
que aponta para a localização real dos dados,usadas para armazenar
objetos complexos, como instâncias de classes em linguagens orientadas
a objetos.Quando uma variável do tipo referência, é copiada para
outra variável ou passada como argumento para uma função, ambas as
variáveis apontam para o mesmo objeto na memória. Portanto, as
alterações em uma variável afetam a outra, pois ambas estão
referenciando o mesmo objeto.