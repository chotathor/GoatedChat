# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
-keep class com.goatedchat.mesh.protocol.** { *; }
-keep class com.goatedchat.mesh.crypto.** { *; }
-dontwarn org.bouncycastle.**
-keep class org.bouncycastle.** { *; }

# Keep SecureIdentityStateManager from being obfuscated to prevent reflection issues
-keep class com.goatedchat.mesh.identity.SecureIdentityStateManager {
    private android.content.SharedPreferences prefs;
    *;
}

# Keep all classes that might use reflection
-keep class com.goatedchat.mesh.favorites.** { *; }
-keep class com.goatedchat.mesh.nostr.** { *; }
-keep class com.goatedchat.mesh.identity.** { *; }

# Keep Tor implementation (always included)
-keep class com.goatedchat.mesh.net.RealTorProvider { *; }

# Arti (Custom Tor implementation in Rust) ProGuard rules
-keep class info.guardianproject.arti.** { *; }
-keep class org.torproject.arti.** { *; }
-keepnames class org.torproject.arti.**
-dontwarn info.guardianproject.arti.**
-dontwarn org.torproject.arti.**

# Fix for AbstractMethodError on API < 29 where LocationListener methods are abstract
-keepclassmembers class * implements android.location.LocationListener {
    public <methods>;
}
