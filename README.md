# benchmark-covid

## Executando o aplicativo

Abra o projeto em um IDE. Você pode baixar a edição da comunidade IntelliJ se ainda não tiver um IDE adequado. Uma vez aberto no IDE, localize a Applicationclasse e execute o método principal usando "Debug".

Para obter mais informações sobre a instalação em vários IDEs, veja como importar projetos Vaadin para diferentes IDEs .

Se você instalar o plugin Vaadin para IntelliJ, deverá iniciar a Applicationclasse usando "Depurar usando HotswapAgent" para ver as atualizações no código Java refletidas imediatamente no navegador.

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

## Exemplo da Rota API para os Resultados de Total, Media, Max, Min, de casos e mortes dos dois paises comparados

```
GET http://localhost:8080/api/compare/brazil/argentina?from=2020-03-01&to=2020-04-01
```

