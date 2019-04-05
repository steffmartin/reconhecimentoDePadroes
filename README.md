# Reconhecimento de Padrões

Pequeno executável que implementa e calcula a taxa de acertos do algoritmo __K-NN__ com a metodologia de validação cruzada __K-FOLD__ e normalização de dados com o algoritmo __Z-SCORE__.

Neste arquivo você encontrará instruções para instalação e também tecnologias utilizadas nesta aplicação.

## Primeiros Passos

Siga estas instruções para ter uma cópia do projeto funcionando em seu computador.

### Pré-requisitos

O que você precisará:

```
IDE Eclipse
Maven
JDK
```

### Instalando

Siga os passos a seguir para rodar esta aplicação em seu computador.

> Caso não esteja interessado no desenvolvimento, vá para a página [Releases](https://github.com/steffmartin/reconhecimentoDePadroes/releases) e baixe o compilado para executar. 

#### Obtendo uma cópia

Faça o download, use uma ferramente Git ou a própria IDE Eclipse para clonar este repositório:

```
No Eclipse, vá em File → Import → Git → Projects from Git → Clone URI.
Informe a URI e clique em Next → Next → Next → Import as general project → Next → Finish.
No Eclipse, com o botão direito sobre o projeto, vá em Configure → Convert to Maven Project
```

#### Executando

Execute o projeto como uma aplicação Java:

```
No Eclipse, com o botão direito sobre o projeto, vá em Run As → Java Application
```

## Demonstração

Primeiro informe o `Nome do arquivo com amostras:`. Este deve ser um arquivo texto com o seguinte padrão:

```
3 4
1.0 1.4 1.3 2.0 1
4.5 1.1 0.9 2.1 2
9.9 8.0 7.0 6.0 3
```

> A primeira linha contém apenas 2 valores: a quantidade de amostras (N) e o número de atributos (D) de cada amostra. Cada uma das N linhas seguintes representa uma amostra com seus D atributos e mais um valor numérico inteiro que representa a classificação daquela amostra.

Depois, informe o `Número de partições (K-FOLD):`. Seu conjunto de amostras será dividido nesta quantidade de conjuntos para aplicar a metodologia cruzada.

Informe o `Número de vizinhos (K-NN):` desejado para o cálculo do algoritmo de reconhecimento de padrões.

Responda com `true` ou `false` se deseja `Normalizar dados (Z-SCORE):` e `Aleatorizar amostras:`.

O programa mostrará a taxa de erro do algoritmo para os dados e opções escolhidas.

## Deployment

Distribua este projeto como um arquivo *.jar para rodá-lo em um computador com JRE:

```
No Eclipse, com o botão direito sobre o projeto, vá em Export → Java → JAR file
```

## Tecnologias utilizadas

* [Java](http://www.java.com) - Back-end
* [Maven](https://maven.apache.org/) - Gerenciador de dependências

### Conceitos teóricos aplicados

* [K-NN](https://en.wikipedia.org/wiki/K-nearest_neighbors_algorithm) - Algoritmo K-Nearest Neighbors
* [K-Fold](https://pt.wikipedia.org/wiki/Valida%C3%A7%C3%A3o_cruzada) - Validação Cruzada
* [Z-Score](https://en.wikipedia.org/wiki/Standard_score) - Padronização Estatística

## Autores

* Steffan Martins Alves - [LinkedIn](https://www.linkedin.com/in/steffanmartins/)
* Marcelo Alves Prado - [LinkedIn](https://www.linkedin.com/in/marcelo-prado-a07006118/)
* Heitor Henrique Nunes - [LinkedIn](https://www.linkedin.com/in/heitor-nunes-7b1322176/)

## Licença

Este projeto está licenciado sob a MIT License - leia [LICENSE.md](LICENSE.md) para mais detalhes.
