package com.OTP;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.otp.controller.OtpController;
import com.otp.model.Otp;
import com.otp.service.OtpService;

public class tttt extends OtpApplicationTests {
	@InjectMocks
	OtpController cc;

	@Mock
	OtpService ss;
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void gettAllOtps() throws Exception {
		String uri = "/otp/getAll";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content= mvcResult.getResponse().getContentAsString();
		Otp[] tplist = super.mapFromJson(content, Otp[].class);

		assertTrue(tplist.length == 14);
	}



}
