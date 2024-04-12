package utils;

import java.net.InetAddress;

public record ClientAddress(InetAddress address, int port) {
}
