# Documentación de la clase Rs2Combat

## [Volver](development.md)

## Descripción general
La clase `Rs2Combat` proporciona métodos para controlar la configuración de combate en el juego, como establecer estilos de ataque, gestionar el auto-retaliate y manejar ataques especiales.

## Métodos

### `enableAutoRetialiate`
- **Firma**: `public static boolean enableAutoRetialiate()`
- **Descripción**: Asegura que el auto-retaliate esté habilitado verificando el estado actual e interactuando con la pestaña de opciones de combate si es necesario.

### `getSpecState`
- **Firma**: `public static boolean getSpecState()`
- **Descripción**: Comprueba el estado del widget de ataque especial para determinar si el ataque especial está habilitado.

### `inCombat`
- **Firma**: `public static boolean inCombat()`
- **Descripción**: Determina si el personaje del jugador está actualmente en combate verificando los estados de interacción y animación.

### `isSelected`
- **Firma**: `private static boolean isSelected(int widgetId)`
- **Descripción**: Verifica si un widget está seleccionado comprobando su color de fondo para el indicador de selección.

### `setAttackStyle`
- **Firma**: `public static boolean setAttackStyle(WidgetInfo style)`
- **Descripción**: Configura el estilo de ataque del jugador haciendo clic en el widget correspondiente. Devuelve true si tiene éxito o si ya está configurado.

### `setAutoRetaliate`
- **Firma**: `public static boolean setAutoRetaliate(boolean state)`
- **Descripción**: Habilita o deshabilita el auto-retaliate según el estado proporcionado, interactuando con el widget correspondiente.

### `setSpecState`
- **Firma**: `public static boolean setSpecState(boolean state, int specialAttackEnergyRequired)`
- **Descripción**: Configura el estado del ataque especial, habilitándolo o deshabilitándolo según el nivel de energía requerido comparado con el nivel de energía actual.

### `setSpecState (sobrecargado)`
- **Firma**: `public static boolean setSpecState(boolean state)`
- **Descripción**: Método sobrecargado que configura el estado del ataque especial sin requerir un nivel específico de energía.

## Detalles adicionales
Estos métodos facilitan la gestión de configuraciones y estados relacionados con el combate dentro del juego, mejorando la capacidad del jugador para adaptarse y responder a diversos escenarios de combate de forma programática.
