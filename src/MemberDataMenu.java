import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberDataMenu {
    private Scanner scanner;
    private List<Member> members;

    public MemberDataMenu() {
        this.scanner = new Scanner(System.in);
        this.members = new ArrayList<>();
        //add sample member data
        members.add(new RegularMember("123", "Group 1"));
    }

    public void show() {
        int choice;
        do {
            System.out.println("=========== Member Data ===========");
            System.out.println("1. Check Member");
            System.out.println("2. Register Member");
            System.out.println("3. List Member");
            System.out.println("4. Delete Member");
            System.out.println("5. Back");
            System.out.print("Enter your choice: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid choice, choose 1-5: ");
                scanner.next(); //consume invalid input
            }
            choice = scanner.nextInt();
            scanner.nextLine(); //consume newline left-over

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
                    deleteMember();
                    break;
                case 5:
                    System.out.println("Exiting..");
                    break;
                default:
                    System.out.println("Invalid choice, choose 1-5");
            }
        } while (choice != 5);
    }

    private void deleteMember() {
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        if (phoneNumber.trim().isEmpty()) {
            System.out.println("Do not blank, data cannot be process");
            return;
        }
        Member memberToDelete = null;
        for (Member member : members) {
            if (member.getPhoneNumber().equals(phoneNumber)) {
                memberToDelete = member;
                break;
            }
        }
        if (memberToDelete != null) {
            System.out.println("Member found: " + memberToDelete.getName());
            System.out.print("Is this information correct? (y/n): ");
            String confirmation = scanner.nextLine();
            if (confirmation.equalsIgnoreCase("y")) {
                members.remove(memberToDelete);
                System.out.println("Member deleted successfully");
            } else {
                System.out.println("Deletion cancelled");
            }
        } else {
            System.out.println("Member not found");
        }
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
        if (phoneNumber.trim().isEmpty()) {
            System.out.println("Do not blank, data cannot be process");
            return;
        }
        Member existingMember = findMemberByPhoneNumber(phoneNumber);
        if (existingMember != null) {
            System.out.println("Number already registered with name: " + existingMember.getName());
            return;
        }
        System.out.print("Name: ");
        String name = scanner.nextLine();
        if (name.trim().isEmpty()) {
            System.out.println("Do not blank, data cannot be process");
            return;
        }
        members.add(new RegularMember(phoneNumber, name));
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

    public class RegularMember extends Member {
        public RegularMember(String phoneNumber, String name) {
            super(phoneNumber, name);
        }
    }
}
