package is.studyprojectspringjunit.controllers;

import is.studyprojectspringjunit.entity.Client;
import is.studyprojectspringjunit.service.ClientDAO;
import is.studyprojectspringjunit.service.ClientRepository;
import org.hibernate.exception.SQLGrammarException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import javax.persistence.EntityNotFoundException;



import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class ClientDAOTest {

    @InjectMocks
    private ClientDAO clientDAO;

    @Mock
    private ClientRepository repository;

    private static Client clientExpected1;
    private static Client clientExpected2;

    public ClientDAOTest() {
    }

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);

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

        when(repository.findAll()).thenReturn(Arrays.asList(clientActual1, clientActual2));
        when(repository.getById(1L)).thenReturn(clientActual1);
        when(repository.getById(2L)).thenReturn(clientActual2);
        when(repository.getById(3L)).thenThrow(SQLGrammarException.class);
        when(repository.getById(4L)).thenThrow(EntityNotFoundException.class);
        when(repository.getById(5L)).thenThrow(RuntimeException.class);

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
    public void getAllClientTest() {
        List<Client> clientList = clientDAO.getAllClient(repository);
        assertThat(clientList, notNullValue());
        assertThat(clientList, not(empty()));
        assertThat(clientList, contains(clientExpected1, clientExpected2));
        assertThat(clientList, hasSize(2));
    }

    @Test
    public void getByIdClientTest() {
        Long id = 1L;
        Client client = clientDAO.getByIdClient(repository, id);

        assertThat(client.getId(), allOf(
                not(equalTo(0L)),
                equalTo(id)
        ));

        assertThat(client.getLastName(), allOf(
                notNullValue(),
                equalToIgnoringCase(clientExpected1.getLastName())
        ));

        assertThat(client.getAddressRegistration(), allOf(
                notNullValue(),
                equalToIgnoringCase("SARATOV")
        ));

        assertThat(client.getEmail(), allOf(
                notNullValue(),
                containsString("@")
        ));

        assertThat(client, instanceOf(Client.class));

        assertThat(client, equalTo(clientExpected1));
    }

    @Test
    public void notFoundClient() {
        Client clientActual = clientDAO.getByIdClient(repository, 3L);
        assertThat(clientActual.getId(), equalTo(0L));
        assertThat(clientActual.getLastName(), containsString("table or view does not exist")
        );
    }

    @Test
    public void errorDB() {
        Client clientActual = clientDAO.getByIdClient(repository, 4L);
        assertThat(clientActual.getId(), equalTo(0L));
        assertThat(clientActual.getLastName(), containsString("object not found")
        );
    }

    @Test
    public void errorApplication() {
        Client clientActual = clientDAO.getByIdClient(repository, 5L);
        assertThat(clientActual.getId(), equalTo(0L));
        assertThat(clientActual.getLastName(), containsString("exception")
        );
    }

}
