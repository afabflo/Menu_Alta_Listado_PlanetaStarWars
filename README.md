# Star Wars Planet Manager - Aplicación Multifuncional (Tareas 2, 3 y 4)

## Sobre este Proyecto
Esta aplicación representa la evolución completa del proyecto académico Star Wars. Lo que comenzó como una interfaz básica terminó convirtiéndose en una aplicación Android multifuncional, visualmente cuidada y estructurada bajo una arquitectura moderna, diseñada para gestionar distintas entidades del universo Star Wars.

El proyecto implementa la gestión completa de:

- Planetas  
- Personajes  
- Películas  

### Relación principal trabajada (Requisito obligatorio)
**Planeta → contiene → Personajes**

Esto permite cumplir con el enfoque Maestro-Detalle exigido en las tareas, desarrollando dos entidades relacionadas de forma funcional y coherente.

Además, la aplicación incorpora una sección adicional de películas como ampliación funcional para enriquecer la experiencia general.

---

# Evolución del Proyecto (De Tarea 2 a Tarea 4)

## Tarea 2 — Diseño de Interfaces
En esta fase se desarrollaron:

- Listados visuales
- Formularios de alta
- Primer diseño con Jetpack Compose
- Material 3
- Navegación visual inicial
- Estética inspirada en Star Wars

---

## Tarea 3 — Arquitectura Android Moderna
El proyecto evolucionó hacia una estructura más profesional mediante:

- MVVM
- Hilt (inyección de dependencias)
- Navigation Compose
- StateFlow / Flow
- Repository Pattern
- Separación real de capas

---

## Tarea 4 — Aplicación Funcional Completa
La aplicación alcanza un nivel avanzado mediante:

- CRUD completo
- Persistencia real con Room
- Validaciones
- Control de duplicados
- Notificaciones
- Gestión de permisos
- Pulsación larga
- AlertDialogs
- SnackBars
- Drawer Navigation
- Overflow Menu

---

# Stack Tecnológico y Arquitectura

## Lenguaje:
- Kotlin 1.9+

## UI:
- Jetpack Compose
- Material 3

## Persistencia:
- Room Database (SQLite)

## Inyección de Dependencias:
- Dagger Hilt

## Asincronía:
- Coroutines
- Flow / StateFlow

## Arquitectura:
- MVVM
- Repository Pattern

---

# Base de Datos (Nivel Avanzado)
La aplicación utiliza Room Database para almacenar permanentemente toda la información.

## Base de datos:
```kotlin
star_wars_db_v5
