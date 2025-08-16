# Rs2Keyboard Class Documentation

## [Back](development.md)

## Overview
La clase `Rs2Keyboard` simula acciones del teclado, como escribir cadenas, presionar, mantener y soltar teclas. Interactúa directamente con el canvas del juego para enviar eventos de teclado.

## Methods

### `getCanvas`
- **Descripción**: Recupera el canvas del juego desde el cliente Microbot.
- **Retorna**: `Canvas` - Canvas del juego usado para enviar eventos de teclado.

### `typeString`
- **Descripción**: Escribe una cadena enviando una secuencia de eventos `KEY_TYPED` para cada carácter.
- **Parámetros**:
    - `word`: `String` - La cadena a escribir.
- **Comportamiento**: Simula la escritura con retrasos entre cada pulsación de tecla.

### `keyPress`
- **Descripción**: Presiona una sola tecla de carácter.
- **Parámetros**:
    - `key`: `char` - La tecla a presionar.
- **Comportamiento**: Envía un evento `KEY_TYPED` para el carácter.

### `holdShift`
- **Descripción**: Mantiene presionada la tecla Shift.
- **Comportamiento**: Envía un evento `KEY_PRESSED` para la tecla Shift.

### `releaseShift`
- **Descripción**: Suelta la tecla Shift.
- **Comportamiento**: Envía un evento `KEY_RELEASED` para la tecla Shift.

### `keyHold`
- **Descripción**: Mantiene presionada una tecla específica.
- **Parámetros**:
    - `key`: `int` - Código de la tecla (de `KeyEvent`) a mantener.
- **Comportamiento**: Envía un evento `KEY_PRESSED` para la tecla especificada.

### `keyRelease`
- **Descripción**: Suelta una tecla específica.
- **Parámetros**:
    - `key`: `int` - Código de la tecla (de `KeyEvent`) a soltar.
- **Comportamiento**: Envía un evento `KEY_RELEASED` para la tecla especificada.

### `keyPress`
- **Descripción**: Simula una pulsación completa de tecla (presionar y soltar).
- **Parámetros**:
    - `key`: `int` - Código de la tecla a presionar.
- **Comportamiento**: Llama sucesivamente a `keyHold` y `keyRelease`.

### `enter`
- **Descripción**: Simula presionar la tecla Enter.
- **Comportamiento**: Llama a `keyHold` y `keyRelease` para `KeyEvent.VK_ENTER`.

### `isKeyPressed`
- **Descripción**: Verifica si una tecla específica está presionada.
- **Parámetros**:
    - `keyCode`: `int` - Código de la tecla a verificar.
- **Retorna**: `boolean` - True si la tecla está presionada, false de lo contrario.

## Static Members

### `pressedKeys`
- **Tipo**: `Map<Integer, Boolean>`
- **Descripción**: Mantiene el estado presionado de las teclas.

## Usage Example
```java
Rs2Keyboard.typeString("Hello, world!");
Rs2Keyboard.keyPress('a');
Rs2Keyboard.holdShift();
Rs2Keyboard.releaseShift();
Rs2Keyboard.enter();
boolean isShiftPressed = Rs2Keyboard.isKeyPressed(KeyEvent.VK_SHIFT);