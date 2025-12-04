package konex_technical_test_backend.infrastructure.adapter.in.rest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import konex_technical_test_backend.domain.model.Sale;
import konex_technical_test_backend.domain.model.Medicine;
import konex_technical_test_backend.infrastructure.adapter.in.rest.dto.request.GenerateSaleRequest;
import konex_technical_test_backend.infrastructure.adapter.in.rest.dto.response.SaleResponse;
import konex_technical_test_backend.infrastructure.adapter.in.rest.mapper.SaleRestMapper;

import konex_technical_test_backend.application.service.sale.GenerateSaleUseCaseAdapter;
import konex_technical_test_backend.application.service.sale.GetSaleUseCaseAdapter;

import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.mock;

public class SaleControllerUnitTest {

    private GenerateSaleUseCaseAdapter generateSaleUseCase;
    private GetSaleUseCaseAdapter getSaleUseCase;
    private SaleRestMapper saleRestMapper;

    private SaleController controller;

    @BeforeEach
    public void setup() {
        generateSaleUseCase = mock(GenerateSaleUseCaseAdapter.class);
        getSaleUseCase = mock(GetSaleUseCaseAdapter.class);
        saleRestMapper = mock(SaleRestMapper.class);

        controller = new SaleController(getSaleUseCase, generateSaleUseCase, saleRestMapper);
    }

    @Test
    public void generateSale_returnsCreated() {
        String medicineId = UUID.randomUUID().toString();
        GenerateSaleRequest req = new GenerateSaleRequest(medicineId, 2);

        Medicine med = new Medicine(medicineId, "M", "L", java.time.LocalDate.now(), java.time.LocalDate.now().plusDays(10), 5, 3.0);
        Sale sale = new Sale(UUID.randomUUID().toString(), LocalDateTime.now(), med, 2, med.getUnitPrice(), 2 * med.getUnitPrice());
        SaleResponse resp = new SaleResponse(sale.getId(), sale.getDateTime(), null, sale.getQuantity(), sale.getUnitPrice(), sale.getTotalPrice());

        when(generateSaleUseCase.execute(req.getMedicineId(), req.getQuantity())).thenReturn(sale);
        when(saleRestMapper.toResponse(sale)).thenReturn(resp);

        ResponseEntity<SaleResponse> result = controller.generateSale(req);

        assertEquals(201, result.getStatusCode().value());
        assertEquals(resp, result.getBody());
    }

    @Test
    public void getAll_returnsList() {
        Medicine med = new Medicine(UUID.randomUUID().toString(), "M", "L", java.time.LocalDate.now(), java.time.LocalDate.now().plusDays(10), 5, 3.0);
        Sale s1 = new Sale(UUID.randomUUID().toString(), LocalDateTime.now(), med, 1, med.getUnitPrice(), med.getUnitPrice());
        Sale s2 = new Sale(UUID.randomUUID().toString(), LocalDateTime.now(), med, 2, med.getUnitPrice(), 2 * med.getUnitPrice());
        SaleResponse r1 = new SaleResponse(s1.getId(), s1.getDateTime(), null, s1.getQuantity(), s1.getUnitPrice(), s1.getTotalPrice());
        SaleResponse r2 = new SaleResponse(s2.getId(), s2.getDateTime(), null, s2.getQuantity(), s2.getUnitPrice(), s2.getTotalPrice());

        when(getSaleUseCase.getAll()).thenReturn(List.of(s1, s2));
        when(saleRestMapper.toResponse(s1)).thenReturn(r1);
        when(saleRestMapper.toResponse(s2)).thenReturn(r2);

        ResponseEntity<java.util.List<SaleResponse>> result = controller.getAll();

        assertEquals(200, result.getStatusCode().value());
        assertEquals(2, result.getBody().size());
    }

    @Test
    public void getById_returnsSale() {
        String id = UUID.randomUUID().toString();
        Medicine med = new Medicine(UUID.randomUUID().toString(), "M", "L", java.time.LocalDate.now(), java.time.LocalDate.now().plusDays(10), 5, 3.0);
        Sale s = new Sale(id, LocalDateTime.now(), med, 1, med.getUnitPrice(), med.getUnitPrice());
        SaleResponse r = new SaleResponse(s.getId(), s.getDateTime(), null, s.getQuantity(), s.getUnitPrice(), s.getTotalPrice());

        when(getSaleUseCase.getById(id)).thenReturn(s);
        when(saleRestMapper.toResponse(s)).thenReturn(r);

        ResponseEntity<SaleResponse> result = controller.getMedicineById(id);
        assertEquals(200, result.getStatusCode().value());
        assertEquals(r, result.getBody());
    }

    @Test
    public void generateSale_throwsMedicineNotFoundException() {
        String medicineId = UUID.randomUUID().toString();
        GenerateSaleRequest req = new GenerateSaleRequest(medicineId, 2);
        konex_technical_test_backend.domain.exception.medicine.MedicineNotFoundException ex = new konex_technical_test_backend.domain.exception.medicine.MedicineNotFoundException(medicineId);

        when(generateSaleUseCase.execute(medicineId, 2)).thenThrow(ex);

        try {
            controller.generateSale(req);
        } catch (konex_technical_test_backend.domain.exception.medicine.MedicineNotFoundException e) {
            assertEquals(ex.getMessage(), e.getMessage());
        }
    }

    @Test
    public void generateSale_throwsOutOfStockException() {
        String medicineId = UUID.randomUUID().toString();
        GenerateSaleRequest req = new GenerateSaleRequest(medicineId, 100);
        konex_technical_test_backend.domain.exception.sale.OutOfStockException ex = new konex_technical_test_backend.domain.exception.sale.OutOfStockException();

        when(generateSaleUseCase.execute(medicineId, 100)).thenThrow(ex);

        try {
            controller.generateSale(req);
        } catch (konex_technical_test_backend.domain.exception.sale.OutOfStockException e) {
            assertEquals(ex.getMessage(), e.getMessage());
        }
    }
}
