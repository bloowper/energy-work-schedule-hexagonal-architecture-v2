package orchowski.tomasz.energyworkschedule.framework.adapter.output.mongodb.mapper;

import orchowski.tomasz.energyworkschedule.domain.entity.Device;
import orchowski.tomasz.energyworkschedule.framework.adapter.output.mongodb.data.DeviceData;
import org.mapstruct.Mapper;

@Mapper
interface MongoDbMapper {
    DeviceData toData(Device device);

    Device toDomain(DeviceData deviceData);

}
