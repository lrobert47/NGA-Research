
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

public class Crawler_DailyStar2003To2007 {

	public static void main(String[] args) throws IOException {

		// Construct arrays with months and days
		int[] months = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
		int[] days = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int year = 2007;

		while (year <= 2007) {
			int monthIndx = 7;
			int dayIndx = 7;

			while (months[monthIndx] < 12) {
				int day = 1;

				while (day <= days[dayIndx]) {
					final ArrayList<String> fullLinks = new ArrayList<String>();

					// Pull HTML from archive page, Create doc from HTMl
					if (day < 10 && months[monthIndx] <= 8) {
						String html = "<html><head></head>" + "<body><p>Parsed HTML into a doc." + "</p></body></html>";
						Document doc = Jsoup.connect(
								"http://archive.thedailystar.net/" + year + "/0" + months[monthIndx] + "/0" + day)
								.get();
						Elements links = doc.getElementsByClass("mainheadlink2").select("a[href]");
						// String text =links.first().html();
						// text = text.replaceAll("<br>", "<p>");
						// Elements d = doc.select("a
						// href[class='story__link']").get(0);

						for (Element link : links) {
							// links.wrap("<h5></h5>");
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
								Element t = doc.getElementsByClass("mainheadlink").get(0);
								Elements t1 = doc.select("title");
								// t.wrap("<title></title>");
								// String title = t.text();
								writer.append(t.text() + " | " + t1.text());
								writer.write("\r\n");

								// Get Date
								// Elements result =
								// doc.getElementsByTag("Last-Modified");
								// result.wrap("<meta name=\"Last-Modified\"
								// conetent = />");
								// String htmlDate = "</a> : " + "<span>";
								// Element date = doc.select("meta").get(6);
								Element d = doc.getElementsByClass("updatetime").get(0);
								writer.append(d.text() + "\n");
								writer.write("\r\n");

								// Get body

								Element paragraphs = doc.getElementsByClass("newsdetails").get(0);
								writer.append(paragraphs.text() + "\n");
								writer.write("\r\n");
								// for (Element p : paragraphs) {
								// paragraphs.wrap("<div class='story__content
								// pt-4 '></div>" );
								// writer.write(p.text());
								// }

								writer.close();
							} catch (Exception E) {

							} finally {

							}

						}
						// day++;

					} else if (months[monthIndx] <= 8) {
						String html = "<html><head></head>" + "<body><p>Parsed HTML into a doc." + "</p></body></html>";
						Document doc = Jsoup.connect(
								"http://archive.thedailystar.net/" + year + "/0" + months[monthIndx] + "/" + day).get();
						Elements links = doc.getElementsByClass("mainheadlink2").select("a[href]");
						// String text =links.first().html();
						// text = text.replaceAll("<br>", "<p>");
						// Elements d = doc.select("a
						// href[class='story__link']").get(0);

						for (Element link : links) {
							// links.wrap("<h5></h5>");
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
								Element t = doc.getElementsByClass("mainheadlink").get(0);
								Elements t1 = doc.select("title");
								// t.wrap("<title></title>");
								// String title = t.text();
								writer.append(t.text() + " | " + t1.text());
								writer.write("\r\n");

								// Get Date
								// Elements result =
								// doc.getElementsByTag("Last-Modified");
								// result.wrap("<meta name=\"Last-Modified\"
								// conetent = />");
								// String htmlDate = "</a> : " + "<span>";
								// Element date = doc.select("meta").get(6);
								Element d = doc.getElementsByClass("updatetime").get(0);
								writer.append(d.text() + "\n");
								writer.write("\r\n");

								// Get body

								Element paragraphs = doc.getElementsByClass("newsdetails").get(0);
								writer.append(paragraphs.text() + "\n");
								writer.write("\r\n");
								// for (Element p : paragraphs) {
								// paragraphs.wrap("<div class='story__content
								// pt-4 '></div>" );
								// writer.write(p.text());
								// }

								writer.close();
							} catch (Exception E) {

							} finally {

							}

						}
						// day++;

					}

					else {
						String html = "<html><head></head>" + "<body><p>Parsed HTML into a doc." + "</p></body></html>";
						Document doc = Jsoup
								.connect(
										"http://archive.thedailystar.net/" + year + "/" + months[monthIndx] + "/" + day)
								.get();
						Elements links = doc.getElementsByClass("mainheadlink2").select("a[href]");
						// String text =links.first().html();
						// text = text.replaceAll("<br>", "<p>");
						// Elements d = doc.select("a
						// href[class='story__link']").get(0);

						for (Element link : links) {
							// links.wrap("<h5></h5>");
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
								Element t = doc.getElementsByClass("mainheadlink").get(0);
								Elements t1 = doc.select("title");
								// t.wrap("<title></title>");
								// String title = t.text();
								writer.append(t.text() + " | " + t1.text());
								writer.write("\r\n");

								// Get Date
								// Elements result =
								// doc.getElementsByTag("Last-Modified");
								// result.wrap("<meta name=\"Last-Modified\"
								// conetent = />");
								// String htmlDate = "</a> : " + "<span>";
								// Element date = doc.select("meta").get(6);
								Element d = doc.getElementsByClass("updatetime").get(0);
								writer.append(d.text() + "\n");
								writer.write("\r\n");

								// Get body

								Element paragraphs = doc.getElementsByClass("newsdetails").get(0);
								writer.append(paragraphs.text() + "\n");
								writer.write("\r\n");
								// for (Element p : paragraphs) {
								// paragraphs.wrap("<div class='story__content
								// pt-4 '></div>" );
								// writer.write(p.text());
								// }

								writer.close();
							} catch (Exception E) {

							} finally {

							}

						}
						// day++;

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
