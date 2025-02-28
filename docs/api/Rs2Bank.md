# Rs2Bank Documentación de la clase

## [Volver](development.md)

## Descripción general
La clase `Rs2Bank` administra las interacciones con el sistema bancario en el juego, facilitando operaciones como abrir el banco, depositar, retirar y administrar artículos del inventario.
## Métodos

### `closeBank`
- **Firma**: `public static boolean closeBank()`
- **Descripción**: Cierra la interfaz del banco si está abierta, garantizando que esté correctamente cerrada verificando el estado de la interfaz.

### `depositAll`
- **Firma**: `public static boolean depositAll(int id)`
- **Descripción**: Deposita en el banco todas las instancias de un artículo específico por su ID.

### `depositAllExcept(Integer... ids)`
- **Firma**: `public static boolean depositAllExcept(Integer... ids)`
- **Descripción**: Deposita todos los artículos excepto aquellos identificados por las identificaciones proporcionadas.

### `depositAllExcept(String... names)`
- **Firma**: `public static boolean depositAllExcept(String... names)`
- **Descripción**: Deposita todos los artículos excepto aquellos cuyos nombres coincidan con las cadenas proporcionadas.

### `depositEquipment`
- **Firma**: `public static void depositEquipment()`
- **Descripción**: Deposita todos los elementos actualmente equipados por el jugador en el banco.

### `depositOne`
- **Firma**: `public static void depositOne(int id)`
- **Descripción**: Deposita un solo artículo en el banco por su identificación.

### `depositOne(String name)`
- **Firma**: `public static void depositOne(String name)`
- **Descripción**: Deposita un artículo por su nombre y maneja coincidencias de nombres parciales.

### `depositX`
- **Firma**: `public static void depositX(int id, int amount)`
- **Descripción**: Deposita una cantidad específica de un artículo según su ID.

### `findBankItem`
- **Firma**: `public static Rs2Item findBankItem(String name)`
- **Descripción**: Busca un artículo bancario por su nombre, admitiendo coincidencias exactas y parciales.

### `getNearestBank`
- **Firma**: `public static BankLocation getNearestBank()`
- **Descripción**: Identifica la ubicación del banco más cercano a la posición actual del jugador.

### `handleBankPin`
- **Firma**: `public static boolean handleBankPin(String pin)`
- **Descripción**: Interactúa con la interfaz PIN del banco, ingresando el PIN proporcionado si es necesario.

### `hasItem`
- **Firma**: `public static boolean hasItem(int id)`
- **Descripción**: Verifica si el jugador tiene un artículo específico en el banco, identificado por su ID.

### `invokeMenu`
- **Firma**: `public static void invokeMenu(int entryIndex, Rs2Item rs2Item)`
- **Descripción**: Ejecuta acciones de menú para un elemento y una entrada de menú específicos, ajustándose según el tipo de contenedor.

### `isOpen`
- **Firma**: `public static boolean isOpen()`
- **Descripción**: Comprueba si la interfaz del banco está abierta actualmente. Notifica al usuario si es necesario introducir un PIN bancario.

### `openBank`
- **Firma**: `public static boolean openBank()`
- **Descripción**: Intenta abrir el banco interactuando con el NPC más cercano llamado "banker" u otros objetos relacionados con el banco.

### `openBank(NPC npc)`
- **Firma**: `public static boolean openBank(NPC npc)`
- **Descripción**: Abre el banco interactuando directamente con un NPC específico.

### `openBank(TileObject object)`
- **Firma**: `public static boolean openBank(TileObject object)`
- **Descripción**: Intenta abrir el banco interactuando con un objeto de mosaico específico.

### `storeBankItemsInMemory`
- **Firma**: `public static void storeBankItemsInMemory(ItemContainerChanged e)`
- **Descripción**: Actualiza la lista almacenada en caché de elementos bancarios en función de los cambios detectados en un evento contenedor de elementos.

### `useBank`
- **Firma**: `public static boolean useBank()`
- **Descripción**: Facilita el uso de un banco a través de los medios de interacción disponibles.

### `walkToBank`
- **Firma**: `public static boolean walkToBank()`
- **Descripción**: Indica al jugador que camine hasta el banco más cercano.

### `wearItem(int id)`
- **Firma**: `public static void wearItem(int id)`
- **Descripción**: Equipa un artículo directamente desde el banco por su ID.

### `withdrawAll`
- **Firma**: `public static void withdrawAll(int id)`
- **Descripción**: Retira todas las instancias de un artículo específico del banco según su ID.

### `withdrawAll(String name)`
- **Firma**: `public static void withdrawAll(String name)`
- **Descripción**: Retira todos los artículos con un nombre específico del banco.

### `withdrawAndEquip(int id)`
- **Firma**: `public static void withdrawAndEquip(int id)`
- **Descripción**: Retira y equipa inmediatamente un objeto según su ID.

### `withdrawOne`
- **Firma**: `public static void withdrawOne(int id)`
- **Descripción**: Retira un solo artículo del banco por su ID.

### `withdrawOne(String name)`
- **Firma**: `public static void withdrawOne(String name)`
- **Descripción**: Retira un solo artículo según su nombre del banco.

### `withdrawX`
- **Firma**: `public static void withdrawX(int id, int amount)`
- **Descripción**: Retira una cantidad específica de un artículo del banco según su ID.

## Detalles adicionales
Para métodos más complejos que involucran identificación e interacción de elementos, los ejemplos de uso pueden aclarar aún más el comportamiento esperado y los posibles casos extremos.