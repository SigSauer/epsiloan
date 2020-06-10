package com.sigsauer.epsiloan.lending.enums;

/**
 * ProlongationStrategy is enum-class that contains options for calculating the cost of prolongation.
 *
 * @see com.sigsauer.epsiloan.lending.entity.ProductProperties;
 * @see ProductProperty#PROLONGATION_ORDER;
 *
 * @author SigSauer
 * @version 1.0
 */
public enum ProlongationStrategy {

    /** Default value. In this case, no calculation (doesn`t matter). */
    INF,

    /** Fixed price that is set initially (c.u.). */
    FIXED,

    /** Depend on loan amount. In this case, the cost is calculated as a percentage of the loan amount. */
    PERCENTAGE_OF_AMOUNT,

    /** Depend on accrual interests and penalties. In this case, the cost is calculated as a percentage of accruals. */
    PERCENTAGE_OF_ACCRUALS,

    /** Depend on prolongation period. In this case, the cost is calculated per period unit from the cost of full period.    */
    FOR_EACH_PERIOD_UNIT;

}
