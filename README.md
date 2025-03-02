# LearningApp

### DESCRIPCIÓN 

**LearningApp** es una aplicación de aprendizaje de idiomas totalmente gratuita desarrollada por los alumnos; **Mohammed Amrou Labied Nasser**, **Sergio García García** y **Jorge Fernandéz Corcoles**, bajo la supervisión del profesor **José Antonio García Díaz**.

El propósito de LearningApp es facilitar el aprendizaje de idiomas como el inglés, francés o el italiano a través de cursos diseñados tanto por la propia aplicación como por otros usuarios con experiencia en la enseñanza.Esto permite a los usuarios acceder a una gran variedad de materiales adaptados a diferentes niveles y estilos de aprendizaje. 

Además, LearningApp brinda la oportunidad de que cada usuario pueda crear y compartir su propio curso, ofreciendo una comunidad de aprendiazje colaboratica donde los estudiantes pueden beneficiarse de contenidos personalizados y adaptados a sus necesidades. 

### Requisitos

En este apartado vamos a identificar los requisitos necesarios para el correcto funcionamiento de LearningApp, tanto a nivel de usuario como a nivel del sistema. Esto permitirá establecer objetivos claros para su implementación, resolviendo posibles ambigüedades y evitando la duplicidad de análisis. Para ello, utilizaremos los casos de uso.

### Casos de Uso

***Caso de uso:*** Registrarse en la aplicacion ([Desarrollo-Caso1](https://github.com/the-HaMo/PDS-proyect/blob/main/info/Desarrollo-Caso1.md))\
***Actor:*** Usuario

***Caso de uso:*** Iniciar Sesion en la aplicacion ([Desarrollo-Caso2](https://github.com/the-HaMo/PDS-proyect/blob/main/info/Desarrollo-Caso2.md))\
***Actor:*** Usuario

***Caso de uso:*** Cerrar Sesion\
***Actor:*** Usuario

***Caso de uso:*** Elegir curso disponible\
***Actor:*** Usuario

***Caso de uso:*** Mostrar Ejercicios dentro de un curso\
***Actor:*** Sistema

***Caso de uso:*** Crear Curso\
***Actor:*** Usuario

***Caso de uso:*** Compartir cursos creados propios\
***Actor:*** Usuario

***Caso de uso:*** Dentro de un curso, elige estrategia de aprendizaje\
***Actor:*** Usuario

***Caso de uso:*** Guarda estado del curso para un usuario\
***Actor:*** Sistema

***Caso de uso:*** Instalar nuevos cursos en su biblioteca interna\
***Actor:*** Usuario

### Diseño

A continuación, mostraremos una primera versión en el siguiente apartado del modelo de dominio de AppLearning donde se destaca la existencia de dos tipos de entidades que heredan de Usuario que son **Creador** y **Estudiante**. La primera entidad es la encargada de crear cursos, mientras que la segunda tiene el rol de realizar esos cursos. 

Otras cuestiones de interés son que la clase **Ejercicio** es una clase abstracta lo que permite añadir nuevos tipos de ejercicio en un futuro . Además se muestra la relación entre los estudiantes y su progreso mediante la clase **Progreso**, permitiendo así un seguimiento detallado del aprendizaje.

Como característica adicional, hemos añadido la clase **EstadísticaCurso**, que almacenará información relacionada de cada curso disponible. Esto permitirá agregar nuevas funcionalidades, como ordenar los cursos por popularidad (cursos en TENDENCIA) o ver el número de "me gusta" que ha recibido cada uno.

### Modelo

![Mdominio.V1-0](https://github.com/the-HaMo/PDS-proyect/blob/main/info/diagrama.jpg)

### Diseño de Vistas

Una primera versión de las vistas en la aplicación es la siguiente:

![Login.V1-0](https://github.com/the-HaMo/PDS-proyect/blob/main/info/login.jpg)

![MenuPrincipal.V1-0](https://github.com/the-HaMo/PDS-proyect/blob/main/info/menu_principal.jpg)




