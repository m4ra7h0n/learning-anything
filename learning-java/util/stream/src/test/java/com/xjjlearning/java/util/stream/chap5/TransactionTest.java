package com.xjjlearning.java.util.stream.chap5;

import com.xjjlearning.java.util.stream.pojo.Trader;
import com.xjjlearning.java.util.stream.pojo.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.xjjlearning.java.util.stream.pojo.Transaction.TRANSACTIONS;

@SpringBootTest
public class TransactionTest {
    @Test
    public void transactionTest() {
        // 找出2011年发生的所有交易，并按交易额排序（从低到高）。
        List<Transaction> practice1 = TRANSACTIONS.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .collect(Collectors.toList());

        // 交易员都在哪些不同的城市工作过？
        List<String> practice2 = TRANSACTIONS.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());

        // 查找所有来自于剑桥的交易员，并按姓名排序。
        List<Trader> practice3 = TRANSACTIONS.stream()
                .map(Transaction::getTrader)
                .filter(t -> t.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());

        // 返回所有交易员的姓名字符串，按字母顺序排序。
        String practice4 = TRANSACTIONS.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .sorted()
                // .reduce("", (o1, o2) -> o1 + o2);
                .collect(Collectors.joining());

        // 有没有交易员是在米兰工作的？
        boolean practice5 = TRANSACTIONS.stream()
                .map(Transaction::getTrader)
                .anyMatch(t -> t.getCity().equals("Milan"));

        // 打印生活在剑桥的交易员的所有交易额。
        List<String> practice6 = TRANSACTIONS.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(t -> t.getTrader().getName() + ": " + t.getValue())
                .collect(Collectors.toList());

        // 所有交易中，最高的交易额是多少？
        Optional<Integer> practice7 = TRANSACTIONS.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);

        // 找到交易额最小的交易。
        Optional<Integer> practice8 = TRANSACTIONS.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
    }
}
