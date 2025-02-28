# Documentación de la clase Rs2Player
## [Volver](development.md)
## Descripción general
La clase `Rs2Player` es responsable de administrar el estado del jugador y las interacciones dentro del juego. Incluye métodos para administrar el estado del jugador, como puntos de vida, energía y varios efectos de pociones, así como acciones del jugador, como cerrar sesión, comer y alternar la energía de carrera.

## Métodos

### `hasAntiFireActive`
- **Devuelve**: `boolean` - Verdadero si hay algún efecto de antifire potion activo.

### `hasSuperAntiFireActive`
- **Devuelve**: `boolean` - Verdadero si hay un efecto de super antifire potion activo.

### `hasDivineRangedActive`
- **Devuelve**: `boolean` - Verdadero si hay un efecto de divine ranging potion activo.

### `hasRangingPotionActive`
- **Devuelve**: `boolean` - Verdadero si hay un efecto de ranging potion activo que potencia la habilidad de alcance más allá de los niveles básicos.

### `hasAntiVenomActive`
- **Devuelve**: `boolean` - Verdadero si hay algún efecto antipoison activo, incluidos los efectos de elementos como el serpentine helm.

### `hasAntiPoisonActive`
- **Devuelve**: `boolean` - Verdadero si hay algún efecto antipoison  activo.

### `handlePotionTimers`
- **Parámetros**:
- `event`: `VarbitChanged` - El evento que se activa cuando cambia un varbit, se usa para actualizar los temporizadores de poción.
- **Descripción**: Actualiza los temporizadores de poción según los cambios de varbit.

### `waitForWalking`
- **Descripción**: Espera hasta que el jugador comience a caminar y luego deje de caminar.

### `isAnimating`
- **Devuelve**: `boolean` - Verdadero si el jugador está reproduciendo una animación.

### `isWalking`
- **Devuelve**: `boolean` - Verdadero si el jugador está caminando.

### `isMember`
- **Devuelve**: `boolean` - Verdadero si el jugador tiene una membresía activa.

### `logout`
- **Descripción**: Inicia una acción de cierre de sesión para el jugador.

### `eatAt`
- **Parámetros**:
- `percentage`: `int` - El umbral de porcentaje de salud por debajo del cual el jugador debe comer.
- **Devuelve**: `boolean` - Verdadero si el jugador comió con éxito para mantener la salud por encima del porcentaje especificado.

### `getPlayers`
- **Devuelve**: `List<Player>` - Una lista de jugadores en el entorno de juego actual, excluyendo al jugador mismo.

### `getWorldLocation`
- **Devuelve**: `WorldPoint` - La ubicación actual del jugador en el mundo, teniendo en cuenta las regiones de instancia.

### `isFullHealth`
- **Devuelve**: `boolean` - Verdadero si el jugador tiene la salud completa.

### `isInMulti`
- **Devuelve**: `boolean` - Verdadero si el jugador está en un área de combate múltiple.

### `logoutIfPlayerDetected`
- **Parámetros**:
- `amountOfPlayers`: `int` - Cantidad de jugadores a detectar antes de cerrar sesión.
- **Devuelve**: `boolean` - Verdadero si se detecta un jugador.

### `logoutIfPlayerDetected`
- **Parámetros**:
- `amountOfPlayers`: `int` - Cantidad de jugadores a detectar antes de cerrar sesión.
- `time`: `int` - El tiempo en milisegundos que debe transcurrir para que se detecte a un jugador antes de cerrar la sesión.
- **Devuelve**: `boolean` - Verdadero si se detecta a un jugador.

### `logoutIfPlayerDetected`
- **Parámetros**:
- `amountOfPlayers`: `int` - Cantidad de jugadores que se deben detectar antes de cerrar la sesión.
- `time`: `int` - El tiempo en milisegundos que debe transcurrir para que se detecte a un jugador antes de cerrar la sesión.
- `distance`: `int` - La distancia que debe haber entre un jugador y otro antes de cerrar la sesión.
- **Devuelve**: `boolean` - Verdadero si se detecta a un jugador.

## Observaciones
- **Interacción con el juego**: esta clase proporciona funciones fundamentales para interactuar con varias mecánicas del juego, desde los efectos de las pociones hasta el movimiento del jugador.
- **Métodos de utilidad**: incluye utilidades para esperar las acciones del jugador (como caminar o realizar una animación), que son esenciales para coordinar tareas en los scripts de automatización.

## Conclusión
La clase `Rs2Player` es esencial para gestionar la interacción del jugador dentro del juego, ya que proporciona métodos para gestionar la salud, los efectos de estado y la conciencia del entorno. Sirve como punto central para programar el comportamiento del jugador en respuesta a los cambios de estado del juego y las condiciones del jugador.