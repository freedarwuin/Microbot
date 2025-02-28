# Documentación de la clase Rs2Prayer
## [Volver](development.md)
## Descripción general
La clase `Rs2Prayer` ofrece funcionalidades para administrar e interactuar con las habilidades de oración en el juego. Incluye métodos para activar o desactivar las oraciones, verificar si las oraciones están activas y determinar si el jugador se quedó sin puntos de oración.

## Métodos

### `toggle(Rs2PrayerEnum name)`
- **Parámetros**:
- `name`: `Rs2PrayerEnum` - La entrada de enumeración que representa la oración que se va a activar.
- **Descripción**: Activa o desactiva la oración especificada sin verificar su estado actual.

### `toggle(Rs2PrayerEnum name, boolean on)`
- **Parámetros**:
- `name`: `Rs2PrayerEnum` - La entrada de enumeración que representa la oración que se va a activar o desactivar.
- `on`: `boolean` - Estado deseado de la oración; `true` para activar, `false` para desactivar.
- **Descripción**: Cambia la oración especificada al estado deseado. Verifica el estado actual para evitar cambios innecesarios.

### `isPrayerActive(Rs2PrayerEnum name)`
- **Parámetros**:
- `name`: `Rs2PrayerEnum` - La oración a verificar.
- **Devuelve**: `boolean` - Verdadero si la oración especificada está actualmente activa.
- **Descripción**: Verifica si la oración especificada está activa.

### `isQuickPrayerEnabled`
- **Devuelve**: `boolean` - Verdadero si las oraciones rápidas están actualmente habilitadas.
- **Descripción**: Verifica si las oraciones rápidas están habilitadas.

### `isOutOfPrayer`
- **Devuelve**: `boolean` - Verdadero si al jugador no le quedan puntos de oración.
- **Descripción**: Comprueba si al jugador se le han acabado los puntos de oración.

## Ejemplos de uso

### Activar o desactivar una oración
```java
Rs2Prayer.toggle(Rs2PrayerEnum.PIETY, true); // Activa el PIETY
Rs2Prayer.toggle(Rs2PrayerEnum.PIETY, false); // Desactiva el PIETY