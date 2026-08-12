package com.antiprocrastinacion.lock

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

// V26: widget de escritorio movible/redimensionable con inicio rápido del
// Modo Enfoque, frase motivacional y acceso a las notas del launcher.
class ZenWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        appWidgetIds.forEach { id -> updateWidget(context, appWidgetManager, id) }
    }

    companion object {
        const val ACTION_START_FOCUS = "com.antiprocrastinacion.lock.START_FOCUS"
        const val ACTION_OPEN_NOTES = "com.antiprocrastinacion.lock.OPEN_NOTES"
        const val EXTRA_DURATION = "extra_duration"

        fun updateWidget(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            val views = RemoteViews(context.packageName, R.layout.zen_widget)

            val phrase = MotivationalPhrases.getRandomPhrase()
            views.setTextViewText(R.id.widget_phrase, phrase)

            listOf(25, 45, 90).forEach { minutes ->
                val intent = Intent(context, MainActivity::class.java).apply {
                    action = ACTION_START_FOCUS
                    putExtra(EXTRA_DURATION, minutes)
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP
                }
                val pi = PendingIntent.getActivity(
                    context,
                    minutes,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
                val viewId = when (minutes) {
                    25 -> R.id.widget_btn_25
                    45 -> R.id.widget_btn_45
                    else -> R.id.widget_btn_90
                }
                views.setOnClickPendingIntent(viewId, pi)
            }

            val notesIntent = Intent(context, MainActivity::class.java).apply {
                action = ACTION_OPEN_NOTES
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            val notesPi = PendingIntent.getActivity(
                context,
                1001,
                notesIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            views.setOnClickPendingIntent(R.id.widget_open_notes, notesPi)

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}
