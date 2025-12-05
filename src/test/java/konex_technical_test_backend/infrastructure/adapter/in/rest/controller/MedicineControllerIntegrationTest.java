package konex_technical_test_backend.infrastructure.adapter.in.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import konex_technical_test_backend.domain.model.Medicine;
import konex_technical_test_backend.infrastructure.adapter.in.rest.mapper.MedicineRestMapper;
import konex_technical_test_backend.application.service.medicine.CreateMedicineUseCaseAdapter;
import konex_technical_test_backend.application.service.medicine.GetMedicineUseCaseAdapter;

public class MedicineControllerIntegrationTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    private CreateMedicineUseCaseAdapter createMUseCase;
    private GetMedicineUseCaseAdapter getMUseCase;
    private MedicineRestMapper medicineRestMapper;

    @BeforeEach
    public void setup() {
        createMUseCase = org.mockito.Mockito.mock(CreateMedicineUseCaseAdapter.class);
        getMUseCase = org.mockito.Mockito.mock(GetMedicineUseCaseAdapter.class);
        medicineRestMapper = org.mockito.Mockito.mock(MedicineRestMapper.class);

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.findAndRegisterModules();

        MedicineController controller = new MedicineController(createMUseCase, getMUseCase, null, null, medicineRestMapper);

        var advice = new konex_technical_test_backend.infrastructure.adapter.in.rest.config.GlobalExceptionHandler();

        mockMvc = MockMvcBuilders.standaloneSetup(controller)
            .setControllerAdvice(advice)
            .setMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
            .build();
    }

    @Test
    public void createMedicine_returnsCreated_whenValid() throws Exception {
        var req = new konex_technical_test_backend.infrastructure.adapter.in.rest.dto.request.MedicineRequest("Paracetamol", "LabX", LocalDate.of(2023,1,1), LocalDate.of(2024,1,1), 10, 5.5);
        Medicine domain = new Medicine(UUID.randomUUID().toString(), req.getName(), req.getFactoryLaboratory(), req.getDateManufactured(), req.getExpirationDate(), req.getQuantityInStock(), req.getUnitPrice());
        var resp = new konex_technical_test_backend.infrastructure.adapter.in.rest.dto.response.MedicineResponse(domain.getId(), domain.getName(), domain.getFactoryLaboratory(), domain.getDateManufactured(), domain.getExpirationDate(), domain.getQuantityInStock(), domain.getUnitPrice());

        when(createMUseCase.execute(req.getName(), req.getFactoryLaboratory(), req.getDateManufactured(), req.getExpirationDate(), req.getQuantityInStock(), req.getUnitPrice())).thenReturn(domain);
        when(medicineRestMapper.toResponse(domain)).thenReturn(resp);

        String json = "{\"name\":\"Paracetamol\",\"factoryLaboratory\":\"LabX\",\"dateManufactured\":\"2023-01-01\",\"expirationDate\":\"2024-01-01\",\"quantityInStock\":10,\"unitPrice\":5.5}";

        mockMvc.perform(post("/api/medicines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(domain.getId().toString()))
            .andExpect(jsonPath("$.name").value(domain.getName()));
    }

    @Test
    public void getAll_returnsOk() throws Exception {
        mockMvc.perform(get("/api/medicines/get-all"))
            .andExpect(status().isOk());
    }

    @Test
    public void createMedicine_returnsBadRequest_whenNameIsEmpty() throws Exception {
        String json = "{\"name\":\"\",\"factoryLaboratory\":\"LabX\",\"dateManufactured\":\"2023-01-01\",\"expirationDate\":\"2024-01-01\",\"quantityInStock\":10,\"unitPrice\":5.5}";

        mockMvc.perform(post("/api/medicines")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void createMedicine_returnsBadRequest_whenQuantityIsNegative() throws Exception {
        String json = "{\"name\":\"Paracetamol\",\"factoryLaboratory\":\"LabX\",\"dateManufactured\":\"2023-01-01\",\"expirationDate\":\"2024-01-01\",\"quantityInStock\":-5,\"unitPrice\":5.5}";

        mockMvc.perform(post("/api/medicines")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void createMedicine_returnsConflict_whenOptimisticLockingFailure() throws Exception {
        String json = "{\"name\":\"Acetaminofen\",\"factoryLaboratory\":\"Ninguna\",\"dateManufactured\":\"2023-12-04\",\"expirationDate\":\"2027-12-04\",\"quantityInStock\":200,\"unitPrice\":5000}";

        when(createMUseCase.execute(org.mockito.Mockito.anyString(), org.mockito.Mockito.anyString(), org.mockito.Mockito.any(), org.mockito.Mockito.any(), org.mockito.Mockito.anyInt(), org.mockito.Mockito.anyDouble()))
            .thenThrow(new ObjectOptimisticLockingFailureException("Medicine", "Entity was updated by another transaction"));

        mockMvc.perform(post("/api/medicines")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andExpect(status().isConflict())
            .andExpect(jsonPath("$.code").value("CONFLICT"))
            .andExpect(jsonPath("$.message").value("El recurso fue modificado por otra transacción. Por favor, intente nuevamente."));
    }
}
