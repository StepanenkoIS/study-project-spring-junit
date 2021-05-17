package is.studyprojectspringjunit.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import is.studyprojectspringjunit.entity.Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




@RunWith(SpringRunner.class)
@SpringBootTest
 public class ClientControllerDBTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    ObjectMapper om = new ObjectMapper();

    @Before
    public void setUp() {
        mockMvc= MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void idClientTest() throws Exception {
        Client client = new Client();
        client.setId(1L);
        client.setLastName("Pechorin");
        client.setFirstName("Grigory");
        client.setMiddleName("Alexandrovich");
        client.setBirthday("1985-01-25");
        client.setPassportNumber("0001224466");
        client.setAddressRegistration("Saratov");
        client.setAddressResidential("Saratov");
        client.setMobilePhone("8-900-01-01");
        client.setEmail("yyyy@mail.ru");

        String jsonRequest = om.writeValueAsString(client);

        String urlTemplate = String.format("/client/id?id=%s", 1);

        MvcResult result = mockMvc
                .perform(get(urlTemplate).content(jsonRequest)
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();

        Assert.assertEquals(jsonRequest, resultContent);
        System.out.println("Test idClientTest - OK");
    }
}