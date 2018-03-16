package com.test.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {

    @Test
    public void contextLoads() {
    }

    public static void main(String[] args) {
        RedisApplicationTests redisApplicationTests = new RedisApplicationTests();
        MathOperation addition = (int a, int b) -> a + b;
        MathOperation subtraction = (a, b) -> a - b;
        System.out.println("10 + 5 = " + redisApplicationTests.operate(10, 5, addition));
        System.out.println("10 - 5 = " + redisApplicationTests.operate(10, 5, subtraction));
    }

    interface MathOperation {
        int operation(int a, int b);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

}
