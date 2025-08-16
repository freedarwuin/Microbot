# Documentación de la clase Rs2Bank

## [Volver](development.md)

## Descripción general
La clase `Rs2Bank` gestiona las interacciones con el sistema bancario del juego, facilitando operaciones como abrir el banco, depositar, retirar y manejar los objetos del inventario.

## Métodos

### `closeBank`
- **Firma**: `public static boolean closeBank()`
- **Descripción**: Cierra la interfaz del banco si está abierta, asegurando que se cierre correctamente mediante la verificación del estado de la interfaz.

### `depositAll`
- **Firma**: `public static boolean depositAll(int id)`
- **Descripción**: Deposita todas las instancias de un objeto específico por su ID en el banco.

### `depositAllExcept(Integer... ids)`
- **Firma**: `public static boolean depositAllExcept(Integer... ids)`
- **Descripción**: Deposita todos los objetos excepto aquellos identificados por los IDs proporcionados.

### `depositAllExcept(String... names)`
- **Firma**: `public static boolean depositAllExcept(String... names)`
- **Descripción**: Deposita todos los objetos excepto aquellos cuyos nombres coinciden con las cadenas proporcionadas.

### `depositEquipment`
- **Firma**: `public static void depositEquipment()`
- **Descripción**: Deposita todos los objetos que el jugador tenga equipados actualmente en el banco.

### `depositOne`
- **Firma**: `public static void depositOne(int id)`
- **Descripción**: Deposita un solo objeto en el banco por su ID.

### `depositOne(String name)`
- **Firma**: `public static void depositOne(String name)`
- **Descripción**: Deposita un objeto por su nombre, manejando coincidencias parciales del nombre.

### `depositX`
- **Firma**: `public static void depositX(int id, int amount)`
- **Descripción**: Deposita una cantidad específica de un objeto por su ID.

### `findBankItem`
- **Firma**: `public static Rs2Item findBankItem(String name)`
- **Descripción**: Busca un objeto en el banco por su nombre, soportando coincidencias exactas o parciales.

### `getNearestBank`
- **Firma**: `public static BankLocation getNearestBank()`
- **Descripción**: Identifica la ubicación del banco más cercana respecto a la posición actual del jugador.

### `handleBankPin`
- **Firma**: `public static boolean handleBankPin(String pin)`
- **Descripción**: Interactúa con la interfaz del PIN del banco, ingresando el PIN proporcionado si es necesario.

### `hasItem`
- **Firma**: `public static boolean hasItem(int id)`
- **Descripción**: Verifica si el jugador tiene un objeto específico en el banco, identificado por su ID.

### `invokeMenu`
- **Firma**: `public static void invokeMenu(int entryIndex, Rs2Item rs2Item)`
- **Descripción**: Ejecuta acciones del menú para un objeto y entrada de menú específicos, ajustándose según el tipo de contenedor.

### `isOpen`
- **Firma**: `public static boolean isOpen()`
- **Descripción**: Comprueba si la interfaz del banco está abierta. Notifica al usuario si se necesita ingresar un PIN del banco.

### `openBank`
- **Firma**: `public static boolean openBank()`
- **Descripción**: Intenta abrir el banco interactuando con el NPC más cercano llamado "banker" u otros objetos relacionados con el banco.

### `openBank(NPC npc)`
- **Firma**: `public static boolean openBank(NPC npc)`
- **Descripción**: Abre el banco interactuando directamente con un NPC especificado.

### `openBank(TileObject object)`
- **Firma**: `public static boolean openBank(TileObject object)`
- **Descripción**: Intenta abrir el banco interactuando con un objeto de la baldosa especificado.

### `updateLocalBank`
- **Firma**: `public static void updateLocalBank(ItemContainerChanged e)`
- **Descripción**: Actualiza la lista en caché de los objetos del banco basándose en los cambios detectados en un evento de contenedor de objetos.

### `useBank`
- **Firma**: `public static boolean useBank()`
- **Descripción**: Facilita el uso del banco a través de los medios de interacción disponibles.

### `walkToBank`
- **Firma**: `public static boolean walkToBank()`
- **Descripción**: Dirige al jugador a caminar hasta la ubicación del banco más cercana.

### `wearItem(int id)`
- **Firma**: `public static void wearItem(int id)`
- **Descripción**: Equipa un objeto directamente desde el banco por su ID.

### `withdrawAll`
- **Firma**: `public static void withdrawAll(int id)`
- **Descripción**: Retira todas las instancias de un objeto específico del banco por su ID.

### `withdrawAll(String name)`
- **Firma**: `public static void withdrawAll(String name)`
- **Descripción**: Retira todos los objetos con un nombre específico del banco.

### `withdrawAndEquip(int id)`
- **Firma**: `public static void withdrawAndEquip(int id)`
- **Descripción**: Retira y equipa inmediatamente un objeto por su ID.

### `withdrawOne`
- **Firma**: `public static void withdrawOne(int id)`
- **Descripción**: Retira un solo objeto del banco por su ID.

### `withdrawOne(String name)`
- **Firma**: `public static void withdrawOne(String name)`
- **Descripción**: Retira un solo objeto del banco por su nombre.

### `withdrawX`
- **Firma**: `public static void withdrawX(int id, int amount)`
- **Descripción**: Retira una cantidad específica de un objeto del banco por su ID.

## Detalles adicionales
Para métodos más complejos que involucren identificación de objetos e interacción, ejemplos de uso pueden aclarar mejor el comportamiento esperado y posibles casos límite.