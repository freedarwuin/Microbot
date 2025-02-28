# Documentación de la clase Rs2Dialogue
## [Volver](development.md)
## Descripción general
La clase `Rs2Dialogue` proporciona métodos para manejar interacciones de diálogo en el juego, lo que permite verificar si hay un diálogo presente y automatizar las respuestas.

## Métodos

### `clickContinue`
- **Firma**: `public static void clickContinue()`
- **Descripción**: Simula presionar la barra espaciadora para continuar a través de los diálogos cuando el mensaje "Click here to continue" está visible.

### `hasSelectAnOption`
- **Firma**: `public static boolean hasSelectAnOption()`
- **Descripción**: Verifica si el diálogo "Seleccionar una opción" está actualmente en la pantalla, lo que indica que el jugador debe realizar una elección.

### `isInDialogue`
- **Firma**: `public static boolean isInDialogue()`
- **Descripción**: determina si hay algún diálogo activo al verificar los mensajes de diálogo comunes, como "Click here to continue" o "please wait...".

## Detalles adicionales
Estos métodos son útiles para automatizar interacciones rutinarias con diálogos de juegos, lo que facilita una jugabilidad más fluida y el manejo de interacciones en scripts.