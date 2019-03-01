### API para consulta de vendas e albuns

### Detalhes da API RESTful
* Projeto criado com Spring Boot e Java 8
* Banco de dados Mysql

### Porque Java?
 * Java é a liguagem que eu mais tenho experiencia e é uma linguagem bem robusta.
 
### Como executar a API
* Certifique-se de ter o Maven instalado e adicionado ao PATH de seu sistema operacional, assim como o Git
* git clone git clone https://github.com/danilomolina/api-music-albuns.git
* cd api-music
* mvn spring-boot:run

### Importando o projeto no Eclipse ou STS
* No Eclipse/STS, importe o projeto como projeto Maven.

### MySql
 * O banco de dados Mysql deve estar instalado na maquina com usuario root e senha isl4tybz
 * Deve ser criado um Schema com o nome de api-music
 
### EndPoints
 * Documentação Conmpleta dos endpoints: http://localhost:8080/swagger-ui.html
 
 * GET - http://localhost:8080/api/music/v1/autorization
   * endPoint retorna um link para que possa ser feita a autenticação no spotify, copie este link e cole no navegador
   Depois de acessar o link utilize o usuário: apimusicspotify@gmail.com com a senha isl4tybz
   Depois de permitido o acesso a API do Spotify o endpoint vai inserir os albuns no banco de dados
   
  * Para consumir a API do Spotify utilizei a bilioteca Spotify Web API Java que esta disponivel no github: 
    https://github.com/thelinmichael/spotify-web-api-java
    
 * GET - http://localhost:8080/api/music/v1/genre/rock
   * endPoint retorna todos os albuns conforme o genero solicitado
   
  * GET - http://localhost:8080/api/music/v1/45
   * Este endPoint retorna um album conforme o ID solicitado
   
 * POST - http://localhost:8080/api/music/v1/
   * endPoint responsavel em criar uma venda
   * Ex. de Json para criar a venda(deve ser passado no Body):
   * {
	"date": "24/02/2019",
	"value": "20",
	"itens":[{"quant":1, "cashback":0, "disk":56, "value": 10 }, {"quant":1, "cashback":0, "disk":126, "value":10 }]
   }
   
 * GET - http://localhost:8080/api/music/v1/invoice/02.02.2019/24.02.2019
   * endPoint responsavel em consuntar um periodo de venda. Deve ser passada a data no formato: 02.02.2018
   
 * GET - http://localhost:8080/api/music/v1/invoice/78
   * endPoint que retorna uma venda pelo ID

  
