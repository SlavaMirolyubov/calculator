package src;

class Priority {

    int getPriority(String str) {
        switch (str) {
            case "*" :
            case "/" :
                return 3;
            case "-" :
            case "+" :
                return 2;
            case "(" :
                return 1;
        }
        return 0;
    }
}
