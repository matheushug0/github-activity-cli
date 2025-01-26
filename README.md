# <img src="https://roadmap.sh/images/gifs/rocket.gif" width="25px"> Github User Activity CLI

![GitHub](https://img.shields.io/badge/GitHub-User%20Activity%20CLI-blue?logo=github&style=flat-square)

Aplicação com interface de linha de comando CLI(<i>Command Line Interface</i>) para busca e exibição de atividades recentes de um usuário no Github.
<br>Projeto sugerido pelo [Roadmap.sh - **Backend Developer**](https://roadmap.sh/projects/github-user-activity).
---

## Features
- **Busca** por atividades recentes de um usuário específico do Github.
- **Exibição** de eventos como **Push, Issues, Create, Watch** e outros.
- **Tratamento** de exceções e errors de **request** para a **API do Github**
---
## Funcionamento
1. A aplicação faz uma `HTTPRequest` ao endpoint `https://api.github.com/users/<USERNAME>/events`
2. Caso a **response** da requisição seja válida, o `response.body()` é tratado pela biblioteca [Gson](https://github.com/google/gson), responsável pela conversão do JSON em um Array.
3. Os dados obtidos são tratados de acordo com `type` dos eventos, e formatados para exibição no terminal.
---
## Instalação
1. Clone o repositório
```bash
git clone https://github.com/matheushug0/github-activity-cli.git
cd github-activity-cli
```
2. Faça o build do projeto Maven
```bash
mvn clean package install
```
3. Execute a aplicação
```bash
java -jar target/github-activity-cli-1.0.jar <username>
```
---
## Uso
```bash
java -jar target/github-activity-cli-1.0.jar matheushug0
```
### Exemplo de Saída:
```
Pushed 3 commits to matheushug0/task-tracker 4 days ago
Created in matheushug0/task-tracker 5 days ago
Opened a new Issue in matheushug0/calculadora99pay 8 days ago
Pushed 1 commit to matheushug0/imagesliteapi 34 days ago
Created in matheushug0/imagesliteapi 34 days ago
```
---
## Estrutura do Projeto
```
github-activity
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── br./
│   │           └── com/
│   │               └── mh/
│   │                   └── Main.java
├── target/   # Maven build output (ignored by .gitignore)
├── pom.xml   # Maven configuration
└── README.md # This file
```
---
## Tecnologias Usadas
- **Java**
- **Maven**
- **Github API**
- **Gson**
---
## Contato
- [**LinkedIn**](https://www.linkedin.com/in/matheus-hugo/)
---
## Referências
- [Github API](https://docs.github.com/en/rest?apiVersion=2022-11-28)
- [Gson](https://github.com/google/gson)
- [lephuocloc1729](https://github.com/lephuocloc1729/github-user-activity-cli)
- [iOmega17](https://github.com/iOmega17/GitHub-activity-CLI)