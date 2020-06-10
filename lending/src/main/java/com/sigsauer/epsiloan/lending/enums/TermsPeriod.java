package com.sigsauer.epsiloan.lending.enums;

/**
 * TermPeriod is enum-class that contains units of terms period.
 *
 * @see com.sigsauer.epsiloan.lending.entity.ProductProperties;
 * @see ProductProperty#TERMS;
 * @see ProductProperty#PROLONGATION_PERIOD;
 * @see ProductProperty#GRACE_PERIOD;
 *
 * @author SigSauer
 * @version 1.0
 */
public enum TermsPeriod {

    /** Default value. In this case, no calculation (doesn`t matter). */
    INF,

    /** {@code value} is set in days. Calculation is performed every day */
    DAYS,

    /** {@code value} is set in weeks. Calculation is performed every week. (e.g WED JAN 1th - WEB JAN 8th etc.)*/
    WEEKS,

    /**
     * {@code value} is set in months. Calculation is performed every month (e.g JAN 7th - FEB 7th - MAR 7th etc.)
     *  In case of FEB and leap years: accrual began on JAN 30th - next time will calculate MAR 1th - MAR 30th)
     */
    MONTHS,

    /** {@code value} is set in years. Calculation is performed every year (e.g JAN 7th 2020 - JAN 7th 2021 etc.) */
    YEARS;

}
