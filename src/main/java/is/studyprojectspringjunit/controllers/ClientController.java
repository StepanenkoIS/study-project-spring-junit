package is.studyprojectspringjunit.controllers;


import is.studyprojectspringjunit.entity.Client;
import is.studyprojectspringjunit.service.ClientDAO;
import is.studyprojectspringjunit.service.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {

    private ClientRepository clientRepository;
    private ClientDAO dao = new ClientDAO();

    @Autowired
    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/client/all")
    public List<Client> allClient() {
        return dao.getAllClient(clientRepository);
    }

    @GetMapping("/client/id")
    public Client idClient(@RequestParam long id) {
        return dao.getByIdClient(clientRepository, id);
    }


}
