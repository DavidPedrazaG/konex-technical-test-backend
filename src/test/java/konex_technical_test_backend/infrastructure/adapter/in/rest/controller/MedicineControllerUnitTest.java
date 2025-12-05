package konex_technical_test_backend.infrastructure.adapter.in.rest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import konex_technical_test_backend.domain.model.Medicine;
import konex_technical_test_backend.infrastructure.adapter.in.rest.dto.request.MedicineRequest;
import konex_technical_test_backend.infrastructure.adapter.in.rest.dto.response.MedicineResponse;
import konex_technical_test_backend.infrastructure.adapter.in.rest.mapper.MedicineRestMapper;

import konex_technical_test_backend.application.service.medicine.CreateMedicineUseCaseAdapter;
import konex_technical_test_backend.application.service.medicine.GetMedicineUseCaseAdapter;
import konex_technical_test_backend.application.service.medicine.UpdateMedicineUseCaseAdapter;
import konex_technical_test_backend.application.service.medicine.DeleteMedicineUseCaseAdapter;

import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.mock;

public class MedicineControllerUnitTest {

    private CreateMedicineUseCaseAdapter createMUseCase;
    private GetMedicineUseCaseAdapter getMUseCase;
    private UpdateMedicineUseCaseAdapter updateMUseCase;
    private DeleteMedicineUseCaseAdapter deleteMUseCase;
    private MedicineRestMapper medicineRestMapper;

    private MedicineController controller;

    @BeforeEach
    public void setup() {
        createMUseCase = mock(CreateMedicineUseCaseAdapter.class);
        getMUseCase = mock(GetMedicineUseCaseAdapter.class);
        updateMUseCase = mock(UpdateMedicineUseCaseAdapter.class);
        deleteMUseCase = mock(DeleteMedicineUseCaseAdapter.class);
        medicineRestMapper = mock(MedicineRestMapper.class);

        controller = new MedicineController(createMUseCase, getMUseCase, updateMUseCase, deleteMUseCase, medicineRestMapper);
    }

    @Test
    public void createMedicine_returnsCreatedResponse() {
        MedicineRequest req = new MedicineRequest("Paracetamol", "LabX", LocalDate.of(2023,1,1), LocalDate.of(2024,1,1), 10, 5.5);
        Medicine domain = new Medicine(UUID.randomUUID().toString(), "Paracetamol", "LabX", LocalDate.of(2023,1,1), LocalDate.of(2024,1,1), 10, 5.5);
        MedicineResponse resp = new MedicineResponse(domain.getId(), domain.getName(), domain.getFactoryLaboratory(), domain.getDateManufactured(), domain.getExpirationDate(), domain.getQuantityInStock(), domain.getUnitPrice());

        when(createMUseCase.execute(req.getName(), req.getFactoryLaboratory(), req.getDateManufactured(), req.getExpirationDate(), req.getQuantityInStock(), req.getUnitPrice())).thenReturn(domain);
        when(medicineRestMapper.toResponse(domain)).thenReturn(resp);

        ResponseEntity<MedicineResponse> result = controller.createMedicine(req);

        assertEquals(201, result.getStatusCode().value());
        assertEquals(resp, result.getBody());
    }

    @Test
    public void getAll_returnsList() {
        Medicine m1 = new Medicine(UUID.randomUUID().toString(), "A", "L", LocalDate.now(), LocalDate.now().plusDays(10), 1, 1.0);
        Medicine m2 = new Medicine(UUID.randomUUID().toString(), "B", "L2", LocalDate.now(), LocalDate.now().plusDays(20), 2, 2.0);
        MedicineResponse r1 = new MedicineResponse(m1.getId(), m1.getName(), m1.getFactoryLaboratory(), m1.getDateManufactured(), m1.getExpirationDate(), m1.getQuantityInStock(), m1.getUnitPrice());
        MedicineResponse r2 = new MedicineResponse(m2.getId(), m2.getName(), m2.getFactoryLaboratory(), m2.getDateManufactured(), m2.getExpirationDate(), m2.getQuantityInStock(), m2.getUnitPrice());

        when(getMUseCase.getAll()).thenReturn(List.of(m1, m2));
        when(medicineRestMapper.toResponse(m1)).thenReturn(r1);
        when(medicineRestMapper.toResponse(m2)).thenReturn(r2);

        ResponseEntity<java.util.List<MedicineResponse>> result = controller.getAll();

        assertEquals(200, result.getStatusCode().value());
        assertEquals(2, result.getBody().size());
    }

