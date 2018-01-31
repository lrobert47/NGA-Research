	import java.io.File;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.util.ArrayList;

	import org.jsoup.Jsoup;
	import org.jsoup.nodes.Document;
	import org.jsoup.nodes.Element;
	import org.jsoup.select.Elements;
	
public class Crawler_IndianExpress2000 {


		 public static void main(String[] args) throws IOException {
			 
		        // Construct arrays with months and days
		        int[] months = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
		        int[] days = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		        int year = 2008;
		 
		        while (year <= 2008) {
		            int monthIndx = 1;
		            int dayIndx = 1;
		 
		            while (months[monthIndx] <= 12) {
		                int day = 1;
		 
		                while (day <= days[dayIndx]) {
		                    final ArrayList<String> fullLinks = new ArrayList<String>();
		 
		                    // Pull HTML from archive page, Create doc from HTMl
		                    String html = "<html><head></head>" + "<body><p>Parsed HTML into a doc." + "</p></body></html>";
		                    Document doc = Jsoup.connect("http://archive.indianexpress.com/archive/news/" + day + "/"
		                            + months[monthIndx] + "/" + year + "/").timeout(100 * 10000).get();
		 
		                    // Extract all links from the page, add them to arrayList
		                    Elements links = doc.select("[href*=/Storyold/]");
		                    for (Element link : links) {
		                        fullLinks.add(link.attr("abs:href"));
		                    }
		 
		                    // Result arrayList contains all of the articles on the
		                    // page.
		                    // Now we send the loops to extract all of the data.
		                    // And save them to files.
		 
		                    for (int i = 000; i < fullLinks.size(); i += 1) {
		                        // Error handling for dead links / archives that don't
		                        // have files
		 
		                        try {
		 
		                            // Connect to the links on the page / create a file
		                            // and writer for each
		                            doc = Jsoup.connect(fullLinks.get(i)).get();
		                            // Relocates files to new folder instead of pack
		                            		File file = new File(
		        									"C:\\Newspapers_Workspace\\IE Articles\\"+ year + "\\" + months[monthIndx] + "\\" + day + "_article" + i + ".txt");
		                            FileWriter writer = new FileWriter(file);
		 
		                            // Get title
		                            String title = doc.title();
		                            writer.append(title);
		                            writer.write("\r\n");
		 
		                            // Get Date
		                            Element result = doc.select("story-date").first();
		                            String date = result.text();
//		                            String htmlDate = "<strong><span>Posted:</span>" + "</strong>";
//		                            Element date = doc.select("strong").get(1);
		                            writer.append(result.text() + "\n");
		                            writer.write("\r\n");
		 
		                            // Get body
		                            Elements paragraphs = doc.select("p");
		                            for (Element p : paragraphs) {
		                                writer.write(p.text());
		                            }
		 
		                            writer.close();
		                        } catch (Exception E) {
		 
		                        } finally {
		 
		                        }
		 
		                    }
		                    day++;
		 
		                }
		                monthIndx++;
		                dayIndx++;
		 
		            }
		 
		        }
		        year++;
		    }
		 
		}



