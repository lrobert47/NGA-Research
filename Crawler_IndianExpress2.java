
//http://archive.indianexpress.com
//http://archive.indianexpress.com/archive/news/1/1/2000/
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler_IndianExpress2 {

	public static void main(String[] args) throws IOException {

		// Construct arrays with months and days
		int[] months = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
		int[] days = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int year = 2008;

		while (year <= 2017) {
			int monthIndx = 1;
			int dayIndx = 1;

			while (months[monthIndx] < 12) {
				int day = 1;

				while (day <= days[dayIndx]) {
					final ArrayList<String> fullLinks = new ArrayList<String>();

					// Pull HTML from archive page, Create doc from HTMl
					String html = "<html><head></head>" + "<body><p>Parsed HTML into a doc." + "</p></body></html>";
					Document doc = Jsoup.connect("http://archive.indianexpress.com/archive/news/" + day + "/"
							+ months[monthIndx] + "/" + year + "/").get();

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
					Elements links = doc.select("a[href]");

					for (Element link : links) {
						links.wrap("<div class=\"news_head\"><ul><li></ul></li>a[href]</div>");
						// if (links.wrap("<div
						// class=\"news_head\"><ul><li></ul></li>a[href]</div>")
						// !=null)
						fullLinks.add(link.attr("abs:href"));
						// System.out.print(links);
					}

					// Result arrayList contains all of the articles on the
					// page.
					// Now we send the loops to extract all of the data.
					// And save them to files.

					for (int i = 000; i < fullLinks.size(); i += 12) {
						// Error handling for dead links / archives that don't
						// have files

						try {

							// Connect to the links on the page / create a file
							// and writer for each
							doc = Jsoup.connect(fullLinks.get(i)).get();
							File file = new File(
									"C:\\Users\\nball\\Dropbox\\Research\\Crawler_IndianExpress\\Test File\\" + year
											+ "_" + months[monthIndx] + "_" + day + "_article" + i + ".txt");
							FileWriter writer = new FileWriter(file);

							// Get title
							Element t = doc.select("div").first();
							t.wrap("<h1></h1>");
							String title = t.text();
							writer.append(title);
							writer.write("\r\n");

							// Get Date
							Element result = doc.select("div").first();
							result.wrap("<div class=\"story-date\"><span>");
							String date = result.text();
							// String htmlDate = "</a> : " + "<span>";
							// Element date = doc.select("span").get(1);
							writer.append(date + "\n");
							writer.write("\r\n");

							// Get body
							Elements paragraphs = doc.select("p");
							for (Element p : paragraphs) {
								paragraphs.wrap("<p></p>");
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
