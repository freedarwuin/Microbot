# Documentación de la clase Rs2Keyboard
## [Volver](development.md)
## Descripción general
La clase `Rs2Keyboard` simula acciones del teclado, como escribir cadenas, presionar, mantener presionadas y soltar teclas. Interactúa directamente con el lienzo del juego para enviar eventos del teclado.

## Métodos

### `getCanvas`
- **Descripción**: Recupera el lienzo del juego del cliente Microbot.
- **Devuelve**: `Canvas` - El lienzo del juego utilizado para enviar eventos del teclado.

### `typeString`
- **Descripción**: Escribe una cadena enviando una secuencia de eventos `KEY_TYPED` para cada carácter.
- **Parámetros**:
- `word`: `String` - La cadena que se va a escribir.
- **Comportamiento**: Simula la escritura con demoras entre las pulsaciones de teclas.

### `keyPress`
- **Descripción**: Presiona una tecla de un solo carácter.
- **Parámetros**:
- `key`: `char` - La tecla del carácter que se debe presionar.
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
- `key`: `int` - El código de tecla (de `KeyEvent`) que se debe mantener presionado.
- **Comportamiento**: Envía un evento `KEY_PRESSED` para la tecla específica.

### `keyRelease`
- **Descripción**: Suelta una tecla especificada.
- **Parámetros**:
- `key`: `int` - El código de tecla (de `KeyEvent`) que se va a soltar.
- **Comportamiento**: Envía un evento `KEY_RELEASED` para la tecla especificada.

### `keyPress`
- **Descripción**: Simula una pulsación completa de una tecla (pulsación y liberación).
- **Parámetros**:
- `key`: `int` - El código de tecla que se va a pulsar.
- **Comportamiento**: Llama a `keyHold` y `keyRelease` sucesivamente.

### `enter`
- **Descripción**: Simula pulsar la tecla Enter.
- **Comportamiento**: Llama a `keyHold` y `keyRelease` para `KeyEvent.VK_ENTER`.

### `isKeyPressed`
- **Descripción**: Comprueba si una tecla específica está presionada actualmente.
- **Parámetros**:
- `keyCode`: `int` - El código de la tecla a verificar.
- **Devoluciones**: `boolean` - Verdadero si la tecla está presionada, falso en caso contrario.

## Miembros estáticos

### `pressedKeys`
- **Tipo**: `Map<Integer, Boolean>`
- **Descripción**: Realiza un seguimiento del estado presionado de las teclas.

## Ejemplo de uso
```java
Rs2Keyboard.typeString("¡Hola, mundo!");
Rs2Keyboard.keyPress('a');
Rs2Keyboard.holdShift();
Rs2Keyboard.releaseShift();
Rs2Keyboard.enter();
booleano isShiftPressed = Rs2Keyboard.isKeyPressed(KeyEvent.VK_SHIFT);