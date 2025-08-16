# Documentación de la clase Rs2GameObject

## [Volver](development.md)

## Descripción general
La clase `Rs2GameObject` proporciona métodos para interactuar con objetos del juego dentro del entorno del juego. Ofrece funcionalidades para localizar, interactuar y manipular objetos del juego basándose en varios criterios como ID, nombre o ubicación.

## Métodos

### `clickObject`
- **Firma**: `private static boolean clickObject(TileObject object, String action)`
- **Descripción**: Maneja el clic en un objeto del juego, aplicando una acción específica. Este método incluye lógica detallada para manejar la acción según las propiedades del objeto y el estado actual del juego.

### `convertGameObjectToObjectComposition`
- **Firma**: `public static ObjectComposition convertGameObjectToObjectComposition(TileObject tileObject)`
- **Descripción**: Convierte un `TileObject` a un `ObjectComposition` obteniendo su definición desde el cliente, considerando restricciones de proximidad.

### `exists`
- **Firma**: `public static boolean exists(int id)`
- **Descripción**: Verifica si un objeto del juego con el ID especificado existe en el área visible.

### `findBank`
- **Firma**: `public static GameObject findBank()`
- **Descripción**: Busca un objeto del juego que funcione como banco dentro del área visible.

### `findChest`
- **Firma**: `public static GameObject findChest()`
- **Descripción**: Busca un objeto del juego que funcione como cofre dentro del área visible.

### `findDoor`
- **Firma**: `public static WallObject findDoor(int id)`
- **Descripción**: Encuentra un objeto puerta por ID dentro de los tiles visibles de la escena actual.

### `findGameObjectByLocation`
- **Firma**: `public static TileObject findGameObjectByLocation(WorldPoint worldPoint)`
- **Descripción**: Encuentra un objeto del juego en un punto específico del mundo.

### `findObject`
- **Firma**: `@Deprecated public static ObjectComposition findObject(int id)`
- **Descripción**: Obtiene la composición de un objeto por su ID. Este método está obsoleto y se eliminará en favor de otros métodos de obtención de objetos.

### `findObjectById`
- **Firma**: `public static TileObject findObjectById(int id)`
- **Descripción**: Localiza un objeto del juego según su ID en varios tipos de objetos como objetos en el suelo, paredes, etc.

### `findObjectByIdAndDistance`
- **Firma**: `public static TileObject findObjectByIdAndDistance(int id, int distance)`
- **Descripción**: Encuentra un objeto del juego por ID que se encuentre dentro de una distancia especificada.

### `findObjectByLocation`
- **Firma**: `public static TileObject findObjectByLocation(WorldPoint worldPoint)`
- **Descripción**: Encuentra un objeto del juego basado en su ubicación en el mundo.

### `get`
- **Firma**: `public static GameObject get(String name, boolean exact)`
- **Descripción**: Recupera un objeto del juego por nombre, con opción de coincidencia exacta.

### `getGameObject`
- **Firma**: `public static GameObject getGameObject(LocalPoint localPoint)`
- **Descripción**: Recupera un objeto del juego en un punto local determinado.

### `getGameObjects`
- **Firma**: `public static List<GameObject> getGameObjects()`
- **Descripción**: Recupera una lista de todos los objetos del juego dentro del área visible.

### `getGroundObjects`
- **Firma**: `public static List<GroundObject> getGroundObjects()`
- **Descripción**: Recupera una lista de todos los objetos en el suelo dentro de una distancia especificada del jugador.

### `getTiles`
- **Firma**: `public static List<Tile> getTiles()`
- **Descripción**: Recupera todos los tiles dentro del área visible del juego hasta cierta distancia.

### `getWallObjects`
- **Firma**: `public static List<WallObject> getWallObjects()`
- **Descripción**: Recupera una lista de todos los objetos de pared dentro de una distancia especificada del jugador.

### `hasLineOfSight`
- **Firma**: `public static boolean hasLineOfSight(TileObject tileObject)`
- **Descripción**: Verifica si hay línea de visión hacia el objeto especificado desde la ubicación actual del jugador.

### `interact`
- **Firma**: `public static boolean interact(int id, String action, int distance)`
- **Descripción**: Interactúa con un objeto del juego por ID, realizando una acción específica solo si se encuentra dentro de cierta distancia.

## Detalles adicionales
Esta clase es crucial para scripts que necesitan interactuar dinámicamente con objetos del juego según varias condiciones y parámetros, mejorando las capacidades de automatización dentro del entorno del juego.