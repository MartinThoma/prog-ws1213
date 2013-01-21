package fiveinarow;

public enum Token {
    JOKER {
        @Override
        public String toString() {
            return "J";
        }
    }, STOPPER {
        @Override
        public String toString() {
            return "x";
        }
    }, WHITE {
        @Override
        public String toString() {
            return "w";
        }
    }, BLACK {
        @Override
        public String toString() {
            return "b";
        }
    }, EMPTY {
        @Override
        public String toString() {
            return " ";
        }
    };
}
