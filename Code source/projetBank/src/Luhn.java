public class Luhn {


	public static boolean isValidNumber(String s) {
        return doLuhn(s, false) % 10 == 0;
    }

    public static String generateDigit(String s) {
        int digit = (doLuhn(s, true)*9) % 10;
        return "" + digit;
    }

    public static int doLuhn(String s, boolean evenPosition) {
        int sum = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(s.substring(i, i + 1));
            if (evenPosition) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            evenPosition = !evenPosition;
        }

        return sum;
    }
}