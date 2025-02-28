# Rs2Combat Documentación de la clase

## [Volver](development.md)

## Overview
La clase `Rs2Combat` proporciona métodos para controlar la configuración de combate en el juego, como establecer estilos de ataque, administrar represalias automáticas y manejar ataques especiales.
## Methods

### `enableAutoRetialiate`
- **Signature**: `public static boolean enableAutoRetialiate()`
- **Description**: Se garantiza que la retaliate automática esté habilitada verificando el estado actual e interactuando con la pestaña de opciones de combate si es necesario.

### `getSpecState`
- **Signature**: `public static boolean getSpecState()`
- **Description**: Comprueba el estado del widget de ataque especial para determinar si el ataque especial está habilitado.

### `inCombat`
- **Signature**: `public static boolean inCombat()`
- **Description**: Determina si el personaje del jugador está actualmente involucrado en combate verificando los estados de interacción y animación.

### `isSelected`
- **Signature**: `private static boolean isSelected(int widgetId)`
- **Description**: Verifica si un widget está seleccionado verificando su color de fondo para el indicador de selección.

### `setAttackStyle`
- **Signature**: `public static boolean setAttackStyle(WidgetInfo style)`
- **Description**: Establece el estilo de ataque del jugador haciendo clic en el widget correspondiente. Devuelve verdadero si la operación se realizó correctamente o ya se configuró.

### `setAutoRetaliate`
- **Signature**: `public static boolean setAutoRetaliate(boolean state)`
- **Description**: Habilita o deshabilita el retaliate automática según el estado proporcionado al interactuar con el widget correspondiente.

### `setSpecState`
- **Signature**: `public static boolean setSpecState(boolean state, int specialAttackEnergyRequired)`
- **Description**: Establece el estado de ataque especial, habilitándolo o deshabilitándolo según el nivel de energía requerido en comparación con el nivel de energía actual.

### `setSpecState (overloaded)`
- **Signature**: `public static boolean setSpecState(boolean state)`
- **Description**: Método sobrecargado que establece el estado de ataque especial sin requerir un nivel de energía específico.

## Detalles adicionales
Estos métodos facilitan la gestión de configuraciones y estados relacionados con el combate dentro del juego, mejorando la capacidad del jugador para adaptarse y responder a varios escenarios de combate de manera programada.