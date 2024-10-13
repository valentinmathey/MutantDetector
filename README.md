# 🚀 Parcial Magneto - Detección de Mutantes

## 🧬 Introducción
Magneto está en busca de mutantes para su causa y te ha contratado para desarrollar un sistema que detecte si un humano es mutante basándose en su secuencia de ADN. El sistema debe procesar secuencias de ADN y determinar si existen más de una secuencia de cuatro letras iguales de forma oblicua, horizontal o vertical.

## ⚙️ Funcionamiento
Se recibirá como parámetro un array de Strings que representan cada fila de una tabla de (6x6) con la secuencia del ADN. Las letras de los Strings solo pueden ser: (A,T,C,G), las cuales representa cada base nitrogenada del ADN.

Se sabrá si un humano es mutante, si se encuentra MAS DE UNA SECUENCIA de cuatro letras iguales, de forma oblicua, horizontal o vertical.

Las filas de la matriz a verificar se ingresan por teclado.

Ejemplo de input: 'ATCGTA' (esto equivale a una fila de la matriz)

Una vez cargada correctamente la misma, se aplica una función que verifica si hay presencia en la matriz de mutantes o no y se devuelve el resultado al usuario en base a eso.

## 🚀 Firma del método:
```java
boolean isMutant(String[] dna)
```
Recibe un array de Strings que representan cada fila de una tabla de NxN con la secuencia del ADN. Las letras válidas son: A, T, C, G, representando las bases nitrogenadas del ADN.

## 🔍 Funcionalidades principales:

•Verificación de secuencias mutantes: Se detectan secuencias en las direcciones horizontal, vertical y diagonal.

•Soporte para validación de matrices no cuadradas.

•Validación de matrices con caracteres no válidos.

•Detección de ADN ya registrado en la base de datos.

•Manejo de matrices vacías.

## 🚀 Ejecución
El proyecto ha sido deployado a Render y puedes accederlo mediante el siguiente link:

🔗 https://mutantdetectorapi.onrender.com

## 🌐 Endpoints

- **POST /mutant** - Recibe un JSON con la matriz de ADN a verificar. Ejemplo:

```json
{
    "dna": [
        "ATGCGA",
        "CAGTGC",
        "TTATGT",
        "AGAAGG",
        "CCCCTA",
        "TCACTG"
    ]
}
```
- **GET /stats** - Devuelve un JSON con la cantidad de mutantes y humanos verificados. Ejemplo:
```json
{
    "count_mutant_dna": 50,
    "count_human_dna": 150,
    "ratio": 0.33
}
```
## 🧬 Ejemplo de ADN

Ejemplo de matriz **MUTANTE**:

```json
{
    "dna": [
      "ATGCGA",
      "CAGTGC",
      "TTATGT",
      "AGAAAG",
      "CCCCTA",
      "TCACTG"
    ]
}
```
## 🧬 Ejemplo de ADN

Ejemplo de matriz **NO MUTANTE**:

```json
{
    "dna": [
      "ATGGTG",
      "GTCTTA",
      "AATTGG",
      "ACTAGT",
      "GGATTC", 
      "AGGCAA"
    ]
}
```

# 💻 Tech Stack:
<div align="center">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" height="40" alt="Java logo" /> 
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" height="40" alt="Spring logo" /> 
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/docker/docker-original.svg" height="40" alt="Docker logo" /> 
  <img src="https://junit.org/junit5/assets/img/junit5-logo.png" height="40" alt="JUnit logo" /> 
  <img src="https://raw.githubusercontent.com/mockito/mockito.github.io/master/img/logo%402x.png" height="40" alt="Mockito logo" /> 
</div>

# 🧑🏻‍💻 Autor:

Valentin Mathey | <a href="https://github.com/valentinmathey">@valentinmathey</a>

[![Discord](https://img.shields.io/badge/Discord-%237289DA.svg?logo=discord&logoColor=white)](https://discord.gg/valentinmathey) [![Facebook](https://img.shields.io/badge/Facebook-%231877F2.svg?logo=Facebook&logoColor=white)](https://facebook.com/https://www.facebook.com/ValentinEzequielMathey) [![Instagram](https://img.shields.io/badge/Instagram-%23E4405F.svg?logo=Instagram&logoColor=white)](https://instagram.com/https://www.instagram.com/valen.mathey/) [![LinkedIn](https://img.shields.io/badge/LinkedIn-%230077B5.svg?logo=linkedin&logoColor=white)](https://linkedin.com/in/https://www.linkedin.com/in/valentin-mathey/) [![Twitter](https://img.shields.io/badge/Twitter-%231DA1F2.svg?logo=Twitter&logoColor=white)](https://twitter.com/https://twitter.com/valen_mathey)
