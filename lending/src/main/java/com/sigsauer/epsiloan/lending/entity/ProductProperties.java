package com.sigsauer.epsiloan.lending.entity;

import com.sigsauer.epsiloan.lending.enums.AccrualStrategy;
import com.sigsauer.epsiloan.lending.enums.ProductProperty;
import com.sigsauer.epsiloan.lending.enums.ProlongationStrategy;
import com.sigsauer.epsiloan.lending.enums.TermsPeriod;
import com.sigsauer.epsiloan.lending.exceptions.InvalidProductPropertyException;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.Entity;

import static com.sigsauer.epsiloan.lending.enums.ProductProperty.*;

/**
 * ProductProperties is entity-class which contain rules for calculating the interests rate
 *
 * @see Product
 * @see PropertyConfigurer
 * @see ProductProperty
 *
 * @author SigSauer
 * @version 1.0
 */
@Data
@Entity
public class ProductProperties implements PropertyConfigurer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private double minAmount;
    private double maxAmount;

    private TermsPeriod termPeriod = TermsPeriod.INF;
    private int minTerm;
    private int maxTerm;

    private AccrualStrategy feeStrategy = AccrualStrategy.INF;
    private double feeValue;

    private AccrualStrategy rateStrategy = AccrualStrategy.INF;
    private double rateValue;

    private AccrualStrategy penaltyStrategy = AccrualStrategy.INF;
    private double penaltyValue;

    private ProlongationStrategy prolongationStrategy = ProlongationStrategy.INF;
    private double prolongationValue;

    private TermsPeriod prolongationTermPeriod = TermsPeriod.INF;
    private int prolongationTermValue;

    private AccrualStrategy prolongationAccrualStrategy = AccrualStrategy.INF;
    private double prolongationAccrualValue;

    private TermsPeriod gracePeriod = TermsPeriod.INF;
    private int graceValue;


    public ProductProperties() {

    }

