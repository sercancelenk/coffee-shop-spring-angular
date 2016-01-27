package sr.api.core.util;

import java.math.BigDecimal;

/**
 * Created by byzas on 15/01/16.
 */
public final class NumberUtility {
    private static final int ROUND_DECIMAL_PLACE = 2;

    public static BigDecimal toBigDecimal(double input) {
        BigDecimal bd = new BigDecimal(input);
        bd = bd.setScale(ROUND_DECIMAL_PLACE,BigDecimal.ROUND_FLOOR);
        return bd;
    }

    public static double round(double input) {
        return (toBigDecimal(input).doubleValue());
    }
}
