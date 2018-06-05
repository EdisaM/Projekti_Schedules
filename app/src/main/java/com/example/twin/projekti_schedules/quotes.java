package com.example.twin.projekti_schedules;

public class quotes
        {

            private String quote;
            private String author;

            public quotes(String quote, String author){
                super();
                this.quote=quote;
                this.author=author;
            }

            public String getQuote() {
                return quote;
            }

            public void setQuote(String quote) {
                this.quote = quote;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }
        }
