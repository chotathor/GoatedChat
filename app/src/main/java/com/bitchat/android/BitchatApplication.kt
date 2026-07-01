package com.goatedchat.mesh

import android.app.Application
import com.goatedchat.mesh.nostr.RelayDirectory
import com.goatedchat.mesh.ui.theme.ThemePreferenceManager
import com.goatedchat.mesh.net.ArtiTorManager

/**
 * Main application class for goatedchat Android
 */
class BitchatApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Initialize Tor first so any early network goes over Tor
        try {
            val torProvider = ArtiTorManager.getInstance()
            torProvider.init(this)
        } catch (_: Exception){}

        // Initialize relay directory (loads assets/nostr_relays.csv)
        RelayDirectory.initialize(this)

        // Initialize LocationNotesManager dependencies early so sheet subscriptions can start immediately
        try { com.goatedchat.mesh.nostr.LocationNotesInitializer.initialize(this) } catch (_: Exception) { }

        // Initialize favorites persistence early so MessageRouter/NostrTransport can use it on startup
        try {
            com.goatedchat.mesh.favorites.FavoritesPersistenceService.initialize(this)
        } catch (_: Exception) { }

        // Warm up Nostr identity to ensure npub is available for favorite notifications
        try {
            com.goatedchat.mesh.nostr.NostrIdentityBridge.getCurrentNostrIdentity(this)
        } catch (_: Exception) { }

        // Initialize theme preference
        ThemePreferenceManager.init(this)

        // Initialize debug preference manager (persists debug toggles)
        try { com.goatedchat.mesh.ui.debug.DebugPreferenceManager.init(this) } catch (_: Exception) { }

        // Initialize Wi‑Fi Aware controller with persisted default
        try {
            val enabled = com.goatedchat.mesh.ui.debug.DebugPreferenceManager.getWifiAwareEnabled(false)
            com.goatedchat.mesh.wifiaware.WifiAwareController.initialize(this, enabled)
        } catch (_: Exception) { }

        // Initialize Geohash Registries for persistence
        try {
            com.goatedchat.mesh.nostr.GeohashAliasRegistry.initialize(this)
            com.goatedchat.mesh.nostr.GeohashConversationRegistry.initialize(this)
        } catch (_: Exception) { }

        // Initialize mesh service preferences
        try { com.goatedchat.mesh.service.MeshServicePreferences.init(this) } catch (_: Exception) { }

        // Proactively start the foreground service to keep mesh alive
        try { com.goatedchat.mesh.service.MeshForegroundService.start(this) } catch (_: Exception) { }

        // TorManager already initialized above
    }
}
