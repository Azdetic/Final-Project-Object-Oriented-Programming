import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberDataMenu {
    private Scanner scanner;
    private List<Member> members;

    public MemberDataMenu() {
        this.scanner = new Scanner(System.in);
        this.members = new ArrayList<>();
        // Add sample member data
        members.add(new Member("123", "Wira"));
    }

    public void show() {
        int choice;
        do {
            System.out.println("=========== Member Data ===========");
            System.out.println("1. Check Member");
            System.out.println("2. Register Member");
            System.out.println("3. List Member");
            System.out.println("4. Back");
            System.out.print("Enter your choice: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid choice, choose 1-4: ");
                scanner.next(); // Consume invalid input
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    checkMember();
                    break;
                case 2:
                    registerMember();
                    break;
                case 3:
                    listMembers();
                    break;
                case 4:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice, choose 1-4:");
            }
        } while (choice != 4);
    }

    private void checkMember() {
        String response;
        do {
            System.out.println("=========== Check Member ===========");
            System.out.print("Phone Number: ");
            String phoneNumber = scanner.nextLine();

            Member member = findMemberByPhoneNumber(phoneNumber);
            if (member != null) {
                System.out.println("Member found: " + member);
            } else {
                System.out.println("PhoneNumber don't have any Member");
                System.out.print("Do you want to make a member? (y/n): ");
                response = scanner.nextLine();
                if (response.equalsIgnoreCase("y")) {
                    registerMember();
                }
            }

            System.out.print("Want to check another member? (y/n): ");
            response = scanner.nextLine();
        } while (response.equalsIgnoreCase("y"));
    }

    private void registerMember() {
        System.out.println("=========== Register Member ===========");
        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();
        
        Member existingMember = findMemberByPhoneNumber(phoneNumber);
        if (existingMember != null) {
            System.out.println("Number already registered with name: " + existingMember.getName());
            return;
        }

        System.out.print("Name: ");
        String name = scanner.nextLine();

        members.add(new Member(phoneNumber, name));
        System.out.println("Member registered: " + name + " (" + phoneNumber + ")");
    }

    private void listMembers() {
        System.out.println("=========== List Member ===========");
        int count = 1;
        for (Member member : members) {
            System.out.println(count + ". " + member.getPhoneNumber() + " " + member.getName());
            count++;
        }
    }

    public Member findMemberByPhoneNumber(String phoneNumber) {
        for (Member member : members) {
            if (member.getPhoneNumber().equals(phoneNumber)) {
                return member;
            }
        }
        return null;
    }

    public boolean isMember(String phoneNumber) {
        return findMemberByPhoneNumber(phoneNumber) != null;
    }

    public class Member {
        private String phoneNumber;
        private String name;

        public Member(String phoneNumber, String name) {
            this.phoneNumber = phoneNumber;
            this.name = name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name + " (" + phoneNumber + ")";
        }
    }
}
