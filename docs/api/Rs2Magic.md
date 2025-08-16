# Rs2Magic Class Documentation

## [Back](development.md)

## Overview
La clase `Rs2Magic` facilita operaciones relacionadas con magia en el cliente Microbot, permitiendo lanzar hechizos sobre varias entidades y realizar alquimia. Maneja interacciones con la interfaz de magia del juego y proporciona funciones útiles para distintas acciones mágicas.

## Methods

### `canCast`
- **Descripción**: Verifica si el jugador tiene el nivel de Magia requerido para lanzar un hechizo específico.
- **Parámetros**:
    - `magicSpell`: `MagicAction` - El hechizo mágico a verificar.
- **Retorna**: `boolean` - True si el jugador puede lanzar el hechizo, de lo contrario false.

### `cast`
- **Descripción**: Lanza un hechizo específico.
- **Parámetros**:
    - `magicSpell`: `MagicAction` - El hechizo mágico a lanzar.
- **Comportamiento**: Cambia a la pestaña de magia, ajusta el estado y ejecuta la acción del hechizo.

### `castOn`
- **Descripción**: Lanza un hechizo sobre un actor.
- **Parámetros**:
    - `magicSpell`: `MagicAction` - El hechizo mágico a lanzar.
    - `actor`: `Actor` - El actor objetivo.
- **Comportamiento**: Se asegura de que el objetivo esté visible antes de lanzar el hechizo.

### `alch`
- **Descripción**: Realiza alquimia sobre un ítem, eligiendo High o Low Alchemy según el nivel de Magia.
- **Sobrecargas**:
    - **Por nombre de ítem**: Lanza alquimia sobre el primer ítem con el nombre especificado en el inventario.
    - **Por objeto de ítem**: Lanza alquimia directamente sobre un objeto `Rs2Item` proporcionado.
- **Parámetros**:
    - `itemName`: `String` - Nombre del ítem a alquimizar.
    - `item`: `Rs2Item` - Objeto de ítem a alquimizar.

### `highAlch` y `lowAlch`
- **Descripción**: Realiza High Level Alchemy o Low Level Alchemy sobre un ítem.
- **Parámetros**:
    - `item`: `Rs2Item` - El ítem a alquimizar.
- **Comportamiento**: Cambia a la pestaña de magia, verifica que el hechizo esté disponible y realiza la alquimia.

## Static Members

### `sleepUntil`
- **Descripción**: Método de utilidad que pausa la ejecución hasta que se cumpla una condición, usado para sincronizar cambios de estado como cambios de pestaña.

### `alch`
- **Descripción**: Método auxiliar para realizar el proceso real de alquimia sobre un ítem.
- **Parámetros**:
    - `alch`: `Widget` - Widget del hechizo de alquimia.
    - `item`: `Rs2Item` - Ítem a alquimizar (opcional).

## Usage Examples
```java
Rs2Magic.cast(MagicAction.FIRE_STRIKE);
Rs2Magic.alch("Rune Platelegs");
Rs2Magic.castOn(MagicAction.TELEKINETIC_GRAB, someActor);
Rs2Magic.highAlch(someRs2Item);