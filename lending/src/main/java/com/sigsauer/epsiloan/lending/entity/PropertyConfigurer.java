package com.sigsauer.epsiloan.lending.entity;

import com.sigsauer.epsiloan.lending.enums.*;
import com.sigsauer.epsiloan.lending.exceptions.InvalidProductPropertyException;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.sigsauer.epsiloan.lending.enums.ProductProperty.*;

/**
 * ProductConfigurer is an interface for managing of setting property values
 * @see Product
 * @see ProductProperties
 * @see AccrualStrategy
 * @see ProductProperty
 * @see ProlongationStrategy
 * @see TermsPeriod
 *
 * @author SigSauer
 * @version 1.0
 */
public interface PropertyConfigurer {

    /**
     * Pair key-value, that contain Property and flag:
     * {@code false} if Property has default values;
     * {@code true} in other cases
     */
    Map<ProductProperty, Boolean> setupProperties = Stream.of(values())
            .collect(Collectors.toMap(p -> p, p -> false));

    /**
     * Change flag value to {@code false}.
     * @param property that has been dropped
     */
    default void propertyDropped(ProductProperty property) {
        setupProperties.replace(property, false);
    }

    /**
     * Change flag value to {@code true}.
     * @param property that has been set
     */
    default void propertySet(ProductProperty property) {
        setupProperties.replace(property, true);
    }

    /**
     * Method for setting up of property values
     * @param propertyName is name of property that will be set up
     * @param values are values that will be set up
     *
     * @throws InvalidProductPropertyException in cases:
     * if the property, that is being configured, does not exist;
     * if the values are invalid or not relevant;
     * if property set up method of  was not defined.
     *
     * @deprecated :is not safe to use and will be removed in future.
     */
    @Deprecated
    default void configure(String propertyName, String... values) {
        try {
            ProductProperty property = ProductProperty.valueOf(propertyName);
            try {
                switch (property) {
                    case AMOUNT:
                        setAmount(Double.valueOf(values[0]),
                                Double.valueOf(values[1]));
                        break;
                    case TERMS:
                        setTerms(TermsPeriod.valueOf(values[0]),
                                Integer.valueOf(values[1]), Integer.valueOf(values[2]));
                        break;
                    case FEE:
                        setFee(AccrualStrategy.valueOf(values[0]),
                                Double.valueOf(values[1]));
                        break;
                    case RATE:
                        setRate(AccrualStrategy.valueOf(values[0]),
                                Double.valueOf(values[1]));
                        break;
                    case PENALTY:
                        setPenalty(AccrualStrategy.valueOf(values[0]),
                                Double.valueOf(values[1]));
                        break;
                    case PROLONGATION_ORDER:
                        setProlongationOrder(ProlongationStrategy.valueOf(values[0]),
                                (Double.valueOf(values[1])));
                        break;
                    case PROLONGATION_PERIOD:
                        setProlongationPeriod(TermsPeriod.valueOf(values[0]),
                                Integer.valueOf(values[1]));
                        break;
                    case PROLONGATION_RATE:
                        setProlongationRate(AccrualStrategy.valueOf(values[0]),
                                Double.valueOf(values[1]));
                        break;
                    case GRACE_PERIOD:
                        setGracePeriod(TermsPeriod.valueOf(values[0]),
                                Integer.valueOf(values[1]));
                        break;
                    default:
                        throw new InvalidProductPropertyException(property);
                }
            } catch (ClassCastException | NullPointerException e) {
                throw new InvalidProductPropertyException(property, values);
            }
        } catch (EnumConstantNotPresentException e) {
            throw new InvalidProductPropertyException(propertyName);
        }
    }

    default boolean hasEmptyProperties() {
        return setupProperties.containsValue(false);
    }

    default Object[] getProperty(ProductProperty property) {
        return new Object[] {};
    }

    /**
     * Method for mapping values, that are being set up
     * @param propertyName is name of property that will be set up
     * @param values are values that will be set up
     *
     * @deprecated :unimplemented and will be removed in future.
     */
    default void configure(String propertyName, Map<String, String> values) {
    }

    /**
     * Method validates and sets amount extreme values
     * @param min minimum amount
     * @param max maximum amount
     *
     * @throws InvalidProductPropertyException when consists are not positive value
     */
    default void setAmount(Double min, Double max) {
        propertySet(AMOUNT);
    }

    default void dropAmount() {
        setAmount(0.,0.);
        propertyDropped(AMOUNT);
    }

    default boolean hasAmount() {
        return setupProperties.get(AMOUNT);
    }


    /**
     * Method validates and sets terms extreme values and time unit of terms period:
     * @param period variant of time unit from {@link TermsPeriod};
     * @param min minimum term;
     * @param max maximum term;
     *
     * @throws InvalidProductPropertyException when consists not positive value
     */
    default void setTerms(TermsPeriod period, Integer min, Integer max) {
        propertySet(TERMS);
    }

    default void dropTerms() {
        setTerms(TermsPeriod.INF, 0, 0);
        propertyDropped(TERMS);
    }

