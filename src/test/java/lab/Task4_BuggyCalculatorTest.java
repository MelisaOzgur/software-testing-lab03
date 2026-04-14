package lab;

import net.jqwik.api.*;
import net.jqwik.api.constraints.*;
import static org.assertj.core.api.Assertions.*;

/**
 * ═══════════════════════════════════════════════════════════════════
 *  TASK 4 – Find the Bugs in BuggyCalculator  (35 min)
 * ═══════════════════════════════════════════════════════════════════
 *
 *  BuggyCalculator contains INTENTIONAL bugs in three methods:
 *    - abs(int n)
 *    - max(int a, int b)
 *    - isPrime(int n)
 *
 *  YOUR TASKS:
 *  1. Write property-based tests that DETECT each bug.
 *     A good test will FAIL with the current (buggy) implementation
 *     and PASS once the bug is fixed.
 *
 *  2. For each method, describe the bug you found in the comment
 *     provided (after running the test and seeing the counterexample).
 *
 *  3. (Bonus) Also test clamp() and find out whether it has a bug.
 *
 *  RULES:
 *  - Do NOT look at BuggyCalculator.java source until AFTER your
 *    property fails and jqwik gives you a counterexample.
 *  - Do NOT fix the implementation — only write tests.
 */
public class Task4_BuggyCalculatorTest {

    // ── abs() ────────────────────────────────────────────────────────
    //
    // Mathematical properties of absolute value:
    //   - abs(n) >= 0  for all n
    //   - abs(n) == n  for n >= 0
    //   - abs(n) == -n for n < 0
    //   - abs(abs(n)) == abs(n)  (idempotent)
    //
    // Write at least TWO properties. One of them must catch the bug.
    //
    // BUG FOUND (fill in after jqwik reports a counterexample):
    // abs() fails for Integer.MIN_VALUE.
    // For the counterexample n = -2147483648, the result is still negative.
    // So abs(n) is not always >= 0.
    @Property
    void absIsNeverNegative(@ForAll int n) {
        assertThat(BuggyCalculator.abs(n)).isGreaterThanOrEqualTo(0);

    }

    @Property
    void absProperty2(@ForAll int n) {
        // TODO: write a second property that catches the actual bug
        Assume.that(n < 0 && n != Integer.MIN_VALUE);
        assertThat(BuggyCalculator.abs(n)).isEqualTo(-n);
    }

    // ── max() ────────────────────────────────────────────────────────
    //
    // Mathematical properties of max:
    //   - max(a, b) >= a
    //   - max(a, b) >= b
    //   - max(a, b) == a  OR  max(a, b) == b  (result is one of the inputs)
    //   - max(a, b) == max(b, a)  (commutative)
    //
    // Write at least TWO properties. At least one must catch the bug.
    //
    // BUG FOUND (fill in after jqwik reports a counterexample):
    // max() does not always return the larger input.
    // A failing case shows that it returned 0 even though one input was 1.
    // So the result can be smaller than one of the arguments.
    @Property
    void maxIsAtLeastBothInputs(
            @ForAll int a,
            @ForAll int b) {
        // TODO
        int result = BuggyCalculator.max(a, b);
        assertThat(result).isGreaterThanOrEqualTo(a);
        assertThat(result).isGreaterThanOrEqualTo(b);
    }

    @Property
    void maxProperty2(@ForAll int a, @ForAll int b) {
        // TODO
        int result = BuggyCalculator.max(a, b);
        assertThat(result == a || result == b).isTrue();
    }

    // ── isPrime() ────────────────────────────────────────────────────
    //
    // Properties of prime numbers:
    //   - All primes are > 1
    //   - 2 is prime
    //   - No even number > 2 is prime
    //   - A prime p has no divisors other than 1 and itself
    //
    // Write at least TWO properties. At least one must catch the bug.
    //
    // BUG FOUND (fill in after jqwik reports a counterexample):
    // isPrime() incorrectly returns false for 2.
    // But 2 is a prime number, so the implementation misclassifies this edge case.
    @Property
    void twoIsPrime() {
        // TODO: this one does not need any @ForAll parameter —
        //       just directly assert that isPrime(2) is true.
        assertThat(BuggyCalculator.isPrime(2)).isTrue();
    }

    @Property
    void isPrimeProperty2(@ForAll @IntRange(min = 2, max = 200) int n) {
        // TODO
        Assume.that(BuggyCalculator.isPrime(n));
        for (int divisor = 2; divisor < n; divisor++) {
            assertThat(n % divisor).isNotEqualTo(0);
        }
    }

    @Property
    void noEvenNumberGreaterThanTwoIsPrime(
            @ForAll @IntRange(min = 2, max = 500) int k) {

        int even = 2 * k;
        assertThat(BuggyCalculator.isPrime(even)).isFalse();
    }

    // ── BONUS: clamp() ───────────────────────────────────────────────
    //
    // clamp(value, min, max) must satisfy:
    //   - result >= min
    //   - result <= max
    //   - if min <= value <= max, result == value
    //   - if value < min, result == min
    //   - if value > max, result == max
    //
    // Does clamp() have a bug? Write properties to find out.
    // If it does, describe the bug below. If not, state that it is correct.
    //
    // RESULT:
    // No bug was detected by this property.
    // clamp() stayed within the given bounds for the tested inputs.
    @Property
    void clampStaysWithinBounds(
            @ForAll int value,
            @ForAll @IntRange(min = -1000, max = 0)   int min,
            @ForAll @IntRange(min = 0,    max = 1000) int max) {

        Assume.that(min <= max);

        // TODO
        int result = BuggyCalculator.clamp(value, min, max);
        assertThat(result).isGreaterThanOrEqualTo(min);
        assertThat(result).isLessThanOrEqualTo(max);
    }
}
