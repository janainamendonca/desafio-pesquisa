package fastprimenumber;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FastPrimeNumberTest {

    @Test
    public void isPrime1() {
        assertTrue(FastPrimeNumber.isPrime(2));
        assertTrue(FastPrimeNumber.isPrime(3));
        assertTrue(FastPrimeNumber.isPrime(5));
        assertTrue(FastPrimeNumber.isPrime(7));
        assertTrue(FastPrimeNumber.isPrime(11));
        assertTrue(FastPrimeNumber.isPrime(13));
        assertTrue(FastPrimeNumber.isPrime(17));
        assertTrue(FastPrimeNumber.isPrime(19));
        assertTrue(FastPrimeNumber.isPrime(23));
        assertTrue(FastPrimeNumber.isPrime(101));
        assertTrue(FastPrimeNumber.isPrime(1999));
        assertTrue(FastPrimeNumber.isPrime(198491317));
        assertTrue(FastPrimeNumber.isPrime(Integer.MAX_VALUE));
    }

    @Test
    public void isPrime2() {
        assertFalse(FastPrimeNumber.isPrime(4));
        assertFalse(FastPrimeNumber.isPrime(15));
        assertFalse(FastPrimeNumber.isPrime(21));
        assertFalse(FastPrimeNumber.isPrime(33));
        assertFalse(FastPrimeNumber.isPrime(225));
    }

}
