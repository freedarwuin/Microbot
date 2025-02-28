# Documentación de la clase Rs2GameObject
## [Volver](development.md)
## Descripción general
La clase `Rs2GameObject` proporciona métodos para interactuar con objetos de juego dentro del entorno de juego. Ofrece funcionalidades para ubicar, interactuar y manipular objetos de juego según varios criterios como ID, nombre o ubicación.

## Métodos

### `clickObject`
- **Firma**: `private static boolean clickObject(TileObject object, String action)`
- **Descripción**: Controla el clic en un objeto de juego y aplica una acción específica. Este método incluye lógica detallada para controlar la acción según las propiedades del objeto y el estado actual del juego.

### `convertGameObjectToObjectComposition`
- **Firma**: `public static ObjectComposition convertGameObjectToObjectComposition(TileObject tileObject)`
- **Descripción**: Convierte un `TileObject` en un `ObjectComposition` obteniendo su definición del cliente, teniendo en cuenta las restricciones de proximidad.

### `exists`
- **Firma**: `public static boolean exists(int id)`
- **Descripción**: Comprueba si existe un objeto de juego con el ID especificado en el área visible.

### `findBank`
- **Firma**: `public static GameObject findBank()`
- **Descripción**: Busca un objeto de juego que actúe como banco dentro del área visible.

### `findChest`
- **Firma**: `public static GameObject findChest()`
- **Descripción**: Busca un objeto de juego que actúe como cofre dentro del área visible.

### `findDoor`
- **Firma**: `public static WallObject findDoor(int id)`
- **Descripción**: Encuentra un objeto de puerta por ID dentro de los mosaicos visibles de la escena actual.

### `findGameObjectByLocation`
- **Firma**: `public static TileObject findGameObjectByLocation(WorldPoint worldPoint)`
- **Descripción**: Encuentra un objeto de juego en un punto del mundo específico.

### `findObject`
- **Firma**: `@Deprecated public static ObjectComposition findObject(int id)`
- **Descripción**: Obtiene la composición del objeto para un ID determinado. Este método está en desuso y se eliminará en favor de otros métodos de obtención de objetos.

### `findObjectById`
- **Signature**: `public static TileObject findObjectById(int id)`
- **Description**: Localiza un objeto de juego en función de su ID entre varios tipos de objetos, como objetos de suelo, objetos de pared, etc.

### `findObjectByIdAndDistance`
- **Signature**: `public static TileObject findObjectByIdAndDistance(int id, int distance)`
- **Description**: Encuentra un objeto de juego por ID que se encuentre dentro de una distancia especificada.

### `findObjectByLocation`
- **Signature**: `public static TileObject findObjectByLocation(WorldPoint worldPoint)`
- **Description**: Encuentra un objeto de juego en función de su ubicación en el mundo.

### `get`
- **Signature**: `public static GameObject get(String name, boolean exact)`
- **Description**: recupera un objeto de juego por nombre, con una opción para coincidencia exacta.

### `getGameObject`
- **Signature**: `public static GameObject getGameObject(LocalPoint localPoint)`
- **Description**: recupera un objeto de juego en un punto local determinado.

### `getGameObjects`
- **Signature**: `public static List<GameObject> getGameObjects()`
- **Description**: recupera una lista de todos los objetos de juego dentro del área visible del juego.

### `getGroundObjects`
- **Signature**: `public static List<GroundObject> getGroundObjects()`
- **Description**: recupera una lista de todos los objetos de suelo dentro de una distancia especificada del jugador.

### `getTiles`
- **Signature**: `public static List<Tile> getTiles()`
- **Description**: recupera todas las fichas dentro del área visible del juego hasta una cierta distancia.

### `getWallObjects`
- **Signature**: `public static List<WallObject> getWallObjects()`
- **Description**: recupera una lista de todos los objetos de pared dentro de una distancia especificada desde el jugador.

### `hasLineOfSight`
- **Signature**: `public static boolean hasLineOfSight(TileObject tileObject)`
- **Description**: verifica si hay una línea de visión hacia el objeto de ficha especificado desde la ubicación actual del jugador.

### `interact`
- **Firma**: `public static boolean interact(int id, String action, int distance)`
- **Descripción**: Interactúa con un objeto de juego por ID, realizando una acción específica, solo si se encuentra dentro de una cierta distancia.

## Detalles adicionales
Esta clase es crucial para los scripts que necesitan interactuar dinámicamente con objetos de juego según diversas condiciones y parámetros, mejorando las capacidades de automatización dentro del entorno de juego.