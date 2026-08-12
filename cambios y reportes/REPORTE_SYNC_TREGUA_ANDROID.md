# Reporte: Sincronización de Tregua desde Android hacia la Extensión

Para que la extensión de Chrome detecte que se ha activado una tregua (desbloqueo temporal) desde la aplicación de Android y pueda desbloquear las páginas web, es necesario realizar los siguientes cambios en un futuro:

## 1. Cambios en la App de Android (`LockManager.kt`)
Actualmente, Android envía a Firebase su estado de bloqueo (`is_locked`) pero no envía la hora en que termina la tregua. 
- En el método `pushLockStateToFirebase()`, se debe añadir el valor de la tregua:
  ```kotlin
  val data = mapOf(
      "is_locked" to locked,
      "temp_unlock_end_time" to tempUnlockEndTime, // <- NUEVA LÍNEA
      "expires_at" to expiresAt,
      // ... resto de los datos
  )
  ```
- Adicionalmente, cada vez que se llame a `startTempUnlock()`, se debe invocar a `pushLockStateToFirebase()` para forzar la actualización inmediata en la base de datos para que la extensión la detecte.

## 2. Cambios en la Extensión de Chrome (`background.js`)
La extensión debe leer el nuevo campo enviado por Android.
- En la función de sincronización (donde se lee `lock_state.json`), se debe verificar si `lockState.temp_unlock_end_time` es mayor que la hora actual (`Date.now()`).
- Si es mayor, se debe asignar ese valor a la variable `treguaUntil` y llamar a `updateNetRules()`. Esto automáticamente pausará el bloqueo de páginas y actualizará el color amarillo de "Tregua" en el popup de la PC sin afectar tu temporizador de horas principal.
