package com.xjjlearning.java.util.concurrent.chapter6;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class SumTask extends RecursiveTask<Long> {
  private static final int SEQUENTIAL_THRESHOLD = 50;
  private final List<Long> data;
  public SumTask(List<Long> data) {
    this.data = data;
  }

  @Override
  protected Long compute() {
    if (data.size() <= SEQUENTIAL_THRESHOLD) {
      long sum = computeSumDirectly();
      System.out.format("Sum of %s: %d\n", data.toString(), sum);
      return sum;
    } else {
      int mid = data.size() / 2;
      SumTask firstSubtask = new SumTask(data.subList(0, mid));
      SumTask secondSubtask = new SumTask(data.subList(mid, data.size()));
      // 执行子任务
      firstSubtask.fork();
      secondSubtask.fork();
      // 等待子任务执行完成，并获取结果
      long firstSubTaskResult = firstSubtask.join();
      long secondSubTaskResult = secondSubtask.join();
      return firstSubTaskResult + secondSubTaskResult;
    }
  }

  private long computeSumDirectly() {
    long sum = 0;
    for (Long l : data) {
      sum += l;
    }
    return sum;
  }

  public static void main(String[] args) {
    Random random = new Random();

    List<Long> data = random
            .longs(1_000, 1, 100)
            .boxed()
            .collect(Collectors.toList());

    ForkJoinPool pool = new ForkJoinPool();
    SumTask task = new SumTask(data);
    pool.invoke(task);

    System.out.println("Sum: " + pool.invoke(task));
  }
}