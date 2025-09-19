class Spreadsheet {
    private int[][] grid;
    private int rows;

    public Spreadsheet(int rows) {
        this.rows = rows;
        this.grid = new int[rows][26];
    }
    
    private int getColumnIndex(char column) {
        return column - 'A';
    }
    
    public void setCell(String cell, int value) {
        int columnIndex = getColumnIndex(cell.charAt(0));
        int rowIndex = Integer.parseInt(cell.substring(1)) - 1;
        grid[rowIndex][columnIndex] = value;
    }
    
    public void resetCell(String cell) {
        int columnIndex = getColumnIndex(cell.charAt(0));
        int rowIndex = Integer.parseInt(cell.substring(1)) - 1;
        grid[rowIndex][columnIndex] = 0;
    }
    
    public int getValue(String formula) {
        String expression = formula.substring(1);
        String[] parts = expression.split("\\+");
        int firstOperand = parseOperand(parts[0]);
        int secondOperand = parseOperand(parts[1]);
        return firstOperand + secondOperand;
    }
    
    private int parseOperand(String operand) {
        if (Character.isLetter(operand.charAt(0))) {
            int columnIndex = getColumnIndex(operand.charAt(0));
            int rowIndex = Integer.parseInt(operand.substring(1)) - 1;
            return grid[rowIndex][columnIndex];
        } else {
            return Integer.parseInt(operand);
        }
    }
}