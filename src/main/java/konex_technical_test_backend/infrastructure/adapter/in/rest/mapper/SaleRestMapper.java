package konex_technical_test_backend.infrastructure.adapter.in.rest.mapper;

import org.springframework.stereotype.Component;

import konex_technical_test_backend.application.dto.SaleDTO;
import konex_technical_test_backend.domain.model.Sale;
import konex_technical_test_backend.infrastructure.adapter.in.rest.dto.response.SaleResponse;

@Component
public class SaleRestMapper {

    private final MedicineRestMapper medicineRestMapper;

    public SaleRestMapper(MedicineRestMapper medicineRestMapper) {
        this.medicineRestMapper = medicineRestMapper;

    }

    public SaleResponse toResponse(Sale domainModel) {
        if (domainModel == null) {
            return null;
        }

        SaleResponse response = new SaleResponse();
        response.setId(domainModel.getId());
        response.setDateTime(domainModel.getDateTime());
        response.setMedicine(medicineRestMapper.toResponse(domainModel.getMedicine()));
        response.setQuantity(domainModel.getQuantity());
        response.setUnitPrice(domainModel.getUnitPrice());
        response.setTotalPrice(domainModel.getTotalPrice());
        return response;
    }
    
    public SaleResponse toResponse(SaleDTO dtoModel) {
        if (dtoModel == null) {
            return null;
        }

        SaleResponse response = new SaleResponse();
        response.setId(dtoModel.getId());
        response.setDateTime(dtoModel.getDateTime());
        response.setMedicine(medicineRestMapper.toResponse(dtoModel.getMedicine()));
        response.setQuantity(dtoModel.getQuantity());
        response.setUnitPrice(dtoModel.getUnitPrice());
        response.setTotalPrice(dtoModel.getTotalPrice());
        return response;
    }
}
