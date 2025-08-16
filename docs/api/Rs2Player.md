# Rs2Player Class Documentation

## [Back](development.md)

## Overview
La clase `Rs2Player` se encarga de gestionar el estado del jugador y sus interacciones dentro del juego. Incluye métodos para manejar el estado del jugador, como puntos de vida, energía y efectos de pociones, así como acciones del jugador como cerrar sesión, comer y activar/desactivar correr.

## Methods

### `hasAntiFireActive`
- **Retorna**: `boolean` - `true` si cualquier efecto de poción antifuego está activo.

### `hasSuperAntiFireActive`
- **Retorna**: `boolean` - `true` si un efecto de super poción antifuego está activo.

### `hasDivineRangedActive`
- **Retorna**: `boolean` - `true` si un efecto de poción de rango divino está activo.

### `hasRangingPotionActive`
- **Retorna**: `boolean` - `true` si una poción de rango está activa que aumenta la habilidad de rango por encima del nivel base.

### `hasAntiVenomActive`
- **Retorna**: `boolean` - `true` si cualquier efecto de antiveneno está activo, incluyendo efectos de items como el casco serpentino.

### `hasAntiPoisonActive`
- **Retorna**: `boolean` - `true` si cualquier efecto de antipoison está activo.

### `handlePotionTimers`
- **Parámetros**:
    - `event`: `VarbitChanged` - Evento disparado cuando un varbit cambia, usado para actualizar los temporizadores de pociones.
- **Descripción**: Actualiza los temporizadores de pociones según los cambios de varbits.

### `waitForWalking`
- **Descripción**: Espera hasta que el jugador comience y luego deje de caminar.

### `isAnimating`
- **Retorna**: `boolean` - `true` si el jugador actualmente está en una animación.

### `isWalking`
- **Retorna**: `boolean` - `true` si el jugador está caminando.

### `isMember`
- **Retorna**: `boolean` - `true` si el jugador tiene membresía activa.

### `logout`
- **Descripción**: Inicia la acción de cerrar sesión para el jugador.

### `eatAt`
- **Parámetros**:
    - `percentage`: `int` - Umbral de porcentaje de salud por debajo del cual el jugador debe comer.
- **Retorna**: `boolean` - `true` si el jugador comió exitosamente para mantener la salud por encima del porcentaje especificado.

### `getPlayers`
- **Retorna**: `List<Player>` - Lista de jugadores en el entorno actual, excluyendo al propio jugador.

### `getWorldLocation`
- **Retorna**: `WorldPoint` - La ubicación mundial actual del jugador, considerando regiones de instancia.

### `isFullHealth`
- **Retorna**: `boolean` - `true` si el jugador está a plena salud.

### `isInMulti`
- **Retorna**: `boolean` - `true` si el jugador se encuentra en un área de combate múltiple.

### `logoutIfPlayerDetected`
- **Parámetros**:
    - `amountOfPlayers`: `int` - Cantidad de jugadores a detectar antes de cerrar sesión.
- **Retorna**: `boolean` - `true` si se detecta un jugador.

### `logoutIfPlayerDetected`
- **Parámetros**:
    - `amountOfPlayers`: `int` - Cantidad de jugadores a detectar antes de cerrar sesión.
    - `time`: `int` - Tiempo en milisegundos para detectar un jugador antes de cerrar sesión.
- **Retorna**: `boolean` - `true` si se detecta un jugador.

### `logoutIfPlayerDetected`
- **Parámetros**:
    - `amountOfPlayers`: `int` - Cantidad de jugadores a detectar antes de cerrar sesión.
    - `time`: `int` - Tiempo en milisegundos para detectar un jugador antes de cerrar sesión.
    - `distance`: `int` - Distancia entre el jugador detectado y el jugador principal antes de cerrar sesión.
- **Retorna**: `boolean` - `true` si se detecta un jugador.

## Remarks
- **Interacción con el juego**: Esta clase proporciona funcionalidades críticas para interactuar con distintas mecánicas del juego, desde efectos de pociones hasta movimiento del jugador.
- **Métodos de utilidad**: Incluye utilidades para esperar acciones del jugador (como caminar o animación), esenciales para coordinar tareas en scripts de automatización.

## Conclusion
La clase `Rs2Player` es esencial para gestionar la interacción del jugador dentro del juego, ofreciendo métodos para manejar salud, efectos de estado y conciencia ambiental. Sirve como punto central para programar el comportamiento del jugador en respuesta a cambios del estado del juego y condiciones del jugador.
