package com.dziubi.forher.data

import android.graphics.Color

enum class OrderState( val textColor: Int) {
    Anulowane(Color.RED),
    Wysłane(Color.GREEN),
    Dostarczone(Color.GREEN),
    Błąd_wysyłki(Color.RED),
    W_przygotowaniu(Color.BLACK)
}