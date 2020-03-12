package app.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.persistence.model.OperationDo;

@Repository
public interface OperationRepository extends JpaRepository<OperationDo, String>{

    public OperationDo findByCryptoOperation(String userName);

}
