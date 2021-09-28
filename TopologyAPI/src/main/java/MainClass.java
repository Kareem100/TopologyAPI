import com.fasterxml.jackson.databind.JsonNode;
import topology_library.*; // imports the topology api library

import java.util.List;
import java.util.Scanner;

/**
 * <span class="strong">Main Class!</span>
 * Class for offering six different optionalities to deal with
 * Json files and Topology API.
 *
 * @author Kareem Sherif
 * @version 1.0
 * @since 2021-09-28
 */
public class MainClass {

    private static Scanner scanner;

    /**
     * the main entry point program for establishing
     * an open dialog with the user.
     *
     * @param args the command-line arguments.
     * */
    public static void main(String[] args) {
        // ElectronicDevice instantiation
        ElectronicDevice device = new ElectronicDevice();
        // instantiation of an input object
        scanner = new Scanner(System.in);
        // console dialog for taking actions
        makeUserDialog(device);
    }

    private static void makeUserDialog(ElectronicDevice device) {
        System.out.println("\n=============== START TOPOLOGY API ==============");

        String choice = "1";
        while (!choice.equals("0")) {
            // method for showing what inputs
            // should the user make to take a specific action.
            showInputGuide();
            System.out.print("\nPlease Enter your Choice: ");
            choice = scanner.nextLine();

            switch (choice) {
                case "0": // exit action
                    break;
                case "1": // read action
                    read(device);
                    break;
                case "2": // write action
                    write(device);
                    break;
                case "3": // query about topologies action
                    topologiesQuery(device);
                    break;
                case "4": // delete action
                    deleteTopology(device);
                    break;
                case "5": // query about components action
                    componentsQuery(device);
                    break;
                case "6": // query about devices that contain netlist with a given nodeID
                    netlistQuery(device);
                    break;
                default:
                    System.out.println("Please be Sure to Choose from the above choices.");
            }
        }

        System.out.println("===============  END TOPOLOGY API ==============\n");
    }

    private static void showInputGuide() {
        System.out.println("\n=============================================================================|");
        System.out.println("Enter [1] to Read a topology from a given JSON file.                         |");
        System.out.println("Enter [2] to Write a topology to a JSON file.                                |");
        System.out.println("Enter [3] to Query about all topologies in Memory.                           |");
        System.out.println("Enter [4] to Delete a topology from Memory.                                  |");
        System.out.println("Enter [5] to Query about all devices in a topology.                          |");
        System.out.println("Enter [6] to Query about components connected to netlist node in a topology. |");
        System.out.println("Enter [0] to Exit.                                                           |");
        System.out.println("=============================================================================|");
    }

    private static void read(ElectronicDevice device) {
        System.out.print("[Input] Enter Json file path: ");
        String filePath = scanner.nextLine();
        Topology tpg = device.readFromJSON(filePath);
        if (tpg != null) {
            // System.out.println(tpg.toString()); // printing topology in a readable manner
            System.out.println("[MESSAGE]: File has been read successfully !\n");
        }
    }

    private static void write(ElectronicDevice device) {
        String tpgID = chooseTopology(device);
        if (!tpgID.equals("-1")) {
            System.out.print("[Input] Your New File Path (e.g.C:\\Users\\Kareem\\filename): ");
            String filePath = scanner.nextLine();
            Topology tpg = device.writeToJSON(tpgID, filePath);
            if (tpg != null)
                System.out.println("[MESSAGE]: File has been wrote successfully !\n");
        } else System.out.println("[MESSAGE]: No Topologies in Memory at the Moment.");
    }

    private static void topologiesQuery(ElectronicDevice device) {
        List<Topology> topologies = device.queryTopologies();
        if (topologies.isEmpty())
            System.out.println("[Message]: No Topologies in Memory at the Moment.");
        else for (Topology tp : topologies)
            System.out.println(tp.toString());
    }

    private static void deleteTopology(ElectronicDevice device) {
        String tpgID = chooseTopology(device);
        if (!tpgID.equals("-1")) {
            Topology tpg = device.deleteTopology(tpgID);
            if (tpg != null)
                System.out.println("[MESSAGE]: Topology has been Deleted Successfully !");
        } else
            System.out.println("[Message]: No Topologies in Memory at the Moment.");
    }

    private static void componentsQuery(ElectronicDevice device) {
        String tpgID = chooseTopology(device);
        if (!tpgID.equals("-1")) {
            JsonNode components = device.queryComponents(tpgID);
            if (components != null)
                System.out.println(components.toPrettyString());
            else
                System.out.println("[MESSAGE]: No Components found matched !");
        } else
            System.out.println("[Message]: No Topologies in Memory at the Moment.");
    }

    private static void netlistQuery(ElectronicDevice device) {
        String tpgID = chooseTopology(device);
        if (!tpgID.equals("-1")) {
            System.out.print("[Input] Enter Netlist Node ID: e.g.(\"drain\"): ");
            String netlistNodeID = scanner.nextLine();
            List<JsonNode> components = device.queryDevicesWithNetlistNode(tpgID, netlistNodeID);
            if (components.isEmpty())
                System.out.println("[MESSAGE]: No Match Found !!");
            else
                for (int i = 0; i < components.size(); ++i) {
                    System.out.print(components.get(i).toPrettyString());
                    if (i != components.size()-1) System.out.print(",");
                    System.out.println();
                }
        }
        else
            System.out.println("[Message]: No Topologies in Memory at the Moment.");
    }

    private static String chooseTopology(ElectronicDevice device) {
        List<Topology> topologiesList = device.queryTopologies();
        String tpgID = "-1";
        if (!topologiesList.isEmpty()) {
            System.out.println("[MESSAGE]: Please Choose topologyID From the List Below...");
            for (Topology tpg : topologiesList)
                System.out.println(tpg.getId());

            System.out.print("[Input] Your Topology ID Choice: ");
            tpgID = scanner.nextLine();
        }
        return tpgID;
    }

}
