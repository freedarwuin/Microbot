# Documentación de la clase Rs2MiniMap
## [Volver](development.md)
## Descripción general
La clase `Rs2MiniMap` proporciona métodos para traducir las coordenadas del juego en puntos del minimapa en el cliente de Microbot. Esta funcionalidad es esencial para las tareas de navegación y búsqueda de rutas que requieren la interacción con el minimapa.

## Métodos

### `localToMinimap(LocalPoint localPoint)`
- **Descripción**: Convierte un `LocalPoint` en su punto correspondiente en el minimapa.
- **Parámetros**:
- `localPoint`: `LocalPoint` - El punto local dentro de la región actual.
- **Devoluciones**: `Point` - Las coordenadas del minimapa del punto local especificado. Devuelve `null` si la entrada es `null` o si el punto no se puede convertir.