package konex_technical_test_backend.infrastructure.adapter.in.rest.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import konex_technical_test_backend.application.service.sale.*;
import konex_technical_test_backend.domain.model.Sale;
import konex_technical_test_backend.infrastructure.adapter.in.rest.dto.request.GenerateSaleRequest;
import konex_technical_test_backend.infrastructure.adapter.in.rest.dto.response.SaleResponse;
import konex_technical_test_backend.infrastructure.adapter.in.rest.mapper.SaleRestMapper;

@RestController
@RequestMapping("/api/sales")
@CrossOrigin(origins = {"*"})
@Tag(name = "Sale Controller", description = "Controller for managing sales")
public class SaleController {
    
    private final GenerateSaleUseCaseAdapter generateSaleUseCase;
    private final GetSaleUseCaseAdapter getSaleUseCase;
    private final SaleRestMapper saleRestMapper;

    public SaleController(GetSaleUseCaseAdapter getSaleUseCase,
            GenerateSaleUseCaseAdapter generateSaleUseCase,
            SaleRestMapper saleRestMapper) {
        this.generateSaleUseCase = generateSaleUseCase;
        this.getSaleUseCase = getSaleUseCase;
        this.saleRestMapper = saleRestMapper;
    }

    @PostMapping
    public ResponseEntity<SaleResponse> generateSale(@Valid @RequestBody GenerateSaleRequest request) {
        Sale sale = generateSaleUseCase.execute(
            request.getMedicineId(),
            request.getQuantity()
        );

        SaleResponse response = saleRestMapper.toResponse(sale);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<SaleResponse>> getAll() {
        List<Sale> sales = getSaleUseCase.getAll();
        List<SaleResponse> responses = sales.stream()
            .map(saleRestMapper::toResponse)
            .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<SaleResponse> getMedicineById(@PathVariable("id") String id) {
        Sale sale = getSaleUseCase.getById(id);
        SaleResponse response = saleRestMapper.toResponse(sale);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-by-medicine-id/{medicineId}")
    public ResponseEntity<List<SaleResponse>> getByMedicineId(@PathVariable("medicineId") String medicineId) {
        List<Sale> sales = getSaleUseCase.getByMedicineId(medicineId);
        List<SaleResponse> responses = sales.stream()
            .map(saleRestMapper::toResponse)
            .toList();
        return ResponseEntity.ok(responses);
    }
    
    @GetMapping("/get-by-date-manufactured/{startDate}/{endDate}")
    public ResponseEntity<List<SaleResponse>> getByDateTime(@PathVariable("startDate") LocalDateTime startDate, @PathVariable("endDate") LocalDateTime endDate) {
        List<Sale> sales = getSaleUseCase.getByDateTime(startDate, endDate);
        List<SaleResponse> responses = sales.stream()
            .map(saleRestMapper::toResponse)
            .toList();
        return ResponseEntity.ok(responses);
    }
    
    @GetMapping("/get-by-quantity/{from}/{to}")
    public ResponseEntity<List<SaleResponse>> getByQuantity(@PathVariable("from") int from, @PathVariable("to") int to) {
        List<Sale> sales = getSaleUseCase.getByQuantity(from, to);
        List<SaleResponse> responses = sales.stream()
            .map(saleRestMapper::toResponse)
            .toList();
        return ResponseEntity.ok(responses);
    }
    
    @GetMapping("/get-by-unit-price/{from}/{to}")
    public ResponseEntity<List<SaleResponse>> getByUnitPrice(@PathVariable("from") double from, @PathVariable("to") double to) {
        List<Sale> sales = getSaleUseCase.getByUnitPrice(from, to);
        List<SaleResponse> responses = sales.stream()
            .map(saleRestMapper::toResponse)
            .toList();
        return ResponseEntity.ok(responses);
    }
    
    @GetMapping("/get-by-total-price/{from}/{to}")
    public ResponseEntity<List<SaleResponse>> getByTotalPrice(@PathVariable("from") double from, @PathVariable("to") double to) {
        List<Sale> sales = getSaleUseCase.getByTotalPrice(from, to);
        List<SaleResponse> responses = sales.stream()
            .map(saleRestMapper::toResponse)
            .toList();
        return ResponseEntity.ok(responses);
    }

}
