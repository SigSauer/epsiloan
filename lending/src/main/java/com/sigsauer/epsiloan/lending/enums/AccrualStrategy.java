package com.sigsauer.epsiloan.lending.enums;

import com.sigsauer.epsiloan.lending.service.implementation.ProductServiceImpl;

/**
 * AccrualStrategy is enum-class that contains options for calculating basic interests rate.
 *
 * @see com.sigsauer.epsiloan.lending.entity.ProductProperties;
 * @see ProductProperty#FEE;
 * @see ProductProperty#RATE;
 * @see ProductProperty#PENALTY;
 * @see ProductProperty#PROLONGATION_RATE;
 *
 * @author SigSauer
 * @version 1.0
 */
public enum AccrualStrategy {

    /** Default value. In this case, no calculation (doesn`t matter). */
    INF,

    /** Fixed price that is set initially (c.u.). */
    FIXED,

    /** The cost is calculated as a percentage of the initial loan amount. */
    PERCENTAGE_OF_BASIC,

    /** The cost is calculated as a percentage of the current repayment amount. */
    PERCENTAGE_OF_CURRENT,

    /** The cost is calculated as a percentage of the total repayment amount. */
    PERCENTAGE_OF_REPAYMENT;

}
