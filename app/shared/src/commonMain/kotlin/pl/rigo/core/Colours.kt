package pl.rigo.core

import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color

object Colours {
    // ==========================================
    // BRAND / ACCENT (Zielony przewodni i akcenty)
    // ==========================================
    /** Główny neonowy/energetyczny zielony akcent (CTA, aktywne statusy, przyciski) */
    val PrimaryGreen = Color(0xFF23A55A) // Klasyczna gamingowa zieleń
    val PrimaryGreenHover = Color(0xFF28B764) // Jaśniejszy stan hover/focus
    val PrimaryGreenActive = Color(0xFF1E8E4D) // Ciemniejszy stan wciśnięcia (pressed)
    val PrimaryGreenDim = Color(0x2623A55A) // 15% krycia - tła pod zaznaczenia / badge

    /** Jasny neonowy odcień do drobnych akcentów, glow i ikon */
    val MintAccent = Color(0xFF57F287)

    // ==========================================
    // BACKGROUNDS & SURFACES (Tła w stylu Discord/Medal)
    // ==========================================

    /** Najgłębsze tło (np. pasek boczny serwerów, główne okno modalne) */
    val BackgroundDarkest = Color(0xFF1E1F22)

    /** Główne tło aplikacji (np. lista kanałów, główny viewport) */
    val BackgroundDark = Color(0xFF2B2D31)

    /** Tło paneli, kart i wiadomości */
    val SurfaceCard = Color(0xFF313338)

    /** Tło dla elementów najechania (hover) lub drugorzędnych kart */
    val SurfaceCardHover = Color(0xFF383A40)

    /** Tło pól tekstowych (input), wyszukiwarek i zagnieżdżonych boksów */
    val SurfaceInput = Color(0xFF1E1F22)

    // ==========================================
    // BORDERS & DIVIDERS (Subtelne obramowania)
    // ==========================================
    val BorderSubtle = Color(0xFF3F4147)
    val BorderFocus = Color(0xFF23A55A) // Obramowanie aktywnego inputa

    // ==========================================
    // TEXT & CONTENT (Typografia i ikony)
    // ==========================================

    /** Główny tekst (nagłówki, najważniejszy content) */
    val TextPrimary = Color(0xFFF2F3F5)

    /** Tekst drugorzędny (opisy, znaczniki czasu, drugorzędne etykiety) */
    val TextSecondary = Color(0xFF949BA4)

    /** Wyciszony tekst (placeholdery, wyłączone elementy) */
    val TextMuted = Color(0xFF5C6067)

    /** Tekst bezpośrednio na zielonych przyciskach */
    val TextOnPrimary = Color(0xFFFFFFFF)

    // ==========================================
    // STATUSES / FEEDBACK (Stany i powiadomienia)
    // ==========================================
    val StatusOnline = Color(0xFF23A55A) // Online / Sukces
    val StatusIdle = Color(0xFFF0B232) // Zaraz wracam / Ostrzeżenie
    val StatusDnd = Color(0xFFF23F43) // Nie przeszkadzać / Błąd
    val StatusOffline = Color(0xFF80848E) // Offline / Neutralny

    val AppDarkColorScheme =
        darkColorScheme(
            primary = PrimaryGreen,
            onPrimary = TextOnPrimary,
            primaryContainer = PrimaryGreenDim,
            onPrimaryContainer = MintAccent,
            background = BackgroundDark,
            onBackground = TextPrimary,
            surface = SurfaceCard,
            onSurface = TextPrimary,
            surfaceVariant = SurfaceCardHover,
            onSurfaceVariant = TextSecondary,
            outline = BorderSubtle,
            error = StatusDnd,
        )
}
