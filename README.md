# benchmark-covid

Só conseguir iniciar essa aplicação em uma segunda feira e estou finalizando ela na quinta as 23:59 da mesma semana.
Como o tempo é corrido apenas apos o trabalho, no caso, as 18h consegui dar progressos na aplicação.
O projeto ainda existe muitas possibilidades de melhorias de codigo, implementação, verificações, erros e testes.

## Aplicativo

O Aplicativo contem uma Tela de Login e de Cadastro inicialmente

Apos logar o usuario seja jogado ao um Menu com 3 Opções

O Usuario pode Deslogar ou Editar o Usuario ao clickar no nome 

Em Benchmark o usuario pode fazer pesquisa de comparação ao digitar os nomes dos paises e colocar as datas

Apos a pesquisa o usuario pode clickar no botão Resultado que ia lhe enviar para outra tela

Na Tela Benchmatk Result ele tera as analises dos dois paises comparados

Em History ele tera o seu historico de comparações que ele fez

O Sistema tem uma Rota API para receber os resultados

```
GET http://localhost:8080/api/compare/brazil/canada?from=2020-03-08&to=2020-03-13
```

# Telas
#### Login
<img src="https://github.com/user-attachments/assets/15f8e151-95db-4c14-a2d5-e190792873c6" alt="Descrição da Imagem" width="500">

#### Cadastro
<img src="https://github.com/user-attachments/assets/5b2b9516-934c-42fb-8977-1679b6592322" alt="Descrição da Imagem" width="500">

#### Benchmark
<img src="https://github.com/user-attachments/assets/1d442138-f570-4e76-91d5-a76620296999" alt="Descrição da Imagem" width="500">

#### Result Benchmark
<img src="https://github.com/user-attachments/assets/f68c3c94-3475-49b7-a8c3-304633bc4e08" alt="Descrição da Imagem" width="500">

#### History
<img src="https://github.com/user-attachments/assets/aa108cfd-ee5d-40c3-90ee-550b566ccead" alt="Descrição da Imagem" width="500">

#### Button Deletar
<img src="https://github.com/user-attachments/assets/9f395694-26f3-4d85-890c-6b2f7edd0c72" alt="Descrição da Imagem" width="500">

#### Editar Usuario
<img src="https://github.com/user-attachments/assets/26d1df85-386e-405e-b898-eae11ea60908" alt="Descrição da Imagem" width="500">

## Implantando na produção

O projeto é um projeto Maven padrão. Para criar uma compilação de produção, chame

```
./mvnw clean package -Pproduction
```
Se você tiver o Maven instalado globalmente, poderá substituí-lo ./mvnwpor mvn.
 
```
java -jar target/benchmark-covid-1.0-SNAPSHOT.jar
```

## Estrutura do projeto
```
📦 com.wendril.application
├── 📂 controller
│   ├── 📄 BenchmarkApiController.java
│   ├── 📄 BenchmarkController.java
│   ├── 📄 ControllerGeneric.java
│   ├── 📄 CovidApiController.java
│   ├── 📄 ResultadoBenchmarkController.java
│   ├── 📄 TranslateApiGoogleController.java
│   ├── 📄 UserController.java
│
├── 📂 data
│   └── (Pasta para dados de Id das Model, Role, CovidApiProperties)
│
├── 📂 model
│   ├── 📄 Benchmark.java
│   ├── 📄 DadosCovidPais.java
│   ├── 📄 ResultadoBenchmark.java
│   ├── 📄 User.java
│
├── 📂 repository
│   ├── 📄 BenchmarkRepository.java
│   ├── 📄 ResultadoBenchmarkRepository.java
│   ├── 📄 UserRepository.java
│
├── 📂 security
│   ├── 📄 AuthenticatedUser.java
│   ├── 📄 SecurityConfiguration.java
│   ├── 📄 UserDetailsServiceImpl.java
│
├── 📂 services
│   ├── 📄 BenchmarkApiService.java
│   ├── 📄 BenchmarkStateService.java
│   ├── 📄 CovidApiService.java
│   ├── 📄 TranslateApiGoogleService.java
│
├── 📂 utils
│   ├── 📄 ConverterJsonToDadosCovidPais.java
│   ├── 📄 DatePickerPT.java
│   ├── 📄 Message.java
│   ├── 📄 NotificationComponent.java
│   ├── 📄 Titles.java
│
├── 📂 views
│   ├── 📂 benchmark
│   ├── 📂 history
│   ├── 📂 login
│   ├── 📂 register
│   ├── 📂 resultado
│   ├── 📄 MainLayout.java
│
├── 📄 Application.java
```


