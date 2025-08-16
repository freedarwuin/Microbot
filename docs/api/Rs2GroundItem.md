# Documentación de la clase Rs2GroundItem

## [Volver](development.md)

## Descripción general
La clase `Rs2GroundItem` proporciona métodos para interactuar con los objetos en el suelo dentro del juego, facilitando operaciones como recoger, verificar existencia e interactuar con ítems basándose en diversos criterios como ID del ítem, nombre o ubicación.

## Métodos

### `exists`
- **Firma**: `public static boolean exists(String itemName, int range)`
- **Descripción**: Verifica si un objeto en el suelo con el nombre especificado existe dentro de un rango dado.

### `exists`
- **Firma**: `public static boolean exists(int itemId, int range)`
- **Descripción**: Verifica si un objeto en el suelo con el ID especificado existe dentro de un rango dado.

### `getAll`
- **Firma**: `public static RS2Item[] getAll(int range)`
- **Descripción**: Recupera todos los objetos en el suelo dentro de un rango especificado desde la ubicación del jugador.

### `getAllAt`
- **Firma**: `public static RS2Item[] getAllAt(int x, int y)`
- **Descripción**: Recupera todos los objetos en el suelo ubicados en un tile específico.

### `getTile`
- **Firma**: `public static Tile getTile(int x, int y)`
- **Descripción**: Recupera el tile en una coordenada específica del mundo.

### `interact`
- **Firma**: `public static boolean interact(String itemName, String action, int x, int y)`
- **Descripción**: Interactúa con un objeto del suelo por nombre y acción especificada en una ubicación de tile específica.

### `interact`
- **Firma**: `public static boolean interact(int itemId, String action, int x, int y)`
- **Descripción**: Interactúa con un objeto del suelo por ID y acción especificada en una ubicación de tile específica.

### `interact`
- **Firma**: `public static boolean interact(String itemName, String action, int range)`
- **Descripción**: Interactúa con todos los objetos del suelo que coincidan con el nombre y acción especificada dentro de un rango determinado.

### `interact`
- **Firma**: `public static boolean interact(int itemId, String action, int range)`
- **Descripción**: Interactúa con todos los objetos del suelo que coincidan con el ID y acción especificada dentro de un rango determinado.

### `loot`
- **Firma**: `public static boolean loot(String lootItem, int minQuantity, int range)`
- **Descripción**: Intenta recoger objetos del suelo que coincidan con el nombre del ítem, cantidad mínima y dentro de un rango determinado.

### `lootAllItemBasedOnValue`
- **Firma**: `public static boolean lootAllItemBasedOnValue(int value, int range)`
- **Descripción**: Intenta recoger todos los objetos en el suelo basándose en su valor total superando una cantidad especificada dentro de un rango determinado.

### `lootAtGePrice`
- **Firma**: `public static boolean lootAtGePrice(int minGePrice)`
- **Descripción**: Intenta recoger objetos basándose en que su precio en el Grand Exchange sea superior a un umbral específico.

### `lootItemBasedOnValue`
- **Firma**: `public static boolean lootItemBasedOnValue(int value, int range)`
- **Descripción**: Intenta recoger un solo objeto basándose en que su valor total supere una cantidad especificada dentro de un rango determinado.

### `pickup`
- **Firma**: `public static boolean pickup(String lootItem, int range)`
- **Descripción**: Alias para recoger un objeto, enfocándose en la acción de levantarlo.

### `take`
- **Firma**: `public static boolean take(String lootItem, int range)`
- **Descripción**: Otro alias para recoger un objeto, enfatizando la acción de tomarlo.

## Detalles adicionales
Esta clase es esencial para scripts que automatizan la recolección o interacción con objetos en el suelo dentro del juego, mejorando la eficiencia y gestionando el inventario según las propiedades del ítem y las necesidades del jugador.