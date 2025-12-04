package konex_technical_test_backend.infrastructure.adapter.in.rest.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.hibernate.StaleObjectStateException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    public void handleOptimisticLockingFailure_returnsConflictStatus() {
        ObjectOptimisticLockingFailureException ex = new ObjectOptimisticLockingFailureException("Medicine", "Entity was updated by another transaction");
        ResponseEntity<GlobalExceptionHandler.ErrorResponse> result = handler.handleOptimisticLockingFailure(ex);
        assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
        assertEquals("CONFLICT", result.getBody().getCode());
    }

    @Test
    public void handleStaleObjectState_returnsConflictStatus() {
        StaleObjectStateException ex = new StaleObjectStateException("MedicineJpaEntity", UUID.randomUUID().toString());
        ResponseEntity<GlobalExceptionHandler.ErrorResponse> result = handler.handleStaleObjectState(ex);
        assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
        assertEquals("CONFLICT", result.getBody().getCode());
    }
}
