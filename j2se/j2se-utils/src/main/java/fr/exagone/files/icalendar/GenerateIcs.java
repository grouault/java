package fr.exagone.files.icalendar;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Iterator;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.ParameterList;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.parameter.Encoding;
import net.fortuna.ical4j.model.parameter.Value;
import net.fortuna.ical4j.model.property.Attach;
import net.fortuna.ical4j.model.property.Categories;
import net.fortuna.ical4j.model.property.Clazz;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.Geo;
import net.fortuna.ical4j.model.property.Location;
import net.fortuna.ical4j.model.property.Summary;
import net.fortuna.ical4j.model.property.Url;
import net.fortuna.ical4j.model.property.Version;


public class GenerateIcs {

    public static void main(String[] args) {
        
        try {
            
          String calFile = "d:/tmp/mycalendar.ics";
          String attachFile = "D:/tmp/FirstPdf.pdf";
          
          //Creating a new calendar
          Calendar calendar = new net.fortuna.ical4j.model.Calendar();
          // calendar.getProperties().add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN"));
          calendar.getProperties().add(Version.VERSION_2_0);
          // calendar.getProperties().add(CalScale.GREGORIAN);
          
          //Class : PUBLIC
          // calendar.
          // calendar.getProperties().add(Property.CLASS);
          // calendar.getProperties().getProperty(Property.CLASS).setValue();
          
          //Creating an event
          java.util.Calendar cal = java.util.Calendar.getInstance();
          cal.set(java.util.Calendar.MONTH, java.util.Calendar.DECEMBER);
          cal.set(java.util.Calendar.DAY_OF_MONTH, 25);

          VEvent christmas = new VEvent(new Date(cal.getTime()), new Date(cal.getTime()), "Christmas Day");
          
          // initialise as an all-day event..
          christmas.getProperties().getProperty(Property.DTSTART).getParameters().add(Value.DATE);
          christmas.getProperties().getProperty(Property.DTEND).getParameters().add(Value.DATE);

          // Clazz.
          Clazz clazz = new Clazz();
          clazz.setValue(Clazz.PUBLIC.getValue());
          christmas.getProperties().add(clazz);
          
          // CATEGORIE
          Categories categories = new Categories("TypeEvent");
          christmas.getProperties().add(categories);

          // DESCRIPTION
          Description description = new Description("description-value");
          christmas.getProperties().add(description);
          
          // SUMMURY
          Summary summury = new Summary();
          summury.setValue("mon résumé");
          christmas.getProperties().add(summury);
          
          // LOCATION
          Location location = new Location("location");
          christmas.getProperties().add(location);
          
          // URL
          Url urlEvent = new Url();
          urlEvent.setValue("http://test.url");
          christmas.getProperties().add(urlEvent);
          
          // ATTACH. : adresse de la plaquette.
          Attach attach = new Attach();
          FileInputStream fis = new FileInputStream(attachFile);
          ByteArrayOutputStream bout = new ByteArrayOutputStream();
          for (int i = fis.read(); i >= 0;) {
              bout.write(i);
              i = fis.read();
          }
          net.fortuna.ical4j.model.ParameterList params = new ParameterList();
          params.add (Value.BINARY);
          params.add(Encoding.BASE64);
          Attach attachObj = new Attach(params, bout.toByteArray());
          christmas.getProperties().add(attachObj);
          
          // GEO
          Geo geo = new Geo();
          geo.setLongitude(256.10f);
          geo.setLattitude(450.01f);
          christmas.getProperties().add(geo);
         
          
          // UidGenerator uidGenerator = new UidGenerator("1");
          // christmas.getProperties().add(uidGenerator.generateUid());

          calendar.getComponents().add(christmas);
          
          //Saving an iCalendar file
          FileOutputStream fout;
          fout = new FileOutputStream(calFile);


          CalendarOutputter outputter = new CalendarOutputter();
          outputter.setValidating(false);
          outputter.output(calendar, fout);
          
          //Now Parsing an iCalendar file
          FileInputStream fin = new FileInputStream(calFile);

          CalendarBuilder builder = new CalendarBuilder();

          calendar = builder.build(fin);
          
          //Iterating over a Calendar
          /*
          for (Iterator i = calendar.getComponents().iterator(); i.hasNext();) {
              Component component = (Component) i.next();
              System.out.println("Component [" + component.getName() + "]");

              for (Iterator j = component.getProperties().iterator(); j.hasNext();) {
                  Property property = (Property) j.next();
                  System.out.println("Property [" + property.getName() + ", " + property.getValue() + "]");
              }
          }//for
         */
         
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ValidationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        
    }
    
}

