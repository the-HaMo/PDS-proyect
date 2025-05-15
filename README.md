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

Este manual describe cómo utilizar la aplicación, incluyendo el registro de usuarios, el inicio de sesión y las funcionalidades disponibles según el **rol**: **Colaborador** o **Estudiante**.

## Registro de usuario
Desde la pantalla principal puedes registrarte como **Estudiante** o **Colaborador**.

1. Haz clic en el botón **"Registrarse"**.
2. Introduce tu nombre, contraseña y selecciona tu rol.
3. Pulsa **"Aceptar"** para completar el registro.

![VentanaRegistro](https://github.com/the-HaMo/PDS-proyect/blob/main/info/Ventana-Registro.png)


## Inicio de Sesión

Una vez registrado, podrás acceder con tus credenciales:

1. Introduce tu **nombre** y **contraseña** que has utilizado al registrarte como estudiante o colaborador.
2. Pulsa **"Iniciar sesión"**.
3. Serás redirigido al panel correspondiente a tu rol.

![Ventana-Login](https://github.com/the-HaMo/PDS-proyect/blob/main/info/Ventana-Login.png)


## Rol Colaborador

El colaborador puede crear y gestionar cursos educativos.

### Funcionalidades:
- **Importar curso (.json)** con bloques y preguntas.
- **Compartir un curso** a la biblioteca pública para que los estudiantes puedan acceder a hacerlos.
- **Ordenar cursos** por número de descargas.
- **Log out** si quieres iniciar sesión con otra cuenta.

![VentanaColaborador](https://github.com/the-HaMo/PDS-proyect/blob/main/info/Ventana-Colaborador.png)

Para importar el curso pulsa el botón de **"Importar Curso"** y se desplegará tu biblioteca de arvhivos del sistema. 

![Biblioteca](https://github.com/the-HaMo/PDS-proyect/blob/main/info/Biblioteca.png)

Ahora podrás seleccionar el curso **con extensión .json** y al aceptar debería aparecer en la biblioteca privada llamada **"Cursos subidos"**.

Una vez tengas el curso en la biblioteca privada, podrás hacerlo público para que otros usuarios puedan verlo y hacerlo. Para ello, pulsa en **"Compartir Curso"**.

![CursoSinPublicar](https://github.com/the-HaMo/PDS-proyect/blob/main/info/CursoSinPublicar.png)

Otros usuarios pueden dar **"like"** a tu curso. Otra funcionalidad del sistema es ordenar los cursos por número de descargas.
De esta manera, si apretas el botón **"TOP descargas"** se ordenarán los cursos para poder ver qué curso va líder en el ranking. 


## Rol Estudiante

El estudiante puede visualizar y resolver cursos disponibles.
### Funcionalidades:
- **Ver cursos públicos** creados por colaboradores.
- **Dar like** a los cursos.
- **Exportar cursos** para guardarlos en su biblioteca privada.
- **Resolver bloques** de contenido uno a uno.
- **Elegir estrategia** de aprendizaje al comenzar un curso.
- **Examinarse** del curso descargado y ejecutado
- **Ver estadisticas del Usuario** y rachas de respuestas correctas.

### Ver Cursos Publicos:
Como cualquier estudiante, lo mas importante es poder ver los cursos subidos por los colaboradores de la aplicacion. Para ello primero hay que asumir que hay cursos publicados en la aplicacion. Para saber cuales son los cursos subidos, en el menu habran dos listas, una para los cursos publicos y otro para los cursos que el usuario se ha descargado, en este caso el que nos interesa es la lista de la izquierda marcado en la imagen inferior:

![VerCursos](https://github.com/the-HaMo/PDS-proyect/blob/main/info/vercursos.png)

### Dar like
Una funcionalidad que tiene la aplicacion LearningApp es poder darle like a un curso a nuestro gusto. Obviamente, no es posible darle mas de una vez un like a un curso. Cuando se le de me gusta a un curso, aumentara el contador de me gustas que tiene cada Elemento del curso en la lista tanto privada como publica. Para dar un me gusta, hay que seleccionar el curso y darle al boton de "Dar Like" indicado a continuacion:

![DarLike](https://github.com/the-HaMo/PDS-proyect/blob/main/info/darlike.png)

### Exportar cursos
Para poder ejecutar un curso primero hay que importarlo a la biblioteca privada de cada alumno. Para ello lo que hay que hacer es seleccionar el curso de la lista publica y hacer clic sobre el boton de Descargar. Con ello, el curso se agregara en nuestra lista de cursos privados como se puede apreciar a continuacion:

![ExportarCursos](https://github.com/the-HaMo/PDS-proyect/blob/main/info/exportarcursos.png)

### Elegir Estrategia
A la hora de examinarse de un curso, primero habra que selecionar el tipo de Estrategia que queremos aplicar al examinarnos del curso. Hay que tener en cuenta que la estrategia no se puede cambiar, una vez se le asigne una estrategia a un curso que esta guardado no se podra modificar. Para aplicar una estrategia hay que primero ejecutar el curso, para eso hay que hacer doble clic donde se nos abrira una ventana con tres posibles opciones; Secuencial, Aleatoria y Repeticion Espaciada. La estrategia Secuencial es simplemente que las preguntas del examen sigan el orden normal del curso. Estrategia aleatoria es la que genera el examen con preguntas ordenadas de forma aleatoria de todos los bloques. Por ultimo, la repeticion espaciada es que se van repitiendo de manera aleatoria preguntas del test.

![ElegirEstrategia](https://github.com/the-HaMo/PDS-proyect/blob/main/info/elegirestrategia.png)

### Resolver bloques
Con la estategia escogida, nos metera dentro del curso. Aqui tiene la opcion de ser capaz de testearse a si mismo con los bloques de contenido. Aqui es capaz de ir ejecutandos bloque por bloque de manera independiente:

![Bloques](https://github.com/the-HaMo/PDS-proyect/blob/main/info/bloques.png)

Una vez realizado un bloque de contenido, nos marcara si esta completado el bloque de contenido como se puede ver a continuacion:

![ResolverBloques](https://github.com/the-HaMo/PDS-proyect/blob/main/info/resolverbloques.png)

### Examinarse
Cuando el alumno quiera, puede examinarse del curso, en esta funcionalidad si se aplicara la estrategia como ya mencionada anteriormente. Cuando se examine el alumno, simplemente le dara la opcion de responder, cerrar la ventana o ir a la siguiente pregunta pero no dejara ir a la pregunta anterior. Al final del examen dira cuantas respuestas se han tenido correctas.

![Examenes](https://github.com/the-HaMo/PDS-proyect/blob/main/info/examenes.png)

### Ver estadisticas del Usuario
Un usuario tiene la capacidad de ver sus estadisticas ya sean Tiempo conectado, mejor racha de preguntas contestadas de manera correcta y la racha actual de preguntas correctas:

![VerProgreso](https://github.com/the-HaMo/PDS-proyect/blob/main/info/verprogreso.png)


## Ejemplos de Cursos

Los cursos diseñados por los colaboradores deben tener la extensión *json*, algunos ejemplos se muestran a continuación para descargar:

Curso inglés:  [curso-Ing.json](https://github.com/the-HaMo/PDS-proyect/blob/main/cursos/curso-Ing.json)\
Curso francés: [curso-Fr.json](https://github.com/the-HaMo/PDS-proyect/blob/main/cursos/curso-Fr.json)\
Curso español: [curso-Esp.json](https://github.com/the-HaMo/PDS-proyect/blob/main/cursos/curso-Esp.json)

