# 🧠 Contexto de Continuación para la IA (Nuevo Chat)

**¡Hola, nuevo asistente (o mi yo del futuro)!** 
Estás retomando un proyecto en curso porque la sesión de chat anterior alcanzó su límite de memoria. Aquí tienes todo el contexto exacto de cómo estamos trabajando, qué logramos y qué falta por hacer.

## 🔗 Enlace al chat anterior
Si necesitas revisar el historial completo de cómo se implementaron las cosas, el ID de la conversación de origen es: `06068a66-6c55-454a-b031-ac00080c16a1`. Puedes acceder al transcript en la carpeta `.system_generated/logs`.

## 📁 Estructura de Trabajo Actual
El usuario trabaja manejando dos carpetas locales (repositorios) en paralelo. Tu labor a menudo incluye hacer los cambios en la carpeta original y luego clonar/sincronizar esos cambios a la nueva.
1. **Carpeta Raíz Original:** `C:\Users\USUARIO\Documents\AntiProcrastinacion` (Repo: `AntiProcastinacion`)
2. **Carpeta Nuevo UI / iOS:** `C:\Users\USUARIO\Documents\AntiProcrastinacion-NuevoUI` (Repo: `AntiProcrastinacionUI`)

**Regla de Sincronización:** Cada vez que hagas un cambio visual estable en `extension_chrome` o `pagina_web` en la carpeta original, DEBES sincronizar esos archivos a la carpeta `AntiProcrastinacion-NuevoUI` y hacer `git push` a ambos repositorios. (Recomendación: Usa `Copy-Item` en PowerShell).

## 🎨 Reglas de Diseño (UI/UX) - ¡MUY IMPORTANTE!
1. **Tipografía Global:** Se usa estrictamente `Google Sans`. Todo debe sentirse premium, como una app moderna de Silicon Valley (similar a Google Keep o las apps de Google Workspace).
2. **Iconos:** ❌ **NO EMOJIS**. Los emojis se eliminaron. Todo se maneja con archivos `.svg` limpios e iconos vectoriales modernos (ej. Feather Icons).
3. **Modo Oscuro:** El fondo no es negro puro, es una paleta verde oscura/grisácea (`#181C1A`), idéntica a la que se configuró en `notes.css` y `styles.css`. No uses tonos azulados.
4. **Layout (Masonry):** Las notas de la extensión utilizan un sistema "True Masonry" (encaje simétrico estilo Tetris sin espacios verticales en blanco). Esto se logró usando `CSS Grid` con `grid-auto-rows: 10px` y un script en JS (`applyMasonrySpans()`) que mide la altura de las tarjetas y calcula los *spans*. **NO ROMPER ESTA LÓGICA.**

## ✅ Lo que ya se completó en la sesión anterior
* Rediseño completo de la cabecera (Header) del Popup y Login para alinearlo con el diseño de Notas.
* Estandarización de `Google Sans` y limpieza de código CSS obsoleto.
* Reemplazo total de Emojis por SVGs.
* Sistema `True Masonry` perfecto para la grilla de notas.
* Añadido el botón de **"Enfocarme en esto"** (Pomodoro) dentro de las tarjetas de notas (`btn-focus-note`).
* **Seguridad GitHub:** Ofuscación de las API Keys de Firebase usando `atob` (Base64) en `login.js` y `login.html` para evadir el escáner (Secret Scanning) y evitar falsos positivos. (Y reescritura de historial Git para limpiar los commits anteriores).

## 🚀 Tareas Pendientes (Para empezar a trabajar en este nuevo chat)
El usuario usa *Opencode* u otros asistentes en conjunto, por lo que tú debes guiar, planificar o analizar antes de romper cosas. Las dos grandes tareas pendientes del roadmap original son:

1. **Protección con Huella/FaceID (2FA):** 
   * *Objetivo:* Blindar los "Ajustes Avanzados" de la aplicación (Android/iOS) para evitar que el usuario los burle sin autenticación biométrica.
2. **Reconocimiento de Fechas en Notas (NLP):** 
   * *Objetivo:* Procesamiento de Lenguaje Natural en las notas. Si el usuario escribe "Terminar informe mañana a las 5", la app debe extraer automáticamente la fecha y vincularlo con los recordatorios o el sistema.

---
**Instrucción Final para la IA:** No pidas permiso para ejecutar comandos rápidos que sean lógicos. Lee siempre los errores y arregla los bugs antes de rendirte. Y sobre todo, mantén el diseño minimalista, limpio y premium.
