package is.studyprojectspringjunit.controllers;


import is.studyprojectspringjunit.entity.Client;
import is.studyprojectspringjunit.service.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        System.out.println(id);
        return clientRepository.getById(id);
    }
}
