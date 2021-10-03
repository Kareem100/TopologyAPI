package topology_library;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * <span class="strong">Topology Components!</span>
 * The Topology class implements a set of electronic components
 * that are connected together inside one topology.
 *
 * @author  Kareem Sherif
 * @version 1.0
 * @since   2021-09-28
 */
public class Topology {

    // Jackson JsonNode to represent a topology.
    private final JsonNode tpgJsonNode;
    // topology id.
    private final String id;
    // Jackson JsonNode to represent the components in a topology.
    @JsonProperty("components")
    private final JsonNode componentsJsonNode;

    /**
     * Constructor for initializing topology node and components.
     *
     * @param tpgJsonNode Jackson JsonNode to represent a topology.
     */
    public Topology (JsonNode tpgJsonNode) {
        this.tpgJsonNode = tpgJsonNode;
        this.id = tpgJsonNode.findValue("id").asText();
        this.componentsJsonNode = tpgJsonNode.findValue("components");
    }

    /**
     * Method for converting to a pretty readable string.
     *
     * @return String tpgJsonNode as a string.
     */
    @Override
    public String toString() {
        return tpgJsonNode.toPrettyString();
    }

    //***************** getters ************************//

    /**
     * @return String topology ID
     */
    public String getId() {
        return id;
    }

    /**
     * @return JsonNode topology components
     */
    public JsonNode getComponentsJsonNode() {
        return componentsJsonNode;
    }

}
