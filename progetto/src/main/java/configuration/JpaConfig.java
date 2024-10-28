package configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import entity.dao.TrenoDAO;
import entity.dao.UserDAO;
import entity.dao.VagoneDAO;
import entity.dao.VotoDAO;
import entity.dao.AcquistoDAO;
import entity.dao.AdminDAO;
import entity.dao.ServizioDAO;
import jakarta.persistence.EntityManagerFactory;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class JpaConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/Hibernate_db");
        dataSource.setUsername("root");
        dataSource.setPassword("M1c0ll3g0_"); //ognuno deve mettere la propria password 46U34%ubsIp2 M1c0ll3g0_ Mauro01

        return dataSource;
    }

    
    @Bean(name="entityManager")
    @Primary
    public LocalContainerEntityManagerFactoryBean getEntityManager() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        // JDBC
        factoryBean.setDataSource(dataSource());
        // impostare il luogo dove si trovano le entity con il mapping
        factoryBean.setPackagesToScan("entity.*"); //   

        // imposta il dialogo tra JPA e hibernate
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

        // facoltativo, attiva il DDL cioe' hibernate creera' le strutture nel DB se non sono gia' essitenti
        properties.setProperty("hibernate.hbm2ddl.auto", "update");

        // mostra l'SQL, comodo per i corsi e per il debug ma in produzione solitamente � a false
        properties.setProperty("hibernate.show_sql", "true");

        factoryBean.setJpaProperties(properties);
        
        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }


     @Bean(name="trenoDAO")
    public TrenoDAO getTrenoDao() {
    	TrenoDAO dao = new TrenoDAO();
        return dao;
    }

    @Bean(name="vagoneDAO")
    public VagoneDAO getVagoneDAO() {
    	VagoneDAO dao = new VagoneDAO();
        return dao;
    }

    @Bean(name="servizioDAO")
    public ServizioDAO getServizioDAO() {
    	ServizioDAO dao = new ServizioDAO();
        return dao;
    }
    
    @Bean(name="userDAO")
    public UserDAO getUserDAO() {
    	UserDAO dao = new UserDAO();
        return dao;
    }
  
    @Bean(name="votoDAO")
    public VotoDAO getVotoDAO() {
    	VotoDAO dao = new VotoDAO();
        return dao;
    }

    
    @Bean(name="acquistoDAO")
    public AcquistoDAO getAcquistoDAO() {
    	AcquistoDAO dao = new AcquistoDAO();
        return dao;
    }
    
    @Bean(name="adminDAO")
    public AdminDAO adminDAO() {
    	AdminDAO dao = new AdminDAO();
        return dao;
    }

}

