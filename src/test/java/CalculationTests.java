import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.farafonov.VacationCalculatorApplication;
import ru.farafonov.components.HolidayManager;
import ru.farafonov.controllers.VacationCalculatorController;
import ru.farafonov.services.CalculatorService;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = VacationCalculatorApplication.class)
@AutoConfigureMockMvc
public class CalculationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void calculate() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/calculate?avgSalary=899&amountOfVacationDays=14")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value("429.52"));
    }
    @Test
    public void calculateWithDate() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/calculate?avgSalary=978.03&amountOfVacationDays=16&vacationDate=08-08-2024")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value("534.08"));
    }
    @Test
    public void calculateWithHoliday() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/calculate?avgSalary=921.51&amountOfVacationDays=20&vacationDate=30-04-2024")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value("629.0"));
    }
}

