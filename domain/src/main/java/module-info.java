module domain {
    requires static lombok;
    requires org.slf4j;
    exports orchowski.tomasz.energyworkschedule.domain.exception;
    exports orchowski.tomasz.energyworkschedule.domain.entity;
    exports orchowski.tomasz.energyworkschedule.domain.value;
    exports orchowski.tomasz.energyworkschedule.domain.event;
}

// [Q] i dont like this that in this type of arch every is public :/
// how to export only part of package, rly have i to do something like public/private package?
