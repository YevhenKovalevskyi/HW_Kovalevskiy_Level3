package ru.gb.hw04.task2.database.datasource;

import lombok.extern.slf4j.Slf4j;
import ru.gb.hw04.task2.helpers.FilesHelper;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

@Slf4j
public class DataSourceFactory implements DataSourceInterface {
    
    public DataSource getMySQLDataSource() {
        Properties props = new Properties();
        MysqlDataSource mysqlDS = null;
        
        String filePath = FilesHelper.getResourceRootFilePathGradle(
                DataSourceFactory.class, "DataSourceFactory.class", "jdbc.properties"
        );
        log.info("Got path to jdbc.properties resource file.");

        try (FileInputStream fis = new FileInputStream(filePath)) {
            props.load(fis);
            log.info("Loaded jdbc.properties resource file.");

            mysqlDS = new MysqlDataSource();
            mysqlDS.setURL(props.getProperty("jdbc.mysql.url"));
            mysqlDS.setUser(props.getProperty("jdbc.mysql.user"));
            mysqlDS.setPassword(props.getProperty("jdbc.mysql.password"));
            mysqlDS.setServerTimezone("UTC");
            log.info("The MysqlDataSource object is created and the properties are set based on resource file.");
        } catch (IOException e) {
            log.error("jdbc.properties file not found / loaded error.");
            e.printStackTrace();
        } catch (SQLException e) {
            log.error("MySQL DataSource setServerTimezone error.");
            e.printStackTrace();
        }
        
        return mysqlDS;
    }
    
    public DataSource getPgSQLDataSource() {
        Properties props = new Properties();
        PGSimpleDataSource pgsqlDS = null;
        
        String filePath = FilesHelper.getResourceRootFilePathGradle(
                DataSourceFactory.class, "DataSourceFactory.class", "jdbc.properties"
        );
        log.info("Got path to jdbc.properties resource file.");
        
        try (FileInputStream fis = new FileInputStream(filePath)) {
            props.load(fis);
            log.info("Loaded jdbc.properties resource file.");
            
            pgsqlDS = new PGSimpleDataSource();
            pgsqlDS.setUrl(props.getProperty("jdbc.pgsql.url"));
            pgsqlDS.setUser(props.getProperty("jdbc.pgsql.user"));
            pgsqlDS.setPassword(props.getProperty("jdbc.pgsql.password"));
            log.info("The PGSimpleDataSource object is created and the properties are set based on resource file.");
        } catch (IOException e) {
            log.error("jdbc.properties file not found / loaded error.");
            e.printStackTrace();
        }
        
        return pgsqlDS;
    }
}
