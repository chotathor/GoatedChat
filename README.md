# GoatedChat — BLE Mesh Chat
[![Stars](https://img.shields.io/github/stars/chotathor/GoatedChat)](https://github.com/chotathor/GoatedChat/stargazers)

> Offline mesh chat via Bluetooth — no internet, no servers, no accounts.

## Features
- BLE mesh networking (multi-hop relay)
- Noise Protocol end-to-end encryption
- Nostr protocol for global bridge
- Location-based geohash channels
- IRC-style commands (/msg, /who, /slap)
- Emergency wipe
- Message compression (LZ4)
- Ephemeral key rotation

## How It Works
GoatedChat creates a decentralized mesh network via Bluetooth. Messages hop between devices (up to 7 hops), reaching people up to 500m away without any internet. Optional Nostr bridge syncs your messages globally when connected.

## Download
Get the latest APK from [Releases](https://github.com/chotathor/GoatedChat/releases)

## Tech
- Kotlin & Jetpack Compose
- Nordic BLE library
- Noise Protocol + Nostr
- Tor (Arti) for anonymous sync

## License
GPL v3.0