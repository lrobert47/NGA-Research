
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler_Nation {

	public static void main(String[] args) throws IOException {

		// Construct arrays with months and days
		String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
		int[] days = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int year = 2017;

		while (year <= 2017) {
			int monthIndx =4;
			int dayIndx =4;

			while (monthIndx <= (12)) {
				int day = 21;
						

				while (day <= days[dayIndx]) {
					final ArrayList<String> fullLinks = new ArrayList<String>();

					// Pull HTML from archive page, Create doc from HTMl
					String html = "<html><head></head>" + "<body><p>Parsed HTML into a doc." + "</p></body></html>";
					Document doc = Jsoup
							.connect("http://nation.com.pk/archives/" + months[monthIndx] + "-" + day + "-" + year)
							.get();
					// System.out.print("http://nation.com.pk/archives/" +
					// months[monthIndx] + "-"
					// + day + "-" + year);
					// String title = doc.title();
					// String[] twords = title.split(" ");
					//// ArrayList aList= new
					// ArrayList(Arrays.asList(title.split(",")));
					// for( int i = 0; i < twords.length-1; i++ ){
					// title = twords[i] + "-";
					// }
					// System.out.print(title);
					// //input lines of code to add string and split with hyphen
					//

					// Extract all links from the page, add them to arrayList
					Elements links = doc.getElementsByClass("ntitle").select("a[href]");

					for (Element link : links) {
						// links.wrap("<div
						// class=\"news_head\"><ul><li></ul></li></div>");
						// if (links.wrap("<div
						// class=\"news_head\"><ul><li></ul></li>a[href]</div>")
						// !=null)
						fullLinks.add(link.absUrl("href"));
						// System.out.print(links);
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
							File file = new File("C:\\Newspapers_Workspace\\Nation News Pakistan\\" + year + "\\"
									+ months[monthIndx] + "\\" + day + "_article" + i + ".txt");
							FileWriter writer = new FileWriter(file);

							// Get title
							Element t = doc.select("h1").first();
							t.wrap("<h1></h1>");
							String title = t.text();
							writer.append(title);
							writer.write("\r\n");

							// Get Date
							// Elements result =
							// doc.getElementsByTag("Last-Modified");
							// result.wrap("<meta name=\"Last-Modified\"
							// conetent = />");
							// String htmlDate = "</a> : " + "<span>";
							// Element date = doc.select("meta").get(6);
							String d = doc.select("meta[itemprop=datePublished]").get(0).attr("content");
							writer.append(d + "\n");
							writer.write("\r\n");

							// Get body
							Element paragraphs = doc.getElementsByClass("post-content").get(0);
							writer.append(paragraphs.text() + "\n");
							writer.write("\r\n");

							// Collect Key words
							String key = doc.select("meta[name=keywords]").get(0).attr("content");
							writer.append("Keywords: " + key + "\n");
							writer.write("\r\n");

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
