package com.sigsauer.epsiloan.lending.enums;

/**
 * ProductProperty is enum-class that contains properties for calculate loan interests
 *
 * @see ProlongationStrategy
 * @see TermsPeriod
 * @see AccrualStrategy
 * @see com.sigsauer.epsiloan.lending.entity.ProductProperties
 * @see com.sigsauer.epsiloan.lending.entity.PropertyConfigurer
 *
 * @author SigSauer
 * @version 1.0
 */
public enum ProductProperty {

    /** Basic loan amounts */
    AMOUNT,

    /** Basic loan terms  */
    TERMS,

    /** One-time loan commission */
    FEE,

    /** Basic loan interests (rate) */
    RATE,

    /** Penalty fare for loan overdue */
    PENALTY,

    /** Options and method of prolongation calculation */
    PROLONGATION_ORDER,

    /** Terms of calculating prolongation terms */
    PROLONGATION_PERIOD,

    /** Loan interests that during prolongation */
    PROLONGATION_RATE,

    /** Non-accrual loan period */
    GRACE_PERIOD;
}
