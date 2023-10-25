import java.io.*;
import java.util.*;

public class ChatApplication {
    private static final String PRIVATE_CHAT_FILE = "C:\\Users\\user\\Desktop\\lab1\\Donut[AFK].log";
    private static final String PUBLIC_CHAT_FILE = "C:\\Users\\user\\Desktop\\lab1\\Eurakarte.log";
    private static final String FRIENDS_LIST_FILE = "C:\\Users\\user\\Desktop\\Lab1\\friends.list";

    public static void main(String[] args) {
        ChatApplication app = new ChatApplication();
        app.start();
    }

    private void start() {
        List<String> privateChat = readChatFromFile(PRIVATE_CHAT_FILE);
        List<String> publicChat = readChatFromFile(PUBLIC_CHAT_FILE);
        List<String> friendsList = readFriendsList(FRIENDS_LIST_FILE);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. View private chat");
            System.out.println("2. View public chat");
            System.out.println("3. View friends list");
            System.out.println("4. Write to private chat");
            System.out.println("5. Write to public chat");
            System.out.println("6. Add a friend");
            System.out.println("7. Exit");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character after reading the option

            switch (option) {
                case 1:
                    viewChat(privateChat, "Private Chat");
                    break;
                case 2:
                    viewChat(publicChat, "Public Chat");
                    break;
                case 3:
                    viewFriendsList(friendsList);
                    break;
                case 4:
                    writeToChat(scanner, PRIVATE_CHAT_FILE);
                    break;
                case 5:
                    writeToChat(scanner, PUBLIC_CHAT_FILE);
                    break;
                case 6:
                    addFriend(scanner);
                    break;
                case 7:
                    System.out.println("Exiting the application...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private List<String> readChatFromFile(String fileName) {
        List<String> chat = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                chat.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading chat from file: " + fileName);
        }

        return chat;
    }

    private List<String> readFriendsList(String fileName) {
        List<String> friendsList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                friendsList.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading friends list from file: " + fileName);
        }

        return friendsList;
    }

    private void viewChat(List<String> chat, String chatName) {
        System.out.println(chatName + ":");
        for (String message : chat) {
            System.out.println(message);
        }
        System.out.println();
    }

    private void viewFriendsList(List<String> friendsList) {
        System.out.println("Friends List:");
        for (String friend : friendsList) {
            System.out.println(friend);
        }
        System.out.println();
    }

    private void writeToChat(Scanner scanner, String fileName) {
        System.out.println("Enter your message:");
        String message = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(message);
            writer.newLine();
            System.out.println("Message written to chat successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to chat file: " + fileName);
        }
    }
    private void addFriend(Scanner scanner) {
        System.out.println("Enter the name of the friend:");
        String friendName = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FRIENDS_LIST_FILE, true))) {
            writer.write(friendName);
            writer.newLine();
            System.out.println("Friend added successfully.");
        } catch (IOException e) {
            System.out.println("Error adding friend to the friends list.");
        }
    }
}