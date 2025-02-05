import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Library {
    private String title;
    private String author;
    private int copies;
    private int borrowed;

    public Library() {}

    public Library(String title, String author, int copies, int borrowed) {
        this.title = title;
        this.author = author;
        this.copies = copies;
        this.borrowed = borrowed;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public int getCopies() { return copies; }
    public void setCopies(int copies) { this.copies = copies; }
    public int getBorrowed() { return borrowed; }
    public void setBorrowed(int borrowed) { this.borrowed = borrowed; }

    public boolean borrowBook() {
        if (copies > borrowed) {
            borrowed++;
            return true;
        }
        return false;
    }

    public boolean returnBook() {
        if (borrowed > 0) {
            borrowed--;
            return true;
        }
        return false;
    }

    public void display() {
        System.out.println("Title: " + title + ", Author: " + author + ", Copies: " + copies + ", Borrowed: " + borrowed);
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Library book1 = new Library();
        Library book2 = new Library();
        
        System.out.println("Enter the title of the first book:");
        book1.setTitle(scanner.nextLine());
        System.out.println("Enter the author of the first book:");
        book1.setAuthor(scanner.nextLine());
        System.out.println("Enter the number of copies of the first book:");
        book1.setCopies(scanner.nextInt());
        book1.setBorrowed(0);
        scanner.nextLine(); 
        
        System.out.println("Enter the title of the second book:");
        book2.setTitle(scanner.nextLine());
        System.out.println("Enter the author of the second book:");
        book2.setAuthor(scanner.nextLine());
        System.out.println("Enter the number of copies of the second book:");
        book2.setCopies(scanner.nextInt());
        book2.setBorrowed(0);
        scanner.nextLine(); 
        
        Library book3 = new Library("1984", "George Orwell", 10, 2);
        Library book4 = new Library("The Little Prince", "Antoine de Saint-Exupéry", 5, 1);
        
        book1.display();
        book2.display();
        book3.display();
        book4.display();

        System.out.println("Attempting to borrow a book...");
        if (book1.borrowBook()) {
            System.out.println("Book successfully borrowed.");
        } else {
            System.out.println("No copies available.");
        }
        book1.display();

        Scheduler scheduler = new Scheduler();

        System.out.print("Enter the number of passwords to generate: ");
        int count = scanner.nextInt();

        for (int i = 0; i < count; i++) {
            System.out.print("Enter the length of password " + (i + 1) + ": ");
            int length = scanner.nextInt();
            scheduler.addPassword(length);
        }

        scheduler.listPasswords();
        
        scanner.close();
    }
}

class Account {
    private String holder;
    private double balance;

    public Account() {
        this.holder = "";
        this.balance = 0.0;
    }

    public Account(String holder, double balance) {
        this.holder = holder;
        this.balance = balance;
    }

    public String getHolder() { return holder; }
    public void setHolder(String holder) { this.holder = holder; }
    public double getBalance() { return balance; }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public boolean transfer(Account destination, double amount) {
        if (this.withdraw(amount)) {
            destination.deposit(amount);
            return true;
        }
        return false;
    }
}

class Password {
    private int length;
    private String password;

    public Password() {
        this.length = 8;
        generatePassword();
    }

    public Password(int length) {
        this.length = length;
        generatePassword();
    }

    public Password(int length, String password) {
        this.length = length;
        this.password = password;
    }

    public int getLength() { return length; }
    
    public void setLength(int length) {
        this.length = length;
        generatePassword();
    }

    public String getPassword() { return password; }

    public boolean isStrong() {
        int uppercase = 0, lowercase = 0, digits = 0;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) uppercase++;
            else if (Character.isLowerCase(c)) lowercase++;
            else if (Character.isDigit(c)) digits++;
        }
        return uppercase >= 2 && lowercase >= 1 && digits >= 5;
    }

    private void generatePassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        this.password = sb.toString();
    }
}

class Scheduler {
    private ArrayList<Password> passwords;
    
    public Scheduler() {
        passwords = new ArrayList<>();
    }

    public void addPassword(int length) {
        passwords.add(new Password(length));
    }

    public void listPasswords() {
        for (int i = 0; i < passwords.size(); i++) {
            System.out.println("Password " + (i + 1) + ": " + passwords.get(i).getPassword() +
                    " | Strong: " + (passwords.get(i).isStrong() ? "Yes" : "No"));
        }
    }
}