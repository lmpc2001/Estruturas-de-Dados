# Estruturas de dados – Época de Normal

## Table of Contents

- [Estruturas de dados – Época de Normal](#estruturas-de-dados--época-de-normal)
  - [Table of Contents](#table-of-contents)
  - [Objetivos](#objetivos)
  - [Implementação](#implementação)
  - [Ferramentas necessárias antes de Iniciar a aplicação](#ferramentas-necessárias-antes-de-iniciar-a-aplicação)
  - [Testar a Aplicação via Testes Unitários](#testar-a-aplicação-via-testes-unitários)
  - [Iniciar o Jogo - Com o VS Code](#iniciar-o-jogo---com-o-vs-code)
  - [Extrutura dos Packages](#extrutura-dos-packages)
  - [Estrutura do repositório](#estrutura-do-repositório)

## Objetivos

- Utilizar os conhecimentos sobre estruturas de dados para escolher as estruturas de dados que 
melhor se aplicam à resolução do problema proposto;
- Desenhar e implementar, de forma eficaz e eficiente, os algoritmos de resolução do problema proposto

## Implementação
Durante o desenvolvimento do presente projeto, não foram utilizadas nenhuma das coleções da plataforma de coleções Java, tornando o seu desenvolvimento mais complexo e interessante.

Para além disso, foram utilizadas algumas ferramentas e boas práticas, tais como:
- **Ferramentas:**
  - Java;
  - VS Code;
- **Boas Práticas:**
  - Documentação do código através do JavaDoc;
  - Implementação de padrões de programação como: POO e SOLID;
  - Utilização do conceito de Clean Architecture;
  - Controlo de versões via Github [@lmpc2001](https://github.com/lmpc2001/Estruturas-de-Dados);

## Ferramentas necessárias antes de Iniciar a aplicação
* [Instalar Java](https://www.java.com/en/download/);
* [Instalar JDK 17 (Java Development Kit)](https://www.oracle.com/java/technologies/downloads/);

## Testar a Aplicação via Testes Unitários

```bash
# Executar todos os testes unitários da apicação
$ mvn clean test

# Executar apenas os testes unitários pertencentes ao módulo "com.example"
$ mvn test -Dtest="com.example.**"
```

## Iniciar o Jogo - Com o VS Code

```bash
$ cd c:${PATH_TO_PROJECT_FOLDER} ; /usr/bin/env C:\\Program\ Files\\Java\\jdk-17\\bin\\java.exe @C:${PATH_TO_YOUR_PC_USER}\AppData\\Local\\Temp\\cp_70tnek3dfhj7e490u65v9ybik.argfile com.example.App
```

## Extrutura dos Packages

- com.example:
  - Contém o ficheiro principal do projeto ([Para abrir clique aqui](./ed_project/src/main/java/com/example/)):
    - App
  
- com.example.configs:
  - Contém os ficheiros com as configurações do projeto (constantes, etc.) ([Para abrir clique aqui](./ed_project/src/main/java/com/example/configs/)):
    - Properties
  
- com.example.domain:
  - Contém todas as entidades identificadas durante o estudo do problema e necessárias para a sua resolução ([Para abrir clique aqui](./ed_project/src/main/java/com/example/domain/)):
    - Bot
    - Game
    - GameMap
    - Pair
    - Player

- com.example.domain.exceptions:
  - Contém as excepções relativas às entidades do projeto e por elas disparadas ([Para abrir clique aqui](./ed_project/src/main/java/com/example/domain/exceptions/)):
    - EmptyMapException
    - InvalidStrategyException

- com.example.structures.adt:
  - Contém as interfaces a implementar pelas estruturas de dados utilizadas ([Para abrir clique aqui](./ed_project/src/main/java/com/example/structures/adt/)):
    - GraphADT
    - ListADT
    - NetowkrADT
    - QueueADT
    - StackADT
    - UnorderListADT

- com.example.structures.exceptions:
  - Contém as excepções globais, relativas às estruturas de dados e por elas utilizadas  ([Para abrir clique aqui](./ed_project/src/main/java/com/example/structures/exceptions/)):
    - ElementNotFoundException
    - EmptyListException

- com.example.structures.implementation:
  - Contém a implementação dos componentes partilhados entre várias estruturas de dados ([Para abrir clique aqui](./ed_project/src/main/java/com/example/structures/implementation/)):
    - LinearNode
  
- com.example.structures.implementation.list:
  - Contém a implementação das listas utilizadas ([Para abrir clique aqui](./ed_project/src/main/java/com/example/structures/implementation/list/)):
    - ArrayList
    - ArrayUnorderedList
  
- com.example.structures.implementation.network:
  - Contém a implementação da estrutura "Network" ([Para abrir clique aqui](./ed_project/src/main/java/com/example/structures/implementation/network/)):
    - Graph
    - Network
  
- com.example.structures.implementation.network.exceptions:
  - Contém as excepções utilizadas pela estrutura "Network" ([Para abrir clique aqui](./ed_project/src/main/java/com/example/structures/implementation/network/exceptions/)):
    - InvalidValueException

- com.example.structures.implementation.queue:
  - Contém a implementação de uma "LinkedQueue" ([Para abrir clique aqui](./ed_project/src/main/java/com/example/structures/implementation/queue/)):
    - LinkedQueue
  
- com.example.structures.implementation.stack:
  - Contém a implementação de uma "LinkedStack" ([Para abrir clique aqui](./ed_project/src/main/java/com/example/structures/implementation/stack/)):
    - LinkedStack
  
- com.example.usecases:
  - Contém os casos de uso identificados para a resolução do problema  ([Para abrir clique aqui](./ed_project/src/main/java/com/example/usecases/)):
    - GenerateKickOffPlayerUseCase
    - GenerateMapUseCase
    - LoadPreviousGameUseCase
    - MoveBotUseCase
    - SetFlagLocationUseCase
    - SetPlayerBotsStrategyUseCase
    - SetPlayerBotsUseCase
    - SetPlayersUseCase
    - StartGameUseCase
  
- com.example.utils:
  - Contém as bibliotecas costumizadas e utilizadas no projeto ([Para abrir clique aqui](./ed_project/src/main/java/com/example/utils/)):
    - JSON
    - Menu
    - Randomness
    - RandomnessADT
    - Scanners
    - ScannersADT

## Estrutura do repositório

- [Docs](./Docs) : Constituída pelos documentos fornecidos pelos docentes da disciplina
- [ed_project](./ed_project/): Projeto Desenvolvido
- [ApiDocs](./ed_project/target/site/apidocs/index.html): Onde pode ser ecnontrado o JavaDoc do projeto
- [Files](./Files/) : Contém os seguintes ficheiros
  - Ficheiro resultante da exportação do Jogo;
  ```json
    {
      "map": [
        		[0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 7.0],
		        [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 7.0, 0.0, 0.0, 0.0],
		        [0.0, 0.0, 0.0, 5.0, 15.0, 15.0, 0.0, 0.0, 0.0, 0.0],
		        [0.0, 0.0, 5.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],
		        [0.0, 0.0, 15.0, 0.0, 0.0, 0.0, 0.0, 2.0, 0.0, 0.0],
		        [0.0, 0.0, 15.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],
		        [0.0, 7.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 3.0],
		        [0.0, 0.0, 0.0, 0.0, 2.0, 0.0, 0.0, 0.0, 2.0, 0.0],
		        [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 2.0, 0.0, 0.0],
		        [7.0, 0.0, 0.0, 0.0, 0.0, 0.0, 3.0, 0.0, 0.0, 0.0]
      ],
      "Players": [
        {
          "bots": [
            {
              "Strategy": "Shortest_Path",
              "Name": "bot 1",
              "Location": 2
            }
          ],
          "flag": 2,
          "name": "Lmpc"
        },
        {
          "bots": [
            {
              "Strategy": "Random",
              "Name": "Bot 2",
              "Location": 2
            }
          ],
          "flag": 2,
          "name": "Pfpc"
        }
      ]
    }
  ```
