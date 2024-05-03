public class Tiles {
        private String name;
        private String[] dialogue;
        private char symbol;
        public Tiles(String name, char symbol) {
            this.name = name;
            this.symbol = symbol;
        }
        public void setName(String name) {
            this.name = name;
        }
        public void setSymbol(char symbol) {
            this.symbol = symbol;
        }
        public String getName() {
            return name;
        }
        public char getSymbol() {
            return symbol;
        }
}