    default boolean hasTerms() {
        return setupProperties.get(TERMS);
    }

    /**
     * Method validates and sets fee value
     * @param strategy variant of accrual strategy from {@link AccrualStrategy}
     * @param fee value of loan commission
     *
     * @throws InvalidProductPropertyException when value is not positive or uncountable.
     */
    default void setFee(AccrualStrategy strategy, Double fee) {
        propertySet(FEE);
    }

    default void dropFee() {
        setFee(AccrualStrategy.INF, 0.);
        propertyDropped(FEE);
    }

    default boolean hasFee() {
        return setupProperties.get(FEE);
    }


    /**
     * Method validates and sets loan interests rate
     * @param strategy variant of accrual strategy from {@link AccrualStrategy}
     * @param rate value of loan interests rate
     *
     * @throws InvalidProductPropertyException value is not positive or uncountable.
     */
    default void setRate(AccrualStrategy strategy, Double rate) {
        propertySet(RATE);
    }

    default void dropRate() {
        setRate(AccrualStrategy.INF, 0.);
        propertyDropped(RATE);
    }
    default boolean hasRate() {
        return setupProperties.get(RATE);
    }


    /**
     * Method validates and sets loan penalty
     * @param strategy variant of accrual strategy from {@link AccrualStrategy}
     * @param penalty value of loan interests rate
     *
     * @throws InvalidProductPropertyException when value is not positive or uncountable.
     */
    default void setPenalty(AccrualStrategy strategy, Double penalty) {
        propertySet(PENALTY);
    }

    default void dropPenalty() {
        setPenalty(AccrualStrategy.INF, 0.);
        propertyDropped(PENALTY);
    }

    default boolean hasPenalty() {
        return setupProperties.get(PENALTY);
    }

    /**
     * Method validates and sets order and strategy of prolongation
     * @param strategy type of conditions for activate prolongation from {@link ProlongationStrategy}
     * @param value value, which perform different role depending on {@code strategy}
     *
     * @throws InvalidProductPropertyException when value is not positive or uncountable.
     */
    default void setProlongationOrder(ProlongationStrategy strategy, Double value) {
        propertySet(PROLONGATION_ORDER);
    }

    default void dropProlongationOrder() {
        setProlongationOrder(ProlongationStrategy.INF, 0.);
        propertyDropped(PROLONGATION_ORDER);
    }

    default boolean hasProlongationOrder() {
        return setupProperties.get(PROLONGATION_ORDER);
    }

    /**
     * Method validates and sets terms and time unit of terms period on prolongation:
     * @param period variant of time unit from {@link TermsPeriod};
     * @param value value of terms
    *
     * @throws InvalidProductPropertyException when value is not positive.
     */
    default void setProlongationPeriod(TermsPeriod period, Integer value) {
        propertySet(PROLONGATION_PERIOD);
    }

    default void dropProlongationPeriod() {
        setProlongationPeriod(TermsPeriod.INF, 0);
        propertyDropped(PROLONGATION_PERIOD);
    }

    default boolean hasProlongationPeriod() {
        return setupProperties.get(PROLONGATION_PERIOD);
    }

    /**
     * Method validates and sets loan interests rate on prolongation
     * @param strategy variant of accrual strategy from {@link AccrualStrategy}
     * @param rate value of interests rate
     *
     * @throws InvalidProductPropertyException value is not positive or uncountable
     */
    default void setProlongationRate(AccrualStrategy strategy, Double rate) {
        propertySet(PROLONGATION_RATE);
    }

    default void dropProlongationRate() {
        setProlongationRate(AccrualStrategy.INF, 0.);
        propertyDropped(PROLONGATION_RATE);
    }

    default boolean hasProlongationRate() {
        return setupProperties.get(PROLONGATION_RATE);
    }

    /**
     * Method validates and sets terms and time unit of grace period:
     * @param period variant of time unit from {@link TermsPeriod};
     * @param value value of period
     *
     * @throws InvalidProductPropertyException when value is not positive<
     */
    default void setGracePeriod(TermsPeriod period, Integer value) {
        propertySet(GRACE_PERIOD);
    }

    default void dropGracePeriod() {
        setGracePeriod(TermsPeriod.INF, 0);
        propertyDropped(GRACE_PERIOD);
    }

    default boolean hasGracePeriod() {
        return setupProperties.get(GRACE_PERIOD);
    }


    /**
     * Method drops all property values
     */
    default void dropAll() {
        dropAmount();
        dropTerms();
        dropFee();
        dropRate();
        dropPenalty();
        dropProlongationOrder();
        dropProlongationRate();
        dropProlongationPeriod();
        dropGracePeriod();
    }

    /**
     * Method for setting up combine calculating loan interests rate
     *
     * <b>Future improvements</b>
     */
    default void setComplexRate() {
    }

    /**
     * Method for setting up combine calculating loan penalty
     *
     * <b>Future improvements</b>
     */
    default void setComplexPenalty() {
    }

}
