# Tarea 3 - Gestión de Planetas Star Wars (Jetpack Compose)

# 🪐 Star Wars Planet Manager - Evolución hacia MVVM

## 📝 Sobre este Proyecto
Este proyecto nació como una simple lista de planetas y ha evolucionado hasta convertirse en una aplicación Android robusta que implementa la arquitectura recomendada por Google. A través de esta práctica, he transformado una estructura básica en un sistema profesional y escalable.

## 🚀 Lo que he aprendido y aplicado
Este proyecto ha sido un reto de aprendizaje donde he pasado de "hacer que funcione" a "hacerlo con arquitectura profesional".

### 🏛️ Arquitectura MVVM (Model-View-ViewModel)
He aprendido a separar la lógica de negocio de la interfaz de usuario. Ahora, las pantallas (Views) solo se encargan de mostrar datos, mientras que los ViewModels gestionan el estado, haciendo que el código sea mucho más limpio y fácil de mantener.

### 💉 Inyección de Dependencias con Hilt
Uno de los mayores aprendizajes ha sido la implementación de **Dagger Hilt**. He pasado de crear objetos manualmente a dejar que el sistema los inyecte. Esto me ha permitido desacoplar el Repositorio de los ViewModels, facilitando la escalabilidad del proyecto.

### 🔄 Estados Reactivos con StateFlow
He dejado atrás las actualizaciones manuales para usar **StateFlow**. Aprender a manejar flujos de datos reactivos ha sido clave: ahora la UI "reacciona" automáticamente cuando los datos en el repositorio cambian, garantizando que el usuario siempre vea la información actualizada.

### 🎭 UX y Animaciones en Compose
He profundizado en **Navigation Compose** para crear una experiencia de usuario fluida. Implementar transiciones mediante `AnimatedVisibility` (fades, slides y scales) me ha enseñado cómo los pequeños detalles visuales transforman una app sencilla en una experiencia mucho más profesional.

### 🛠️ UI Avanzada con Material 3
* **Scaffold & Snackbars:** He aprendido a coordinar estados de UI globales, como mostrar avisos (Snackbars) sincronizados con acciones del usuario (borrado/edición).
* **CompositionLocal:** He implementado proveedores de diseño globales para mantener la coherencia en los espaciados de toda la app.

## 🛠️ Stack Tecnológico
* **Lenguaje:** Kotlin
* **UI:** Jetpack Compose (Material 3)
* **Arquitectura:** MVVM + Clean Architecture principles
* **DI:** Hilt
* **Navegación:** Compose Navigation con Animaciones

---
*Este proyecto representa mi camino en el aprendizaje de Jetpack Compose y las arquitecturas modernas en Android.* Ejecutar en un emulador o dispositivo físico con API 24 o superior.
