#!/usr/bin/python

from apiclient.discovery import build
from apiclient.errors import HttpError
from oauth2client.tools import argparser


# Set DEVELOPER_KEY to the API key value from the APIs & auth > Registered apps
# tab of
#   https://cloud.google.com/console
# Please ensure that you have enabled the YouTube Data API for your project.
DEVELOPER_KEY = "AIzaSyBQh7wULaC7NJ6mzz6ftwO-QhBxM7cWFD0"
YOUTUBE_API_SERVICE_NAME = "youtube"
YOUTUBE_API_VERSION = "v3"

def youtube_search(options):
  youtube = build(YOUTUBE_API_SERVICE_NAME, YOUTUBE_API_VERSION,
    developerKey=DEVELOPER_KEY)

  # Call the search.list method to retrieve results matching the specified
  # query term.
  search_response = youtube.search().list(
    q="Fire",
    type="video",
    location=options.location,
    locationRadius=options.location_radius,
    part="id,snippet",
    maxResults=options.max_results
  ).execute()

  search_videos = []


  # Merge video ids
  for search_result in search_response.get("items", []):
    search_videos.append(search_result["id"]["videoId"])
  video_ids = ",".join(search_videos)
  outfile = open("Surat_Videos_Fire_ID.txt", "w")
  outfile.write("\n".join(search_videos).encode('utf8'))
  outfile.close()

  # Call the videos.list method to retrieve location details for each video.
  video_response = youtube.videos().list(
    id=video_ids,
    part='snippet, recordingDetails'
  ).execute()

  videos = []


  # Add each result to the list, and then display the list of matching videos.
  for video_result in video_response.get("items", []):
    videos.append("%s, (%s,%s)" % (video_result["snippet"]["title"],
                              video_result["recordingDetails"]["location"]["latitude"],
                              video_result["recordingDetails"]["location"]["longitude"]))
  # Writing the results to a text file
  outfile = open("Surat_Videos_Fire_Titles.txt", "w")
  outfile.write("\n".join(videos).encode('utf8'))
  outfile.close()


  print "Videos:\n", "\n".join(videos), "\n"




if __name__ == "__main__":
  argparser.add_argument("--q", help="Search term", default="Google")
  argparser.add_argument("--location", help="Location", default="21.1702,72.8311")
  argparser.add_argument("--location-radius", help="Location radius", default="150km")
  argparser.add_argument("--max-results", help="Max results", default=50)
  args = argparser.parse_args()

  try:
    youtube_search(args)
  except HttpError, e:
    print "An HTTP error %d occurred:\n%s" % (e.resp.status, e.content)

