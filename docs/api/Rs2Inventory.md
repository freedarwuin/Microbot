# Rs2Inventory Class Documentation

## [Back](development.md)

## Overview
`Rs2Inventory` gestiona el inventario del jugador en el juego, ofreciendo métodos para interactuar con los ítems, verificar el estado del inventario y realizar manipulaciones complejas de los objetos.

## Methods

### `inventory`
- **Descripción**: Recupera el contenedor actual del inventario.
- **Retorna**: `ItemContainer` - El inventario actual.

### `inventoryItems`
- **Descripción**: Contiene la lista de ítems actualmente en el inventario.

### `storeInventoryItemsInMemory`
- **Descripción**: Actualiza la memoria con los ítems actuales del inventario cuando hay un cambio.
- **Parámetros**:
    - `e`: `ItemContainerChanged` - Evento disparado cuando el inventario cambia.

### `items`
- **Descripción**: Proporciona una lista de todos los ítems en el inventario.
- **Retorna**: `List<Rs2Item>` - Lista de ítems del inventario.

### `all`
- **Descripción**: Retorna una lista de todos los ítems en el inventario.
- **Retorna**: `List<Rs2Item>` - Lista de todos los ítems.

### `all`
- **Descripción**: Retorna una lista de todos los ítems que cumplen con un filtro dado.
- **Parámetros**:
    - `filter`: `Predicate<Rs2Item>` - Filtro a aplicar.
- **Retorna**: `List<Rs2Item>` - Lista filtrada de ítems.

### `capacity`
- **Descripción**: Retorna la capacidad total del inventario.
- **Retorna**: `int` - Capacidad del inventario.

### `combine`
- **Descripción**: Combina dos ítems en el inventario usando sus IDs.
- **Parámetros**:
    - `primaryItemId`: `int` - ID del ítem primario.
    - `secondaryItemId`: `int` - ID del ítem secundario.
- **Retorna**: `boolean` - True si se realiza con éxito, false de lo contrario.

### `combine`
- **Descripción**: Combina dos ítems en el inventario usando sus nombres.
- **Parámetros**:
    - `primaryItemName`: `String` - Nombre del ítem primario.
    - `secondaryItemName`: `String` - Nombre del ítem secundario.
- **Retorna**: `boolean` - True si se realiza con éxito, false de lo contrario.

### `contains`
- **Descripción**: Verifica si el inventario contiene un ítem con un ID específico.
- **Parámetros**:
    - `id`: `int` - ID a verificar.
- **Retorna**: `boolean` - True si el ítem está presente, false de lo contrario.

### `contains`
- **Descripción**: Verifica si el inventario contiene ítems con IDs específicos.
- **Parámetros**:
    - `ids`: `int[]` - IDs a verificar.
- **Retorna**: `boolean` - True si todos los ítems están presentes, false de lo contrario.

### `contains`
- **Descripción**: Verifica si el inventario contiene un ítem con un nombre específico.
- **Parámetros**:
    - `name`: `String` - Nombre a verificar.
- **Retorna**: `boolean` - True si el ítem está presente, false de lo contrario.

### `count`
- **Descripción**: Cuenta la cantidad de ítems que coinciden con un ID específico.
- **Parámetros**:
    - `id`: `int` - ID a coincidir.
- **Retorna**: `int` - Cantidad de ítems coincidentes.

### `deselect`
- **Descripción**: Deselecciona cualquier ítem seleccionado en el inventario.
- **Retorna**: `boolean` - True si un ítem fue deseleccionado, false de lo contrario.

### `drop`
- **Descripción**: Desecha un ítem con un ID específico.
- **Parámetros**:
    - `id`: `int` - ID del ítem a desechar.
- **Retorna**: `boolean` - True si el ítem fue descartado, false de lo contrario.

### `dropAll`
- **Descripción**: Desecha todos los ítems en el inventario.
- **Retorna**: `boolean` - True si todos los ítems fueron descartados, false de lo contrario.

### `emptySlotCount`
- **Descripción**: Retorna la cantidad de espacios vacíos en el inventario.
- **Retorna**: `int` - Número de espacios vacíos.

### `get`
- **Descripción**: Recupera un ítem por su ID.
- **Parámetros**:
    - `id`: `int` - ID del ítem.
- **Retorna**: `Rs2Item` - El ítem, o null si no se encuentra.

### `getActionsForSlot`
- **Descripción**: Recupera las acciones disponibles para un ítem en un slot específico.
- **Parámetros**:
    - `slot`: `int` - Slot a verificar.
- **Retorna**: `String[]` - Array de acciones.

### `isFull`
- **Descripción**: Verifica si el inventario está lleno.
- **Retorna**: `boolean` - True si está lleno, false de lo contrario.

### `isEmpty`
- **Descripción**: Verifica si el inventario está vacío.
- **Retorna**: `boolean` - True si está vacío, false de lo contrario.

### `interact`
- **Descripción**: Interactúa con un ítem por ID.
- **Parámetros**:
    - `id`: `int` - ID del ítem.
    - `action`: `String` - Acción a realizar (opcional).
- **Retorna**: `boolean` - True si la interacción fue exitosa, false de lo contrario.

## Additional Methods
La clase incluye numerosos otros métodos para manipulaciones más específicas o avanzadas del inventario, como filtrar ítems, verificar la presencia de ítems por distintos criterios, interactuar con múltiples ítems y manejar casos especiales como ítems notados o no notados.

## Usage Example
```java
Rs2Inventory.interact(12345, "Use");
boolean hasItem = Rs2Inventory.contains("Magic potion");
int emptySlots = Rs2Inventory.emptySlotCount();