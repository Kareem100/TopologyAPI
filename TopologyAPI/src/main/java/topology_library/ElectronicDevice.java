package topology_library;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * <span class="strong">Electronic Device!</span>
 * This class implements a set of functionalities to access,
 * manage and store device topologies.
 *
 * @author Kareem Sherif
 * @version 1.0
 * @since 2021-09-28
 */
public class ElectronicDevice {

    // Jackson ObjectMapper for reading a json file
    private final ObjectMapper objectMapper;

    // List of the stored topologies during runtime
    private final List<Topology> topologiesInMemory;

    // List of files loaded during runtime
    private final List<String> filePaths;

    /**
     * Constructor for making instantiations for
     * ObjectMapper node and topologiesInMemory.
     */
    public ElectronicDevice() {
        objectMapper = new ObjectMapper();
        topologiesInMemory = new ArrayList<>();
        filePaths = new ArrayList<>();
    }

    /**
     * Method for reading a json file into JsonNode.
     *
     * @param fileName Json file path.
     * @return Topology the topology just read or
     * null if it is a duplicate file or incorrect filePath.
     */
    public Topology readFromJSON(String fileName) {
        if (filePaths.contains(fileName)) {
            System.out.println("[ERROR]: Can't upload the same file twice !");
            return null;
        }
        try {
            // helper method to get json data as a string.
            String tpgJson = readJsonFileAsString(fileName);
            JsonNode tpgJsonNode = objectMapper.readTree(tpgJson);
            Topology newTopology = new Topology(tpgJsonNode);
            topologiesInMemory.add(newTopology);
            filePaths.add(fileName);
            return newTopology;

        } catch (Exception e) {
            System.out.println("[ERROR]: Incorrect File Path: " + e.getMessage());
        }
        return null;
    }

    /**
     * Method for reading a json file into JsonNode.
     *
     * @param topologyID  topology id.
     * @param newFilePath path in which to save json data
     * @return Topology the topology just wrote.
     */
    public Topology writeToJSON(String topologyID, String newFilePath) {
        try {
            Topology tpg = getTopologyById(topologyID);
            // helper method to write a topology to json file.
            writeTopologyToJsonFile(tpg, newFilePath);
            return tpg;
        } catch (IOException e) {
            System.out.println("Error Occurred While Writing To Json File.\n" + e.getMessage());
        }
        return null;
    }

    /**
     * Getting all topologies that are currently in memory.
     *
     * @return Topologies list.
     */
    public List<Topology> queryTopologies() {
        return topologiesInMemory;
    }

    /**
     * Method for removing a topology from memory.
     *
     * @param topologyID topology id.
     * @return Topology removed topology.
     */
    public Topology deleteTopology(String topologyID) {
        Topology tpg = getTopologyById(topologyID);
        if (tpg != null)
            topologiesInMemory.remove(tpg);
        return tpg;
    }

    /**
     * Getting all components inside a topology.
     *
     * @param topologyID topology id.
     * @return JsonNode components json node or null on invalid Topology ID.
     */
    public JsonNode queryComponents(String topologyID) {
        Topology tpg = getTopologyById(topologyID);
        if (tpg == null) return null;
        return tpg.getComponentsJsonNode();
    }

    /**
     * Getting all components associated
     * with a netlist with a specific node.
     *
     * @param topologyID    topology id.
     * @param netlistNodeID netlist node id.
     * @return List of Components as a JsonNode.
     */
    public List<JsonNode> queryDevicesWithNetlistNode(String topologyID, String netlistNodeID) {
        List<JsonNode> requiredComponents = new ArrayList<>();
        JsonNode components = queryComponents(topologyID);
        if (components != null && !components.isEmpty())
            for (JsonNode component : components) {
                JsonNode netlist = component.findValue("netlist");
                if (netlist != null)
                    if (netlist.findValue(netlistNodeID) != null)
                        requiredComponents.add(component);
            }
        return requiredComponents;
    }

    // **************** Helper Methods ********************** //

    private String readJsonFileAsString(String file) throws Exception {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    private void writeTopologyToJsonFile(Topology topology, String newFilePath) throws IOException {
        if (topology != null)
            objectMapper.writeValue(new File(newFilePath + ".json"), topology);
    }

    private Topology getTopologyById(String topologyID) {
        for (Topology tpg : topologiesInMemory)
            if (tpg.getId().equals(topologyID))
                return tpg;
        System.out.println("[ERROR]: Incorrect Topology ID !");
        return null;
    }
}
