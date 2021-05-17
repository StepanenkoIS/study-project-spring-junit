package is.studyprojectspringjunit.controllers;


import is.studyprojectspringjunit.entity.Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class ClientControllerMockitoTest {

    private static ClientController clientController;
    private static Client clientExpected1;
    private static Client clientExpected2;

    @BeforeClass
    public static void setUp(){
        clientController = mock(ClientController.class);

        Client clientActual1 = new Client();
        clientActual1.setId(1L);
        clientActual1.setLastName("Pechorin");
        clientActual1.setFirstName("Grigory");
        clientActual1.setMiddleName("Alexandrovich");
        clientActual1.setBirthday("1985-01-25");
        clientActual1.setPassportNumber("0001224466");
        clientActual1.setAddressRegistration("Saratov");
        clientActual1.setAddressResidential("Saratov");
        clientActual1.setMobilePhone("8-900-01-01");
        clientActual1.setEmail("yyyy@mail.ru");

        Client clientActual2 = new Client();
        clientActual2.setId(2L);
        clientActual2.setLastName("Ivanov");
        clientActual2.setFirstName("Egor");
        clientActual2.setMiddleName("Sergeyevich");
        clientActual2.setBirthday("1986-02-25");
        clientActual2.setPassportNumber("0002335577");
        clientActual2.setAddressRegistration("Saratov");
        clientActual2.setAddressResidential("Saratov");
        clientActual2.setMobilePhone("8-900-01-02");
        clientActual2.setEmail("pppp@mail.ru");

        when(clientController.allClient()).thenReturn(Arrays.asList(clientActual1, clientActual2));
        when(clientController.idClient(1L)).thenReturn(clientActual1);
        when(clientController.idClient(2L)).thenReturn(clientActual2);
        when(clientController.idClient(3L)).thenReturn(new Client());
    }

    @Before
    public void setExpected() {
        clientExpected1 = new Client();
        clientExpected1.setId(1L);
        clientExpected1.setLastName("Pechorin");
        clientExpected1.setFirstName("Grigory");
        clientExpected1.setMiddleName("Alexandrovich");
        clientExpected1.setBirthday("1985-01-25");
        clientExpected1.setPassportNumber("0001224466");
        clientExpected1.setAddressRegistration("Saratov");
        clientExpected1.setAddressResidential("Saratov");
        clientExpected1.setMobilePhone("8-900-01-01");
        clientExpected1.setEmail("yyyy@mail.ru");

        clientExpected2 = new Client();
        clientExpected2.setId(2L);
        clientExpected2.setLastName("Ivanov");
        clientExpected2.setFirstName("Egor");
        clientExpected2.setMiddleName("Sergeyevich");
        clientExpected2.setBirthday("1986-02-25");
        clientExpected2.setPassportNumber("0002335577");
        clientExpected2.setAddressRegistration("Saratov");
        clientExpected2.setAddressResidential("Saratov");
        clientExpected2.setMobilePhone("8-900-01-02");
        clientExpected2.setEmail("pppp@mail.ru");
    }

    @Test
    public void idClientTest() {
        Assert.assertEquals(clientExpected1, clientController.idClient(1));
        Assert.assertEquals(clientExpected2, clientController.idClient(2));
    }

    @Test
    public void allClient() {
        List<Client> clientList = clientController.allClient();
        Assert.assertEquals(2, clientList.size());
    }

    @Test
    public void notFoundClient() {
        Assert.assertEquals(new Client(), clientController.idClient(3));
    }

}

