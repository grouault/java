package fr.exagone.rss.rome;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.rss.Category;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.synd.SyndCategory;
import com.sun.syndication.feed.synd.SyndCategoryImpl;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEnclosure;
import com.sun.syndication.feed.synd.SyndEnclosureImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedOutput;
import com.sun.syndication.io.WireFeedOutput;

public class RssRome {

	public static final String TYPE_FLUX_RSS_1_0 = "rss_1.0";
	public static final String TYPE_FLUX_RSS_2_0 = "rss_2.0";
	
	public static void main(String[] args) {
		
		// CONSTRUCTION D'UN CHANNEL. 
		SyndFeed feed = new SyndFeedImpl();
		// Type
        feed.setFeedType(TYPE_FLUX_RSS_2_0);
        // Title
        feed.setTitle("MyProject Build Results");
        // Link
        feed.setLink("http://myproject.mycompany.com/continuum");
        // Description
        feed.setDescription("Continuous build results for the MyProject project");    
        // language
        feed.setLanguage("fr-FR");
        // date de publication
        feed.setPublishedDate(new Date());
        // catégories
        List<SyndCategory> categoriesFeed = new ArrayList<SyndCategory>();
        SyndCategory categoryFeed = new SyndCategoryImpl();
        categoryFeed.setName("name category 1");
        categoryFeed.setTaxonomyUri("taxonomy 1");
        categoriesFeed.add(categoryFeed);
        SyndCategory categoryFeed2 = new SyndCategoryImpl();
        categoryFeed2.setName("name category 1");
        categoryFeed2.setTaxonomyUri("taxonomy 1");
        categoriesFeed.add(categoryFeed2);
        feed.setCategories(categoriesFeed);
        
        // CONSTRUCTION D'UNE ENTRY.
        List<SyndEntry> entries = new ArrayList<SyndEntry>(); 
        
        // ADDING AN ENTRY.
        SyndEntry entry = new SyndEntryImpl();
        // Title
        entry.setTitle("BUILD SUCCESSFUL");
        // Link et guid
        entry.setLink("http://myproject.mycompany.com/continuum/build-results-1");
        // Description.
        SyndContent description = new SyndContentImpl();
        description.setType("text/html");
        description.setValue("The build was successful!");
        entry.setDescription(description);
        // Category
        List<SyndCategory> categories = new ArrayList<SyndCategory>();
        SyndCategory category = new SyndCategoryImpl();
        category.setName("MyProject");
        categories.add(category);
        entry.setCategories(categories);
        // FIXME : Lien vers la partie commentaire de l'évènement.
        List<String> links = new ArrayList<String>();
        String link = "http://mon-lien-http.com";
        links.add(link);
        entry.setLinks(links);
        
        // FIXME : enclosure
        List<SyndEnclosure> enclosures = new ArrayList<SyndEnclosure>();
        SyndEnclosure enclosure = new SyndEnclosureImpl();
        enclosure.setType("audio/mpeg");
        enclosure.setLength(1000L);
        enclosure.setUrl("http://monenclosure.fr");
        enclosures.add(enclosure);
        entry.setEnclosures(enclosures);
                
        // Date de publication
        entry.setPublishedDate(new Date() );
        
        entries.add(entry);
        
        // AJOUT ENTRY AU FLUX.
        feed.setEntries(entries);
        
        
        WireFeed feedWire = feed.createWireFeed();
        final Channel result = (Channel)feed.createWireFeed();
        result.setTtl(60);
        result.setDocs("ttp://feed2.w3.org/docs/rss2.htm");
        
        // Ecriture du flux.
        Writer writer;
		try {
			writer = new FileWriter("c:/temp/stream.xml");
//	        SyndFeedOutput output = new SyndFeedOutput();
//	        output.output(result,writer);
			WireFeedOutput output = new WireFeedOutput();
			output.output(result,writer);
			writer.close();  
	        System.out.println("Génération du flux OK.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FeedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
        
	}
	
}
