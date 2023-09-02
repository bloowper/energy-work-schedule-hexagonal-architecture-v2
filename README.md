# Purpose

The WorkSchedule is a microservice responsible for creating and managing work shifts for devices. These work shifts aim to optimize energy consumption and ensure the efficient functioning of devices. It also emits notifications at the beginning of each work shift to inform other components or systems.

# Inspiration

this project is based on the principles, concepts and solutions from [Designing hexagonal architecture wit java](https://www.packtpub.com/product/designing-hexagonal-architecture-with-java/9781801816489) book  
related github: https://github.com/PacktPublishing/Designing-Hexagonal-Architecture-with-Java 


## Deviations from the original
* JPMS is at this point used only in domain hexagon(got lots of problems with using JPMS)
