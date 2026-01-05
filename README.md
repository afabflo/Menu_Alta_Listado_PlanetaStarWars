# Tarea 3 - Gestión de Planetas Star Wars (Jetpack Compose)

## Descripción
Esta aplicación permite la gestión completa (Listado, Alta, Edición y Borrado) de planetas del universo Star Wars. Se ha desarrollado siguiendo los principios modernos de desarrollo en Android propuestos por Google, centrados en la separación de capas y la reactividad.

## Arquitectura
Se ha implementado el patrón **MVVM (Model-View-ViewModel)** para garantizar un código limpio, mantenible y testeable:

* **Model:** Representado por la entidad `Planet` y un repositorio estático que gestiona la fuente de verdad de los datos.
* **View:** Pantallas desarrolladas íntegramente con **Jetpack Compose**, utilizando componentes de Material 3.
* **ViewModel:** Cada pantalla cuenta con su propio ViewModel para gestionar el estado de la UI y la lógica de negocio.

## Tecnologías y Conceptos Implementados

### 1. Inyección de Dependencias (Dagger Hilt)
Se utiliza **Hilt** para desacoplar las dependencias del proyecto. El repositorio de datos se inyecta en los ViewModels, facilitando la escalabilidad y cumpliendo con el **Punto 9** del enunciado.

### 2. Gestión de Estados (StateFlow)
El listado de planetas y los estados de edición se gestionan mediante **StateFlow**. Esto permite que la interfaz de usuario sea reactiva y se actualice automáticamente ante cualquier cambio en el repositorio de datos.

### 3. Navegación y Animaciones
Se utiliza **Navigation Compose** para la gestión de rutas. Siguiendo el **Punto 7**, se han implementado animaciones de transición personalizadas mediante `AnimatedVisibility` (Fade, Scale y Slide) para mejorar la experiencia de usuario (UX).

### 4. Componentes Avanzados de UI
* **Scaffold:** Estructura base con `TopAppBar`, `FloatingActionButton` y `SnackbarHost`.
* **SnackBars:** Notificaciones informativas sincronizadas con el `Scaffold` tras acciones de borrado o edición (**Punto 10**).
* **CompositionLocal:** Uso de `LocalPlanetPadding` para la gestión global de márgenes y espaciados (**Punto 12**).
* **Previews:** Implementación de `@Preview` avanzados para todas las pantallas del proyecto.

## Instrucciones de Ejecución
1. Clonar el repositorio.
2. Sincronizar el proyecto con Gradle.
3. Ejecutar en un emulador o dispositivo físico con API 24 o superior.
