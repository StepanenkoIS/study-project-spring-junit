package is.studyprojectspringjunit.controllers;


import is.studyprojectspringjunit.entity.Client;
import is.studyprojectspringjunit.service.ClientRepository;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
public class ClientController {

    private ClientRepository clientRepository;

    @Autowired
    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/client/all")
    public List<Client> allClient() {
        return clientRepository.findAll();
    }

    @GetMapping("/client/id")
    public Client idClient(@RequestParam long id) {
        Client client;
        try {
            client = clientRepository.getById(id);
            System.out.println(client);
        } catch (SQLGrammarException exception) {
            System.out.println("table or view does not exist. ID = " + id);
            return new Client();
        } catch (EntityNotFoundException exception) {
            System.out.println("object not found. ID = " + id);
            return new Client();
        } catch (Exception exception) {
            System.out.println("error. ID = " + id);
            return new Client();
        }
        System.out.println("RequestParam " + id);
        return client;
    }
}
