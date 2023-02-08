package com.musala;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musala.dto.DroneDto;
import com.musala.dto.MedicationDto;
import com.musala.model.DroneModel;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DronesApplicationTests {

	@Autowired
	private MockMvc mvc;
	DroneDto drone = new DroneDto("1234abc4der", DroneModel.Lightweight, 100, 90);

	@org.junit.Test
	public void _1_getDrones() throws Exception {

		mvc.perform(get("/drone")
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(jsonPath("$.size").value(10))
				.andExpect(status().isOk());
	}

	@org.junit.Test
	public void _2_addDrone() throws Exception {

		mvc.perform(post("/drone")
						.content(asJsonString(drone))
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@org.junit.Test
	public void _3_addDuplicateDrone() throws Exception {

		mvc.perform(post("/drone")
						.content(asJsonString(drone))
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isConflict());
	}

	@org.junit.Test
	public void _4_loadDrone() throws Exception {

		MedicationDto medicationDto = new MedicationDto();
		medicationDto.setCode("KUNDGDGDD");
		medicationDto.setName("mashdhdfd2");
		medicationDto.setWeight(100);

		mvc.perform(post("/dispatch/load-drone/{id}", "b9156122-c619-44b0-8774-ca3912d467af")
						.content(asJsonString(medicationDto))
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(jsonPath("$.message").value("Saved successfully"))
				.andExpect(status().isOk());
	}

	@org.junit.Test
	public void _5_loadDroneAndExpectBadRequest() throws Exception {

		MedicationDto medicationDto = new MedicationDto();
		medicationDto.setCode("KUNDGDGDD*");
		medicationDto.setName("mashdhdfd2");
		medicationDto.setWeight(100);

		mvc.perform(post("/dispatch/load-drone/{id}", "b9156122-c619-44b0-8774-ca3912d467af")
						.content(asJsonString(medicationDto))
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}

	@org.junit.Test
	public void _6_getDroneBatteryLevel() throws Exception {

		mvc.perform(get("/dispatch/check-drone-battery-level/{id}", "b9156122-c619-44b0-8774-ca3912d467af")
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(jsonPath("$.batteryCapacity").value(90))
				.andExpect(status().isOk());
	}

	@org.junit.Test
	public void _7_getAvailableDrones() throws Exception {

		mvc.perform(get("/dispatch/check-available-drones")
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(status().isOk());
	}


	public static String asJsonString(final Object obj) {

		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
