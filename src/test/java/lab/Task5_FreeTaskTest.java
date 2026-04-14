package lab;

import net.jqwik.api.*;
import static org.assertj.core.api.Assertions.*;
import net.jqwik.api.Combinators;

/**
 * ═══════════════════════════════════════════════════════════════════
 *  TASK 5 – Free Design Task  (30 min)
 * ═══════════════════════════════════════════════════════════════════
 *
 *  Choose ONE method from FreeTask.java:
 *
 *    Option A – caesarEncrypt(String text, int shift)
 *    Option B – average(List<Double> values)
 *    Option C – fizzBuzz(int n)
 *    Option D – compress(String s)
 *
 *  STEP 1: Write which option you chose and WHY in the comment below.
 *  STEP 2: List at least THREE properties you identified (in comments).
 *  STEP 3: Implement those properties as jqwik tests below.
 *
 *  Grading criteria:
 *    [1 pt] Correct option declared and reasoning given.
 *    [2 pt] At least 3 meaningful, distinct properties listed.
 *    [3 pt] All listed properties implemented as runnable @Property tests.
 *    [1 pt] Tests pass with the correct implementation.
 *    [1 pt] Data generation is purposeful (not just @ForAll String).
 *    [1 pt] At least one property uses a @Provide method or Combinators.
 *
 * ────────────────────────────────────────────────────────────────────
 *  CHOSEN OPTION:
 *  C
 *
 *  REASON FOR CHOICE:
 * I chose fizzBuzz because the rules are simple and split into clear cases.
 *
 *  PROPERTIES IDENTIFIED:
 *  1. If n is divisible by both 3 and 5, the result is "FizzBuzz".
 *  2. If n is divisible by 3 but not 5, the result is "Fizz".
 *  3. If n is divisible by 5 but not 3, the result is "Buzz".
 *  4. If n is divisible by neither 3 nor 5, the result is String.valueOf(n).
 *
 * ────────────────────────────────────────────────────────────────────
 */
public class Task5_FreeTaskTest {

    @Provide
    Arbitrary<Integer> multiplesOf15() {
        return Arbitraries.integers()
                .between(-10_000, 10_000)
                .map(k -> k * 15);
    }

    @Provide
    Arbitrary<Integer> multiplesOf3Not5() {
        return Combinators.combine(
                Arbitraries.integers().between(-10_000, 10_000),
                Arbitraries.of(3, 6, 9, 12)
        ).as((k, r) -> k * 15 + r);
    }

    @Provide
    Arbitrary<Integer> multiplesOf5Not3() {
        return Combinators.combine(
                Arbitraries.integers().between(-10_000, 10_000),
                Arbitraries.of(5, 10)
        ).as((k, r) -> k * 15 + r);
    }

    @Provide
    Arbitrary<Integer> neither3Nor5() {
        return Combinators.combine(
                Arbitraries.integers().between(-10_000, 10_000),
                Arbitraries.of(1, 2, 4, 7, 8, 11, 13, 14)
        ).as((k, r) -> k * 15 + r);
    }

    // ── PROPERTY 1 ───────────────────────────────────────────────────
    @Property
    void property1(@ForAll("multiplesOf15") int n) {
        assertThat(FreeTask.fizzBuzz(n)).isEqualTo("FizzBuzz");
    }

    // ── PROPERTY 2 ───────────────────────────────────────────────────
    @Property
    void property2(@ForAll("multiplesOf3Not5") int n) {
        assertThat(FreeTask.fizzBuzz(n)).isEqualTo("Fizz");
    }

    // ── PROPERTY 3 ───────────────────────────────────────────────────
    @Property
    void property3(@ForAll("multiplesOf5Not3") int n) {
        assertThat(FreeTask.fizzBuzz(n)).isEqualTo("Buzz");
    }

    // Add more @Property methods if you identified additional properties.
    @Property
    void property4(@ForAll("neither3Nor5") int n) {
        assertThat(FreeTask.fizzBuzz(n)).isEqualTo(String.valueOf(n));
    }
}
