# Documentación de la clase Rs2Magic
## [Volver](development.md)
## Descripción general
La clase `Rs2Magic` facilita las operaciones relacionadas con la magia en el cliente Microbot, lo que permite lanzar hechizos sobre varias entidades y realizar alquimia. Maneja las interacciones con la interfaz mágica del juego y proporciona funciones de utilidad para diferentes acciones de hechizos.

## Métodos

### `canCast`
- **Descripción**: Comprueba si el jugador tiene el nivel de magia necesario para lanzar un hechizo específico.
- **Parámetros**:
- `magicSpell`: `MagicAction` - El hechizo mágico a comprobar.
- **Devuelve**: `boolean` - Verdadero si el jugador puede lanzar el hechizo, falso en caso contrario.

### `cast`
- **Descripción**: Lanza un hechizo específico.
- **Parámetros**:
- `magicSpell`: `MagicAction` - El hechizo mágico a lanzar.
- **Comportamiento**: cambia a la pestaña de magia, establece el estado e invoca la acción del hechizo.

### `castOn`
- **Descripción**: lanza un hechizo específico sobre un actor.
- **Parámetros**:
- `magicSpell`: `MagicAction` - El hechizo mágico que se lanzará.
- `actor`: `Actor` - El actor objetivo.
- **Comportamiento**: se asegura de que el objetivo esté a la vista antes de lanzar el hechizo.

### `alch`
- **Descripción**: realiza alquimia sobre un objeto, eligiendo alquimia alta o baja según el nivel de magia.
- **Sobrecargas**:
- **Nombre del objeto**: lanza alquimia sobre el primer objeto con el nombre especificado que se encuentre en el inventario.
- **Objeto del objeto**: lanza alquimia directamente sobre el objeto `Rs2Item` proporcionado.
- **Parámetros**:
- `itemName`: `String` - El nombre del objeto a alquimizar.
- `item`: `Rs2Item` - El objeto objeto a alquimizar.

### `highAlch` y `lowAlch`
- **Descripción**: Realiza alquimia de alto nivel o alquimia de bajo nivel en un objeto.
- **Parámetros**:
- `item`: `Rs2Item` - El objeto a alquimizar.
- **Comportamiento**: Cambia a la pestaña de magia, se asegura de que el hechizo esté disponible y realiza la alquimia.

## Miembros estáticos

### `sleepUntil`
- **Descripción**: Método de utilidad que pausa la ejecución hasta que una condición se vuelve verdadera, utilizado para sincronizar cambios de estado como cambios de pestaña.

### `alch`
- **Descripción**: Método auxiliar para realizar el proceso de alquimia real en un objeto.
- **Parámetros**:
- `alch`: `Widget` - El widget del hechizo de alquimia.
- `item`: `Rs2Item` - El objeto a alquimizar (opcional).

## Ejemplos de uso

```java
Rs2Magic.cast(MagicAction.FIRE_STRIKE);
Rs2Magic.alch("Rune Platelegs");
Rs2Magic.castOn(MagicAction.TELEKINETIC_GRAB, someActor);
Rs2Magic.highAlch(someRs2Item);