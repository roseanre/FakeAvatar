import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String csvFile = "D:\\Kumar\\fake_data.csv";
        List<FakeAvatar> fakeAvatarList = readDataFromCSV(csvFile);

        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("Choose an option:");
            System.out.println("1. Add Person");
            System.out.println("2. Display Person Details");
            System.out.println("3. Exit");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (option) {
                case 1:
                    addPerson(fakeAvatarList, scanner);
                    break;
                case 2:
                    displayPersonDetails(fakeAvatarList);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }

        saveDataToCSV(fakeAvatarList, csvFile);
        System.out.println("Program exited.");
    }

    public static List<FakeAvatar> readDataFromCSV(String csvFile)
    {
        List<FakeAvatar> fakeAvatarList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;

            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length != 6) {
                    // Invalid data row, handle or skip as needed
                    continue;
                }

                int id = Integer.parseInt(data[0].trim());
                String firstName = data[1].trim();
                String lastName = data[2].trim();
                String dateOfBirth = data[3].trim();
                String phoneNumber = data[4].trim();
                String address = data[5].trim();

                FakeAvatar fakeAvatar = new FakeAvatar(id, firstName, lastName, dateOfBirth, phoneNumber, address);
                fakeAvatarList.add(fakeAvatar);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fakeAvatarList;
    }

    public static void displayPersonDetails(List<FakeAvatar> fakeAvatarList) {
        for (FakeAvatar fakeAvatar : fakeAvatarList) {
            System.out.println(fakeAvatar);
        }
    }

    public static void addPerson(List<FakeAvatar> fakeAvatarList, Scanner scanner) {
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter Date of Birth: ");
        String dateOfBirth = scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        int nextId = getNextId(fakeAvatarList);
        FakeAvatar newFakeAvatar = new FakeAvatar(nextId, firstName, lastName, dateOfBirth, phoneNumber, address);

        fakeAvatarList.add(newFakeAvatar);
        System.out.println("Person added successfully.");
    }

    public static void saveDataToCSV(List<FakeAvatar> fakeAvatarList, String csvFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile, true))) {
            for (FakeAvatar fakeAvatar : fakeAvatarList) {
                String line = String.format("%d,%s,%s,%s,%s,%s",
                        fakeAvatar.getId(),
                        fakeAvatar.getFirstName(),
                        fakeAvatar.getLastName(),
                        fakeAvatar.getDateOfBirth(),
                        fakeAvatar.getPhoneNumber(),
                        fakeAvatar.getAddress());

                writer.write(line);
                writer.newLine();
            }

            System.out.println("Data saved to the CSV file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static int getNextId(List<FakeAvatar> fakeAvatarList) {
        int maxId = 0;
        for (FakeAvatar fakeAvatar : fakeAvatarList) {
            if (fakeAvatar.getId() > maxId) {
                maxId = fakeAvatar.getId();
            }
        }
        return maxId + 1;
    }
}
class FakeAvatar {
    private int id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String phoneNumber;
    private String address;

    public FakeAvatar(int id, String firstName, String lastName, String dateOfBirth, String phoneNumber, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}