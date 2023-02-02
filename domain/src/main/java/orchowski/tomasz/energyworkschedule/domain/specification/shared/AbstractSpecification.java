package orchowski.tomasz.energyworkschedule.domain.specification.shared;


import orchowski.tomasz.energyworkschedule.domain.exception.GenericSpecificationException;

public abstract class AbstractSpecification<T> {

    public abstract boolean isSatisfiedBy(T t);

    public abstract void check(T t) throws GenericSpecificationException;

    public AbstractSpecification<T> and(final AbstractSpecification<T> specification) {
        return new AndSpecification<T>(this, specification);
    }
}
