# Documentación de la clase Rs2Dialogue

## [Volver](development.md)

## Descripción general
La clase `Rs2Dialogue` proporciona métodos para manejar interacciones de diálogo dentro del juego, permitiendo verificar si hay un diálogo presente y automatizar respuestas.

## Métodos

### `clickContinue`
- **Firma**: `public static void clickContinue()`
- **Descripción**: Simula presionar la barra espaciadora para continuar los diálogos cuando aparece el mensaje "Click here to continue" (Haz clic aquí para continuar).

### `hasSelectAnOption`
- **Firma**: `public static boolean hasSelectAnOption()`
- **Descripción**: Comprueba si el diálogo "Select an Option" (Seleccionar una opción) está actualmente en pantalla, indicando que el jugador debe tomar una decisión.

### `isInDialogue`
- **Firma**: `public static boolean isInDialogue()`
- **Descripción**: Determina si hay algún diálogo activo verificando los mensajes comunes de diálogo como "Click here to continue" o "please wait..." (por favor espera...).

## Detalles adicionales
Estos métodos son útiles para automatizar interacciones rutinarias con los diálogos del juego, facilitando una experiencia de juego más fluida y un manejo eficiente de las interacciones en los scripts.