Aplicação da To-do List

Introdução:
Esta é uma simples aplicação de uma To-Do List construída usando Spring Boot e React. A aplicação permite que os usuários gerenciem suas tarefas adicionando, completando e excluindo-as. Também inclui funcionalidades de desfazer e refazer para reverter e reaplicar alterações. A aplicação fornece uma API REST para interagir com as tarefas e uma interface de usuário moderna construída com React e estilizada usando Tailwind CSS.

Tecnologias:
Java
Spring Boot
Spring Data JPA
Banco de dados H2 (banco de dados em memória)
Maven
React
Tailwind CSS

Recursos:
Adicionar uma nova tarefa com uma descrição:
Completar uma tarefa.
Excluir uma tarefa.
Desfazer a última operação.
Refazer a última operação desfeita.

Para executar a aplicação siga estes passos:

Tenha o Java (JDK) e o Maven instalados na sua máquina.
Abra o projeto em sua IDE. 
Compile o projeto usando o Maven: 'mvn clean install'.
Execute a aplicação Spring Boot: 'mvn spring-boot:run'.
Navegue até o diretório frontend: 'cd frontend'.
Instale as dependências necessárias para o frontend React: 'npm install'.
Inicie o servidor de desenvolvimento React: 'npm start'.
A aplicação estará acessível em http://localhost:3000.

Endpoint da API:
GET /tasks: Obter todas as tarefas.
GET /tasks/{id}: Obter uma tarefa por ID.
POST /tasks/add: Adicionar uma nova tarefa com a descrição fornecida.
PUT /tasks/complete/{id}: Completar uma tarefa por ID.
DELETE /tasks/delete/{id}: Excluir uma tarefa por ID.
POST /tasks/undo: Desfazer a última operação.
POST /tasks/redo: Refazer a última operação desfeita.

Exemplo para adicionar uma nova tarefa:
Endpoint: POST /tasks/add
Corpo da solicitação: { "description": "Comprar mantimentos" }
Resposta: Tarefa adicionada com sucesso

Completar uma tarefa:
Endpoint: PUT /tasks/complete/1
Resposta: Tarefa concluída com sucesso

Desfazer a última operação:
Endpoint: POST /tasks/delete/1
Resposta: Tarefa excluída com sucesso

Desfazer a última operação:
Endpoint: POST /tasks/undo
Resposta: Desfazer com sucesso

Refazer a última operação:
Endpoint: POST /tasks/redo
Resposta: Refazer com sucesso


Interface de Comando (TaskCommand):
Esta interface define os métodos comuns execute() e undo() que as classes de comando concretas devem implementar. O método execute() é responsável por realizar a ação do comando, e o método undo() é usado para reverter esta ação.

Classes de Comando Concretas (AddTaskCommand, CompleteTaskCommand, DeleteTaskCommand):

Essas classes implementam a interface TaskCommand e representam ações específicas em tarefas: adicionar, completar e excluir tarefas. Cada classe de comando concreta recebe uma referência ao TaskService e a um objeto Task (para AddTaskCommand, CompleteTaskCommand e DeleteTaskCommand) para realizar ações nas tarefas.

Invoker (TaskService):
O TaskService atua como o invocador no padrão de comando. Ele contém as pilhas de desfazer e refazer para gerenciar comandos executados.
Quando uma ação relacionada a uma tarefa (adicionar, completar, excluir) é invocada, o TaskService cria um objeto de comando correspondente (por exemplo, AddTaskCommand, CompleteTaskCommand, DeleteTaskCommand) e chama seu método execute().
O método execute() realiza a ação correspondente na tarefa usando o TaskRepository.
Cliente (TaskController):

O TaskController atua como o cliente no padrão de comando.
Quando uma solicitação do cliente é recebida (por exemplo, adicionar tarefa, completar tarefa, excluir tarefa), o TaskController invoca o método correspondente no TaskService.
O TaskService cria e executa o comando apropriado, e a ação é realizada na tarefa.

Desfazer e Refazer:
Para oferecer suporte às funcionalidades de desfazer e refazer, o TaskService mantém duas pilhas: undoStack e redoStack.
Quando uma ação é executada, o comando correspondente é empilhado na undoStack.

Quando uma solicitação de desfazer é recebida, o TaskService retira o último comando da undoStack, chama seu método undo() e coloca o comando na redoStack.
Quando uma solicitação de refazer é recebida, o TaskService retira o último comando da redoStack, chama seu método execute() e coloca o comando de volta na undoStack.