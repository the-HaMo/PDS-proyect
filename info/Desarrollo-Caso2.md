## Caso de  Uso
 Nombre: Iniciar Sesion en la aplicacion
 Actor: Usuario

@startuml
left to right direction
:Jugador: --> (Iniciar Sesion)
@enduml

![image](https://github.com/user-attachments/assets/ca52fec9-46b8-4e2e-bbb0-4c7f7ef6278d)


### Precondiciones:

El usuario debe estar registrado en el sistema.
El usuario debe tener una sesión activa con la misma cuenta.

### Flujo Básico:
1- El usuario introduce sus credenciales (nombre de usuario y contraseña) en los campos correspondientes.
2- El usuario hace clic en el botón "Iniciar Sesión".

### Postcondiciones
- Si las credenciales son correctas, el usuario accede a la página principal de la aplicación
- El usuario puede iniciar o continuar un curso de su biblioteca.

### Reglas de Negocio

Un usuario no puede iniciar sesión si ya tiene una sesión activa con la misma cuenta.
