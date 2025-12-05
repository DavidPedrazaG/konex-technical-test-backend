package konex_technical_test_backend.infrastructure.adapter.in.rest.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import konex_technical_test_backend.application.service.medicine.*;
import konex_technical_test_backend.domain.model.Medicine;
import konex_technical_test_backend.infrastructure.adapter.in.rest.dto.request.MedicineRequest;
import konex_technical_test_backend.infrastructure.adapter.in.rest.dto.response.MedicineResponse;
import konex_technical_test_backend.infrastructure.adapter.in.rest.mapper.MedicineRestMapper;

@RestController
@RequestMapping("/api/medicines")
@CrossOrigin(origins = {"*"})
@Tag(name = "Medicine Controller", description = "Controller for managing medicines")
public class MedicineController {
    
    private final CreateMedicineUseCaseAdapter createMUseCase;
    private final GetMedicineUseCaseAdapter getMUseCase;
    private final UpdateMedicineUseCaseAdapter updateMUseCase;
    private final DeleteMedicineUseCaseAdapter deleteMUseCase;
    private final MedicineRestMapper medicineRestMapper;

    public MedicineController(CreateMedicineUseCaseAdapter createMUseCase,
            GetMedicineUseCaseAdapter getMUseCase,
            UpdateMedicineUseCaseAdapter updateMUseCase,
            DeleteMedicineUseCaseAdapter deleteMUseCase,
            MedicineRestMapper medicineRestMapper) {
        this.createMUseCase = createMUseCase;
        this.getMUseCase = getMUseCase;
        this.updateMUseCase = updateMUseCase;
        this.deleteMUseCase = deleteMUseCase;
        this.medicineRestMapper = medicineRestMapper;
    }

    @PostMapping
    public ResponseEntity<MedicineResponse> createMedicine(@Valid @RequestBody MedicineRequest request) {
        Medicine medicine = createMUseCase.execute(
            request.getName(), 
            request.getFactoryLaboratory(), 
            request.getDateManufactured(), 
            request.getExpirationDate(),
            request.getQuantityInStock(),
            request.getUnitPrice()
        );

        MedicineResponse response = medicineRestMapper.toResponse(medicine);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<MedicineResponse>> getAll() {
        List<Medicine> medicines = getMUseCase.getAll();
        List<MedicineResponse> responses = medicines.stream()
            .map(medicineRestMapper::toResponse)
            .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<MedicineResponse> getMedicineById(@PathVariable("id") String id) {
        Medicine medicine = getMUseCase.getById(id);
        MedicineResponse response = medicineRestMapper.toResponse(medicine);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/get-by-name/{name}")
    public ResponseEntity<MedicineResponse> getMedicineByName(@PathVariable("name") String name) {
        Medicine medicine = getMUseCase.getByName(name);
        MedicineResponse response = medicineRestMapper.toResponse(medicine);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-by-factory-laboratory/{factoryLaboratory}")
    public ResponseEntity<List<MedicineResponse>> getByFactoryLaboratory(@PathVariable("factoryLaboratory") String factoryLaboratory) {
        List<Medicine> medicines = getMUseCase.getByFactoryLaboratory(factoryLaboratory);
        List<MedicineResponse> responses = medicines.stream()
            .map(medicineRestMapper::toResponse)
            .toList();
        return ResponseEntity.ok(responses);
    }
    
    @GetMapping("/get-by-date-manufactured/{startDate}/{endDate}")
    public ResponseEntity<List<MedicineResponse>> getByDateManufactured(@PathVariable("startDate") LocalDate startDate, @PathVariable("endDate") LocalDate endDate) {
        List<Medicine> medicines = getMUseCase.getByDateManufactured(startDate, endDate);
        List<MedicineResponse> responses = medicines.stream()
            .map(medicineRestMapper::toResponse)
            .toList();
        return ResponseEntity.ok(responses);
    }
    
    @GetMapping("/get-by-expiration-date/{startDate}/{endDate}")
    public ResponseEntity<List<MedicineResponse>> getByExpirationDate(@PathVariable("startDate") LocalDate startDate, @PathVariable("endDate") LocalDate endDate) {
        List<Medicine> medicines = getMUseCase.getByExpirationDate(startDate, endDate);
        List<MedicineResponse> responses = medicines.stream()
            .map(medicineRestMapper::toResponse)
            .toList();
        return ResponseEntity.ok(responses);
    }
    
    @GetMapping("/get-by-quantity-in-stock/{from}/{to}")
    public ResponseEntity<List<MedicineResponse>> getByQuantityInStock(@PathVariable("from") int from, @PathVariable("to") int to) {
        List<Medicine> medicines = getMUseCase.getByQuantityInStock(from, to);
        List<MedicineResponse> responses = medicines.stream()
            .map(medicineRestMapper::toResponse)
            .toList();
        return ResponseEntity.ok(responses);
    }
    
    @GetMapping("/get-by-unit-price/{from}/{to}")
    public ResponseEntity<List<MedicineResponse>> getByUnitPrice(@PathVariable("from") double from, @PathVariable("to") double to) {
        List<Medicine> medicines = getMUseCase.getByUnitPrice(from, to);
        List<MedicineResponse> responses = medicines.stream()
            .map(medicineRestMapper::toResponse)
            .toList();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicineResponse> updateMedicine(@PathVariable("id") String id, @Valid @RequestBody MedicineRequest request) {
        Medicine medicine = updateMUseCase.execute(
            id,
            request.getName(), 
            request.getFactoryLaboratory(), 
            request.getDateManufactured(), 
            request.getExpirationDate(),
            request.getQuantityInStock(),
            request.getUnitPrice()
        );

        MedicineResponse response = medicineRestMapper.toResponse(medicine);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicine(@PathVariable("id") String id) {
        deleteMUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
