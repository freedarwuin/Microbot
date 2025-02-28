# Documentación de la clase Rs2Equipment
## [Volver](development.md)
## Descripción general
La clase `Rs2Equipment` proporciona métodos para interactuar con el equipo del jugador, lo que permite verificar el estado del equipo, manipular elementos específicos y administrar acciones relacionadas con las ranuras de equipo.

## Métodos

### `equipment`
- **Firma**: `public static ItemContainer equipment()`
- **Descripción**: recupera el contenedor de elementos de equipo actual del cliente del juego.

### `getEquippedItem`
- **Firma**: `public static Rs2Item getEquippedItem(EquipmentInventorySlot slot)`
- **Descripción**: recupera un elemento equipado de una ranura de equipo específica.

### `hasEquipped`
- **Signature**: `public static boolean hasEquipped(String itemName)`
- **Description**: Comprueba si un elemento con un nombre específico está equipado. Este método está en desuso y se eliminará en favor de `isWearing`.

### `hasEquippedContains`
- **Signature**: `public static boolean hasEquippedContains(String itemName)`
- **Description**: Comprueba si el nombre de cualquier elemento equipado contiene la subcadena especificada.

### `hasEquipped`
- **Signature**: `public static boolean hasEquipped(int id)`
- **Description**: Determina si un elemento con un ID específico está equipado.

### `hasEquippedSlot`
- **Firma**: `public static boolean hasEquippedSlot(EquipmentInventorySlot slot)`
- **Descripción**: Comprueba si hay algún objeto equipado en la ranura especificada.

### `hasGuthanBodyEquiped`
- **Firma**: `public static boolean hasGuthanBodyEquiped()`
- **Descripción**: Comprueba si está equipado el "Guthan's platebody".

### `hasGuthanHelmEquiped`
- **Firma**: `public static boolean hasGuthanHelmEquiped()`
- **Descripción**: Comprueba si está equipado el "Guthan's platebody".

### `hasGuthanLegsEquiped`
- **Firma**: `public static boolean hasGuthanLegsEquiped()`
- **Descripción**: Comprueba si la "Guthan's chainskirt" está equipada.

### `hasGuthanWeaponEquiped`
- **Firma**: `public static boolean hasGuthanWeaponEquiped()`
- **Descripción**: Comprueba si la "Guthan's warspear" está equipada.

### `isEquipped`
- **Firma**: `public static boolean isEquipped(String name, EquipmentInventorySlot slot, boolean exact)`
- **Descripción**: Determina si un objeto específico por nombre está equipado en una ranura determinada, con una opción para una coincidencia exacta.

### `isEquipped`
- **Firma**: `public static boolean isEquipped(int id, EquipmentInventorySlot slot)`
- **Descripción**: Comprueba si un elemento específico por ID está equipado en una ranura determinada.

### `isWearing`
- **Firma**: `public static boolean isWearing(String name, boolean exact)`
- **Descripción**: Comprueba en todas las ranuras de equipo si se lleva puesto un elemento específico por nombre, con una opción para una coincidencia exacta.

### `isWearing`
- **Firma**: `public static boolean isWearing(int id)`
- **Descripción**: Comprueba en todas las ranuras de equipo si se lleva puesto un elemento específico por ID.

### `isWearingFullGuthan`
- **Firma**: `public static boolean isWearingFullGuthan()`
- **Descripción**: determina si el jugador lleva puesto el conjunto completo de equipamiento de Guthan.

### `storeEquipmentItemsInMemory`
- **Firma**: `public static void storeEquipmentItemsInMemory(ItemContainerChanged e)`
- **Descripción**: actualiza la lista almacenada de elementos de equipamiento en función de los cambios detectados en el contenedor de elementos de equipamiento.

### `useAmuletAction`
- **Firma**: `public static void useAmuletAction(JewelleryLocationEnum jewelleryLocationEnum)`
- **Descripción**: realiza una acción sobre un amuleto, como equiparlo, en función de la ubicación de la joya especificada.

### `useRingAction`
- **Firma**: `public static void useRingAction(JewelleryLocationEnum jewelleryLocationEnum)`
- **Descripción**: Realiza una acción en un anillo, como equiparlo, según la ubicación de la joya especificada.

## Detalles adicionales
Esta clase es fundamental para los scripts que necesitan interactuar con los elementos equipados del jugador, lo que permite realizar controles y manipulaciones eficientes relacionados con el equipo y los accesorios.