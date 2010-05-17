package elex;

public class YytokenElement {

        private int token;
        private String text;

        public YytokenElement(int token, String text) {
                this.token = token;
                this.text  = text;
        }

        public int getToken() {
                return token;
        }

        public String getText() {
                return text;
        }

}

