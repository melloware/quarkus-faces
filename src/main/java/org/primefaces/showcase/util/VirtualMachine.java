package org.primefaces.showcase.util;

import org.apache.commons.lang3.SystemUtils;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.extern.jbosslog.JBossLog;

@RegisterForReflection
@JBossLog
public final class VirtualMachine {

    /**
     * Returns a human-readable version of the file size, where the input
     * represents a specific number of bytes. Example: 7077888: 7.1 kB 6.8 MiB
     *
     * @param bytes the number of bytes to turn human readable
     * @param si    whether to use International System of Units (SI) representation
     *              or 1024 byte representation
     * @return a human readable string of byte size
     * @see <a href=
     * "http://stackoverflow.com/questions/3758606/how-to-convert-byte-size-into-human-readable-format-in-java">Stack
     * Overflow</a>
     */
    public static String byteCountToDisplaySize(final long bytes, final boolean si) {
        final int unit = si ? 1000 : 1024;
        if (bytes < unit) {
            return bytes + " B";
        }
        final int exp = (int) (Math.log(bytes) / Math.log(unit));
        final String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }

    /**
     * Overloaded method for human readable byte format.
     *
     * @param bytes the number of bytes to turn human readable
     * @return a human readable string of byte size
     */
    public static String byteCountToDisplaySize(final long bytes) {
        return byteCountToDisplaySize(bytes, true);
    }

    /**
     * Calculates the available memory in the VM.
     *
     * @return a long representing the bytes of memory available
     */
    public static long getAvailableMemory() {
        final Runtime runtime = Runtime.getRuntime();
        final long totalMemory = runtime.totalMemory();
        final long freeMemory = runtime.freeMemory();
        final long maxMemory = runtime.maxMemory();
        final long usedMemory = totalMemory - freeMemory;
        return maxMemory - usedMemory;
    }

    /**
     * Calculates the available memory in the VM as a String.
     *
     * @return a String representation of the available VM memory
     */
    public static String getAvailableMemoryAsString() {
        return byteCountToDisplaySize(getAvailableMemory());
    }

    /**
     * Calculates the used memory in the VM.
     *
     * @return a long representing the bytes of memory used
     */
    public static long getUsedMemory() {
        final Runtime runtime = Runtime.getRuntime();
        final long totalMemory = runtime.totalMemory();
        final long freeMemory = runtime.freeMemory();
        return totalMemory - freeMemory;
    }

    /**
     * Calculates the used memory in the VM as a String.
     *
     * @return a String representation of the used VM memory
     */
    public static String getUsedMemoryAsString() {
        return byteCountToDisplaySize(getUsedMemory());
    }

    /**
     * Gets the memory statistics of the JVM in a String for display in a log
     * file.
     *
     * @return the string containing the memory stats
     */
    public static String getMemoryStatisticsAsString() {
        System.gc();
        final Runtime runtime = Runtime.getRuntime();
        final long totalMemory = runtime.totalMemory();
        final long freeMemory = runtime.freeMemory();
        final long maxMemory = runtime.maxMemory();
        final long usedMemory = totalMemory - freeMemory;
        final long availableMemory = maxMemory - usedMemory;

        String output = "Used: " +
                    byteCountToDisplaySize(usedMemory) +
                    " Free: " +
                    byteCountToDisplaySize(availableMemory) +
                    " Max: " +
                    byteCountToDisplaySize(maxMemory);
        log.info(output);

        return output;
    }

    /**
     * Gets the Java version information.
     *
     * @return the JVM name, version, and vendor
     */
    public static String getJavaVersion() {
        // java.runtime.name=Java(TM) SE Runtime Environment
        // java.runtime.version=1.7.0-b147
        // java.specification.name=Java Platform API Specification
        // java.specification.vendor=Oracle Corporation
        return SystemUtils.JAVA_RUNTIME_NAME + " " + SystemUtils.JAVA_RUNTIME_VERSION + " "
                    + SystemUtils.JAVA_SPECIFICATION_VENDOR;
    }

    /**
     * Gets the OS version information.
     *
     * @return the OS name, version, and vendor
     */
    public static String getOSVersion() {
        return SystemUtils.OS_NAME + " " + SystemUtils.OS_VERSION + " " + SystemUtils.OS_ARCH;
    }

}
