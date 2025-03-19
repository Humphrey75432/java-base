package com.hhp.samples;

import org.apache.commons.collections4.ListUtils;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class AsyncIOProcessingExample {

    // 配置参数（建议通过配置类管理）
    private static final int BATCH_SIZE = 300;    // 根据IO响应时间调整
    private static final int MAX_THREADS = 10;    // 根据系统资源调整
    private static final int AWAIT_TIMEOUT = 300; // 秒

    public static void main(String[] args) throws InterruptedException {
        // 模拟20万数据
        String[] data = new String[200_000];
        Arrays.fill(data, "data");

        // 转换为分区列表
        List<List<String>> partitions = ListUtils.partition(Arrays.asList(data), BATCH_SIZE);

        // 创建适合IO密集型的线程池
        ExecutorService executor = new ThreadPoolExecutor(
                MAX_THREADS,
                MAX_THREADS,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(partitions.size()),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy() // 拒绝策略
        );

        // 异步提交所有任务
        partitions.forEach(partition ->
                CompletableFuture.runAsync(
                        () -> processWithRetry(partition, 3), // 添加重试机制
                        executor
                )
        );

        // 优雅关闭处理
        awaitTermination(executor);
    }

    /**
     * 带重试机制的IO处理
     */
    private static void processWithRetry(List<String> partition, int maxRetries) {
        int attempt = 0;
        while (attempt <= maxRetries) {
            try {
                processPartition(partition);
                return;
            } catch (Exception e) {
                if (++attempt > maxRetries) {
                    logError(partition, e);
                    break;
                }
                System.err.printf("重试分区 %d/%d (错误: %s)%n",
                        attempt, maxRetries, e.getMessage());
            }
        }
    }

    /**
     * 实际IO处理逻辑
     */
    private static void processPartition(List<String> partition) {
        try {
            // 模拟IO操作（如API调用、文件写入等）
            System.out.printf("开始处理批次（大小：%d 线程：%s）%n",
                    partition.size(),
                    Thread.currentThread().getName());

            // 实际IO操作应在此处实现
            for (String item : partition) {
                // 执行如：HTTP请求、数据库写入等IO操作
            }

        } catch (Exception e) {
            throw new RuntimeException("分区处理失败", e);
        }
    }

    /**
     * 线程池终止等待
     */
    private static void awaitTermination(ExecutorService executor)
            throws InterruptedException {

        executor.shutdown(); // 停止接收新任务
        if (!executor.awaitTermination(AWAIT_TIMEOUT, TimeUnit.SECONDS)) {
            List<Runnable> dropped = executor.shutdownNow();
            System.err.println("强制终止，丢弃任务数: " + dropped.size());
        }
    }

    /**
     * 错误日志记录
     */
    private static void logError(List<String> partition, Throwable e) {
        System.err.printf("分区处理失败（大小：%d）原因：%s%n",
                partition.size(),
                e.getMessage());
        // 实际应记录到日志系统，并考虑将失败分区存入死信队列
    }
}
