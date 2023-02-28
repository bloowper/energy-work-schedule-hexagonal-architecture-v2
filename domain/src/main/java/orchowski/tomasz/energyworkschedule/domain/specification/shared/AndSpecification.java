package orchowski.tomasz.energyworkschedule.domain.specification.shared;


import orchowski.tomasz.energyworkschedule.domain.exception.GenericSpecificationException;

public class AndSpecification<T> extends AbstractSpecification<T> {

    private AbstractSpecification<T> spec1;
    private AbstractSpecification<T> spec2;

    public AndSpecification(final AbstractSpecification<T> spec1, final AbstractSpecification<T> spec2) {
        this.spec1 = spec1;
        this.spec2 = spec2;
    }

    @Override
    public boolean isSatisfiedBy(final T t) {
        return spec1.isSatisfiedBy(t) && spec2.isSatisfiedBy(t);
    }

    @Override
    public void check(T t) throws GenericSpecificationException {
        spec1.check(t);
        spec2.check(t);
    }
}
