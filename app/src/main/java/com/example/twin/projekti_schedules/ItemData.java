package com.example.twin.projekti_schedules;

public class ItemData {

        String text;
        Integer imageId;
        public ItemData(String text, Integer imageId){
            this.text=text;
            this.imageId=imageId;
        }

        public String getText(){
            return text;
        }

        public Integer getImageId(){
            return imageId;
        }
    }

