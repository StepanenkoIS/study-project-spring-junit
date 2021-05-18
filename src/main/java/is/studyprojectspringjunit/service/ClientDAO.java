package is.studyprojectspringjunit.service;

import is.studyprojectspringjunit.entity.Client;
import org.hibernate.exception.SQLGrammarException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public class ClientDAO {

    public List<Client> getAllClient(ClientRepository clientRepository) {
        return clientRepository.findAll();
    }

    public Client getByIdClient(ClientRepository clientRepository, Long id) {
        Client client = new Client();
        System.out.println("RequestParam " + id);
        try {
            client = clientRepository.getById(id);
        } catch (SQLGrammarException exception) {
            System.out.println("table or view does not exist. ID = " + id);
            client.setId(0L);
            client.setLastName("table or view does not exist");
            return client;
        } catch (EntityNotFoundException exception) {
            System.out.println("object not found. ID = " + id);
            client.setId(0L);
            client.setLastName("object not found");
            return client;
        } catch (Exception exception) {
            System.out.println("Exception ID = " + id);
            client.setId(0L);
            client.setLastName("exception");
            return client;
        }
        return client;
    }
}
