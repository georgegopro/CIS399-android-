package edu.uoregon.cnf.tidetracker;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Christopher on 7/7/2016.
 */
public class DataParser extends DefaultHandler {
    private ParsedData dataCollection;
    private DataItem dataItem;

    private boolean titleReadFlag = false;
    private boolean dateReadFlag = false;

    private boolean isDate = false;
    private boolean isDay = false;
    private boolean isTime = false;
    private boolean isFeet = false;
    private boolean isCentimeters = false;
    private boolean isHighLow = false;

    public ParsedData getFeed() {
        return dataCollection;
    }

    @Override
    public void startDocument() throws SAXException {
        dataCollection = new ParsedData();
        dataItem = new DataItem();
    }

    @Override
    public void startElement(String namespaceURI, String localName,
                             String qName, Attributes atts) throws SAXException {

        if (qName.equals("item")) {
            dataItem = new DataItem();
            return;
        }
        else if (qName.equals("date")) {
            isDate = true;
            return;
        }
        else if (qName.equals("day")) {
            isDay = true;
            return;
        }
        else if (qName.equals("time")) {
            isTime = true;
            return;
        }
        else if (qName.equals("predictions_in_ft")) {
            isFeet = true;
            return;
        }
        else if (qName.equals("predictions_in_cm")) {
            isCentimeters = true;
            return;
        }
        else if (qName.equals("highlow")){
            isHighLow = true;
            return;
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName,
                           String qName) throws SAXException
    {
        if (qName.equals("item")) {
            dataCollection.addItem(dataItem);
            return;
        }
    }


    @Override
    public void characters(char ch[], int start, int length) {
        String s = new String(ch, start, length);
        if (isDate) {
            dataItem.setDate(s);
            isDate = false;
        }
        if (isDay) {
            String dayName = null;
            switch(s)
            {
                case "Sun":
                    dayName = "Sunday";
                    break;
                case "Mon":
                    dayName = "Monday";
                    break;
                case "Tue":
                    dayName = "Tuesday";
                    break;
                case "Wed":
                    dayName = "Wednesday";
                    break;
                case "Thu":
                    dayName = "Thursday";
                    break;
                case "Fri":
                    dayName = "Friday";
                    break;
                case "Sat":
                    dayName = "Saturday";
                    break;
            }
            dataItem.setDay(dayName);
            isDay = false;
        }
        else if (isTime) {
            dataItem.setTime(s);
            isTime = false;
        }
        else if (isFeet) {
            dataItem.setFeet(s);
            isFeet = false;
        }
        else if (isCentimeters) {
            dataItem.setCentimeters(s);
            isCentimeters = false;
        }
        else if (isHighLow){
            String highLow = null;
            char c = s.charAt(0);
            if(c == 'H')
                highLow = "High";
            else
                highLow = "Low";
            dataItem.setHighlow(highLow);
            isHighLow = false;
        }
    }
}
