package test_topology;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import topology_library.Topology;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * <span class="strong">Topology Test!</span>
 * Automated Testing Class tests the Topology class.
 *
 * @author Kareem Sherif
 * @version 1.0
 * @since 2021-09-28
 */
public class TopologyTest {

    /**
     * Tests topologyID var in Topology class.
     * @throws IOException on reading from file failure.
     */
    @Test
    @DisplayName("Test Topology ID")
    public void testTopologyID() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String tpgJson = new String(Files.readAllBytes(Paths.get("src/main/resources/tpg_1.json")));

        JsonNode tpgJsonNode = objectMapper.readTree(tpgJson);
        Topology topology = new Topology(tpgJsonNode);

        Assertions.assertEquals(topology.getId(), "tpg_1");
    }

    /**
     * Tests componentsJsonNode var in Topology class.
     * @throws IOException on reading from file failure.
     */
    @Test
    @DisplayName("Test Topology Components")
    public void testTopologyComponents() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String tpgJson = new String(Files.readAllBytes(Paths.get("src/main/resources/tpg_1.json")));

        JsonNode tpgJsonNode = objectMapper.readTree(tpgJson);
        Topology topology = new Topology(tpgJsonNode);

        Assertions.assertEquals(topology.getComponentsJsonNode().toString(), "[{\"type\":\"resistor\",\"id\":\"res1\",\"resistance\":{\"default\":100,\"min\":10,\"max\":1000},\"netlist\":{\"t1\":\"vdd\",\"t2\":\"n1\"}},{\"type\":\"nmos\",\"id\":\"m1\",\"m(l)\":{\"default\":1.5,\"min\":1,\"max\":2},\"netlist\":{\"drain\":\"n1\",\"gate\":\"vin\",\"source\":\"vss\"}}]");
    }

}
