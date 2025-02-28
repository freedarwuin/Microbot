# Rs2GroundItem Class Documentation
## [Volver](development.md)
## Overview
The `Rs2GroundItem` La clase proporciona métodos para interactuar con elementos del suelo dentro del juego, facilitando operaciones como recoger, verificar existencia e interactuar con elementos en función de varios criterios, como ID, nombre o ubicación del elemento.

## Methods

### `exists`
- **Firma**: `public static boolean exists(String itemName, int range)`
- **Descripción**: Comprueba si existe un elemento terrestre con el nombre especificado dentro de un rango determinado.

### `exists`
- **Firma**: `public static boolean exists(int itemId, int range)`
- **Descripción**: Comprueba si existe un elemento terrestre con el ID especificado dentro de un rango determinado.

### `getAll`
- **Firma**: `public static RS2Item[] getAll(int range)`
- **Descripción**: Recupera todos los elementos del suelo dentro de un rango específico desde la ubicación del jugador.

### `getAllAt`
- **Firma**: `public static RS2Item[] getAllAt(int x, int y)`
- **Descripción**: Recupera todos los elementos del suelo ubicados en una casilla específica.

### `getTile`
- **Signature**: `public static Tile getTile(int x, int y)`
- **Descripción**: Recupera el mosaico en una coordenada mundial determinada.

### `interact`
- **Firma**: `public static boolean interact(String itemName, String action, int x, int y)`
- **Descripción**: Interactúa con un elemento de tierra por nombre y acción especificada en una ubicación de mosaico específica.

### `interact`
- **Firma**: `public static boolean interact(int itemId, String action, int x, int y)`
- **Descripción**: Interactúa con un elemento de tierra mediante ID y acción especificada en una ubicación de mosaico específica.

### `interact`
- **Firma**: `public static boolean interact(String itemName, String action, int range)`
- **Descripción**: Interactúa con todos los elementos del suelo que coincidan con el nombre y la acción especificados dentro de un rango determinado.

### `interact`
- **Firma**: `public static boolean interact(int itemId, String action, int range)`
- **Descripción**: Interactúa con todos los elementos terrestres que coincidan con el ID y la acción especificados dentro de un rango determinado.

### `loot`
- **Firma**: `public static boolean loot(String lootItem, int minQuantity, int range)`
- **Descripción**: Intenta recoger elementos del suelo que coincidan con el nombre del elemento especificado, la cantidad y dentro de un rango determinado.

### `lootAllItemBasedOnValue`
- **Firma**: `public static boolean lootAllItemBasedOnValue(int value, int range)`
- **Descripción**: Intenta recoger todos los objetos terrestres cuyo valor total supere una cantidad específica dentro de un rango determinado.

### `lootAtGePrice`
- **Firma**: `public static boolean lootAtGePrice(int minGePrice)`
- **Descripción**: Intenta recoger artículos en función de que su precio en Gran Intercambio sea superior a un cierto umbral.

### `lootItemBasedOnValue`
- **Firma**: `public static boolean lootItemBasedOnValue(int value, int range)`
- **Descripción**: Intenta recoger un solo artículo en función de su valor total que exceda una cantidad específica dentro de un rango determinado.

### `pickup`
- **Firma**: `public static boolean pickup(String lootItem, int range)`
- **Descripción**: Alias para saquear un objeto, centrándose en la acción de recogerlo.

### `take`
- **Firma**: `public static boolean take(String lootItem, int range)`
- **Descripción**: Otro alias para saquear un objeto, enfatizando la acción de tomarlo.

## Detalles adicionales
Esta clase es esencial para los scripts que automatizan la recolección o interacción con elementos en el terreno dentro del juego, mejorando la eficiencia y administrando el inventario en función de las propiedades de los elementos y las necesidades del jugador.