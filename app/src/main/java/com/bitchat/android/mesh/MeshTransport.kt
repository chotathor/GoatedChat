package com.goatedchat.mesh.mesh

import com.goatedchat.mesh.model.RoutedPacket
import com.goatedchat.mesh.protocol.BitchatPacket

/**
 * Transport abstraction used by MeshCore to send packets via a specific medium.
 */
interface MeshTransport {
    val id: String

    fun broadcastPacket(routed: RoutedPacket)

    fun sendPacketToPeer(peerID: String, packet: BitchatPacket): Boolean

    fun cancelTransfer(transferId: String): Boolean = false

    fun getDeviceAddressForPeer(peerID: String): String? = null

    fun getDeviceAddressToPeerMapping(): Map<String, String> = emptyMap()

    fun getTransportDebugInfo(): String = ""
}
