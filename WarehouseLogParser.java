import java.util.Scanner;


public class WarehouseLogParser {
    
    // Using a scanner to fill an array
    public static void enterInformation(String[] logs, Scanner scan){
        int i = 0;
        while (i < logs.length) {
            logs[i] = scan.nextLine();
            i++;
        }
    } // Method end 

    // Extracts fields from inputed data 
    public static String extractField(String logs, String key){
       int start = logs.indexOf(key) + key.length();
       int end = logs.indexOf("|", start);
       if (end == -1){
        end = logs.length();
       }
       return logs.substring(start,end);
    } // Method end 

    // parse int form extracted string 
    public static int parseInt(String raw) {
        return Integer.parseInt(raw);
    } // Method end 

    // Determine threat level base on riskscore
    public static String classifyRisk(int risk, String event) {
        if (event.equals("ACCESS_DENIED") && (risk >= 80 && risk <= 100)) {
            return "CRITICAL";
        } else if (event.equals("TAMPER_ALERT") && (risk >= 65 && risk <= 85)){
            return "HIGH RISK";
        } else if (event.equals("MOTION_DETECTED") && (risk >= 45 && risk <= 65)){
            return "INVESTIGATE";
        } else {
            return "ACCEPTABLE";
        }
    } // Method end 
    
    // Prints report after parse 
    public static void printReport(String device, String event, int score, String classification) {
        System.out.println("");
        System.out.println("Device        : " + device);
        System.out.println("Event         : " + event);
        System.out.println("Riskscore     : " + score);
        System.out.println("Classification: " + classification);
        System.out.println("__________");
    } // Method end 
    
     // Main 
     public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Input data (ensure it follows specifications): ");
        String[] logs = new String[5];
        enterInformation(logs, scan);
        scan.close();

        int criticalCount = 0;
        int highCount = 0;
        int investigateCount = 0;

        for (int i = 0; i < logs.length; i++) {
            String device = extractField(logs[i], "DEVICE:");
            String event = extractField(logs[i], "EVENT:");
            String score = extractField(logs[i], "RISKSCORE:");
            int parsedScore = parseInt(score);
            String classification = classifyRisk(parsedScore, event);
            printReport(device, event, parsedScore, classification);
            
            // Counters for classifications to keep count of threats to adjust systems 
            if (classification.equals("CRITICAL")) {
                criticalCount++;
            }

            if (classification.equals("HIGH RISK")) {
                highCount++;
            }

            if (classification.equals("INVESTIGATE")) {
                investigateCount++;
            }
        } // For end 

        System.out.println("");
        System.out.println("Critical risk: " + criticalCount);
        System.out.println("High risk: " + highCount);
        System.out.println("Investigate: " + investigateCount);
     }// Main end 

} // Class end 

