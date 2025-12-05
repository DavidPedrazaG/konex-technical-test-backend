package konex_technical_test_backend.infrastructure.adapter.in.rest.mapper;

import org.springframework.stereotype.Component;

import konex_technical_test_backend.application.dto.MedicineDTO;
import konex_technical_test_backend.domain.model.Medicine;
import konex_technical_test_backend.infrastructure.adapter.in.rest.dto.response.MedicineResponse;

@Component
public class MedicineRestMapper {

    public MedicineRestMapper() {
    }
    
    public MedicineResponse toResponse(Medicine domainModel) {
        if (domainModel == null) {
            return null;
        }

        MedicineResponse response = new MedicineResponse();
        response.setId(domainModel.getId());
        response.setName(domainModel.getName());
        response.setFactoryLaboratory(domainModel.getFactoryLaboratory());
        response.setDateManufactured(domainModel.getDateManufactured());
        response.setExpirationDate(domainModel.getExpirationDate());
        response.setQuantityInStock(domainModel.getQuantityInStock());
        response.setUnitPrice(domainModel.getUnitPrice());
        return response;
    }
    
    public MedicineResponse toResponse(MedicineDTO dtoModel) {
        if (dtoModel == null) {
            return null;
        }

        MedicineResponse response = new MedicineResponse();
        response.setId(dtoModel.getId());
        response.setName(dtoModel.getName());
        response.setFactoryLaboratory(dtoModel.getFactoryLaboratory());
        response.setDateManufactured(dtoModel.getDateManufactured());
        response.setExpirationDate(dtoModel.getExpirationDate());
        response.setQuantityInStock(dtoModel.getQuantityInStock());
        response.setUnitPrice(dtoModel.getUnitPrice());
        return response;
    }

}
