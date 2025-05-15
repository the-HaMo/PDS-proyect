# LearningApp

### DESCRIPCIÓN 

**LearningApp** es una aplicación de aprendizaje de idiomas totalmente gratuita desarrollada por los alumnos; **Mohammed Amrou Labied Nasser**, **Sergio García García** y **Jorge Fernández Córcoles**, bajo la supervisión del profesor **Antonio Lopéz Martinéz Carrasco**.

El propósito de LearningApp es facilitar el aprendizaje de idiomas como el inglés, francés o el español a través de cursos diseñados tanto por la propia aplicación como por otros usuarios con experiencia en la enseñanza. Esto permite a los usuarios acceder a una gran variedad de materiales adaptados a diferentes niveles y estilos de aprendizaje. 

Además, LearningApp brinda la oportunidad de que cada usuario pueda crear y compartir su propio curso, ofreciendo una comunidad de aprendiazje colaboratica donde los estudiantes pueden beneficiarse de contenidos personalizados y adaptados a sus necesidades. 

### Requisitos

En este apartado vamos a identificar los requisitos necesarios para el correcto funcionamiento de LearningApp expresados mediante casos de uso, tanto a nivel de usuario como a nivel del sistema. Esto permitirá establecer objetivos claros para su implementación, resolviendo posibles ambigüedades y evitando la duplicidad de análisis. Antes de comenzar, es importante destacar la existencia de dos roles dentro de aplicación: el **Estudiante**, quien realiza los cursos ,y el **Colaborador**, quien los crea y los sube a la plataforma.

### Casos de Uso

***Caso de uso:*** Registrarse en la aplicacion ([Desarrollo-Caso1](https://github.com/the-HaMo/PDS-proyect/blob/main/info/Desarrollo-Caso1.md))\
***Actor:*** Estudiante y Colaborador

***Caso de uso:*** Iniciar Sesion en la aplicacion ([Desarrollo-Caso2](https://github.com/the-HaMo/PDS-proyect/blob/main/info/Desarrollo-Caso2.md))\
***Actor:*** Estudiante y Colaborador 

***Caso de uso:*** Cerrar Sesion ([Desarrollo-Caso3](https://github.com/the-HaMo/PDS-proyect/blob/main/info/Desarrollo-Caso3.md))\
***Actor:*** Estudiante y Colaborador 

***Caso de uso:*** Instalar curso disponible en biblioteca interna ([Desarrollo-Caso4](https://github.com/the-HaMo/PDS-proyect/blob/main/info/Desarrollo-Caso4.md))\
***Actor:*** Estudiante 

***Caso de uso:*** Ejecutar y realizar Curso ([Desarrollo-Caso5](https://github.com/the-HaMo/PDS-proyect/blob/main/info/Desarrollo-Caso5.md))\
***Actor:*** Estudiante

***Caso de uso:*** Ver estadísticas de uso en la plataforma ([Desarrollo-Caso6](https://github.com/the-HaMo/PDS-proyect/blob/main/info/Desarrollo-Caso6.md))\
***Actor:*** Estudiante

***Caso de uso:*** Subir cursos a la plataforma ([Desarrollo-Caso7](https://github.com/the-HaMo/PDS-proyect/blob/main/info/Desarrollo-Caso7.md))\
***Actor:*** Colaborador

***Caso de uso:*** Compartir cursos ([Desarrollo-Caso8](https://github.com/the-HaMo/PDS-proyect/blob/main/info/Desarrollo-Caso8.md))\
***Actor:*** Colaborador

***Caso de uso:*** Guarda estado del curso para un usuario ([Desarrollo-Caso9](https://github.com/the-HaMo/PDS-proyect/blob/main/info/Desarrollo-Caso9.md))\
***Actor:*** Sistema

### Funcionalidad extra (expresada en casos de uso) 

***Caso de uso:*** Examinarse de un curso ([Desarrollo-Caso10](https://github.com/the-HaMo/PDS-proyect/blob/main/info/Desarrollo-Caso10.md))\
***Actor:*** Estudiante 

***Caso de uso:*** Dar like a un Curso ([Desarrollo-Caso11](https://github.com/the-HaMo/PDS-proyect/blob/main/info/Desarrollo-Caso11.md))\
***Actor:*** Estudiante

***Caso de uso:*** Mostrar una lista ordenada de los cursos según el número de likes ([Desarrollo-Caso12](https://github.com/the-HaMo/PDS-proyect/blob/main/info/Desarrollo-Caso12.md))\
***Actor:*** Estudiante

***Caso de uso:*** Ver el número de descargas que ha recibido un curso ([Desarrollo-Caso13](https://github.com/the-HaMo/PDS-proyect/blob/main/info/Desarrollo-Caso13.md))\
***Actor:*** Colaborador

***Caso de uso:*** Mostrar una lista ordenada de los cursos según el número de descargas ([Desarrollo-Caso14](https://github.com/the-HaMo/PDS-proyect/blob/main/info/Desarrollo-Caso14.md))\
***Actor:*** Colaborador

### Diseño

A continuación, mostraremos la versión final en el siguiente apartado del modelo de dominio de AppLearning donde se destaca la existencia de dos tipos de entidades que heredan de Usuario que son **Creador** y **Estudiante**. La primera entidad es la encargada de crear cursos, mientras que la segunda tiene el rol de realizar esos cursos. 

Otras cuestiones de interés son que la clase **Pregunta** es una clase abstracta lo que permite añadir nuevos tipos de ejercicio en un futuro. Además se muestra la relación entre los estudiantes y su progreso mediante la clase **ProgresoCurso**, permitiendo así un seguimiento detallado del aprendizaje de un curso particular.

Como funcionalidad adicional, hemos añadido la clase **EstadísticaUsiario**, que almacenará información relacionada sobre el tiempo de uso y la mejor racha de preguntas correctas de un estudiante. Y también, la clase **Like** que permite identificar que estudiante le ha dado me gusta a un curso concreto.

### Modelo

![Mdominio.VF](https://github.com/the-HaMo/PDS-proyect/blob/main/info/diagramaVF.jpg)

### MANUAL DE USO 

La aplicación ... 

### Ejemplos de Cursos

Los cursos diseñados por los colaboradores deben tener la extensión *json*, algunos ejemplos se muestran a continuación para descargar:

Curso inglés:  [curso-Ing.json](https://github.com/the-HaMo/PDS-proyect/blob/main/cursos/curso-Ing.json)\
Curso francés: [curso-Fr.json](https://github.com/the-HaMo/PDS-proyect/blob/main/cursos/curso-Fr.json)\
Curso español: [curso-Esp.json](https://github.com/the-HaMo/PDS-proyect/blob/main/cursos/curso-Esp.json)

