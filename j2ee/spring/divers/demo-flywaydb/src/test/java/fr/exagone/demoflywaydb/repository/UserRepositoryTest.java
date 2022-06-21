package fr.exagone.demoflywaydb.repository;

import fr.exagone.demoflywaydb.config.PersistenceConfigTest;
import fr.exagone.demoflywaydb.model.Utilisateur;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= {PersistenceConfigTest.class})
@SqlConfig(dataSource = "dataSourcePOSTGRE", transactionManager = "transactionManager")
public class UserRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryTest.class);

    @Autowired
    private UserRepository repository;

    @Test
    public void find_casNominal(){
        Utilisateur utilisateur = repository.find(-1L);
        assertThat(utilisateur).as("mauvais film récupéré").isNull();

    }

}
