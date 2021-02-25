package com.shpp.p2p.cs.ikripaka.assignment11;

import com.shpp.p2p.cs.ikripaka.assignment16.CustomStack;

import java.util.StringTokenizer;

/**
 * Divides line with equation to note in RPN
 */
class Parser extends Constants {
    private StringBuilder parsedLine = new StringBuilder();

    Parser(String startLine) {
        parseLine(startLine);
    }

    /**
     * Gets parsed line
     *
     * @return - parsed line
     */
    String getParsedLine() {
        return parsedLine.toString();
    }

    /**
     * Divides line into RPN
     */
    private void parseLine(String line) {
        CustomStack<String> stack = new CustomStack<>();
        StringTokenizer tokenizer = new StringTokenizer(line, "/*^()-+", true);
        String currentToken = null, prevToken;

        while (tokenizer.hasMoreTokens()) {
            prevToken = currentToken;
            currentToken = tokenizer.nextToken();

            if ((matches(currentToken, NUMBER) || matches(currentToken, LETTERS)) && !FUNCTIONS.contains(currentToken)) {
                parsedLine.append(currentToken).append(" ");
                continue;
            }

            if (matches(currentToken, ALL_ARITHMETICAL_OPERATIONS) || FUNCTIONS.contains(currentToken)) {

                if(!stack.isEmpty() && FUNCTIONS.contains(currentToken)){
                    stack.push(currentToken);
                    continue;
                }

                if ((prevToken == null && currentToken.equals("-"))
                        || (prevToken != null && prevToken.equals("(") && currentToken.equals("-"))) {
                    parsedLine.append("0").append(" ");
                    stack.push("-");
                    continue;
                }

                if (currentToken.equals("(")) {
                    stack.push(currentToken);
                    continue;
                }

                if (currentToken.equals(")")) {
                    while (!stack.peek().equals("(")) {
                        parsedLine.append(stack.pop()).append(" ");
                    }
                    stack.pop();
                    if(!stack.isEmpty() && FUNCTIONS.contains(stack.peek())){
                        parsedLine.append(stack.pop()).append(" ");
                    }

                    continue;
                }

                if (!stack.isEmpty() && stack.peek().equals("^") && currentToken.equals("^")) {
                    stack.push(currentToken);
                    continue;
                }

                while (!stack.isEmpty() && (getPriority(stack.peek()) > getPriority(currentToken)
                        || getPriority(stack.peek()) == getPriority(currentToken))){
                    parsedLine.append(stack.pop()).append(" ");
                }
                stack.push(currentToken);
            }
        }

        while (!stack.isEmpty()) {
            parsedLine.append(stack.peek());
            if (!stack.isEmpty()) parsedLine.append(" ");
            stack.pop();
        }
    }

    /**
     * Determine what prioritise in arithmetical operation
     *
     * @param operation - arithmetical operation
     * @return - number of operation priority
     */
    private int getPriority(String operation) {
        switch (operation) {
            case "-":
            case "+":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
            default:
                return 0;
        }
    }
}