//    public ProductProperties(double minAmount, double maxAmount, TermsPeriod termPeriod, int minTerm, int maxTerm,
//                  AccrualStrategy feeStrategy, double feeValue, AccrualStrategy rateStrategy, double rateValue,
//                  AccrualStrategy penaltyStrategy, double penaltyValue, ProlongationStrategy prolongationStrategy,
//                  double prolongationValue, TermsPeriod prolongationTermPeriod, int prolongationTermValue,
//                  AccrualStrategy prolongationAccrualStrategy, double prolongationAccrualValue,
//                  TermsPeriod gracePeriod, int graceValue) {
//        this.minAmount = minAmount;
//        this.maxAmount = maxAmount;
//        this.termPeriod = termPeriod;
//        this.minTerm = minTerm;
//        this.maxTerm = maxTerm;
//        this.feeStrategy = feeStrategy;
//        this.feeValue = feeValue;
//        this.rateStrategy = rateStrategy;
//        this.rateValue = rateValue;
//        this.penaltyStrategy = penaltyStrategy;
//        this.penaltyValue = penaltyValue;
//        this.prolongationStrategy = prolongationStrategy;
//        this.prolongationValue = prolongationValue;
//        this.prolongationTermPeriod = prolongationTermPeriod;
//        this.prolongationTermValue = prolongationTermValue;
//        this.prolongationAccrualStrategy = prolongationAccrualStrategy;
//        this.prolongationAccrualValue = prolongationAccrualValue;
//        this.gracePeriod = gracePeriod;
//        this.graceValue = graceValue;
//    }

    @Override
    public void setAmount(Double min, Double max) {
            if (min < 0 || min.isInfinite() || min.isNaN())
                throw new InvalidProductPropertyException(AMOUNT, new String[]{"min"});
            if (max < 0 || max.isInfinite() || max.isNaN())
                throw new InvalidProductPropertyException(AMOUNT, new String[]{"max"});
            this.minAmount = min;
            this.maxAmount = max;
            propertySet(AMOUNT);
    }

    @Override
    public void setTerms(TermsPeriod period, Integer min, Integer max) {
            if (min < 0 || max < 0)
                throw new InvalidProductPropertyException(TERMS, new String[]{"min", "max"});
            this.termPeriod = period;
            this.minTerm = min;
            this.maxTerm = max;
        propertySet(TERMS);
    }

    @Override
    public void setFee(AccrualStrategy strategy, Double fee) {
            if (fee < 0 || fee.isInfinite() || fee.isNaN())
                throw new InvalidProductPropertyException(FEE, new String[]{"fee"});
            this.feeStrategy = strategy;
            this.feeValue = fee;
        propertySet(FEE);
    }

    @Override
    public void setRate(AccrualStrategy strategy, Double rate) {
            if (rate < 0 || rate.isInfinite() || rate.isNaN())
                throw new InvalidProductPropertyException(RATE, new String[]{"rate"});
            this.rateStrategy = strategy;
            this.rateValue = rate;
        propertySet(RATE);
    }

    @Override
    public void setPenalty(AccrualStrategy strategy, Double penalty) {
            if (penalty < 0 || penalty.isInfinite() || penalty.isNaN())
                throw new InvalidProductPropertyException(PENALTY, new String[]{"penalty"});
            this.penaltyStrategy = strategy;
            this.penaltyValue = penalty;
        propertySet(PENALTY);
    }

    @Override
    public void setProlongationOrder(ProlongationStrategy strategy, Double value) {
        if (value < 0 || value.isInfinite() || value.isNaN())
            throw new InvalidProductPropertyException(PROLONGATION_ORDER, new String[]{"value"});
        this.prolongationStrategy = strategy;
        this.prolongationValue = value;
        propertySet(PROLONGATION_ORDER);
    }

    @Override
    public void setProlongationPeriod(TermsPeriod period, Integer value) {
        if(value < 0)
            throw new InvalidProductPropertyException(PROLONGATION_PERIOD, new String[]{"value"});
       this.prolongationTermPeriod = period;
       this.prolongationTermValue = value;
        propertySet(PROLONGATION_PERIOD);
    }

    @Override
    public void setProlongationRate(AccrualStrategy strategy, Double rate) {
        if (rate < 0 || rate.isInfinite() || rate.isNaN())
            throw new InvalidProductPropertyException(PROLONGATION_RATE, new String[]{"rate"});
        this.prolongationAccrualStrategy = strategy;
        this.prolongationAccrualValue = rate;
        propertySet(PROLONGATION_RATE);
    }

    @Override
    public void setGracePeriod(TermsPeriod period, Integer value) {
        if(value < 0)
            throw new InvalidProductPropertyException(GRACE_PERIOD, new String[]{"value"});
        this.gracePeriod = period;
        this.graceValue = value;
        propertySet(GRACE_PERIOD);
    }

    public boolean hasAmount() {
        return maxAmount > 0;
    }

    @Override
    public boolean hasTerms() {
        return termPeriod != TermsPeriod.INF && maxTerm > 0;
    }

    @Override
    public boolean hasFee() {
        return feeStrategy != AccrualStrategy.INF && feeValue > 0;
    }

    @Override
    public boolean hasRate() {
        return rateStrategy != AccrualStrategy.INF && rateValue > 0;
    }

    @Override
    public boolean hasPenalty() {
        return penaltyStrategy != AccrualStrategy.INF && penaltyValue > 0;
    }

    @Override
    public boolean hasProlongationOrder() {
        return prolongationStrategy != ProlongationStrategy.INF && prolongationValue > 0;
    }

    @Override
    public boolean hasProlongationPeriod() {
        return prolongationTermPeriod != TermsPeriod.INF && prolongationTermValue > 0;
    }

    @Override
    public boolean hasProlongationRate() {
        return prolongationAccrualStrategy != AccrualStrategy.INF && prolongationAccrualValue > 0;
    }

    @Override
    public boolean hasGracePeriod() {
        return gracePeriod != TermsPeriod.INF && graceValue > 0;
    }

    @Override
    public Object[] getProperty(ProductProperty property) {
        switch (property) {
            case AMOUNT: return new Object[] { minAmount, maxAmount};
            case TERMS: return new Object[] { termPeriod, minTerm, maxTerm};
            case FEE: return new Object[] { feeStrategy, feeValue};
            case RATE: return new Object[] { rateStrategy, rateValue};
            case PENALTY: return new Object[] { penaltyStrategy, penaltyValue};
            case PROLONGATION_ORDER: return new Object[] { prolongationStrategy, prolongationValue};
            case PROLONGATION_RATE: return new Object[] { prolongationAccrualStrategy, prolongationAccrualValue};
            case PROLONGATION_PERIOD: return new Object[] { prolongationTermPeriod, prolongationTermValue};
            case GRACE_PERIOD: return new Object[] { gracePeriod, graceValue};
            default: throw new InvalidProductPropertyException(property);
        }
    }
}
