package config;

import dao.DaoApi;
import dao.DaoFile;
import dao.DaoImpl;
import dao.DaoImpl2;
import dao.IDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DynamicDaoConfig {

    // Hiérarchie : Variable d'environnement (DAO_TARGET) -> Fichier app.properties (dao.target) -> Valeur par défaut ("dao")
    @Value("${DAO_TARGET:${dao.target:dao}}")
    private String daoTarget;

    @Bean
    @Primary
    public IDao dynamicDao() {
        System.out.println("[DYNAMIC] target = " + daoTarget);
        switch (daoTarget) {
            case "dao":
                return new DaoImpl();
            case "dao2":
                return new DaoImpl2();
            case "daoFile":
                return new DaoFile();
            case "daoApi":
                return new DaoApi();
            default:
                throw new IllegalArgumentException("Cible DAO inconnue: " + daoTarget);
        }
    }
}
