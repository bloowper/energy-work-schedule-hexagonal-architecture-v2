package orchowski.tomasz.energyworkschedule.domain.specification;

import lombok.AllArgsConstructor;
import orchowski.tomasz.energyworkschedule.domain.entity.Policy;
import orchowski.tomasz.energyworkschedule.domain.exception.GenericSpecificationException;
import orchowski.tomasz.energyworkschedule.domain.specification.shared.AbstractSpecification;

import java.time.Instant;
import java.util.SortedSet;
import java.util.stream.Stream;

@AllArgsConstructor(staticName = "basedOnOldPolicies")
public class NewPolicySpecification extends AbstractSpecification<Policy> {
    private final SortedSet<Policy> oldPolicies;

    @Override
    public boolean isSatisfiedBy(Policy policy) {
        boolean containSamePolicy = oldPolicies.contains(policy); // from the point of view of data structure, it is unnecessary while currently it does not want to have such a business flow, just want throw exception
        boolean containPolicyWithSamePriorityInRange = getPoliciesInOpenRange(policy.getEffectiveDate().getStart(), policy.getEffectiveDate().getEnd())
                .anyMatch(p -> p.getPriority().equals(policy.getPriority()));
        return !containSamePolicy && !containPolicyWithSamePriorityInRange;
    }

    @Override
    public void check(Policy policy) throws GenericSpecificationException {
        if (!isSatisfiedBy(policy)) {
            throw new GenericSpecificationException("New policy must have a unique id and not overlap another policy with the same priority");
        }
    }

    private Stream<Policy> getPoliciesInOpenRange(Instant startDate, Instant endDate) {
        return oldPolicies.stream()
                .filter(policy -> policy.getEffectiveDate().getStart().compareTo(endDate) > 0 || policy.getEffectiveDate().getEnd().compareTo(startDate) > 0);
    }
}
