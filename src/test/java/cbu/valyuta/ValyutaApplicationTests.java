package cbu.valyuta;

import cbu.valyuta.dto.CurrencyDto;
import cbu.valyuta.dto.ResponseDto;
import cbu.valyuta.feign.CurrencyFeignClient;
import cbu.valyuta.service.CurrencyService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
class ValyutaApplicationTests {

	@Autowired
	private CurrencyFeignClient feignClient;
	@Autowired
	private CurrencyService currencyService;
	@Autowired
	private MockMvc mockMvc;

	@Test
	@Order(1)
	void checkJsonCurrencyList(){
		List<CurrencyDto> currencyDtoList = feignClient.getAllCurrency();
		Assertions.assertNotNull(currencyDtoList);
	}

	@Test
	@Order(2)
	void checkInsertInto() {
		ResponseDto responseDto = currencyService.insertIntoCurrency();
		Assertions.assertNotNull(responseDto);
		Assertions.assertEquals(0, responseDto.getCode());
		Assertions.assertTrue(responseDto.getSuccess());
	}

	@Test
	@Order(3)
	void getCurrencyByCode(){
		String ccy = "USD";
		ResponseDto<CurrencyDto> responseDto = currencyService.getByCodeCurrency(ccy);
		Assertions.assertNotNull(responseDto);
		Assertions.assertNotNull(responseDto.getResponseData());
		System.out.println("Valyuta by CCY code: " + responseDto.getResponseData());
	}

	@Test
	@Order(4)
	@DisplayName("Integration test")
	void checkXmlCurrencyByCode() throws Exception {
		String ccy = "EUR";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/valyuta/by-code/"+ccy)
				.contentType("application/json");
//				.content(ccy);

		String response = mockMvc.perform(requestBuilder)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_XML))
				.andReturn()
				.getResponse()
				.getContentAsString();

//		XmlMapper xmlMapper = new XmlMapper();
//		ResponseDto<CurrencyDto> responseDto = xmlMapper.readValue(response, new TypeReference<ResponseDto<CurrencyDto>>() {});
		ObjectMapper objectMapper = new ObjectMapper();
		ResponseDto<CurrencyDto> responseDto = objectMapper.readValue(response, new TypeReference<ResponseDto<CurrencyDto>>() {});

		Assertions.assertNotNull(responseDto);
		Assertions.assertNotNull(responseDto.getResponseData());
		Assertions.assertEquals(0, responseDto.getCode());
		Assertions.assertTrue(responseDto.getSuccess());
		Assertions.assertInstanceOf(responseDto.getResponseData().getClass(), new CurrencyDto());
	}
}