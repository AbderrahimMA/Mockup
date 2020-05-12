package com.OTP;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.otp.security.SpringKeycloakSecurityConfiguration;

@WebMvcTest(value = SpringKeycloakSecurityConfiguration.class)
@Import(SpringKeycloakSecurityConfiguration.class)
// Disable Keycloak configuration processing
@TestPropertySource(properties = { "keycloak.enabled=false" })
public class SpringKeycloakTutorialsSecurityTests {

	@Autowired
	MockMvc mockMvc;

	@Test
	public void testUnsecuredPathIsAllowedForAll() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/unsecured")).andExpect(status().isOk())
				.andExpect(jsonPath("$").value("This is an unsecured endpoint payload"));
	}

	/*
	 * @Test
	 * 
	 * @WithMockUser public void testAdminPathIsNotAllowedForAll() throws Exception
	 * { mockMvc.perform(MockMvcRequestBuilders.get("/admin")).andExpect(status().
	 * isForbidden()); }
	 * 
	 * @Test
	 * 
	 * @WithMockUser(roles = "ADMIN") public void
	 * testAdmindPathIsOnlyAllowedForAdminProfil() throws Exception {
	 * mockMvc.perform(MockMvcRequestBuilders.get("/admin")).andExpect(status().isOk
	 * ()) .andExpect(jsonPath("$").value("This is an ADMIN endpoint payload")); }
	 * 
	 * @Test
	 * 
	 * @WithMockUser(roles = "USER") public void
	 * testUserPathIsOnlyAllowedForUserProfil() throws Exception {
	 * mockMvc.perform(MockMvcRequestBuilders.get("/user")).andExpect(status().isOk(
	 * )) .andExpect(jsonPath("$").value("This is an USER endpoint payload")); }
	 * 
	 * @Test
	 * 
	 * @WithMockUser(roles = "USER") public void
	 * testAdmindPathIsNotAllowedForUserProfil() throws Exception {
	 * mockMvc.perform(MockMvcRequestBuilders.get("/admin")).andExpect(status().
	 * isForbidden()); } }
	 * 
	 */

	/***
	 * <p>
	 * Use this configuration class to test if your path is secured
	 * </p>
	 * <p>
	 * his class use
	 * {@link SpringKeycloakTutorialsSecurityConfiguration.CommonSpringKeycloakTutorialsSecuritAdapter}
	 * which define all security matchers
	 * </p>
	 *//*
			 * @TestConfiguration
			 * 
			 * @EnableWebSecurity class SpringKeycloakTutorialsSecurityTestConfiguration
			 * extends WebSecurityConfigurerAdapter {
			 * 
			 * @Override protected void configure(HttpSecurity http) throws Exception { //
			 * use the common configuration to validate matchers http.apply(new
			 * SpringKeycloakSecurityConfiguration.
			 * CommonSpringKeycloakTutorialsSecuritAdapter());
			 * 
			 * }
			 */
}