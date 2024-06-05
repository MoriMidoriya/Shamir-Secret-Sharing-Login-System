import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;

public final class javversionfinal {

    public static SecretShare[] split(final BigInteger secret, int needed, int available, BigInteger prime, Random random) {
        System.out.println("Prime Number: " + prime);

        final BigInteger[] coeff = new BigInteger[needed];
        coeff[0] = secret;
        for (int i = 1; i < needed; i++) {
            BigInteger r;
            while (true) {
                r = new BigInteger(prime.bitLength(), random);
                if (r.compareTo(BigInteger.ZERO) > 0 && r.compareTo(prime) < 0) {
                    break;
                }
            }
            coeff[i] = r;
        }

        final SecretShare[] shares = new SecretShare[available];
        for (int x = 1; x <= available; x++) {
            BigInteger accum = secret;

            for (int exp = 1; exp < needed; exp++) {
                accum = accum.add(coeff[exp].multiply(BigInteger.valueOf(x).pow(exp).mod(prime))).mod(prime);
            }
            shares[x - 1] = new SecretShare(x, accum);
            System.out.println("Share " + shares[x - 1]);
        }

        return shares;
    }

    public static BigInteger combine(final SecretShare[] shares, final BigInteger prime) {
        BigInteger accum = BigInteger.ZERO;

        for (int formula = 0; formula < shares.length; formula++) {
            BigInteger numerator = BigInteger.ONE;
            BigInteger denominator = BigInteger.ONE;

            for (int count = 0; count < shares.length; count++) {
                if (formula == count)
                    continue;

                int startPosition = shares[formula].getNumber();
                int nextPosition = shares[count].getNumber();

                numerator = numerator.multiply(BigInteger.valueOf(nextPosition).negate()).mod(prime);
                denominator = denominator.multiply(BigInteger.valueOf(startPosition - nextPosition)).mod(prime);
            }
            BigInteger value = shares[formula].getShare();
            BigInteger tmp = value.multiply(numerator).multiply(modInverse(denominator, prime));
            accum = prime.add(accum).add(tmp).mod(prime);
        }

        System.out.println("The secret is: " + accum + "\n");

        return accum;
    }

    private static BigInteger[] gcdD(BigInteger a, BigInteger b) {
        if (b.compareTo(BigInteger.ZERO) == 0)
            return new BigInteger[]{a, BigInteger.ONE, BigInteger.ZERO};
        else {
            BigInteger n = a.divide(b);
            BigInteger c = a.mod(b);
            BigInteger[] r = gcdD(b, c);
            return new BigInteger[]{r[0], r[2], r[1].subtract(r[2].multiply(n))};
        }
    }

    private static BigInteger modInverse(BigInteger k, BigInteger prime) {
        k = k.mod(prime);
        BigInteger r = (k.compareTo(BigInteger.ZERO) == -1) ? (gcdD(prime, k.negate())[2]).negate() : gcdD(prime, k)[2];
        return prime.add(r).mod(prime);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the secret: ");
        BigInteger secret = scanner.nextBigInteger();

        System.out.print("Enter the number of shares needed: ");
        int neededShares = scanner.nextInt();

        System.out.print("Enter the total number of shares: ");
        int totalShares = scanner.nextInt();

        final int CERTAINTY = 256;
        final SecureRandom random = new SecureRandom();

        // Generate a random prime number longer than the secret
        final BigInteger prime = new BigInteger(secret.bitLength() + 1, CERTAINTY, random);

        SecretShare[] shares = split(secret, neededShares, totalShares, prime, random);

        // Demonstrate combining shares to reconstruct the secret
        System.out.println("Enter the number of shares to combine:");
        int sharesToCombine = scanner.nextInt();

        SecretShare[] sharesToViewSecret = new SecretShare[sharesToCombine];
        for (int i = 0; i < sharesToCombine; i++) {
            System.out.print("Enter share " + (i + 1) + " position: ");
            int position = scanner.nextInt();
            sharesToViewSecret[i] = shares[position - 1];
        }

        BigInteger result = combine(sharesToViewSecret, prime);

        scanner.close();
    }
}
