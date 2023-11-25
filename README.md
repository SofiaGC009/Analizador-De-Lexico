# Proyecto Final de Calidad y Pruebas de Software

Este repositorio forma parte del proyecto final de la clase de Calidad y Pruebas de Software. Se eligió el proyecto final de Teoría Computacional, un Analizador Léxico, como objeto de estudio. El propósito principal es realizar pruebas detalladas para evaluar y mejorar la calidad del código implementado. Se han diseñado casos de prueba utilizando JUnit 5, cubriendo aspectos clave del analizador léxico. Este enfoque sigue prácticas de desarrollo centradas en la calidad y la implementación de pruebas exhaustivas para garantizar el correcto funcionamiento del analizador léxico en diversos contextos.

## Analizador Léxico

El Analizador Léxico es una parte fundamental de un compilador que se encarga de escanear el código fuente y dividirlo en unidades llamadas "tokens". Estos tokens son bloques de texto con significado semántico, como identificadores, palabras clave, constantes y operadores. La función principal del Analizador Léxico es facilitar el procesamiento del código fuente, simplificando la tarea del Analizador Sintáctico.

### Funciones Principales

1. **Reconocimiento de Tokens:**
   - **Identificadores:** Palabras que representan nombres de variables, funciones u otros elementos del programa.
   - **Palabras Clave:** Palabras reservadas del lenguaje que tienen un significado específico (if, else, while, etc.).
   - **Constantes:** Números (enteros o reales) y cadenas de texto.
   - **Operadores:** Símbolos que realizan operaciones (aritméticas, lógicas, etc.).
   - **Delimitadores:** Caracteres que indican el inicio o fin de bloques de código (por ejemplo, llaves `{}`).

2. **Eliminación de Comentarios y Espacios en Blanco:**
   - El Analizador Léxico suele ignorar comentarios y espacios en blanco, ya que no tienen impacto en la lógica del programa.

3. **Manejo de Errores:**
   - Puede detectar y manejar errores léxicos, como caracteres no reconocidos o tokens mal formados.

## Proceso de Análisis

1. **Escaneo (Scanning):**
   - Se lee el código fuente carácter por carácter para identificar tokens.

2. **Reconocimiento:**
   - Se determina el tipo de cada token basándose en patrones predefinidos (expresiones regulares, en muchos casos).

3. **Generación de Tokens:**
   - Se crea una lista de tokens con información adicional, como el tipo del token, su posición en el código fuente, etc.

### Implementación

En este proyecto, se ha optado por una implementación manual en Java.

### Pruebas

Para garantizar la precisión del Analizador Léxico, se han desarrollado pruebas unitarias que evalúan su capacidad para reconocer correctamente los tokens en diferentes situaciones. Estas pruebas forman parte del proceso de integración continua para asegurar la calidad del código.

## Herramientas utilizadas

- Lenguaje de Programacion: Java
- Gestor de Proyectos: Maven
- Software de Testing: JUnit 5
