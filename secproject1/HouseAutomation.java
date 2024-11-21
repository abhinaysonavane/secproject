import java.util.Scanner;

class Light {
    private boolean isOn;

    public void turnOn() {
        isOn = true;
        System.out.println("The light is ON.");
    }

    public void turnOff() {
        isOn = false;
        System.out.println("The light is OFF.");
    }
}

class Fan {
    private boolean isOn;

    public void turnOn() {
        isOn = true;
        System.out.println("The fan is ON.");
    }

    public void turnOff() {
        isOn = false;
        System.out.println("The fan is OFF.");
    }
}

class Thermostat {
    private int temperature;

    public void setTemperature(int temp) {
        this.temperature = temp;
        System.out.println("Thermostat set to " + temperature + "°C.");
    }
}

public class HouseAutomation {
    private Light light;
    private Fan fan;
    private Thermostat thermostat;

    public HouseAutomation() {
        light = new Light();
        fan = new Fan();
        thermostat = new Thermostat();
    }

    public void displayMenu() {
        System.out.println("\nHouse Automation System");
        System.out.println("1. Turn on/off light");
        System.out.println("2. Turn on/off fan");
        System.out.println("3. Set thermostat");
        System.out.println("4. Exit");
    }

    public void controlLight() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Do you want to turn on or off the light? (on/off): ");
        String command = sc.nextLine();
        if (command.equalsIgnoreCase("on")) {
            light.turnOn();
        } else if (command.equalsIgnoreCase("off")) {
            light.turnOff();
        } else {
            System.out.println("Invalid command!");
        }
    }

    public void controlFan() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Do you want to turn on or off the fan? (on/off): ");
        String command = sc.nextLine();
        if (command.equalsIgnoreCase("on")) {
            fan.turnOn();
        } else if (command.equalsIgnoreCase("off")) {
            fan.turnOff();
        } else {
            System.out.println("Invalid command!");
        }
    }

    public void controlThermostat() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the desired temperature: ");
        int temp = sc.nextInt();
        thermostat.setTemperature(temp);
    }

    public static void main(String[] args) {
        HouseAutomation houseAutomation = new HouseAutomation();
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            houseAutomation.displayMenu();
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();  // consume newline left by nextInt()

            switch (choice) {
                case 1:
                    houseAutomation.controlLight();
                    break;
                case 2:
                    houseAutomation.controlFan();
                    break;
                case 3:
                    houseAutomation.controlThermostat();
                    break;
                case 4:
                    System.out.println("Exiting the system...");
                    return;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
}
