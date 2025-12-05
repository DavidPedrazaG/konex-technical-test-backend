package konex_technical_test_backend.infrastructure.adapter.in.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import konex_technical_test_backend.domain.model.Medicine;
import konex_technical_test_backend.domain.model.Sale;
import konex_technical_test_backend.application.service.sale.GenerateSaleUseCaseAdapter;
import konex_technical_test_backend.application.service.sale.GetSaleUseCaseAdapter;
import konex_technical_test_backend.infrastructure.adapter.in.rest.mapper.SaleRestMapper;

import java.time.LocalDateTime;

public class SaleControllerIntegrationTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    private GenerateSaleUseCaseAdapter generateSaleUseCase;
    private GetSaleUseCaseAdapter getSaleUseCase;
    private SaleRestMapper saleRestMapper;

    @BeforeEach
    public void setup() {
        generateSaleUseCase = org.mockito.Mockito.mock(GenerateSaleUseCaseAdapter.class);
        getSaleUseCase = org.mockito.Mockito.mock(GetSaleUseCaseAdapter.class);
        saleRestMapper = org.mockito.Mockito.mock(SaleRestMapper.class);

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.findAndRegisterModules();

        SaleController controller = new SaleController(getSaleUseCase, generateSaleUseCase, saleRestMapper);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
            .setMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
            .build();
    }

    @Test
    public void generateSale_returnsCreated_whenValid() throws Exception {
        String medicineId = UUID.randomUUID().toString();
        var req = new konex_technical_test_backend.infrastructure.adapter.in.rest.dto.request.GenerateSaleRequest(medicineId, 2);

        Medicine med = new Medicine(medicineId, "M", "L", java.time.LocalDate.now(), java.time.LocalDate.now().plusDays(10), 5, 3.0);
        Sale sale = new Sale(UUID.randomUUID().toString(), LocalDateTime.now(), med, 2, med.getUnitPrice(), 2 * med.getUnitPrice());
        var resp = new konex_technical_test_backend.infrastructure.adapter.in.rest.dto.response.SaleResponse(sale.getId(), sale.getDateTime(), null, sale.getQuantity(), sale.getUnitPrice(), sale.getTotalPrice());

        when(generateSaleUseCase.execute(req.getMedicineId(), req.getQuantity())).thenReturn(sale);
        when(saleRestMapper.toResponse(sale)).thenReturn(resp);

        String json = String.format("{\"medicineId\":\"%s\",\"quantity\":2}", medicineId.toString());

        mockMvc.perform(post("/api/sales")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(sale.getId().toString()));
    }

    @Test
    public void getAll_returnsOk() throws Exception {
        mockMvc.perform(get("/api/sales/get-all"))
            .andExpect(status().isOk());
    }

    @Test
    public void generateSale_returnsBadRequest_whenQuantityIsNull() throws Exception {
        String json = "{\"medicineId\":\"" + UUID.randomUUID() + "\",\"quantity\":null}";

        mockMvc.perform(post("/api/sales")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andExpect(status().isBadRequest());
    }
}
