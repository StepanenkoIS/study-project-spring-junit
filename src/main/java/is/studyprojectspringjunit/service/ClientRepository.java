package is.studyprojectspringjunit.service;

import is.studyprojectspringjunit.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
