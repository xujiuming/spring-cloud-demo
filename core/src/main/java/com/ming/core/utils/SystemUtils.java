package com.ming.core.utils;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 系统工具类
 *
 * @author ming
 * @date 2021-08-12 20:38:47
 */
public class SystemUtils {

    private static volatile SystemInfo SYSTEM_INFO = new SystemInfo();


    public static double getCpuRate() {
        CentralProcessor processor = SYSTEM_INFO.getHardware().getProcessor();
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        // 睡眠1s
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long[] ticks = processor.getSystemCpuLoadTicks();
        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()] - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
        long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()] - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
        long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()] - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()] - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
        long cSys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()] - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        long user = ticks[CentralProcessor.TickType.USER.getIndex()] - prevTicks[CentralProcessor.TickType.USER.getIndex()];
        long iowait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()] - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()] - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
        long totalCpu = user + nice + cSys + idle + iowait + irq + softirq + steal;
        return formatRate(1.0 - (idle * 1.0 / totalCpu));
    }

    public static long getTotalMemorySize() {
        return SYSTEM_INFO.getHardware().getMemory().getTotal();
    }

    public static long getFreeMemorySize() {
        return SYSTEM_INFO.getHardware().getMemory().getAvailable();
    }

    public static long getUseMemorySize() {
        GlobalMemory memory = SYSTEM_INFO.getHardware().getMemory();
        return memory.getTotal() - memory.getAvailable();
    }


    public static long getTotalDiskSize() {
        return Arrays.stream(File.listRoots())
                .mapToLong(File::getTotalSpace)
                .sum();
    }

    public static long getUseDiskSize() {
        return Arrays.stream(File.listRoots())
                .mapToLong(m -> m.getTotalSpace() - m.getFreeSpace())
                .sum();
    }


    public static long getFreeDiskSize() {
        return Arrays.stream(File.listRoots())
                .mapToLong(File::getFreeSpace)
                .sum();
    }


    public static double formatRate(double rate) {
        return BigDecimal.valueOf(rate)
                .multiply(BigDecimal.valueOf(100))
                .setScale(2, RoundingMode.DOWN)
                .doubleValue();
    }


    public static double formatRateByLong(long long1, long long2) {
        return BigDecimal.valueOf(long1)
                .multiply(BigDecimal.valueOf(100))
                .divide(BigDecimal.valueOf(long2), 2, RoundingMode.DOWN)
                .doubleValue();
    }

}
