package dao;

import java.sql.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Sérgio Felipe Starke
 */
public class ConexaoTest {

    Connection conn = null;

    @Before
    public void setUp() {
    }

    @Test
    public void testConnection() {
        conn = Conexao.connection();
        assertNotEquals(null, conn);
    }

}
