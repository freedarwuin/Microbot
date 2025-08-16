# Documentación de la clase Rs2Equipment

## [Volver](development.md)

## Descripción general
La clase `Rs2Equipment` proporciona métodos para interactuar con el equipo del jugador, permitiendo verificar el estado del equipo, manipular elementos específicos y gestionar acciones relacionadas con los espacios de equipo.

## Métodos

### `equipment`
- **Firma**: `public static ItemContainer equipment()`
- **Descripción**: Recupera el contenedor de elementos actualmente equipado desde el cliente del juego.

### `getEquippedItem`
- **Firma**: `public static Rs2Item getEquippedItem(EquipmentInventorySlot slot)`
- **Descripción**: Recupera un elemento equipado en un espacio de equipo específico.

### `hasEquipped`
- **Firma**: `public static boolean hasEquipped(String itemName)`
- **Descripción**: Comprueba si un elemento con un nombre específico está equipado. Este método está en desuso y será reemplazado por `isWearing`.

### `hasEquippedContains`
- **Firma**: `public static boolean hasEquippedContains(String itemName)`
- **Descripción**: Comprueba si algún elemento equipado contiene la subcadena especificada en su nombre.

### `hasEquipped`
- **Firma**: `public static boolean hasEquipped(int id)`
- **Descripción**: Determina si un elemento con un ID específico está equipado.

### `hasEquippedSlot`
- **Firma**: `public static boolean hasEquippedSlot(EquipmentInventorySlot slot)`
- **Descripción**: Comprueba si hay algún elemento equipado en el espacio especificado.

### `hasGuthanBodyEquiped`
- **Firma**: `public static boolean hasGuthanBodyEquiped()`
- **Descripción**: Comprueba si la "armadura de Guthan" está equipada.

### `hasGuthanHelmEquiped`
- **Firma**: `public static boolean hasGuthanHelmEquiped()`
- **Descripción**: Comprueba si el "casco de Guthan" está equipado.

### `hasGuthanLegsEquiped`
- **Firma**: `public static boolean hasGuthanLegsEquiped()`
- **Descripción**: Comprueba si la "falda de malla de Guthan" está equipada.

### `hasGuthanWeaponEquiped`
- **Firma**: `public static boolean hasGuthanWeaponEquiped()`
- **Descripción**: Comprueba si la "lanza de guerra de Guthan" está equipada.

### `isEquipped`
- **Firma**: `public static boolean isEquipped(String name, EquipmentInventorySlot slot, boolean exact)`
- **Descripción**: Determina si un elemento específico por nombre está equipado en un espacio dado, con opción de coincidencia exacta.

### `isEquipped`
- **Firma**: `public static boolean isEquipped(int id, EquipmentInventorySlot slot)`
- **Descripción**: Comprueba si un elemento específico por ID está equipado en un espacio dado.

### `isWearing`
- **Firma**: `public static boolean isWearing(String name, boolean exact)`
- **Descripción**: Comprueba en todos los espacios de equipo si un elemento específico por nombre está siendo usado, con opción de coincidencia exacta.

### `isWearing`
- **Firma**: `public static boolean isWearing(int id)`
- **Descripción**: Comprueba en todos los espacios de equipo si un elemento específico por ID está siendo usado.

### `isWearingFullGuthan`
- **Firma**: `public static boolean isWearingFullGuthan()`
- **Descripción**: Determina si el jugador está usando el set completo de equipo de Guthan.

### `storeEquipmentItemsInMemory`
- **Firma**: `public static void storeEquipmentItemsInMemory(ItemContainerChanged e)`
- **Descripción**: Actualiza la lista almacenada de elementos equipados basándose en los cambios detectados en el contenedor de equipo.

### `useAmuletAction`
- **Firma**: `public static void useAmuletAction(JewelleryLocationEnum jewelleryLocationEnum)`
- **Descripción**: Realiza una acción sobre un amuleto, como equiparlo, basado en la ubicación de joyería especificada.

### `useRingAction`
- **Firma**: `public static void useRingAction(JewelleryLocationEnum jewelleryLocationEnum)`
- **Descripción**: Realiza una acción sobre un anillo, como equiparlo, basado en la ubicación de joyería especificada.

## Detalles adicionales
Esta clase es crítica para scripts que necesitan interactuar con los elementos equipados por el jugador, permitiendo comprobaciones y manipulaciones eficientes relacionadas con el equipo y accesorios.