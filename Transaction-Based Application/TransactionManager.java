import java.util.*;

public class TransactionManager {

    private Deque<Map<String, String>> transactionStack;
    private Map<String, String> database;
    private Map<String, Integer> valueCount;

    public TransactionManager() {
        transactionStack = new ArrayDeque<>();
        database = new HashMap<>();
        valueCount = new HashMap<>();
    }

    // SET command to assign a value to a variable
    public void set(String variable, String value) {
        String oldValue = database.get(variable);
        if (oldValue != null) {
            decrementValueCount(oldValue);
        }
        database.put(variable, value);
        incrementValueCount(value);
    }

    // GET command to retrieve the value of a variable
    public String get(String variable) {
        return database.getOrDefault(variable, "None");
    }

    // UNSET command to remove a variable
    public void unset(String variable) {
        String value = database.get(variable);
        if (value != null) {
            database.remove(variable);
            decrementValueCount(value);
        }
    }

    // NUMEQUALTO command to get count of variables with a specific value
    public int numEqualTo(String value) {
        return valueCount.getOrDefault(value, 0);
    }

    // BEGIN command to start a new transaction
    public void begin() {
        transactionStack.push(new HashMap<>(database));
    }

    // ROLLBACK command to revert to the state before the most recent transaction
    public void rollback() {
        if (transactionStack.isEmpty()) {
            System.out.println("NO TRANSACTION");
        } else {
            database = transactionStack.pop();
            recalculateValueCount();
        }
    }

    // COMMIT command to make changes permanent and discard transaction history
    public void commit() {
        if (transactionStack.isEmpty()) {
            System.out.println("NO TRANSACTION");
        } else {
            transactionStack.clear();
        }
    }

    // Increments the count of a specific value
    private void incrementValueCount(String value) {
        valueCount.put(value, valueCount.getOrDefault(value, 0) + 1);
    }

    // Decrements the count of a specific value
    private void decrementValueCount(String value) {
        valueCount.put(value, valueCount.get(value) - 1);
        if (valueCount.get(value) == 0) {
            valueCount.remove(value);
        }
    }

    // Recalculate value counts after a rollback
    private void recalculateValueCount() {
        valueCount.clear();
        for (String value : database.values()) {
            incrementValueCount(value);
        }
    }

    public static void main(String[] args) {
        TransactionManager tm = new TransactionManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String command = sc.nextLine();
            String[] tokens = command.split("\\s+");

            switch (tokens[0]) {
                case "SET":
                    tm.set(tokens[1], tokens[2]);
                    break;
                case "GET":
                    System.out.println(tm.get(tokens[1]));
                    break;
                case "UNSET":
                    tm.unset(tokens[1]);
                    break;
                case "NUMEQUALTO":
                    System.out.println(tm.numEqualTo(tokens[1]));
                    break;
                case "BEGIN":
                    tm.begin();
                    break;
                case "ROLLBACK":
                    tm.rollback();
                    break;
                case "COMMIT":
                    tm.commit();
                    break;
                case "END":
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid command.");
            }
        }
    }
}
