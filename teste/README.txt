Em vez de fazer as coisas com mock e por já ter um projeto real com acesso a banco, e uma api externa, preferi mandar esse código: 
-Esta aplicação foi desenvolvida com o java 11.
-As suas dependências foram configuradas com Maven.
-O banco de dados é oracle, e antes de rodar a aplicação rode o script "script.sql" no banco de dados, esse script está na pasta inicial do projeto.
-Verifique se a senha e url do banco em application.properties é a mesma que usam.
-A aplicação foi feita com springboot e para iniciá-la, rode o arquivo TesteApplication.java
-Para conferir o que a API oferece, após a aplicação subir com sucesso, digite no navegador o link: http://localhost:8080/swagger-ui-custom.html
