import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class driver {
    public static void main(String[] args) {
        Network network = new Network(0);
        boolean isPathValid = false;
        do {
            System.out.print("Input file path: ");
            Scanner scanner = new Scanner(System.in);
            String path = scanner.nextLine();
            try {
                // Read file
                boolean readFirstLine = false;
                File myObj = new File("data\\" + path);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    if (!readFirstLine) {
                        int size = Integer.parseInt(data.split(" ")[0]);
                        network = new Network(size);
                        readFirstLine = true;
                    } else {
                        int person1 = Integer.parseInt(data.split(" ")[0]);
                        int person2 = Integer.parseInt(data.split(" ")[1]);
                        network.addConnection(person1, person2);
                    }
                }

                System.out.println("Graph loaded.");
                isPathValid = true;
                int choice = 0;
                while (choice != 3) {
                    System.out.println("\nMAIN MENU");
                    System.out.println("[1] Get friend list");
                    System.out.println("[2] Check connection");
                    System.out.println("[3] Exit\n");
                    System.out.print("Enter choice: ");
                    choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            System.out.print("Enter user ID: ");
                            int userID = scanner.nextInt();
                            if (!network.doesExist(userID)) {
                                System.out.println("User does not exist.");
                                break;
                            }
                            network.getUserFriends(userID);
                            break;
                        case 2:
                            System.out.print("Enter user ID 1: ");
                            int userID1 = scanner.nextInt();
                            if (!network.doesExist(userID1)) {
                                System.out.println("User does not exist.");
                                break;
                            }
                            System.out.print("Enter user ID 2: ");
                            int userID2 = scanner.nextInt();
                            if (!network.doesExist(userID2)) {
                                System.out.println("User does not exist.");
                                break;
                            }
                            network.getConnection(userID1, userID2);
                            break;
                        case 3:
                            System.out.println("Exiting...");
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }
                }
                scanner.close();
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found. Please input a valid file path.");
            }
        } while (!isPathValid);
    }
}