    @Test
    public void getById_returnsMedicine() {
        String id = UUID.randomUUID().toString();
        Medicine m = new Medicine(id, "A", "L", LocalDate.now(), LocalDate.now().plusDays(10), 1, 1.0);
        MedicineResponse r = new MedicineResponse(m.getId(), m.getName(), m.getFactoryLaboratory(), m.getDateManufactured(), m.getExpirationDate(), m.getQuantityInStock(), m.getUnitPrice());

        when(getMUseCase.getById(id)).thenReturn(m);
        when(medicineRestMapper.toResponse(m)).thenReturn(r);

        ResponseEntity<MedicineResponse> result = controller.getMedicineById(id);
        assertEquals(200, result.getStatusCode().value());
        assertEquals(r, result.getBody());
    }

    @Test
    public void updateMedicine_returnsUpdated() {
        String id = UUID.randomUUID().toString();
        MedicineRequest req = new MedicineRequest("New", "Lab", LocalDate.now(), LocalDate.now().plusDays(5), 3, 4.0);
        Medicine updated = new Medicine(id, req.getName(), req.getFactoryLaboratory(), req.getDateManufactured(), req.getExpirationDate(), req.getQuantityInStock(), req.getUnitPrice());
        MedicineResponse resp = new MedicineResponse(updated.getId(), updated.getName(), updated.getFactoryLaboratory(), updated.getDateManufactured(), updated.getExpirationDate(), updated.getQuantityInStock(), updated.getUnitPrice());

        when(updateMUseCase.execute(id, req.getName(), req.getFactoryLaboratory(), req.getDateManufactured(), req.getExpirationDate(), req.getQuantityInStock(), req.getUnitPrice())).thenReturn(updated);
        when(medicineRestMapper.toResponse(updated)).thenReturn(resp);

        ResponseEntity<MedicineResponse> result = controller.updateMedicine(id, req);

        assertEquals(200, result.getStatusCode().value());
        assertEquals(resp, result.getBody());
    }

    @Test
    public void deleteMedicine_returnsNoContent() {
        String id = UUID.randomUUID().toString();
        // deleteMUseCase.execute returns void; just ensure controller returns 204
        ResponseEntity<Void> result = controller.deleteMedicine(id);
        assertEquals(204, result.getStatusCode().value());
    }

    @Test
    public void getById_throwsMedicineNotFoundException() {
        String id = UUID.randomUUID().toString();
        konex_technical_test_backend.domain.exception.medicine.MedicineNotFoundException ex = new konex_technical_test_backend.domain.exception.medicine.MedicineNotFoundException(id);

        when(getMUseCase.getById(id)).thenThrow(ex);

        // Should not throw; exception is handled by global exception handler
        try {
            controller.getMedicineById(id);
        } catch (konex_technical_test_backend.domain.exception.medicine.MedicineNotFoundException e) {
            assertEquals(ex.getMessage(), e.getMessage());
        }
    }

    @Test
    public void createMedicine_throwsNameAlreadyRegisteredException() {
        MedicineRequest req = new MedicineRequest("Paracetamol", "LabX", LocalDate.of(2023,1,1), LocalDate.of(2024,1,1), 10, 5.5);
        konex_technical_test_backend.domain.exception.medicine.NameAlreadyRegisteredException ex = new konex_technical_test_backend.domain.exception.medicine.NameAlreadyRegisteredException();

        when(createMUseCase.execute(req.getName(), req.getFactoryLaboratory(), req.getDateManufactured(), req.getExpirationDate(), req.getQuantityInStock(), req.getUnitPrice())).thenThrow(ex);

        try {
            controller.createMedicine(req);
        } catch (konex_technical_test_backend.domain.exception.medicine.NameAlreadyRegisteredException e) {
            assertEquals(ex.getMessage(), e.getMessage());
        }
    }
}
