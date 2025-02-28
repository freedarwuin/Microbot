# Documentación de la clase Rs2Reflection
## [Volver](development.md)
La clase `Rs2Reflection` proporciona métodos de utilidad para manipular elementos del juego a través de la reflexión de Java. Esto permite realizar operaciones que normalmente son inaccesibles a través de llamadas API estándar, como obtener y modificar atributos de entidad del juego e invocar acciones ocultas dentro del cliente del juego.

## Métodos

### `getAnimation(NPC npc)`
- **Parámetros**: `NPC npc` - El NPC cuyo valor de animación se recuperará.
- **Devuelve**: `int` - El identificador de animación del NPC, o `-1` si no se encuentra.
- **Descripción**: Recupera el identificador de animación de un NPC determinado identificando dinámicamente el campo de animación a través de la reflexión.

### `getGroundItemActions(ItemComposition item)`
- **Parámetros**: `ItemComposition item` - La composición del elemento a analizar.
- **Devuelve**: `String[]` - Una matriz de posibles acciones para el elemento en el suelo.
- **Descripción**: Extrae las acciones del elemento en el suelo de la composición de un elemento mediante la reflexión para acceder al campo relevante.

### `setItemId(MenuEntry menuEntry, int itemId)`
- **Parámetros**:
- `MenuEntry menuEntry` - La entrada del menú que se modificará.
- `int itemId` - El ID del elemento que se establecerá.
- **Descripción**: Establece el ID del elemento en una entrada del menú, presumiblemente para modificar el contexto de una interacción de forma dinámica.

### `getObjectByName(String[] names, boolean exact)`
- **Parámetros**:
- `String[] names` - Matriz de nombres de objetos que se buscarán.
- `boolean exact` - Especifica si se deben hacer coincidir los nombres exactamente.
- **Devuelve**: `ArrayList<Integer>` - Una lista de identificadores de objetos que coinciden con los nombres.
- **Descripción**: Busca identificadores de objetos a partir de las definiciones de objetos del juego utilizando sus nombres, con una opción para coincidencias exactas o parciales.

### `invokeMenu(int param0, int param1, int opcode, int identifier, int itemId, String option, String target, int x, int y)`
- **Parámetros**: Parámetros que definen el contexto y los detalles de la acción del menú que se invocará.
- **Descripción**: Invoca directamente una acción del menú del juego utilizando la reflexión, que normalmente se utiliza para simular clics y otras interacciones de forma programática.

## Ejemplo de uso

Este ejemplo demuestra cómo usar `getAnimation` para recuperar la animación de un NPC e imprimirla:

```java
NPC someNpc = ... // obtener la instancia de NPC del cliente del juego
int animationId = Rs2Reflection.getAnimation(someNpc);
System.out.println("ID de animación de NPC actual: " + animationId);