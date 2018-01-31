
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler_DailyStar {
	public static void main(String[] args) throws IOException {

		// Construct arrays with months and days
		int[] months = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
		int[] days = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int year = 2016;

		while (year <= 2017) {
			int monthIndx = 0;
			int dayIndx = 0;

			while (months[monthIndx] <= 12) {
				int day = 1;

				while (day <= days[dayIndx]) {
					final ArrayList<String> fullLinks = new ArrayList<String>();
					// Pull HTML from archive page, Create doc from HTMl
					if (day < 10 && months[monthIndx] <= 8) {
						String html = "<html><head></head>" + "<body><p>Parsed HTML into a doc." + "</p></body></html>";
						Document doc = Jsoup.connect("http://www.thedailystar.net/newspaper?date=" + year + "-0"
								+ months[monthIndx] + "-0" + day).get();
						Elements links = doc.select("h5").select("a[href]");
						// Elements d = doc.select("a
						// href[class='story__link']").get(0);

						for (Element link : links) {
							links.wrap("<h5></h5>");
							// if (links.wrap("<div
							// class=\"news_head\"><ul><li></ul></li>a[href]</div>")
							// !=null)
							fullLinks.add(link.absUrl("href"));
							// System.out.print(links);
						}

						// Result arrayList contains all of the articles on
						// the
						// page.
						// Now we send the loops to extract all of the data.
						// And save them to files.

						for (int i = 000; i < fullLinks.size(); i += 1) {
							// Error handling for dead links / archives that
							// don't
							// have files

							try {

								// Connect to the links on the page / create
								// a file
								// and writer for each
								doc = Jsoup.connect(fullLinks.get(i)).get();
								File file = new File("C:\\Newspapers_Workspace\\Daily Star Articles\\" + year + "\\"
										+ months[monthIndx] + "\\" + day + "_article" + i + ".txt");
								FileWriter writer = new FileWriter(file);

								// Get title
								Element t = doc.select("title").first();
								// t.wrap("<title></title>");
								// String title = t.text();
								writer.append(t.text());
								writer.write("\r\n");

								// Get Date
								// Elements result =
								// doc.getElementsByTag("Last-Modified");
								// result.wrap("<meta name=\"Last-Modified\"
								// conetent = />");
								// String htmlDate = "</a> : " + "<span>";
								// Element date = doc.select("meta").get(6);
								String d = doc.select("meta[property=article:published_time]").get(0).attr("content");
								writer.append(d + "\n");
								writer.write("\r\n");

								// Get body
								Elements paragraphs = doc.select("div.node-content").select("p");
								for (Element p : paragraphs) {
									// paragraphs.wrap("<div
									// class='story__content
									// pt-4 '></div>" );
									writer.write(p.text());
								}

								writer.close();
							} catch (Exception E) {

							} finally {

							}

						}
						// day++;

					} else if (months[monthIndx] <= 8) {
						String html = "<html><head></head>" + "<body><p>Parsed HTML into a doc." + "</p></body></html>";
						Document doc = Jsoup.connect("http://www.thedailystar.net/newspaper?date=" + year + "-0"
								+ months[monthIndx] + "-" + day).get();
						Elements links = doc.select("h5").select("a[href]");
						// Elements d = doc.select("a
						// href[class='story__link']").get(0);

						for (Element link : links) {
							links.wrap("<h5></h5>");
							// if (links.wrap("<div
							// class=\"news_head\"><ul><li></ul></li>a[href]</div>")
							// !=null)
							fullLinks.add(link.absUrl("href"));
							// System.out.print(links);
						}

						// Result arrayList contains all of the articles on
						// the
						// page.
						// Now we send the loops to extract all of the data.
						// And save them to files.

						for (int i = 000; i < fullLinks.size(); i += 1) {
							// Error handling for dead links / archives that
							// don't
							// have files

							try {

								// Connect to the links on the page / create
								// a file
								// and writer for each
								doc = Jsoup.connect(fullLinks.get(i)).get();
								File file = new File("C:\\Newspapers_Workspace\\Daily Star Articles\\" + year + "\\"
										+ months[monthIndx] + "\\" + day + "_article" + i + ".txt");
								FileWriter writer = new FileWriter(file);

								// Get title
								Element t = doc.select("title").first();
								// t.wrap("<title></title>");
								// String title = t.text();
								writer.append(t.text());
								writer.write("\r\n");

								// Get Date
								// Elements result =
								// doc.getElementsByTag("Last-Modified");
								// result.wrap("<meta name=\"Last-Modified\"
								// conetent = />");
								// String htmlDate = "</a> : " + "<span>";
								// Element date = doc.select("meta").get(6);
								String d = doc.select("meta[property=article:published_time]").get(0).attr("content");
								writer.append(d + "\n");
								writer.write("\r\n");

								// Get body
								Elements paragraphs = doc.select("div.node-content").select("p");
								for (Element p : paragraphs) {
									// paragraphs.wrap("<div
									// class='story__content
									// pt-4 '></div>" );
									writer.write(p.text());
								}

								writer.close();
							} catch (Exception E) {

							} finally {

							}

						}
						// day++;

					}

					else {
						String html = "<html><head></head>" + "<body><p>Parsed HTML into a doc." + "</p></body></html>";

						Document doc = Jsoup.connect("http://www.thedailystar.net/newspaper?date=" + year + "-"
								+ months[monthIndx] + "-" + day).get();
						Elements links = doc.select("h5").select("a[href]");
						// Elements d = doc.select("a
						// href[class='story__link']").get(0);

						for (Element link : links) {
							links.wrap("<h5></h5>");
							// if (links.wrap("<div
							// class=\"news_head\"><ul><li></ul></li>a[href]</div>")
							// !=null)
							fullLinks.add(link.absUrl("href"));
							// System.out.print(links);
						}

						// Result arrayList contains all of the articles on
						// the
						// page.
						// Now we send the loops to extract all of the data.
						// And save them to files.

						for (int i = 000; i < fullLinks.size(); i += 1) {
							// Error handling for dead links / archives that
							// don't
							// have files

							try {

								// Connect to the links on the page / create
								// a file
								// and writer for each
								doc = Jsoup.connect(fullLinks.get(i)).get();
								File file = new File("C:\\Newspapers_Workspace\\Daily Star Articles\\" + year + "\\"
										+ months[monthIndx] + "\\" + day + "_article" + i + ".txt");
								FileWriter writer = new FileWriter(file);

								// Get title
								Element t = doc.select("title").first();
								// t.wrap("<title></title>");
								// String title = t.text();
								writer.append(t.text());
								writer.write("\r\n");

								// Get Date
								// Elements result =
								// doc.getElementsByTag("Last-Modified");
								// result.wrap("<meta name=\"Last-Modified\"
								// conetent = />");
								// String htmlDate = "</a> : " + "<span>";
								// Element date = doc.select("meta").get(6);
								String d = doc.select("meta[property=article:published_time]").get(0).attr("content");
								writer.append(d + "\n");
								writer.write("\r\n");

								// Get body
								Elements paragraphs = doc.select("div.node-content").select("p");
								for (Element p : paragraphs) {
									// paragraphs.wrap("<div
									// class='story__content
									// pt-4 '></div>" );
									writer.write(p.text());
								}

								writer.close();
							} catch (Exception E) {

							} finally {

							}

						}

					}
					day++;

				}
				dayIndx++;
				monthIndx++;

			}
			year++;
		}

	}
}
