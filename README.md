# LearningApp

### DESCRIPCIÓN 

**LearningApp** es una aplicación de aprendizaje de idiomas totalmente gratuita desarrollada por los alumnos; **Mohammed Amrou Labied Nasser**, **Sergio García García** y **Jorge Fernández Córcoles**, bajo la supervisión del profesor **Antonio Lopéz Martinéz Carrasco**.

El propósito de LearningApp es facilitar el aprendizaje de idiomas como el inglés, francés o el español a través de cursos diseñados tanto por la propia aplicación como por otros usuarios con experiencia en la enseñanza.Esto permite a los usuarios acceder a una gran variedad de materiales adaptados a diferentes niveles y estilos de aprendizaje. 

Además, LearningApp brinda la oportunidad de que cada usuario pueda crear y compartir su propio curso, ofreciendo una comunidad de aprendiazje colaboratica donde los estudiantes pueden beneficiarse de contenidos personalizados y adaptados a sus necesidades. 

### Requisitos

En este apartado vamos a identificar los requisitos necesarios para el correcto funcionamiento de LearningApp, tanto a nivel de usuario como a nivel del sistema. Esto permitirá establecer objetivos claros para su implementación, resolviendo posibles ambigüedades y evitando la duplicidad de análisis. Antes de comenzar, es importante destacar la existencia de dos roles dentro de aplicación: el **Estudiante**, quien realiza los cursos ,y el **Colaborador**, quien los crea y los sube a la plataforma.

### Casos de Uso

***Caso de uso:*** Registrarse en la aplicacion ([Desarrollo-Caso1](https://github.com/the-HaMo/PDS-proyect/blob/main/info/Desarrollo-Caso1.md))\
***Actor:*** Estudiante y Colaborador

***Caso de uso:*** Iniciar Sesion en la aplicacion ([Desarrollo-Caso2](https://github.com/the-HaMo/PDS-proyect/blob/main/info/Desarrollo-Caso2.md))\
***Actor:*** Estudiante y Colaborador 

***Caso de uso:*** Cerrar Sesion ([Desarrollo-Caso3](https://github.com/the-HaMo/PDS-proyect/blob/main/info/Desarrollo-Caso3.md))\
***Actor:*** Estudiante y Colaborador 

***Caso de uso:*** Elegir curso disponible ([Desarrollo-Caso4](https://github.com/the-HaMo/PDS-proyect/blob/main/info/Desarrollo-Caso4.md))\
***Actor:*** Estudiante 

***Caso de uso:*** Ejecutar y realizar Curso ([Desarrollo-Caso5](https://github.com/the-HaMo/PDS-proyect/blob/main/info/Desarrollo-Caso5.md))\
***Actor:*** Estudiante

***Caso de uso:*** Realizar ejercicio de un tema del curso ([Desarrollo-Caso6](https://github.com/the-HaMo/PDS-proyect/blob/main/info/Desarrollo-Caso6.md))\
***Actor:*** Estudiante

***Caso de uso:*** Dentro de un curso, elige estrategia de aprendizaje ([Desarrollo-Caso7](https://github.com/the-HaMo/PDS-proyect/blob/main/info/Desarrollo-Caso7.md))\
***Actor:*** Estudiante

***Caso de uso:*** Instalar nuevos cursos en su biblioteca interna ([Desarrollo-Caso8](https://github.com/the-HaMo/PDS-proyect/blob/main/info/Desarrollo-Caso8.md))\
***Actor:*** Estudiante 

***Caso de uso:*** Ver estadísticas de uso en la plataforma ([Desarrollo-Caso9](https://github.com/the-HaMo/PDS-proyect/blob/main/info/Desarrollo-Caso9.md))\
***Actor:*** Estudiante

***Caso de uso:*** Subir cursos a la plataforma ([Desarrollo-Caso10](https://github.com/the-HaMo/PDS-proyect/blob/main/info/Desarrollo-Caso10.md))\
***Actor:*** Colaborador

***Caso de uso:*** Compartir cursos ([Desarrollo-Caso11](https://github.com/the-HaMo/PDS-proyect/blob/main/info/Desarrollo-Caso11.md))\
***Actor:*** Colaborador

***Caso de uso:*** Guarda estado del curso para un usuario ([Desarrollo-Caso12](https://github.com/the-HaMo/PDS-proyect/blob/main/info/Desarrollo-Caso12.md))\
***Actor:*** Sistema

### Funcionalidad extra (expresada en casos de uso) 

***Caso de uso:*** Examinarse de un curso ([Desarrollo-Caso13](https://github.com/the-HaMo/PDS-proyect/blob/main/info/Desarrollo-Caso13.md))\
***Actor:*** Estudiante 

***Caso de uso:*** Ver el número de me gustas que ha recibido un curso ([Desarrollo-Caso14](https://github.com/the-HaMo/PDS-proyect/blob/main/info/Desarrollo-Caso14.md))\
***Actor:*** Estudiante

***Caso de uso:*** Mostrar una lista ordenada de los cursos según el número de likes ([Desarrollo-Caso15](https://github.com/the-HaMo/PDS-proyect/blob/main/info/Desarrollo-Caso15.md))\
***Actor:*** Estudiante

***Caso de uso:*** Ver el número de descargas que ha recibido un curso ([Desarrollo-Caso16](https://github.com/the-HaMo/PDS-proyect/blob/main/info/Desarrollo-Caso16.md))\
***Actor:*** Colaborador

***Caso de uso:*** Mostrar una lista ordenada de los cursos según el número de descargas ([Desarrollo-Caso17](https://github.com/the-HaMo/PDS-proyect/blob/main/info/Desarrollo-Caso17.md))\
***Actor:*** Colaborador

### Diseño

A continuación, mostraremos la versión final en el siguiente apartado del modelo de dominio de AppLearning donde se destaca la existencia de dos tipos de entidades que heredan de Usuario que son **Creador** y **Estudiante**. La primera entidad es la encargada de crear cursos, mientras que la segunda tiene el rol de realizar esos cursos. 

Otras cuestiones de interés son que la clase **Ejercicio** es una clase abstracta lo que permite añadir nuevos tipos de ejercicio en un futuro . Además se muestra la relación entre los estudiantes y su progreso mediante la clase **Progreso**, permitiendo así un seguimiento detallado del aprendizaje.

Como funcionalidad adicional, hemos añadido la clase **EstadísticaCurso**, que almacenará información relacionada de cada curso disponible. Esto permitirá agregar nuevas funcionalidades, como ordenar los cursos por popularidad (cursos en TENDENCIA) o ver el número de "me gusta" que ha recibido cada uno.

### Modelo

![Mdominio.VF](https://github.com/the-HaMo/PDS-proyect/blob/main/info/diagramaVF.jpg)

### MANUAL DE USO 

La aplicación ... 

### Ejemplos de Cursos


