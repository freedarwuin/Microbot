# Rs2Prayer Class Documentation

## [Back](development.md)

## Overview
La clase `Rs2Prayer` proporciona funcionalidades para gestionar e interactuar con las oraciones (prayers) en el juego. Incluye métodos para activar o desactivar oraciones, comprobar si están activas y determinar si el jugador se ha quedado sin puntos de oración.

## Methods

### `toggle(Rs2PrayerEnum name)`
- **Parámetros**:
    - `name`: `Rs2PrayerEnum` - Entrada del enum que representa la oración a activar o desactivar.
- **Descripción**: Activa o desactiva la oración especificada sin verificar su estado actual.

### `toggle(Rs2PrayerEnum name, boolean on)`
- **Parámetros**:
    - `name`: `Rs2PrayerEnum` - Entrada del enum que representa la oración a activar o desactivar.
    - `on`: `boolean` - Estado deseado de la oración; `true` para activar, `false` para desactivar.
- **Descripción**: Activa o desactiva la oración según el estado deseado. Verifica el estado actual para evitar cambios innecesarios.

### `isPrayerActive(Rs2PrayerEnum name)`
- **Parámetros**:
    - `name`: `Rs2PrayerEnum` - Oración a verificar.
- **Retorna**: `boolean` - `true` si la oración especificada está activa.
- **Descripción**: Comprueba si la oración especificada está activa.

### `isQuickPrayerEnabled`
- **Retorna**: `boolean` - `true` si las oraciones rápidas (Quick Prayers) están habilitadas.
- **Descripción**: Comprueba si las oraciones rápidas están activas.

### `isOutOfPrayer`
- **Retorna**: `boolean` - `true` si el jugador no tiene puntos de oración restantes.
- **Descripción**: Verifica si el jugador se ha quedado sin puntos de oración.

## Usage Examples

### Activar y Desactivar una Oración
```java
Rs2Prayer.toggle(Rs2PrayerEnum.PIETY, true);  // Activa Piety
Rs2Prayer.toggle(Rs2PrayerEnum.PIETY, false); // Desactiva Piety