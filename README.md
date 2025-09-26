# Testo11

Aplicación Android con **Jetpack Compose (Material 3)**, **Hilt** para DI y **DataStore** (Preferences) para persistencia local. Flujo pensado para un entorno empresarial donde un **Admin Master** crea la empresa y el primer usuario.

## Flujo (resumen)
1. **StartRouter**: si no hay empresa o alias → va al **Wizard**; si existen → va a **LoginAlias**.  
2. **Wizard**:  
   - **CreateCompany**: guarda *companyId* y *companyName*.  
   - **CreateAdminUser**: guarda el **alias** del Admin Master.  
   - **ProfileCard**: datos básicos del usuario (nombre/teléfono/documento).  
3. **LoginAlias**: valida el alias (se prellena desde DataStore).  
4. **Home**: muestra `companyName` y `alias`. “Cerrar sesión” borra **solo el alias** (empresa/perfil permanecen).

## Stack técnico
- Jetpack **Compose** (Material 3) + **Navigation Compose**
- **Hilt** (KSP) para inyección de dependencias
- **DataStore (Preferences)** para sesión/perfil
- Kotlin **17**, Gradle **8.7**, Android Gradle Plugin **8.3.2**, `compileSdk` **34`

## Estructura (simplificada)
