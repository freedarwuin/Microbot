# Documentación de la clase Rs2Inventory
## [Volver](development.md)
## Descripción general
`Rs2Inventory` administra el inventario del jugador en el juego, ofreciendo métodos para interactuar con los elementos, verificar el estado del inventario y realizar manipulaciones complejas de elementos.

## Métodos

### `inventory`
- **Descripción**: recupera el contenedor del inventario actual.
- **Devuelve**: `ItemContainer` - El inventario actual.

### `inventoryItems`
- **Descripción**: contiene la lista de elementos que se encuentran actualmente en el inventario.

### `storeInventoryItemsInMemory`
- **Descripción**: actualiza la memoria con los elementos del inventario actual siempre que haya un cambio.
- **Parámetros**:
- `e`: `ItemContainerChanged` - El evento que se activa cuando cambia el inventario.

### `items`
- **Descripción**: Proporciona una lista de todos los artículos del inventario.
- **Devuelve**: `List<Rs2Item>` - Lista de artículos del inventario.

### `all`
- **Descripción**: Devuelve una lista de todos los artículos del inventario.
- **Devuelve**: `List<Rs2Item>` - Lista de todos los artículos.

### `all`
- **Descripción**: Devuelve una lista de todos los artículos que coinciden con un filtro determinado.
- **Parámetros**:
- `filter`: `Predicate<Rs2Item>` - El filtro que se aplicará.
- **Devuelve**: `List<Rs2Item>` - Lista filtrada de artículos.

### `capacity`
- **Descripción**: Devuelve la capacidad total del inventario.
- **Devuelve**: `int` - La capacidad del inventario.

### `combine`
- **Descripción**: Combina dos elementos del inventario por sus ID.
- **Parámetros**:
- `primaryItemId`: `int` - ID del elemento principal.
- `secondaryItemId`: `int` - ID del elemento secundario.
- **Devuelve**: `boolean` - Verdadero si la operación es exitosa, falso en caso contrario.

### `combine`
- **Descripción**: Combina dos elementos del inventario por sus nombres.
- **Parámetros**:
- `primaryItemName`: `String` - Nombre del elemento principal.
- `secondaryItemName`: `String` - Nombre del elemento secundario.
- **Devuelve**: `boolean` - Verdadero si la operación es exitosa, falso en caso contrario.

### `contains`
- **Descripción**: Comprueba si el inventario contiene un elemento con un ID específico.
- **Parámetros**:
- `id`: `int` - El ID a verificar.
- **Devuelve**: `boolean` - Verdadero si el artículo está presente, falso en caso contrario.

### `contains`
- **Descripción**: Comprueba si el inventario contiene artículos con ID específicos.
- **Parámetros**:
- `ids`: `int[]` - Los ID a verificar.
- **Devuelve**: `boolean` - Verdadero si todos los artículos están presentes, falso en caso contrario.

### `contains`
- **Descripción**: Comprueba si el inventario contiene un artículo con un nombre específico.
- **Parámetros**:
- `name`: `String` - El nombre a verificar.
- **Devuelve**: `boolean` - Verdadero si el artículo está presente, falso en caso contrario.

### `count`
- **Descripción**: Cuenta la cantidad de artículos que coinciden con un ID específico.
- **Parámetros**:
- `id`: `int` - El ID que se va a buscar.
- **Devuelve**: `int` - Cantidad de elementos que coinciden.

### `deselect`
- **Descripción**: Deselecciona cualquier elemento seleccionado en el inventario.
- **Devuelve**: `boolean` - Verdadero si se deseleccionó un elemento, falso en caso contrario.

### `drop`
- **Descripción**: Deselecciona un elemento con un ID específico.
- **Parámetros**:
- `id`: `int` - El ID del elemento que se va a descartar.
- **Devuelve**: `boolean` - Verdadero si se deseleccionó el elemento, falso en caso contrario.

### `dropAll`
- **Descripción**: Deselecciona todos los elementos del inventario.
- **Devuelve**: `boolean` - Verdadero si se deseleccionaron todos los elementos, falso en caso contrario.

### `emptySlotCount`
- **Descripción**: Devuelve el recuento de espacios vacíos en el inventario.
- **Devuelve**: `int` - Número de espacios vacíos.

### `get`
- **Descripción**: Recupera un elemento por ID.
- **Parámetros**:
- `id`: `int` - El ID del elemento.
- **Devuelve**: `Rs2Item` - El elemento, o null si no se encuentra.

### `getActionsForSlot`
- **Descripción**: Recupera las acciones disponibles para un elemento en un espacio especificado.
- **Parámetros**:
- `slot`: `int` - El espacio a verificar.
- **Devuelve**: `String[]` - Matriz de acciones.

### `isFull`
- **Descripción**: Comprueba si el inventario está lleno.
- **Devuelve**: `boolean` - Verdadero si el inventario está lleno, falso en caso contrario.

### `isEmpty`
- **Descripción**: Comprueba si el inventario está vacío.
- **Devuelve**: `boolean` - Verdadero si el inventario está vacío, falso en caso contrario.

### `interact`
- **Descripción**: Interactúa con un elemento por ID.
- **Parámetros**:
- `id`: `int` - El ID del elemento.
- `action`: `String` - La acción a realizar (opcional).
- **Devuelve**: `boolean` - Verdadero si la interacción fue exitosa, falso en caso contrario.

## Métodos adicionales
La clase incluye muchos otros métodos para manipulaciones de inventario más específicas o avanzadas, como filtrar elementos, verificar la presencia de elementos por diferentes criterios, interactuar con múltiples elementos y manejar casos especiales como elementos anotados o no anotados.

## Ejemplo de uso
```java
Rs2Inventory.interact(12345, "Use");
boolean hasItem = Rs2Inventory.contains("Magic potion");
int emptySlots = Rs2Inventory.emptySlotCount();