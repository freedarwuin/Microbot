# Documentación de la clase Rs2Camera

## [Volver](development.md)

## Descripción general
La clase `Rs2Camera` proporciona métodos para manipular la vista de la cámara en el juego, incluyendo ajustar ángulos, inclinación y determinar si una baldosa es visible en la pantalla.

## Métodos

### `angleToTile(Actor t)`
- **Firma**: `public static int angleToTile(Actor t)`
- **Descripción**: Calcula el ángulo desde la ubicación del jugador hasta la posición de un actor dado.

### `angleToTile(TileObject t)`
- **Firma**: `public static int angleToTile(TileObject t)`
- **Descripción**: Calcula el ángulo desde la ubicación del jugador hasta la posición de un objeto de baldosa dado.

### `angleToTile(LocalPoint localPoint)`
- **Firma**: `public static int angleToTile(LocalPoint localPoint)`
- **Descripción**: Calcula el ángulo desde la ubicación del jugador hasta un punto local específico.

### `getAngle()`
- **Firma**: `public static int getAngle()`
- **Descripción**: Obtiene el ángulo absoluto actual de la cámara.

### `getAngleTo(int degrees)`
- **Firma**: `public static int getAngleTo(int degrees)`
- **Descripción**: Calcula la diferencia de ángulo relativo a un valor de grado específico desde el ángulo actual de la cámara.

### `getCharacterAngle(Actor actor)`
- **Firma**: `public static int getCharacterAngle(Actor actor)`
- **Descripción**: Obtiene el ángulo de la cámara necesario para enfocar directamente a un actor.

### `getObjectAngle(TileObject tileObject)`
- **Firma**: `public static int getObjectAngle(TileObject tileObject)`
- **Descripción**: Obtiene el ángulo de la cámara necesario para enfocar directamente a un objeto de baldosa.

### `getTileAngle(Actor actor)`
- **Firma**: `public static int getTileAngle(Actor actor)`
- **Descripción**: Calcula el ángulo modificado necesario para enfocar la cámara directamente en un actor.

### `getTileAngle(TileObject tileObject)`
- **Firma**: `public static int getTileAngle(TileObject tileObject)`
- **Descripción**: Calcula el ángulo modificado necesario para enfocar la cámara directamente en un objeto de baldosa.

### `isTileOnScreen(TileObject tileObject)`
- **Firma**: `public static boolean isTileOnScreen(TileObject tileObject)`
- **Descripción**: Comprueba si un objeto de baldosa es actualmente visible dentro del área de visualización del juego.

### `isTileOnScreen(LocalPoint localPoint)`
- **Firma**: `public static boolean isTileOnScreen(LocalPoint localPoint)`
- **Descripción**: Comprueba si una ubicación representada por un LocalPoint es actualmente visible dentro del área de visualización del juego.

### `setAngle(int degrees)`
- **Firma**: `public static void setAngle(int degrees)`
- **Descripción**: Ajusta el ángulo de la cámara a un grado específico, modificando la vista en consecuencia.

### `setAngle(int degrees, Actor actor)`
- **Firma**: `public static void setAngle(int degrees, Actor actor)`
- **Descripción**: Ajusta la cámara para enfocar a un actor en un ángulo específico.

### `setAngle(int degrees, TileObject tileObject)`
- **Firma**: `public static void setAngle(int degrees, TileObject tileObject)`
- **Descripción**: Ajusta la cámara para enfocar a un objeto de baldosa en un ángulo específico.

### `setAngle(int degrees, LocalPoint localPoint)`
- **Firma**: `public static void setAngle(int degrees, LocalPoint localPoint)`
- **Descripción**: Ajusta la cámara para enfocar un punto local específico en un ángulo determinado.

### `setPitch(float percentage)`
- **Firma**: `public static void setPitch(float percentage)`
- **Descripción**: Ajusta la inclinación de la cámara a un porcentaje específico del máximo permitido.

### `turnTo(Actor actor)`
- **Firma**: `public static void turnTo(final Actor actor)`
- **Descripción**: Rota la cámara para mirar a un actor.

### `turnTo(TileObject tileObject)`
- **Firma**: `public static void turnTo(final TileObject tileObject)`
- **Descripción**: Rota la cámara para enfocar un objeto de baldosa.

### `turnTo(LocalPoint localPoint)`
- **Firma**: `public static void turnTo(final LocalPoint localPoint)`
- **Descripción**: Rota la cámara para enfocar un punto local específico en el mundo del juego.

## Detalles adicionales
Los métodos proporcionados facilitan diversas operaciones de cámara, críticas para mejorar la interacción del jugador y la visualización dentro del entorno del juego. Esta clase utiliza entradas del teclado para ajustar la cámara dinámicamente según los eventos del juego y las ubicaciones de los objetos.