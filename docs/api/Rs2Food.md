# Documentación de la enumeración Rs2Food
## [Volver](development.md)
## Descripción general
La enumeración `Rs2Food` define constantes para varios elementos de comida utilizados en el juego. Cada elemento de comida está asociado con un identificador (ID), la cantidad que cura (curación) y un nombre descriptivo.

## Constantes de enumeración

### Elementos de comida
Cada constante en la enumeración `Rs2Food` representa un tipo específico de comida, incluidas sus propiedades curativas y la identificación del elemento. A continuación, se muestran algunos ejemplos de la enumeración:

- **Dark_Crab**: representa un cangrejo oscuro que cura 27 puntos.
- **ROCKTAIL**: representa un Rocktail que cura 23 puntos.
- **MANTA**: representa una manta raya que cura 22 puntos.
- **SHARK**: representa un tiburón que cura 20 puntos.
- **LOBSTER**: Representa una langosta que cura 12 puntos.
- **TROUT**: Representa una trucha que cura 7 puntos.
- **SALMON**: Representa un salmón que cura 9 puntos.
- **SWORDFISH**: Representa un pez espada que cura 14 puntos.
- **TUNA**: Representa un atún que cura 10 puntos.
- **MONKFISH**: Representa un rape que cura 16 puntos.
- **SEA_TURTLE**: Representa una tortuga marina que cura 21 puntos.
- **CAKE**: Representa una tarta que cura 4 puntos.
- **CHOCOLATE_CAKE**: Representa una tarta de chocolate que cura 5 puntos.
- **PLAIN_PIZZA**: Representa una pizza simple que cura 7 puntos.
- **MEAT_PIZZA**: Representa una pizza de carne que cura 8 puntos.
- **ANCHOVY_PIZZA**: Representa una pizza de anchoas que cura 9 puntos.
- **PINEAPPLE_PIZZA**: Representa una pizza de piña que cura 11 puntos.
- **BREAD**: Representa un pan que cura 5 puntos.
- **APPLE_PIE**: Representa una tarta de manzana que cura 7 puntos.
- **MEAT_PIE**: Representa una tarta de carne que cura 6 puntos.

... y muchos más.

## Métodos

### `getId`
- **Descripción**: Devuelve el ID del alimento, que se utiliza dentro del juego para identificar diferentes tipos de alimentos.

### `getHeal`
- **Descripción**: Devuelve la cantidad de puntos de salud que el alimento puede restaurar cuando se consume.

### `getName`
- **Descripción**: Proporciona el nombre del alimento, que se puede utilizar en las interfaces y scripts del juego.

### `toString`
- **Descripción**: Devuelve una representación en cadena del alimento, que combina su nombre y valor de curación, útil para fines de visualización o depuración.

## Uso
Esta enumeración es particularmente útil para los scripts que tratan con la gestión de la salud, donde se necesitan alimentos para restaurar puntos de salud. Simplifica el proceso de selección de alimentos adecuados en función de sus capacidades de curación y proporciona una forma sencilla de hacer referencia a ellos por nombre o ID.